/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Inventaire;

import Model.Inventaire.Animal;
import Model.Inventaire.Batiment;
import Model.Inventaire.Materiel;
import Model.Inventaire.Produit;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abir
 */
public class BatimentsController implements Initializable {

    @FXML
    private Label idTf;
    @FXML
    private TextField nomTf;
    @FXML
    private ComboBox<String> typeCombobox;
    @FXML
    private Button resetButton;
    @FXML
    private Button saveButton;
    @FXML
    private TableView<Batiment> batimentsTableView;
    @FXML
    private TableColumn<Batiment, String> idColonne;
    @FXML
    private TableColumn<Batiment, String> nomColonne;
    @FXML
    private TableColumn<Batiment, String> typeColonne;

    private ObservableList<Batiment> batimentsList;
  
    private static int currentId = 1;
    @FXML
    private MenuItem suppbat;
    @FXML
    private Label errorlabel;
    @FXML
    private MenuItem mofidbatlist;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Initialize the ComboBox with the types
        ObservableList<String> types = FXCollections.observableArrayList("Animal", "Produit", "Materiel");
        typeCombobox.setItems(types);
        
        // Initialize the TableView
        idColonne.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColonne.setCellValueFactory(new PropertyValueFactory<>("nom"));       
        typeColonne.setCellValueFactory(new PropertyValueFactory<>("type"));
        // Create an empty list for batiments
        batimentsList = FXCollections.observableArrayList();
        batimentsTableView.setItems(batimentsList);
        
        batimentsTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        idTf.setText(newValue.getId());
                        nomTf.setText(newValue.getNom());
                        typeCombobox.setValue(newValue.getType()); 
                    }
                });              
    }    

    @FXML
    private void handleSaveButton(ActionEvent event) {
        String nom = nomTf.getText();
        String type = typeCombobox.getValue();

        // Valider la saisie
        if(validationFields(nom,type) == false) return;
        Batiment selectedBatiment = batimentsTableView.getSelectionModel().getSelectedItem();

        if (selectedBatiment != null && batimentsTableView.getItems().contains(selectedBatiment)) {
            // mise a jour de l'objet batiment selectionné
            selectedBatiment.setNom(nom);
            selectedBatiment.setType(type);
            updateAlert(selectedBatiment);
            batimentsTableView.refresh();
            batimentsTableView.getSelectionModel().clearSelection();
      } else {
        // Créer un nouveau Batiment objet avec l'id generé
        Batiment batiment = new Batiment("bat"+String.valueOf(currentId), nom, type);
        saveAlert(batiment);
        batimentsTableView.getItems().add(batiment);
        currentId++;
        }   
        // vider tout les champs
        clearFields();
    }
     
    @FXML
    private void handleResetButton(ActionEvent event) {
        clearFields();
    }
       
    private boolean validationFields(String nom, String type){
        if (nom.isEmpty()){ errorlabel.setText("il faut remplir le champ nom"); return false;}
        if(type ==null) {errorlabel.setText("il faut selectionner un type"); return false;}
        return true;
    }
    
    private void saveAlert(Batiment batiment){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Batiment crée avec succès");
        alert.setHeaderText(null);
        alert.setContentText("le batiment '"+batiment.getNom()+"' a été créé et son identidiant est: "+batiment.getId());
        alert.showAndWait();
    }
    private void updateAlert(Batiment batiment){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Batiment mis à jour avec succès");
        alert.setHeaderText(null);
        alert.setContentText("le batiment '"+batiment.getNom()+"' a été mis à jour avec succès ");
        alert.showAndWait();
    }
    

    @FXML
    private void MouseClicked(javafx.scene.input.MouseEvent event) {
       if (batimentsTableView.getSelectionModel().getSelectedItem() != null) {
        // If a row is selected, clear the selection and clear the fields
        batimentsTableView.getSelectionModel().clearSelection();
        clearFields();
       }        
    }
    
    private void clearFields(){
        idTf.setText("");  
        nomTf.clear();
        typeCombobox.getSelectionModel().clearSelection();
        errorlabel.setText(""); 
    }

    @FXML
    private void suppBatHandler(ActionEvent event) {
        Batiment selectedBatiment = batimentsTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("etes vous sure de supprimer cet element");
        Optional<ButtonType> action =alert.showAndWait();
        if(action.get() == ButtonType.OK)  
            batimentsTableView.getItems().remove(selectedBatiment);
        clearFields();
    }
    

    @FXML
    private void modifBatListHandler(ActionEvent event) throws IOException{
        try {
            Stage currentStage = (Stage) batimentsTableView.getScene().getWindow();

            // Load the new interface
            String fxmlPath = getFxmlPathForSelectedType();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
          
            if(fxmlPath.equals("/View/Inventaire/BatimentAnimal.fxml"))
            {  
                BatimentAnimalController animalController = loader.getController();
                animalController.setBatiment(batimentsTableView.getSelectionModel().getSelectedItem());
                System.out.println(batimentsTableView.getSelectionModel().getSelectedItem());
            }
            else if(fxmlPath.equals("/View/Inventaire/BatimentProduit.fxml")){
                BatimentProduitController produitController = loader.getController();
                produitController.setBatiment(batimentsTableView.getSelectionModel().getSelectedItem());
                System.out.println(batimentsTableView.getSelectionModel().getSelectedItem());
            }
            else{
                BatimentMaterielController materielController = loader.getController();
                materielController.setBatiment(batimentsTableView.getSelectionModel().getSelectedItem());
                System.out.println(batimentsTableView.getSelectionModel().getSelectedItem());
            }
            
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setOnCloseRequest(event1 -> { currentStage.show();});
            newStage.show();
                currentStage.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    
    private String getFxmlPathForSelectedType() {
        String type = batimentsTableView.getSelectionModel().getSelectedItem().getType();
        switch (type) {
            case "Animal":
                return "/View/Inventaire/BatimentAnimal.fxml";
            case "Produit":
                return "/View/Inventaire/BatimentProduit.fxml";
            case "Materiel":
                return "/View/Inventaire/BatimentMateriel.fxml";
            default:
                return "";
        }
    }
    
    @Override 
    public String toString(){
        String str="[ ";
        for(Batiment bat: batimentsList) str+= bat+"\n";
        return str+"]";
    }

    
}

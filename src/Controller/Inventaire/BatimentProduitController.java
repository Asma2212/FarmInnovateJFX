/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Inventaire;

import Controller.Inventaire.BatimentsController;
import Model.Inventaire.Animal;
import Model.Inventaire.Batiment;
import Model.Inventaire.Engrais;
import Model.Inventaire.Pesticide;
import Model.Inventaire.Produit;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abir
 */
public class BatimentProduitController implements Initializable {

    @FXML
    private Button ajoutPestBtn;
    @FXML
    private ImageView returnbtn;
    @FXML
    private Button ajoutEngBtn;
    @FXML
    private TableColumn<Produit, String> idColonne;
    @FXML
    private TableColumn<Produit, String> formulationColonne;
    @FXML
    private TableColumn<Produit, String> periodeAppColonne;
    @FXML
    private TableColumn<Produit, Double> doseRecmColonne;
    @FXML
    private TableColumn<Produit, Double> qtNetColonne;
    @FXML
    private TableColumn<Produit, Integer> nbrProdColonne;
    @FXML
    private TableColumn<Produit, String> typeColonne;
    @FXML
    private TableColumn<Engrais, String> compositionColonne;
    @FXML
    private TableColumn<Pesticide, Integer> DARColonne;
    @FXML
    private TableView<Produit> produitTableView;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button supprimerbtn;
    
    private ObservableList<Produit> produitList;
           
    private  Batiment batiment;
    

     public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
        ArrayList<Produit> produit = new ArrayList<>();
            for(Object prod :batiment.getList())
                produit.add((Produit) prod);
        produitList = FXCollections.observableArrayList(produit);
        produitTableView.setItems(produitList);
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        modifierbtn.setVisible(false);
        supprimerbtn.setVisible(false);
        
        idColonne.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        formulationColonne.setCellValueFactory(new PropertyValueFactory<>("formulation"));
        periodeAppColonne.setCellValueFactory(new PropertyValueFactory<>("periodeApp"));
        doseRecmColonne.setCellValueFactory(new PropertyValueFactory<>("doseRec"));
        qtNetColonne.setCellValueFactory(new PropertyValueFactory<>("qtNet"));
        nbrProdColonne.setCellValueFactory(new PropertyValueFactory<>("nbrProd"));
        typeColonne.setCellValueFactory(data -> {
        Produit produit = data.getValue();
        if (produit instanceof Pesticide pesticide) {
            return new SimpleStringProperty(pesticide.getType().toString());
        } else if (produit instanceof Engrais engrais) {
            return new SimpleStringProperty(engrais.getType().toString());
        } else {
            return null;
        }
        });
        compositionColonne.setCellValueFactory(data -> {
        Produit produit = data.getValue();
        if (produit instanceof Engrais engrais) {
            return new SimpleStringProperty(engrais.getComposition());
        } else {
            return new SimpleStringProperty("null");
        }
        });
        //compositionColonne.setCellValueFactory(new PropertyValueFactory<>("composition"));
        DARColonne.setCellValueFactory(new PropertyValueFactory<>("DAR"));
        
        produitTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                      modifierbtn.setVisible(true);
                      supprimerbtn.setVisible(true);        
                    }
                }); 

    }  
    private void hideBtn(){
        modifierbtn.setVisible(false);
        supprimerbtn.setVisible(false);
    }

    @FXML
    private void ajoutPestBtnHandler(ActionEvent event) throws IOException {
         Stage currentStage = (Stage) produitTableView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Inventaire/PesticideSaisie.fxml"));
        Parent root = loader.load();
        PesticideSaisieController pesticideSaisieController = loader.getController();
        pesticideSaisieController.setBatiment(batiment,produitTableView,(Pesticide) produitTableView.getSelectionModel().getSelectedItem());
        
        // Create a new stage for the PesticideSaisie.fxml
        Stage newStage = new Stage();
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(currentStage);
        newStage.setTitle("Pesticide Saisie");
        newStage.setScene(new Scene(root));

        newStage.show();
         
    }

    @FXML
    private void ajoutEngBtnHandler(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) produitTableView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Inventaire/EngraisSaisie.fxml"));
        Parent root = loader.load();
        EngraisSaisieController engraisSaisieController = loader.getController();
        engraisSaisieController.setBatiment(batiment,produitTableView,(Engrais) produitTableView.getSelectionModel().getSelectedItem());
        Stage newStage = new Stage();
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(currentStage);
        newStage.setTitle("Engrais Saisie");
        newStage.setScene(new Scene(root));
        newStage.show();
    }
    
    @FXML
    private void returnBtnHandler(MouseEvent event) {
        returnbtn.getScene().getWindow().hide();
    }

    @FXML
    private void modifierBtnHandler(ActionEvent event)throws IOException {
        Stage currentStage = (Stage) produitTableView.getScene().getWindow();
        String str;
        if(produitTableView.getSelectionModel().getSelectedItem().getClass()== Pesticide.class)
            str="/View/Inventaire/PesticideSaisie.fxml";
        else
            str="/View/Inventaire/EngraisSaisie.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
        Parent root = loader.load();
        if(str.equals("/View/Inventaire/PesticideSaisie.fxml")){
            PesticideSaisieController pesticideSaisieController = loader.getController();
            pesticideSaisieController.setBatiment(batiment,produitTableView, (Pesticide) produitTableView.getSelectionModel().getSelectedItem());}
        else{
            EngraisSaisieController engraiseSaisieController = loader.getController();
            engraiseSaisieController.setBatiment(batiment,produitTableView,(Engrais) produitTableView.getSelectionModel().getSelectedItem());
        }
        produitTableView.refresh();
        produitTableView.getSelectionModel().clearSelection();
        Stage newStage = new Stage();
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(currentStage);
        newStage.setTitle("Engrais Saisie");
        newStage.setScene(new Scene(root));
        newStage.show();
        hideBtn();
        
    }

    @FXML
    private void supprimerBtnHandler(ActionEvent event) {
        Produit selectedProduit = produitTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("etes vous sure de supprimer cet element");
        Optional<ButtonType> action =alert.showAndWait();
        if(action.get() == ButtonType.OK){  
            produitTableView.getItems().remove(selectedProduit);
            batiment.getList().remove(selectedProduit);
        }
        hideBtn();
    }
    
    

    @FXML
    private void MouseClicked(MouseEvent event) {
        if (produitTableView.getSelectionModel().getSelectedItem() != null) {
        // If a row is selected, clear the selection and clear the fields
        produitTableView.getSelectionModel().clearSelection();
        hideBtn();
       }   
    }
    
}

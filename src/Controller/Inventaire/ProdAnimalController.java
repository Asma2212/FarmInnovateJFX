/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Inventaire;

import Model.Date;
import Model.Espace.NatureProdAnimal;
import Model.Espace.ProdAnimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller classs
 *
 * @author abir
 */
public class ProdAnimalController implements Initializable {

    @FXML
    private Label idTf;
    @FXML
    private ComboBox<NatureProdAnimal> natureTf;
    @FXML
    private TextField qtTf;   
    @FXML
    private TextField prixTf;
    @FXML
    private Button resetButton;
    @FXML
    private Button saveButton;
    @FXML
    private DatePicker dateTf;
    @FXML
    private TableView<ProdAnimal> prodAnimalTableView;
    @FXML
    private MenuItem suppBtn;
    @FXML
    private TableColumn<ProdAnimal, String> idColonne;
    @FXML
    private TableColumn<ProdAnimal, Integer> QtStockColonne;
    @FXML
    private TableColumn<ProdAnimal, Double> prixColonne;
    @FXML
    private TableColumn<ProdAnimal, NatureProdAnimal> natureColonne;
    @FXML
    private TableColumn<ProdAnimal, Date> dateProdColonne;
    
    private ObservableList<ProdAnimal> prodAnimalList;
    
    private static int currentId = 1;
    @FXML
    private Text errorText;
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<NatureProdAnimal> productins = FXCollections.observableArrayList();
         for(NatureProdAnimal prod : NatureProdAnimal.values()) {
                productins.add(prod);
            }
         natureTf.setItems(productins); 
        prodAnimalList = FXCollections.observableArrayList();
        prodAnimalTableView.setItems(prodAnimalList);
         
        idColonne.setCellValueFactory(new PropertyValueFactory<>("refP"));
        QtStockColonne.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
        prixColonne.setCellValueFactory(new PropertyValueFactory<>("prix"));
        natureColonne.setCellValueFactory(new PropertyValueFactory<>("nature"));
        dateProdColonne.setCellValueFactory(new PropertyValueFactory<>("dateProd"));
        
        prodAnimalTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        idTf.setText(newValue.getRefP());
                        natureTf.setValue(newValue.getNature()); 
                        dateTf.setValue(newValue.getDateProd().toLocalDate());
                        qtTf.setText(String.valueOf(newValue.getQteStock()));
                        prixTf.setText(String.valueOf(newValue.getPrix()));
                    }
                }); 
        
        
    }    
    @FXML
    private void saveBtnHandler(ActionEvent event) {
        try{
        String id = idTf.getText();
        NatureProdAnimal nature = natureTf.getValue();
        Integer qt = Integer.parseInt(qtTf.getText());
        Double prix = Double.parseDouble(prixTf.getText());
        LocalDate myDate = dateTf.getValue();
            if (myDate == null) {
                errorText.setText("Il faut remplir le champ date de production");
                return;
            }
            int year = myDate.getYear();
            int month = myDate.getMonthValue();
            int day = myDate.getDayOfMonth();
            Date date = new Date(day, month, year);

        // Validate the input
        if (!validationFields(nature, qt, prix))return;

        ProdAnimal selectedProdAnimal = prodAnimalTableView.getSelectionModel().getSelectedItem();

        if (selectedProdAnimal != null && prodAnimalTableView.getItems().contains(selectedProdAnimal)) {
            // Update the selected ProdAnimal
            selectedProdAnimal.setRefP(id);
            selectedProdAnimal.setNature(nature);
            selectedProdAnimal.setQteStock(qt);
            selectedProdAnimal.setPrix(prix);           
            selectedProdAnimal.setDateProd(date);

            updateAlert(selectedProdAnimal);
            prodAnimalTableView.refresh();
            prodAnimalTableView.getSelectionModel().clearSelection();
        } else {
            // Create a new ProdAnimal object with the generated id
            ProdAnimal prodAnimal = new ProdAnimal(nature,date,"prodAnimal" + String.valueOf(currentId),qt,prix);
            saveAlert(prodAnimal);
            prodAnimalTableView.getItems().add(prodAnimal);
            currentId++;
        }

        // Clear all fields
        clearFields();}
         catch (NumberFormatException e) {
            errorText.setText("Veuillez entrer des valeurs numériques valides.");
        }
    }

    private boolean validationFields(NatureProdAnimal nature, Integer qt, Double prix) {
        if (nature == null || qt == null || prix == null) {
            errorText.setText("Veuillez remplir tous les champs.");
            return false;
        }
        return true;
    }
    
    @FXML
    private void resetBtnHandler(ActionEvent event) {
        clearFields();
    }
    private void saveAlert(ProdAnimal prodAnimal){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Production crée avec succès");
        alert.setHeaderText(null);
        alert.setContentText(prodAnimal.getNature()+"' a été créé et son identidiant est: "+prodAnimal.getRefP());
        alert.showAndWait();
    }
    private void updateAlert(ProdAnimal prodAnimal){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Batiment mis à jour avec succès");
        alert.setHeaderText(null);
        alert.setContentText(prodAnimal.getNature()+"' a été mis à jour avec succès ");
        alert.showAndWait();
    }
    
    private void clearFields() {
        idTf.setText("");
        //natureTf.setValue(null);
        qtTf.clear();
        prixTf.clear();
        dateTf.setValue(null);

        errorText.setText(""); // Clear any error messages
    }

    @FXML
    private void MouseClicked(MouseEvent event) {
        if (prodAnimalTableView.getSelectionModel().getSelectedItem() != null) {
        prodAnimalTableView.getSelectionModel().clearSelection();
        clearFields();
       }  
    }
    @FXML
    private void suppBtnHandler(ActionEvent event) {
        System.out.println("asma  helloooo");
        ProdAnimal selectedProdAnimal = prodAnimalTableView.getSelectionModel().getSelectedItem();
                System.out.println(selectedProdAnimal);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("etes vous sure de supprimer cet element");
        Optional<ButtonType> action =alert.showAndWait();
        if(action.get() == ButtonType.OK){  
            prodAnimalTableView.getItems().remove(selectedProdAnimal);
        }
        clearFields();
    }
    
    
}

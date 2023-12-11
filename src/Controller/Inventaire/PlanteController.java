/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Inventaire;

import Model.Inventaire.Plante;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author abir
 */
public class PlanteController implements Initializable {

    @FXML
    private Label idTf;
    @FXML
    private TextField cultureTf;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private TextField saisonTf;
    @FXML
    private TextField qtTf;
    @FXML
    private Button resetButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label errorlabel;
    @FXML
    private TableColumn<Plante, String> idColonne;
    @FXML
    private TableColumn<Plante, String> cultureColonne;
    @FXML
    private TableColumn<Plante, String> natureColonne;
    @FXML
    private TableColumn<Plante, String> saisonColonne;
    @FXML
    private TableColumn<Plante, Double> qtColonne;
    @FXML
    private TableView<Plante> plantesTableView;
    
    private static int currentId = 1;
    @FXML
    private MenuItem suppBtn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<String> natures = FXCollections.observableArrayList("annuelle", "pérenne");
       combobox.setItems(natures);  
       idColonne.setCellValueFactory(new PropertyValueFactory<>("idPlante"));
       cultureColonne.setCellValueFactory(new PropertyValueFactory<>("culture"));       
       natureColonne.setCellValueFactory(new PropertyValueFactory<>("nature"));
       saisonColonne.setCellValueFactory(new PropertyValueFactory<>("saisonRecolte"));       
       qtColonne.setCellValueFactory(new PropertyValueFactory<>("qtEauJour")); 
       
       plantesTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        idTf.setText(newValue.getIdPlante());
                        cultureTf.setText(newValue.getCulture());
                        combobox.setValue(newValue.getNature()); 
                        saisonTf.setText(newValue.getSaisonRecolte());
                        qtTf.setText(String.valueOf(newValue.getQtEauJour()));
                    }
                });
    }    
    @FXML
    private void saveBtnHandler(ActionEvent event) {
        try {
            String culture = cultureTf.getText();
            String nature = combobox.getValue();
            String saison = saisonTf.getText();
            Double qtEauJour = Double.parseDouble(qtTf.getText());

            if (!validateFields(culture, nature, saison, qtEauJour))return;

            Plante selectedPlante = plantesTableView.getSelectionModel().getSelectedItem();

            if (selectedPlante != null && plantesTableView.getItems().contains(selectedPlante)) {
                selectedPlante.setCulture(culture);
                selectedPlante.setNature(nature);
                selectedPlante.setSaisonRecolte(saison);
                selectedPlante.setQtEauJour(qtEauJour);
                updateAlert(selectedPlante);
                plantesTableView.refresh(); 
            } else {
                Plante plante = new Plante("plante" + String.valueOf(currentId), culture, nature, saison, qtEauJour);
                saveAlert(plante);
                plantesTableView.getItems().add(plante);
                System.out.println(plante);
                currentId++;
            }

            clearFields();
        } catch (NumberFormatException e) {
            errorlabel.setText("Veuillez entrer des valeurs numériques valides.");
        }
    }

    private boolean validateFields(String culture, String nature, String saison, Double qtEauJour) {
        if (culture.isEmpty() || nature.isEmpty() || saison.isEmpty() || qtEauJour == null) {
            errorlabel.setText("Veuillez remplir tous les champs.");
            return false;
        }
        return true;
    }
    
    @FXML
    private void resetBtnHandler(ActionEvent event) {
        clearFields();
    }
    
    @FXML
    private void MouseClicked(MouseEvent event) {
        if (plantesTableView.getSelectionModel().getSelectedItem() != null) {
            plantesTableView.getSelectionModel().clearSelection();
            clearFields();
       }  
    }   
    
    private void saveAlert(Plante plante){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Batiment crée avec succès");
        alert.setHeaderText(null);
        alert.setContentText("la plante '"+plante.getCulture()+"' a été créé et son identidiant est: "+plante.getIdPlante());
        alert.showAndWait();
    }
    private void updateAlert(Plante plante){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Plante mis à jour avec succès");
        alert.setHeaderText(null);
        alert.setContentText("la plante '"+plante.getCulture()+"' a été mis à jour avec succès ");
        alert.showAndWait();
    }
    private void clearFields() {
        idTf.setText("");
        cultureTf.clear();
        combobox.getSelectionModel().clearSelection();
        saisonTf.clear();
        qtTf.clear();
        errorlabel.setText("");
    }

    @FXML
    private void suppBtnHandler(ActionEvent event) {
        Plante selectedPlante = plantesTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sure de supprimer cet element");
        Optional<ButtonType> action =alert.showAndWait();
        if(action.get() == ButtonType.OK)  
            plantesTableView.getItems().remove(selectedPlante);
        clearFields();
    }
   
}

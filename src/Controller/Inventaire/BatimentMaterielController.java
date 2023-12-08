/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Inventaire;

import Model.Date;
import Model.Inventaire.Animal;
import Model.Inventaire.Batiment;
import Model.Inventaire.Espece;
import Model.Inventaire.Materiel;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author abir
 */
public class BatimentMaterielController implements Initializable {

    @FXML
    private ImageView returnbtn;
    @FXML
    private Label idLabel;
    @FXML
    private TextField libelleTf;
    @FXML
    private DatePicker dateAchatTf;
    @FXML
    private TextField nbrMatTf;
    @FXML
    private Button reinitialiserBtn;
    @FXML
    private Button enregistrerBtn;
    @FXML
    private Text errorText;
    @FXML
    private TableView<Materiel> materielTableView;
    @FXML
    private TableColumn<Materiel, String> idColonne;
    @FXML
    private TableColumn<Materiel, String> libelleColonne;
    @FXML
    private TableColumn<Materiel, Date> dateNaissColonne;
    @FXML
    private TableColumn<Materiel, Integer> nbrMatColonne;
    
    private ObservableList<Materiel> materielList;
    
    private static int currentId = 1;
     
    private  Batiment batiment;
    @FXML
    private MenuItem suppbtn;
    
    

     /**
     * This method accepts a batiment
     * @param batiment
     */
     public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
        ArrayList<Materiel> materiel = new ArrayList<>();
            for(Object ani :batiment.getList())
                materiel.add((Materiel) ani);
        materielList = FXCollections.observableArrayList(materiel);
        materielTableView.setItems(materielList);
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColonne.setCellValueFactory(new PropertyValueFactory<>("numM"));
        libelleColonne.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        dateNaissColonne.setCellValueFactory(new PropertyValueFactory<>("dateAchat"));   
        nbrMatColonne.setCellValueFactory(new PropertyValueFactory<>("nbrMat"));
        
         materielTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        idLabel.setText(newValue.getNumM());
                        libelleTf.setText(newValue.getLibelle());
                        dateAchatTf.setValue(newValue.getDateAchat().toLocalDate());
                        nbrMatTf.setText(String.valueOf(newValue.getNbrMat()));                        
                    }
                });
    }    

    @FXML
    private void enregistrerBtnHandler(ActionEvent event) {
       try {
        String libelle = libelleTf.getText();
        LocalDate myDate = dateAchatTf.getValue();
        if (myDate == null) {
            errorText.setText("Il faut remplir le champ date d'achat");
            return;
        }
        int year = myDate.getYear();
        int month = myDate.getMonthValue();
        int day = myDate.getDayOfMonth();
        Date date = new Date(day, month, year);
        int nbrMat = Integer.parseInt(nbrMatTf.getText());
        //valider la saisie
        if (!validationFields(libelle, nbrMat)) return;
        Materiel selectedMateriel = materielTableView.getSelectionModel().getSelectedItem();
        if (selectedMateriel != null && materielTableView.getItems().contains(selectedMateriel)) {
                // mise a jour de l'objet animal selectionné
                selectedMateriel.setLibelle(libelle);
                selectedMateriel.setDateAchat(date);
                selectedMateriel.setNbrMat(nbrMat);
                updateAlert(selectedMateriel);
                materielTableView.refresh();
                materielTableView.getSelectionModel().clearSelection();
            } else {
                // Créer un nouveau Animal objet avec l'id generé
                Materiel materiel = new Materiel("materiel" + String.valueOf(currentId), libelle, date,nbrMat);
                saveAlert(materiel);
                materielTableView.getItems().add(materiel);
                batiment.getList().add(materiel);
                currentId++;
            }
            
            clearFields();
       } catch (NumberFormatException e) {
            errorAlert();
        }   
    }
    
    private boolean validationFields(String libelle, Integer nbrMat){
        if(libelle.isEmpty()) {errorText.setText("Il faut remplir le champ libelle!!!"); return false;}
        if (nbrMat == null){ errorText.setText("Il faut remplir le champ nombre materiel!!!"); return false;}
        return true;
    }
    
    @FXML
    private void reinitialiserBtnHandler(ActionEvent event) {
        clearFields();
    }
    
    private void saveAlert(Materiel materiel){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Materiel créé avec succès");
        alert.setHeaderText(null);
        alert.setContentText("l'id de cet materiel"+materiel.getLibelle()+"est : "+materiel.getNumM());
        alert.showAndWait();
    }
    private void updateAlert(Materiel materiel){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Materiel mis à jour avec succès");
        alert.setHeaderText(null);
        alert.setContentText("le materiel  '"+materiel.getNumM()+"' a été mis à jour avec succès ");
        alert.showAndWait();
    }
    private void errorAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Le champ nombre materiel doit être un nombre valide");
        alert.showAndWait();
    }
    
    private void clearFields(){
        idLabel.setText("");  
        libelleTf.clear();
        dateAchatTf.setValue(null);
        nbrMatTf.clear(); 
    }
    
     @FXML
    private void MouseClicked(MouseEvent event) {
        if (materielTableView.getSelectionModel().getSelectedItem() != null) {
        materielTableView.getSelectionModel().clearSelection();
        clearFields();
       }  
    }
    
    @FXML
    private void suppBtnHandler(ActionEvent event) {
         Materiel selectedMateriel = materielTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sure de supprimer cet element");
        Optional<ButtonType> action =alert.showAndWait();
        if(action.get() == ButtonType.OK){  
            materielTableView.getItems().remove(selectedMateriel);
            batiment.getList().remove(selectedMateriel);
        }
        clearFields();
    }
    
    
    @FXML
    private void returnBtnHandler(MouseEvent event) {
        returnbtn.getScene().getWindow().hide();
    }
  
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Inventaire;

import Model.Inventaire.Animal;
import Model.Inventaire.Batiment;
import Model.Inventaire.Espece;
import Model.Inventaire.Pesticide;
import Model.Inventaire.Produit;
import Model.Inventaire.TypePesticide;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abir
 */
public class PesticideSaisieController implements Initializable {

    @FXML
    private TextField formTf;
    @FXML
    private TextField periodeTf;
    @FXML
    private TextField doseTf;
    @FXML
    private TextField qtTf;
    @FXML
    private TextField nbrprodTf;

    @FXML
    private TextField darTf;
    @FXML
    private Label idLabel; 
    @FXML
    private ComboBox<TypePesticide> combobox;
    @FXML
    private Button reinitialiserbtn;
    @FXML
    private Button enregistrerbtn;
     @FXML
    private Text errorText;

    private  Batiment batiment;
    
    private TableView<Produit> produitTableView;
    
    private Pesticide pesticide;
    
    private static int currentId = 1;
   
    
    

    public void setBatiment(Batiment batiment, TableView<Produit> produitTableView,Pesticide pesticide) {
        this.batiment = batiment;
        this.produitTableView=produitTableView;
        
        if(!batiment.getList().isEmpty()){
            idLabel.setText(pesticide.getIdProduit());
            formTf.setText(pesticide.getFormulation());
            periodeTf.setText(pesticide.getPeriodeApp());
            doseTf.setText(String.valueOf(pesticide.getDoseRec()));
            combobox.setValue(pesticide.getType());
            qtTf.setText(String.valueOf(pesticide.getQtNet()));
            nbrprodTf.setText(String.valueOf(pesticide.getNbrProd()));
            darTf.setText(String.valueOf(pesticide.getDAR()));
            this.pesticide=pesticide;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<TypePesticide> types = FXCollections.observableArrayList();
         for(TypePesticide typ : TypePesticide.values()) {
                types.add(typ);
            }
         combobox.setItems(types);
    }
    
    @FXML
    private void enregistrerBtnHandler(ActionEvent event){
        try{
        String formulation = formTf.getText();
        String periodeApp = periodeTf.getText();
        Double dose = Double.parseDouble(doseTf.getText());
        Double qtNet = Double.parseDouble(qtTf.getText());
        Integer nbrProd = Integer.parseInt(nbrprodTf.getText());
        TypePesticide typePest = combobox.getValue();
        Integer dar = Integer.parseInt(darTf.getText());

        if (!validationFields(formulation, typePest, periodeApp, dose, qtNet, nbrProd, dar)) return;
        System.out.println("pesticide: "+pesticide);
        if (pesticide != null && produitTableView.getItems().contains(pesticide)) {
            pesticide.setFormulation(formulation);
            pesticide.setPeriodeApp(periodeApp);
            pesticide.setDoseRec(dose);
            pesticide.setQtNet(qtNet);
            pesticide.setNbrProd(nbrProd);
            pesticide.setType(typePest);
            pesticide.setDAR(dar);
            updateAlert(pesticide);
            produitTableView.refresh();
            produitTableView.getSelectionModel().clearSelection();
        } else {
            Pesticide newPesticide = new Pesticide("pesticide" + String.valueOf(currentId), formulation, periodeApp,
                    dose, qtNet, nbrProd, typePest, dar);
            saveAlert(newPesticide);
            produitTableView.getItems().add(newPesticide);
            batiment.getList().add(newPesticide);
            currentId++;
            clearFields();
        }

        Stage stage = (Stage) enregistrerbtn.getScene().getWindow();
        stage.close();
        } catch (NumberFormatException e) {
            errorText.setText("Veuillez entrer des valeurs numériques valides.");
        }
    }

    private boolean validationFields(String formulation, TypePesticide type, String periodeApp, Double dose, Double qtNet,Integer nbrProd, Integer dar){
       if (formulation.isEmpty() || type == null || periodeApp.isEmpty() || dose == null || qtNet == null || nbrProd == null || dar == null) {
        errorText.setText("Veuillez remplir tous les champs.");
        return false;
    }
    return true;
    }
    @FXML
    private void reinitialiserBtnHandler(ActionEvent event) {
        clearFields();
    }  
    private void saveAlert(Pesticide pesticide){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pesticide crée avec succès");
        alert.setHeaderText(null);
        alert.setContentText("l'id de ce pesticide est '"+pesticide.getIdProduit()+"'");
        alert.showAndWait();
    }
    private void updateAlert(Pesticide pesticide){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Animal mis à jour avec succès");
        alert.setHeaderText(null);
        alert.setContentText("le pesticide '"+pesticide.getIdProduit()+"' a été mis à jour avec succès ");
        alert.showAndWait();
    }
    
    private void clearFields(){
        //idLabel.setText("");  
        combobox.getSelectionModel().clearSelection();
        formTf.clear();
        periodeTf.clear();
        doseTf.clear();
        qtTf.clear();
        nbrprodTf.clear();
        darTf.clear();
    }
}

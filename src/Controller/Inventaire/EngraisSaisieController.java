/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Inventaire;

import Model.Inventaire.Batiment;
import Model.Inventaire.Engrais;
import Model.Inventaire.Produit;
import Model.Inventaire.TypeEngrais;
import Model.Inventaire.TypePesticide;
import java.net.URL;
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
public class EngraisSaisieController implements Initializable {

    @FXML
    private Label idLabel;
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
    private ComboBox<TypeEngrais> combobox;
    @FXML
    private TextField compTf;
    @FXML
    private Button reinitialiserbtn;
    @FXML
    private Button enregistrerbtn;
    @FXML
    private Text errorText;
    
    
    private  Batiment batiment;
    
    private TableView<Produit> produitTableView;
    
    private static int currentId = 1;
    
    private Engrais engrais;
    
   
    
    

    public void setBatiment(Batiment batiment, TableView<Produit> produitTableView,Engrais engrais) {
        this.batiment = batiment;
        this.produitTableView=produitTableView;
        if(!batiment.getList().isEmpty()){
            idLabel.setText(engrais.getIdProduit());
            formTf.setText(engrais.getFormulation());
            periodeTf.setText(engrais.getPeriodeApp());
            doseTf.setText(String.valueOf(engrais.getDoseRec()));
            combobox.setValue(engrais.getType());
            qtTf.setText(String.valueOf(engrais.getQtNet()));
            nbrprodTf.setText(String.valueOf(engrais.getNbrProd()));
            compTf.setText(engrais.getComposition());
            this.engrais=engrais;}
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<TypeEngrais> types = FXCollections.observableArrayList();
         for(TypeEngrais typ : TypeEngrais.values()) {
                types.add(typ);
            }
         combobox.setItems(types);
    }    
        
    @FXML
    private void enregistrerBtnHandler(ActionEvent event) {
         try{
        String formulation = formTf.getText();
        String periodeApp = periodeTf.getText();
        Double dose = Double.parseDouble(doseTf.getText());
        Double qtNet = Double.parseDouble(qtTf.getText());
        Integer nbrProd = Integer.parseInt(nbrprodTf.getText());
        TypeEngrais typeEng = combobox.getValue();
        String composition = compTf.getText();

        if (!validationFields(formulation, typeEng, periodeApp, dose, qtNet, nbrProd, composition)) return;
        System.out.println("engrais: "+engrais);
        if (engrais != null && produitTableView.getItems().contains(engrais)) {
            engrais.setFormulation(formulation);
            engrais.setPeriodeApp(periodeApp);
            engrais.setDoseRec(dose);
            engrais.setQtNet(qtNet);
            engrais.setNbrProd(nbrProd);
            engrais.setType(typeEng);
            engrais.setComposition(composition);
            updateAlert(engrais);
            produitTableView.refresh();
            produitTableView.getSelectionModel().clearSelection();
        } else {
            Engrais newEngrais = new Engrais("engrais" + String.valueOf(currentId), formulation, periodeApp,
                    dose, qtNet, nbrProd, typeEng, composition);
            saveAlert(newEngrais);
            produitTableView.getItems().add(newEngrais);
            batiment.getList().add(newEngrais);
            currentId++;
            clearFields();
        }

        Stage stage = (Stage) enregistrerbtn.getScene().getWindow();
        stage.close();
        } catch (NumberFormatException e) {
            errorText.setText("Veuillez entrer des valeurs numériques valides.");
        }
    }
    private boolean validationFields(String formulation, TypeEngrais type, String periodeApp, Double dose, Double qtNet, Integer nbrProd, String composition) {
    if (formulation.isEmpty() || type == null || periodeApp.isEmpty() || dose == null || qtNet == null || nbrProd == null || composition == null) {
        errorText.setText("Veuillez remplir tous les champs.");
        return false;
    }
    return true;
}
    
    @FXML
    private void reinitialiserBtnHandler(ActionEvent event) {
        clearFields();
    }
    
    private void saveAlert(Engrais engrais){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Engrais crée avec succès");
        alert.setHeaderText(null);
        alert.setContentText("l'id de cet engrais est '"+engrais.getIdProduit()+"'");
        alert.showAndWait();
    }
    private void updateAlert(Engrais engrais){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Engrais mis à jour avec succès");
        alert.setHeaderText(null);
        alert.setContentText("l'engrais '"+engrais.getIdProduit()+"' a été mis à jour avec succès ");
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
        compTf.clear();
    }
    
}

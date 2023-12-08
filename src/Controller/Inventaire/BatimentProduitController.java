/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Inventaire;

import Controller.Inventaire.BatimentsController;
import Model.Inventaire.Animal;
import Model.Inventaire.Batiment;
import Model.Inventaire.Produit;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private TableColumn<Produit, ?> typeColonne;
    @FXML
    private TableColumn<Produit, String> compositionColonne;
    @FXML
    private TableColumn<Produit, String> subactiveColonne;
    @FXML
    private TableColumn<Produit, String> culturesColonne;
    @FXML
    private TableColumn<Produit, String> ravageursColonne;
    @FXML
    private TableColumn<Produit, Integer> DARColonne;
    @FXML
    private TableView<Produit> produitTableView;
    
    private ObservableList<Produit> produitList;
       
     private static int currentId = 1;
     
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
        
    }    

    @FXML
    private void ajoutPestBtnHandler(ActionEvent event) {
        
    }

    @FXML
    private void ajoutEngBtnHandler(ActionEvent event) {
        
    }
    
    @FXML
    private void returnBtnHandler(MouseEvent event) {
        returnbtn.getScene().getWindow().hide();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Espace;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Model.Espace.Responsable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class ResponsablesController implements Initializable {

    @FXML
    private TableColumn<Responsable,Integer> cin;
    @FXML
    private TableColumn<Responsable,String> nom;
    @FXML
    private TableColumn<Responsable,String> prenom;
    @FXML
    private TableColumn<Responsable,String> genre;
    @FXML
    private TableColumn<Responsable,Integer> numTel;
    @FXML
    private TableColumn<Responsable,String> email;
    @FXML
    private TableColumn<Responsable,Integer> nbreH;
    @FXML
    private TableColumn<Responsable,Integer> salH;
    @FXML 
    private TableView<Responsable> table;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        numTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        nbreH.setCellValueFactory(new PropertyValueFactory<>("nbrHeure"));
        salH.setCellValueFactory(new PropertyValueFactory<>("salaireH"));
        ObservableList<Responsable> listeResp = FXCollections.observableArrayList();
        listeResp.addAll(new Responsable(123456,"Bey", "Asma", "F", 55436345, "a.b@g.c",15,25.0,1),
                new Responsable(45678,"Belhedi", "Abir", "F", 55411345, "B.a@g.c",20,45.0,2),
                new Responsable(111111,"Ben Abid", "Ghayth", "M", 99346345, "ba.g@g.c",30,60.0,3));
        table.setItems(listeResp);
        
    }    

    @FXML
    private void supprimerResponsable(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            table.getItems().remove(selectedIndex);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune selection");
            alert.setHeaderText("Aucun responsable sectionné");
            alert.setContentText("SVP sélectionnez un responsable dans la table");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void ajouterResponsable(ActionEvent event) {
    }
    @FXML
    private void modifierResponsable(ActionEvent event) {
    }
    
    @FXML 
    private void afficherConsole(MouseEvent event){
        System.out.println(table.getSelectionModel().getSelectedItem());
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Espace;

import Model.Espace.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class OuvriersController implements Initializable {

    @FXML
    private TableColumn<Ouvrier, Integer> cin;
    @FXML
    private TableColumn<Ouvrier, String> nom;
    @FXML
    private TableColumn<Ouvrier, String> prenom;
    @FXML
    private TableColumn<Ouvrier, String> genre;
    @FXML
    private TableColumn<Ouvrier, Integer> numTel;
    @FXML
    private TableColumn<Ouvrier, String> taches;
    @FXML
    private TableColumn<Ouvrier, Integer> nbreH;
    @FXML
    private TableColumn<Ouvrier, Double> salH;
    @FXML
    private TableView<Ouvrier> table;
    ObservableList<Ouvrier> listeOuv = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        numTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        taches.setCellValueFactory(new PropertyValueFactory<>("taches"));
        nbreH.setCellValueFactory(new PropertyValueFactory<>("nbrHeure"));
        salH.setCellValueFactory(new PropertyValueFactory<>("salaireH"));
        
        listeOuv.addAll(new Ouvrier(123456,"F", "Bey", "Asma", 55436345, 2, 15.0,"taches1"),
                new Ouvrier(45678, "F", "Belhedi", "Abir", 55411345, 20, 45.0, "taches2"),
                new Ouvrier(111111,"H", "Ben Abid", "Ghayth",  99346345, 30, 60.0, "taches3"));
        table.setItems(listeOuv);

    }

    @FXML
    private void supprimerOuvrier(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        Ouvrier ouv = table.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Ouvrier à supprimer");
            alert.setContentText("Etes vous sur vous voulez supprimer : "+
                    ouv.getNom()+" "+ouv.getPrenom()+" ?");
                        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            table.getItems().remove(selectedIndex);
        }
        else return;
            
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune selection");
            alert.setHeaderText("Aucun responsable sectionné");
            alert.setContentText("SVP sélectionnez un responsable dans la table");
            alert.showAndWait();
        }
    }

    @FXML
    private void ajouterOuvrier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FarmInnovateFX.FarmInnovateFX.class.getResource("/View/Espace/FormOuvrier.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ouvrier");
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            FormOuvrierController formCont = loader.getController();
            Ouvrier rp = new Ouvrier();
            formCont.setDialogStage(dialogStage);
            formCont.setOuvrier(rp);
            dialogStage.showAndWait();
            if(rp.getCin()!=0)
            listeOuv.add(rp);
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
    }

}

@FXML
private void modifierOuvrier(ActionEvent event) {
    int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
    Ouvrier resp = table.getSelectionModel().getSelectedItem() ;
         try {
            FXMLLoader loader = new FXMLLoader(); 
            loader.setLocation(FarmInnovateFX.FarmInnovateFX.class.getResource("/View/Espace/FormOuvrier.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            FormOuvrierController formCont = loader.getController();
            formCont.setDataOuvrier(resp);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ouvrier");
            formCont.setDialogStage(dialogStage);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
            if(resp.getCin()!=0){
                table.getItems().set(selectedIndex, resp);
            }
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
    }}else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune selection");
            alert.setHeaderText("Aucun responsable sectionné");
            alert.setContentText("SVP sélectionnez un responsable dans la table");
            alert.showAndWait();
        }
    }

    @FXML
private void afficherConsole(MouseEvent event) {
        System.out.println(table.getSelectionModel().getSelectedItem());
    }

}

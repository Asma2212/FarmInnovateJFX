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
import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class ResponsablesController implements Initializable {

    @FXML
    private TableColumn<Responsable, Integer> cin;
    @FXML
    private TableColumn<Responsable, String> nom;
    @FXML
    private TableColumn<Responsable, String> prenom;
    @FXML
    private TableColumn<Responsable, String> genre;
    @FXML
    private TableColumn<Responsable, Integer> numTel;
    @FXML
    private TableColumn<Responsable, String> email;
    @FXML
    private TableColumn<Responsable, Integer> nbreH;
    @FXML
    private TableColumn<Responsable, Double> salH;
    @FXML
    private TableColumn<Responsable, String> motDePasse;
    @FXML
    private TableColumn<Responsable, Integer> cSecteur;
    @FXML
    private TableView<Responsable> table;
    ObservableList<Responsable> listeResp = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        numTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        nbreH.setCellValueFactory(new PropertyValueFactory<>("nbrHeure"));
        motDePasse.setCellValueFactory(new PropertyValueFactory<>("motDePasse"));
        salH.setCellValueFactory(new PropertyValueFactory<>("salaireH"));
        cSecteur.setCellValueFactory(new PropertyValueFactory<>("codeS"));
        
        listeResp.addAll(new Responsable(123456, "Bey", "Asma", "F", 55436345, "a.b@g.c","111111", 15, 25.0, 1),
                new Responsable(45678, "Belhedi", "Abir", "F", 55411345, "B.a@g.c","222222", 20, 45.0, 2),
                new Responsable(111111, "Ben Abid", "Ghayth", "H", 99346345, "ba.g@g.c","333333", 30, 60.0, 3));
        
        listeResp.stream().map(r-> r.getCodeS()+r.getCodeS()).forEach(System.out::println);
        table.setItems(listeResp);

    }

    @FXML
    private void supprimerResponsable(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        Responsable resp = table.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Responsable à supprimer");
            alert.setContentText("Etes vous sur vous voulez supprimer : "+
                    resp.getNom()+" "+resp.getPrenom()+" le responsable du secteur "+ resp.getCodeS()+" ?");
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
    private void ajouterResponsable(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FarmInnovateFX.FarmInnovateFX.class.getResource("/View/Espace/FormResponsable.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Responsable");
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            FormResponsableController formCont = loader.getController();
            Responsable rp = new Responsable();
            formCont.setDialogStage(dialogStage);
            formCont.setResponsable(rp);
            dialogStage.showAndWait();
            if(rp.getCin()!=0)
            listeResp.add(rp);
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
    }

}

@FXML
private void modifierResponsable(ActionEvent event) {
    int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
    Responsable resp = table.getSelectionModel().getSelectedItem() ;
         try {
            FXMLLoader loader = new FXMLLoader(); 
            loader.setLocation(FarmInnovateFX.FarmInnovateFX.class.getResource("/View/Espace/FormResponsable.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            FormResponsableController formCont = loader.getController();
            formCont.setDataResponsable(resp);
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Responsable");
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

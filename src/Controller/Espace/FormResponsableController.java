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
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class FormResponsableController implements Initializable {

    @FXML
    private Pane loginPane11;
    @FXML
    private TextField email;
    @FXML
    private PasswordField motDePasse;
    @FXML
    private PasswordField salaireHeure;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private PasswordField nbHeure;
    @FXML
    private PasswordField numTel;
    @FXML
    private TextField nom;
    @FXML
    private RadioButton Femme;
    @FXML
    private ToggleGroup genre;
    @FXML
    private RadioButton Homme;
    @FXML
    private ComboBox<?> secteurCd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleEnregistrer(ActionEvent event) {
    }
    
}

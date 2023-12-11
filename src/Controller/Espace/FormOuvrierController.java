/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Espace;

import Model.Espace.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class FormOuvrierController implements Initializable {

    @FXML
    private TextField taches;
    @FXML
    private TextField salaireHeure;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private TextField nbHeure;
    @FXML
    private TextField numTel;
    @FXML
    private TextField nom;
    @FXML
    private RadioButton Femme;
    @FXML
    private ToggleGroup genre;
    @FXML
    private RadioButton Homme;
    @FXML
    private Label errMsg;

    private Ouvrier ouvrier;
    private Stage dialogStage;
    Connection connection;

    public void setOuvrier(Ouvrier ouvrier) {
        this.ouvrier = ouvrier;
    }

    public void setDataOuvrier(Ouvrier ouvrier) {
        this.ouvrier = ouvrier;
        if (ouvrier.getCin() != 0) {
            cin.setText(String.valueOf(ouvrier.getCin()));
            nom.setText(ouvrier.getNom());
            prenom.setText(ouvrier.getPrenom());
            nbHeure.setText(String.valueOf(ouvrier.getNbrHeure()));
            salaireHeure.setText(String.valueOf(ouvrier.getSalaireH()));
            numTel.setText(String.valueOf(ouvrier.getNumTel()));
            taches.setText(ouvrier.getTaches());
            if (ouvrier.getGenre() == "H") {
                Homme.setSelected(true);
            } else {
                Femme.setSelected(true);
            }

        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("class not found exception ");
        }

        String url1 = "jdbc:oracle:thin:@localhost:1521:XE";
        String name = "ferme";
        String pass = "ferme";
        try {
            connection = DriverManager.getConnection(url1, name, pass);
            String query = "SELECT codeS,nomSecteur FROM Secteur";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            System.out.println("rsss" + rs.toString());
            ObservableList<String> secteurs = FXCollections.observableArrayList();
            while (rs.next()) {

            }
        } catch (SQLException sqlE) {
            System.out.println("SQL:" + sqlE.getMessage());
        }

    }

    @FXML
    private void handleEnregistrer(ActionEvent event) {

        if (isInputValid()) {
            ouvrier.setCin(Integer.parseInt(cin.getText()));
            ouvrier.setNom(nom.getText());
            ouvrier.setPrenom(prenom.getText());
            ouvrier.setNbrHeure(Integer.parseInt(nbHeure.getText()));
            ouvrier.setSalaireH(Double.parseDouble(salaireHeure.getText()));
            ouvrier.setNumTel(Integer.parseInt(numTel.getText()));
            ouvrier.setTaches(taches.getText());
            int cd = 0;
            if (Homme.isSelected()) {
                ouvrier.setGenre("H");
            } else {
                ouvrier.setGenre("F");
            }

            dialogStage.close();
        }

    }

    private boolean isInputValid() {

        String errorMessage = "";

        if (cin.getText() == null || cin.getText().length() == 0) {

            errorMessage += "cin non valide\n";
            cin.setStyle("-fx-border-color: red;");
        } else {
            try {
                Integer.parseInt(cin.getText());
            } catch (NumberFormatException e) {
                errorMessage += "cin doit etre un entier\n";
                cin.setStyle("-fx-border-color: red;");
            }
        }
        if (nom.getText() == null || nom.getText().length() == 0) {
            errorMessage += "nom invalide!\n";
            nom.setStyle("-fx-border-color: red;");
        }
        if (prenom.getText() == null || prenom.getText().length() == 0) {
            errorMessage += "prénom invalide!\n";
            prenom.setStyle("-fx-border-color: red;");
        }
        if (taches.getText() == null || taches.getText().length() == 0) {
            errorMessage += "email invalide!\n";
            taches.setStyle("-fx-border-color: red;");
        }
        if (nbHeure.getText() == null || nbHeure.getText().length() == 0) {
            errorMessage += "nombre d'heure invalide\n";
            nbHeure.setStyle("-fx-border-color: red;");
        } else {

            try {
                Integer.parseInt(nbHeure.getText());
            } catch (NumberFormatException e) {
                errorMessage += "nombre d'heure doit etre un entier\n";
                nbHeure.setStyle("-fx-border-color: red;");
            }
        }

        if (salaireHeure.getText() == null || salaireHeure.getText().length() == 0) {
            errorMessage += "salaire par heure invalide\n";
            salaireHeure.setStyle("-fx-border-color: red;");
        } else {

            try {
                Double.parseDouble(salaireHeure.getText());
            } catch (NumberFormatException e) {
                errorMessage += "salaire par heure doit etre un réel\n";
                salaireHeure.setStyle("-fx-border-color: red;");
            }
        }

        if (numTel.getText() == null || numTel.getText().length() == 0) {
            errorMessage += "numTel invalide\n";
            numTel.setStyle("-fx-border-color: red;");
        } else {

            try {
                Integer.parseInt(numTel.getText());

            } catch (NumberFormatException e) {

                errorMessage += "numTel doit etre un entier\n";
                numTel.setStyle("-fx-border-color: red;");
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            errMsg.setText("*" + errorMessage);
            return false;
        }
    }

}

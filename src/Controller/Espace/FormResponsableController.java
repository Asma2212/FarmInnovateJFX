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
import Model.Espace.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class FormResponsableController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField motDePasse;
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
    private ComboBox<String> secteurCd;
    @FXML
    private Label errMsg;
    
    private Responsable responsable;
    private Stage dialogStage;
    Connection connection ;
    
    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public void setDataResponsable(Responsable responsable) {
        this.responsable = responsable;
        if (responsable.getCin() != 0) {
            cin.setText(String.valueOf(responsable.getCin()));
            nom.setText(responsable.getNom());
            prenom.setText(responsable.getPrenom());
            nbHeure.setText(String.valueOf(responsable.getNbrHeure()));
            salaireHeure.setText(String.valueOf(responsable.getSalaireH()));
            numTel.setText(String.valueOf(responsable.getNumTel()));
            email.setText(responsable.getEmail());
            motDePasse.setText(responsable.getMotDePasse());
            if(responsable.getGenre()=="H") Homme.setSelected(true);
            else
                Femme.setSelected(true);
            int c = responsable.getCodeS();
            if(c!=0){
              String query = "SELECT nomSecteur FROM Secteur WHERE codeS ="+c;
              PreparedStatement ps;
                try {
                    ps = connection.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()){
                        String nomS = rs.getString("nomSecteur");
                        String selectedSect = c+":"+nomS;
                        secteurCd.setValue(selectedSect);
                        // secteurCd.setItems(FXCollections.observableArrayList(selectedSect));
                       // secteurCd.getSelectionModel().selectFirst(); 
                    }
                } catch (SQLException ex) {
                        System.out.println("SQL Exception: " + ex.getMessage());
                        System.out.println("Failed SQL Query: " + query);
                }  
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
                String codeS = rs.getString("codeS");
                String nomS = rs.getString("nomSecteur");
                secteurs.add(codeS + ":" + nomS);
            }
            secteurCd.setItems(secteurs);
        } catch (SQLException sqlE) {
            System.out.println("SQL:" + sqlE.getMessage());
        }
        
    }

    @FXML
    private void handleEnregistrer(ActionEvent event) {
        System.out.println("1111");
        if (isInputValid()) {
            System.out.println("222");
            responsable.setCin(Integer.parseInt(cin.getText()));
            responsable.setNom(nom.getText());
            responsable.setPrenom(prenom.getText());
            responsable.setNbrHeure(Integer.parseInt(nbHeure.getText()));
            responsable.setSalaireH(Double.parseDouble(salaireHeure.getText()));
            responsable.setNumTel(Integer.parseInt(numTel.getText()));
            responsable.setEmail(email.getText());
            responsable.setMotdepasse(motDePasse.getText());
            int cd = 0;
           /* if (secteurCd.getValue() != null) {
                cd = Integer.parseInt(secteurCd.getValue().toString());
            }*/
            responsable.setCodeS(cd);
            if (Homme.isSelected()) {
                responsable.setGenre("H");
            } else {
                responsable.setGenre("F");
            }
            if (secteurCd.getSelectionModel().getSelectedItem() != null) {
                String selectedValue = secteurCd.getSelectionModel().getSelectedItem();
                String codeSString = selectedValue.substring(0, selectedValue.indexOf(":"));
                System.out.println("heey"+codeSString);
                try {
                    int codeS = Integer.parseInt(codeSString);
                    responsable.setCodeS(codeS);
                } catch (NumberFormatException e) {}
                
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
        if (email.getText() == null || email.getText().length() == 0) {
            errorMessage += "email invalide!\n";
            email.setStyle("-fx-border-color: red;");
        }
        if (motDePasse.getText() == null || motDePasse.getText().length() == 0) {
            errorMessage += "mot de passe invalide!\n";
            motDePasse.setStyle("-fx-border-color: red;");
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

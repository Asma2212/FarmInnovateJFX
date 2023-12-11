/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Espace;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    private static String nameF;
    private static String poste;
    private static int idP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        //Model.Date d = new Model.Date();
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (isValidLogin(username, password)) {
            System.out.println("bienvenue");
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/View/Espace/HomeF.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                if(HomeController.getRole()=="Responsable")
                stage.setTitle("FarmInnovate/Espace Responsable");
                else
                stage.setTitle("FarmInnovate/Espace Fermier");
                stage.setScene(scene);
                stage.show();
                Scene currentScene = ((Node) event.getSource()).getScene();
                Stage currentStage = (Stage) currentScene.getWindow();
                currentStage.close();
            } catch (IOException ioe) {
                System.out.println("exception : " + ioe.getMessage());
            }
        } else {
            System.out.println("Login failed!");
            showLoginErrorPopup();
        }
    }

    private boolean isValidLogin(String username, String password) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException cnf) {
            return false;
        }
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String name = "ferme";
        String pass = "ferme";
        if(HomeController.getRole()=="Responsable"){
                    try {
            Connection connection = DriverManager.getConnection(url, name, pass);
            String query = "SELECT p.nom,p.prenom FROM Responsable r,Personne p WHERE r.idP=p.idP AND email = ? AND motDePasse = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("..........");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                nameF = nom + " " + prenom;
                poste = "Responsable";
                return true;
            }

            // If a row is returned, the login is valid
        } catch (SQLException sqlE) {}
        }else{
        try {
            Connection connection = DriverManager.getConnection(url, name, pass);
            String query = "SELECT p.nom,p.prenom FROM Fermier f,Personne p WHERE f.idP=p.idP AND email = ? AND motDePasse = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                nameF = nom + " " + prenom;
                poste = "Fermier";
                return true;
            }

            // If a row is returned, the login is valid
        } catch (SQLException sqlE) {}
        }
        return false;
    }

    private void showLoginErrorPopup() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Connexion impossible");
        alert.setHeaderText(null);
        alert.setContentText("email ou mot de passe invalide !!.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
        }
    }

    public static String getName() {
        return nameF;
    }

    public static String getPoste() {
        return poste;
    }

    public static int getIdP() {
        return idP;
    }
}

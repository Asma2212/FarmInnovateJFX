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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
            try{
        Parent root =FXMLLoader.load(getClass().getResource("/View/Espace/HomeF.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("FarmInnovate/Espace Fermier");
        stage.setScene(scene);  
        stage.show();   
        }catch (IOException ioe){
            System.out.println("exception : "+ioe.getMessage());
        }
        } else {
            System.out.println("Login failed!");
            showLoginErrorPopup();
        }
            }

    private boolean isValidLogin(String username, String password) {
        try{
            Class.forName("oracle.jdbc.OracleDriver");
           }catch(ClassNotFoundException cnf){
            return false;
        }
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String name = "ferme";
            String pass = "ferme";
        try (Connection connection = DriverManager.getConnection(url, name, pass)) {
            String query = "SELECT * FROM Fermier f,Personne p WHERE f.idP=p.idP AND email = ? AND motDePasse = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next(); // If a row is returned, the login is valid
                }
            }
            }catch( SQLException sqlE){
            return false;
        }
    }
        private void showLoginErrorPopup() {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Connexion impossible");
        alert.setHeaderText(null);
        alert.setContentText("email ou mot de passe invalide !!.");

            Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked OK
        }
    }
  }

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package Controller.Espace;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author ADMIN
 */
public class HomeController implements Initializable {
public static String role ;
    @FXML
    private void EspaceFermierAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Espace/Login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            role = "Fermier" ;
            stage.setTitle("FarmInnovate/Connexion");
            stage.setScene(new Scene(root1));
            stage.show();
            Scene currentScene = ((Node) event.getSource()).getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    @FXML
    private void EspaceResponsableAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Espace/Login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            role = "Responsable" ;
            stage.setTitle("ABC");
            stage.setScene(new Scene(root1));
            stage.show();
            Scene currentScene = ((Node) event.getSource()).getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }
    
    public static String getRole (){
        return role ;
    }

    @FXML
    private void EspaceClientAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/Espace/LoginClient.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            root1.setStyle("-fx-background-color: transparent;");
            Stage stage = new Stage();
            role = "Client" ;
            stage.setTitle("FarmInnovate/Acceuil");
            stage.setScene(new Scene(root1));
            stage.show();
            Scene currentScene = ((Node) event.getSource()).getScene();
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

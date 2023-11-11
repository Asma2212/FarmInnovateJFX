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
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    @FXML
    private void EspaceFermierAction(ActionEvent event) {
        try{
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../View/Espace/Login.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
    //stage.initModality(Modality.APPLICATION_MODAL);
    //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("FarmInnovate/Connexion");
        stage.setScene(new Scene(root1));  
        stage.show();   
        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
        
    }
        @FXML
    private void EspaceResponsableAction(ActionEvent event) {
        try{
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../View/Espace/Login.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
    //stage.initModality(Modality.APPLICATION_MODAL);
    //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("ABC");
        stage.setScene(new Scene(root1));  
        stage.show();   
        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
        
    }
        @FXML
    private void EspaceClientAction(ActionEvent event) {
        try{
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../View/Espace/Login.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
    //stage.initModality(Modality.APPLICATION_MODAL);
    //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("FarmInnovate/Acceuil");
        stage.setScene(new Scene(root1));  
        stage.show();   
        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

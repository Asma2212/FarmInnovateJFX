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
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class HomeFController implements Initializable{
    @FXML
    private ImageView menu;

    @FXML
    private ImageView menuBack;

    @FXML
    private AnchorPane sideBar;
    @FXML
    private Label labelUser;
    
    @FXML
    private void handleBatimentAction(ActionEvent event) {
     try{
        Parent root =FXMLLoader.load(getClass().getResource("/View/Inventaire/Batiments.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("FarmInnovate/Espace Fermier");
        stage.setScene(scene);  
        stage.show();   
        }catch (IOException ioe){
            System.out.println("exception : "+ioe.getMessage());
        }
    }
    @FXML
    private void handlePlanteAction(ActionEvent event) {}
    @FXML
    private void handleProductionAction(ActionEvent event) {}
    @FXML
    private void handleDeconnecterAction(ActionEvent event) {}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelUser.setText("Bienvenue "+new LoginController().getName());
        
        sideBar.setTranslateX(-176);
        menu.setOnMouseClicked(event -> { 
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.3));
            slide.setNode(sideBar);
            slide.setToX(0);
            slide.play();
            sideBar.setTranslateX(-176);
            slide.setOnFinished((ActionEvent e)->{
                menu.setVisible(false);
                menuBack.setVisible(true);
            });
        });
        menuBack.setOnMouseClicked(event -> { 
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.3));
            slide.setNode(sideBar);
            slide.setToX(-178);
            slide.play();
            sideBar.setTranslateX(8);
            slide.setOnFinished((ActionEvent e)->{
                menu.setVisible(true);
                menuBack.setVisible(false);
            });
        });
    }    
 
  }

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Espace;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
    private Pane bodyPane;
    @FXML
    private JFXButton compteBtn ;
    @FXML
    private Text nameTxt;
    @FXML
    private Text posteTxt;
    
    
    @FXML
    private void handleBatimentAction(ActionEvent event) {
           
     try{
        Parent root =FXMLLoader.load(getClass().getResource("/View/Inventaire/Batiments.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("FarmInnovate/Espace/Batiments");
        stage.setScene(scene);  
        stage.show();   
        }catch (IOException ioe){
            System.out.println("exception : "+ioe.getMessage());
        }
    }
    
    @FXML
    private void handleSecteurAction(ActionEvent event) {   
        bodyPane.getChildren().clear();
                      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Espace/Secteur.fxml"));
            AnchorPane body = loader.load();
            bodyPane.getChildren().add(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handlePlanteAction(ActionEvent event) {}
    @FXML
    private void handleProductionAction(ActionEvent event) {}
    @FXML
    private void handleDeconnecterAction(ActionEvent event) {}
    @FXML
    private void handleResponsableAction(ActionEvent event){
         bodyPane.getChildren().clear();
                      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Espace/Responsables.fxml"));
            AnchorPane body = loader.load();
            bodyPane.getChildren().add(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         nameTxt.setText(LoginController.getName());
         posteTxt.setText(LoginController.getPoste());
                 try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Inventaire/Batiments.fxml"));
            BorderPane body = loader.load();
            bodyPane.getChildren().add(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        compteBtn.setText(LoginController.getName());
        sideBar.setTranslateX(-181);
        bodyPane.setTranslateX(-183);
        menu.setOnMouseClicked(event -> { 
            sideBar.toFront();
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.3));
            slide.setNode(sideBar);
            slide.setToX(0);
            slide.play();
            sideBar.setTranslateX(-181);
            bodyPane.setTranslateX(0);
             
            slide.setOnFinished((ActionEvent e)->{
                menu.setVisible(false);
                menuBack.setVisible(true);
            });
        });
        menuBack.setOnMouseClicked(event -> { 
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.3));
            slide.setNode(sideBar);
            slide.setToX(-181);
            slide.play();
            sideBar.setTranslateX(8);
            //bodyPane.setTranslateX(-176);
            slide.setOnFinished((ActionEvent e)->{
                menu.setVisible(true);
                menuBack.setVisible(false);
            });
        });
    }    
 
  }

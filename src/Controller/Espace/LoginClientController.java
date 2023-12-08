/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Espace;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class LoginClientController implements Initializable {
@FXML
private VBox signUp;
@FXML
private VBox signIn;

 @FXML
    public void switchSignUpAction(ActionEvent event) {
        // Slide out sign in, slide in sign up
        
        slideOut(signIn);
        slideIn(signUp);

    }

    @FXML
    public void switchSignInAction(ActionEvent event) {
        // Slide out sign up, slide in sign in
        slideOut(signUp);
        slideIn(signIn);

    }

    @FXML
    public void handleSignUpButtonAction(ActionEvent event) {
        // Handle sign up button action
    }

    @FXML
    public void handleSignInButtonAction(ActionEvent event) {
        // Handle sign in button action
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signUp.setVisible(false);
    }

    private void slideIn(VBox vbox) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), vbox);
        transition.setToX(0);
        transition.play();
        vbox.setVisible(true);
    }

    private void slideOut(VBox vbox) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), vbox);
        transition.setToX(-vbox.getLayoutX());
        transition.play();
        vbox.setVisible(false);
    }
}

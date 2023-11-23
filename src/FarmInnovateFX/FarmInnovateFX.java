/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package FarmInnovateFX;

import Controller.Espace.LoginController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ADMIN
 */
public class FarmInnovateFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Espace/HomeF.fxml"));
        Scene scene = new Scene(root);
     
        stage.setScene(scene);
        stage.setTitle("FarmInnovate/Acceuil");
        stage.show();
    } catch (IOException ioe){
            System.out.println(ioe.getMessage());
     }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

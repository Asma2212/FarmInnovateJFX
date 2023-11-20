/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Inventaire;

import Model.Inventaire.Batiment;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abir
 */
public class BatimentAnimalController implements Initializable {

    @FXML
    private Label idLabel;
    @FXML
    private TextField especeTf;
    @FXML
    private ComboBox<?> combobox;
    @FXML
    private RadioButton fembtn;
    @FXML
    private RadioButton malbtn;
    @FXML
    private DatePicker dateTf;
    @FXML
    private TextField poidsTf;
    @FXML
    private Button resetbtn;
    @FXML
    private Button savebtn;
    @FXML
    private TableView<?> animalTableView;
    @FXML
    private ImageView returnbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         returnbtn.setOnMouseClicked(event -> returnBtnHandler());
    }    

    @FXML
    private void resestBtnHandler(ActionEvent event) {
    }

    @FXML
    private void saveBtnHandler(ActionEvent event) {
    }

    @FXML
    private void returnBtnHandler() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Inventaire/Batiments.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Get the current stage (from the image view)
            Stage currentStage = (Stage) returnbtn.getScene().getWindow();

            // Set the new scene to the current stage
            currentStage.setScene(scene);
            
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately, for example, show an error message
        }
    }
  
    
}

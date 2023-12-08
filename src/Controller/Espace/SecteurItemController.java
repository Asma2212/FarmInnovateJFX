/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Espace;

import Model.Espace.Secteur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import Model.Espace.*;
import java.util.ArrayList;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class SecteurItemController implements Initializable {
    
    @FXML
    private TextField nomSecteur;

    @FXML
    private TextField idSecteur;
    @FXML
    private TextField responsableSecteur;
    @FXML
    private TextField batSecteur;
    @FXML
    private TextField planteSecteur;
    @FXML
    private AnchorPane itemAnchor ;
    
    private Secteur secteur;
    
    public void setData(Secteur secteur) {
        this.secteur = secteur;
       // this.myListener = myListener;
        nomSecteur.setText(secteur.getNomSecteur());
        idSecteur.setText(String.valueOf(secteur.getCodeS()));
        responsableSecteur.setText("resp");
        planteSecteur.setText("plante");
        batSecteur.setText("Bat");
    }
    @FXML
    public void deleteSecteur(MouseEvent event){
        String nomS = nomSecteur.getText();
        int idS = Integer.parseInt(idSecteur.getText());
        ArrayList<Responsable> resp = new ArrayList<>();
        
    }
    @FXML
    public void modifSecteur(MouseEvent event){
        
    }
    @FXML
    public void valideModifSecteur(MouseEvent event){
        
    }
    @FXML
    public void onMouseEntered(MouseEvent event) {
        // Scale up on mouse enter
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), itemAnchor);
        scaleTransition.setToX(1.05);
        scaleTransition.setToY(1.05);
        scaleTransition.play();
    }

    @FXML
    public void onMouseExited(MouseEvent event) {
        // Scale back to the original size on mouse exit
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), itemAnchor);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

    

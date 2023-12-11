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
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

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
    private AnchorPane itemAnchor;
    Connection connection;
    private Secteur secteur;
    private List<Secteur> secteurs = new ArrayList<>();

    public void setData(Secteur secteur) {
        this.secteur = secteur;
        System.out.println(this.secteur.getCodeS());
        // this.myListener = myListener;
        nomSecteur.setText(secteur.getNomSecteur());
        idSecteur.setText(String.valueOf(secteur.getCodeS()));
        String query = "SELECT p.nom,p.prenom FROM Responsable r,Personne p WHERE r.idP=p.idP AND codeS =" + this.secteur.getCodeS();
        PreparedStatement ps;
        String nomR = "Responsable";
        try {
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                nomR = nom + " " + prenom;
            }
        } catch (SQLException ex) {
        }
        responsableSecteur.setText(nomR);
        planteSecteur.setText("plante");
        batSecteur.setText("Bat");
    }

    @FXML
    public void deleteSecteur(MouseEvent event) {
        String nomS = nomSecteur.getText();
        int idS = Integer.parseInt(idSecteur.getText());
        Secteur sect = new Secteur(idS, nomS);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Secteur à supprimer");
        alert.setContentText("Etes vous sur vous voulez supprimer : "
                + " le secteur " + nomS + " " + idS + " ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
            } catch (ClassNotFoundException cnf) {
                System.out.println("class not found exception ");
            }
            String url1 = "jdbc:oracle:thin:@localhost:1521:XE";
            String name = "ferme";
            String pass = "ferme";
            try {
                connection = DriverManager.getConnection(url1, name, pass);
                String query = "DELETE FROM Secteur WHERE codeS=" + idS;
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("secteur supprimé !! ");

                }
            } catch (SQLException sqlE) {
                System.out.println("SQL:" + sqlE.getMessage());
            }
        }
    }

    @FXML
    public void modifSecteur(MouseEvent event) {
        String nomS = nomSecteur.getText();
        int idS = Integer.parseInt(idSecteur.getText());
        Secteur sect = new Secteur(idS, nomS);
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("class not found exception ");
        }
        String url1 = "jdbc:oracle:thin:@localhost:1521:XE";
        String name = "ferme";
        String pass = "ferme";
        try {
            connection = DriverManager.getConnection(url1, name, pass);
            String query = "UPDATE Secteur SET nomSecteur=? WHERE codeS=?";
            try ( PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, nomS);
                ps.setInt(2, idS);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Secteur modifié !! ");
                } else {
                    System.out.println("Aucun secteur modifié.");
                }
            }
        } catch (SQLException sqlE) {
            System.out.println("SQL:" + sqlE.getMessage());
        }
    }

    @FXML
    public void valideModifSecteur(MouseEvent event) {

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
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException cnf) {
            System.out.println("class not found exception ");
        }

        String url1 = "jdbc:oracle:thin:@localhost:1521:XE";
        String name = "ferme";
        String pass = "ferme";
        try {
            connection = DriverManager.getConnection(url1, name, pass);

        } catch (SQLException sqlE) {
            System.out.println("SQL:" + sqlE.getMessage());
        }
    }

}

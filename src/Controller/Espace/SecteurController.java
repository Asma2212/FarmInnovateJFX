/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Espace;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import Model.Espace.Secteur;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class SecteurController implements Initializable {

    @FXML
    private ScrollPane scroll;
    
    @FXML
    private GridPane grid;
    private List<Secteur> secteurs = new ArrayList<>();
    
    Connection connection ;
    @FXML
    public void handleAjouterSecteur(ActionEvent event) {
                        Secteur secteur = new Secteur(0,"Nom");
                        secteurs.add(0,secteur);
                setAllItems(0,1);
        
    }
        private List<Secteur> getData() {
        List<Secteur> sect = new ArrayList<>();
        Secteur secteur;

        secteur = new Secteur(0,"Animaux");
        sect.add(secteur);
        secteur = new Secteur(1,"Plante");
        sect.add(secteur);
        secteur = new Secteur(2,"Materiel");
        sect.add(secteur);
        secteur = new Secteur(3,"Sect1");
        sect.add(secteur);
        secteur = new Secteur(4,"Sect2");
        sect.add(secteur);
        secteur = new Secteur(5,"Sect3");
        sect.add(secteur);
        return sect;
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
            String query = "SELECT codeS,nomSecteur FROM Secteur";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String codeS = rs.getString("codeS");
                String nomS = rs.getString("nomSecteur");
                Secteur s = new Secteur (Integer.parseInt(codeS),nomS);
                secteurs.add(s);
            }
        } catch (SQLException sqlE) {
            System.out.println("SQL:" + sqlE.getMessage());
        }
        //secteurs.addAll(getData());
        setAllItems(0,1);
        
    }  
   public void setAllItems(int column,int row){
        try {
            for (int i = 0; i < secteurs.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/Espace/SecteurItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                SecteurItemController itemController = fxmlLoader.getController();
                itemController.setData(secteurs.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); 
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(15));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   } 
}

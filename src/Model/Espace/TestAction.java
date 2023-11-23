/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Model.Espace;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class TestAction {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String name = "ferme";
            String pass = "ferme";
            Model.Date d = new Model.Date();
            Connection conn = DriverManager.getConnection(url,name,pass);
            Fermier fermier = new Fermier("123456",50.0,50.0,123456,"F","Bey","Asma",55423178,d,"asma.b@g.c");
            //fermier.saisir(sc);
            Statement st = conn.createStatement();
            int res = 0;
           /* String insP = "INSERT INTO Personne VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(insP);
            ps.setInt(1, fermier.getIdP());
            ps.setInt(2, fermier.getCin());
            ps.setString(3, fermier.getGenre());
            ps.setString(4, fermier.getNom());
            ps.setString(5, fermier.getPrenom());
            ps.setInt(6, fermier.getNumTel());
            ps.setString(7, fermier.getDateNaiss().toString());
            ps.setString(8, fermier.getEmail());
            int resP = ps.executeUpdate();

            String insF = "INSERT INTO Fermier VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(insF);
            ps.setInt(1, fermier.getIdP());
            ps.setString(2, fermier.getMotDePasse());
            ps.setDouble(3, fermier.getRevenue());
            ps.setDouble(4, fermier.getActions());
            int resF = ps.executeUpdate();*/
            String selectF="SELECT * FROM Personne" ;
            PreparedStatement pst = conn.prepareStatement(selectF);
            ResultSet rs = pst.executeQuery(selectF);
        if (rs.next()) {
            //fermier = rs.first(); 
            System.out.println(" : "+rs.getInt(1)+"name"+rs.getString(4));
        }
        rs.close();
        pst.close();
        conn.close();
        }catch(ClassNotFoundException cnf){
            System.out.println("error class: "+cnf.getMessage()); 
        }catch( SQLException sqlE){
            System.out.println("error SQL: "+sqlE.getMessage()); 
        }
       /* Collection<Client> clients = new ArrayList<>();
        Collection<Fermier> fermiers = new ArrayList<>();
        Collection<Responsable> responsables = new ArrayList<>();
        Fermier fermier = new Fermier();
        Responsable responsable = new Responsable();
        Client client = new Client();
        fermier.setEmail("A");
        fermier.setMotDePasse("A");
        fermier.sinscrire(fermiers);
        fermier.ajouterSecteur(new Secteur(1,"secteur1"));
        responsable.setEmail("B");
        responsable.setMotdepasse("B");
        fermier.ajouterResponsable(1, responsable);
        int choix,choix2,testMenu = 0;
        boolean test;
        do{
        System.out.println("Vous devez choisir un espace : \n");
        System.out.println("1- Espace Fermier \n");
        System.out.println("2- Espace Responsable \n");
        System.out.println("3- Espace Client \n");
        System.out.println("4- Quitter \n");
        choix = sc.nextInt();
        switch (choix) {
            case 1 -> {
                do{
                System.out.println("1- s'authentifier \n");
                System.out.println("2- creer un compte \n");
                System.out.println("3- Retour \n");
                choix2 = sc.nextInt();
                switch(choix2){
                    case 1-> {
                        test = fermier.sauthentifier(sc,fermiers);
                        if(!test) System.out.println("vous devez créer un compte !! ");
                        if(test)
                            testMenu = fermier.menu(sc);
                                if(testMenu==-1) choix2=3;
                        else
                            System.out.println("vous devez créer un compte");
                    }
                    case 2 -> {
                        fermier.saisir(sc);
                        fermier.sinscrire(fermiers);
                    }
                }
                }while(choix2 != 3);
                }
            case 2 -> {
                responsables = fermier.getResponsables();
                do{
                System.out.println("1- s'authentifier \n");
                System.out.println("2- Retour \n");
                choix2 = sc.nextInt();
                if(choix2==1){
                        test = responsable.sauthentifier(sc,responsables);
                        if(!test) System.out.println("vous devez créer un compte !! ");
                        if(test)
                            testMenu = responsable.menu(sc);
                                if(testMenu==-1) choix2=3;
                        else
                            System.out.println("vous devez créer un compte");
                    }
                }while(choix2 != 2);
                }
            case 3 -> {
                do{
                System.out.println("1- s'authentifier \n");
                System.out.println("2- creer un compte \n");
                System.out.println("3- Retour \n");
                choix2 = sc.nextInt();
                switch(choix2){
                    case 1-> {
                        test = client.sauthentifier(sc,clients);
                        if(!test) System.out.println("vous devez créer un compte !! ");
                            choix2=3;
                    }
                    case 2 -> {
                        client.saisir(sc);
                        client.sinscrire(clients);
                    }
                }
                }while(choix2 != 3);
                }
        }
        }while(choix!=4);*/
    }
    
}

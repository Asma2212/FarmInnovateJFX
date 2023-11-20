/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Model.Espace;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
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
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String name = "ferme";
            String pass = "ferme";
            Connection conn = DriverManager.getConnection(url,name,pass);
            PreparedStatement pst = conn.prepareStatement("SELECT SYSDATE FROM DUAL");
            ResultSet rs = pst.executeQuery("SELECT SYSDATE FROM DUAL");
         
         
        if (rs.next()) {
            Date currentDate = rs.getDate(1); // get first column returned
            System.out.println("Current Date from Oracle is : "+currentDate);
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
        Scanner sc = new Scanner(System.in);
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

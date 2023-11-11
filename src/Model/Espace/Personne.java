/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Espace;

import Model.Date;
import Model.Exception.GenreException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Personne {
    private static int id=0;
    private int idP;
    private int cin;
    private String genre ;
    private String nom;
    private String prenom;
    private int numTel;
    private Date dateNaiss = new Date();
    private String email;
    
    // ***********************  Constructeurs ****************************  //
    
    public Personne() {
        id++;
        this.idP = id;
    }    
    public Personne(int cin,String genre, String nom, String prenom, int numTel, Date dateNaiss, String email) {
        id++;
        this.idP = id;
        this.cin = cin;
        this.genre = genre ;
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.dateNaiss = dateNaiss;
        this.email = email;
    }
    // ***********************  CRUD ****************************  //
    public void saisir(Scanner sc){
        try{
        System.out.println("entrer son CIN : ");
        this.setCin(sc.nextInt());
        System.out.println("\nentrer genre : (F ou M)");
        this.genre=sc.next();
        if(!"F".equals(this.genre) && !"M".equals(this.genre)){
        throw new GenreException("vous devez entre M ou F") ;}
        System.out.println("\nentrer son nom : ");
        this.nom=sc.next();
        System.out.println("\nentrer son prenom : ");
        this.prenom=sc.next();
        System.out.println("\nentrer son n°Tel : ");
        this.setNumTel(sc.nextInt());
        System.out.println("\nentrer son date de Naissance : ");
        this.dateNaiss.saisir(sc);
        System.out.println("\nentrer son email\n");
        this.email=sc.next();
        }catch(InputMismatchException e){
        System.out.println("vous n'avez pas inséré un entier");  
        }catch(GenreException ge){
            System.out.println(ge.getMessage());
        }
    }
    
    public void modifier(Scanner sc){
        int test ;
        try{
            System.out.println("si vous voulez changer la cin tapez 1 : ");
            test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer la nouvelle CIN");
                this.setCin(sc.nextInt());               
            }
            System.out.println("si vous voulez changer le genre tapez 1 : ");
            test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer genre : (F ou M)");
                this.setGenre(sc.next());     
                if(!"F".equals(this.genre) && !"M".equals(this.genre)){
                throw new GenreException("vous devez entre M ou F") ;}
            }
            System.out.println("\nsi vous voulez changer le nom tapez 1 : ");
            test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer son nouveau nom : ");
                this.nom= sc.next();            
            }
            System.out.println("\nsi vous voulez changer le prenom tapez 1 : ");
            test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer son nouveau prenom : ");
                this.prenom=sc.next();              
            }
            System.out.println("\nsi vous voulez changer le n°Tel tapez 1 : ");
            test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer son n°Tel ");
                this.setNumTel(sc.nextInt());              
            }
            System.out.println("\nsi vous voulez changer la date de naissance tapez 1 : ");
            test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer son date de Naissance : ");
                this.dateNaiss.saisir(sc);           
            }
            System.out.println("\nsi vous voulez changer l'email tapez 1 : ");
            test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer son email\n");
                this.email=sc.next();            
            }
        }catch(InputMismatchException e){
        System.out.println("vous n'avez pas inséré un entier");  
        }catch(GenreException ge){
            System.out.println(ge.getMessage());
        }
    }
        
    @Override
    public String toString(){
        String ch;
        ch = "ID : "+ getIdP() + "\ncin : "+ cin + "\nnom : "+ nom+ "\nprenom : "+ prenom+ "\nemail : "+ email ;
        return ch ;
    }
    
    // ***********************  Manipulations ****************************  //
    
    
    // ***********************  getters et setters  ****************************  //
    public int getIdP() {
        return idP;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}

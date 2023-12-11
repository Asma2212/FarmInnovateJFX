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
public sealed class Personne permits Fermier, Responsable, Ouvrier, Client{
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
    public Personne(int cin,String genre, String nom, String prenom, int numTel) {
        id++;
        this.idP = id;
        this.cin = cin;
        this.genre = genre ;
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
    }
        
    @Override
    public String toString(){
        String ch;
        ch = "ID : "+ getIdP() + "\ncin : "+ cin + "\nnom : "+ nom+ "\nprenom : "+ prenom+ "\nemail : "+ email ;
        return ch ;
    }
    
    
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Espace;

import Model.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public final class Ouvrier extends Personne{
    private String taches;
    private int nbrHeure;
    private double salaireH ;
    // ***********************  Constructeurs ****************************  //
    public Ouvrier(){
    }
    public Ouvrier(int cin, String genre, String nom, String prenom, int numTel, Date dateNaiss,int nbrHeure, double salaireH, String taches) {
        super(cin, genre, nom, prenom, numTel);
        this.taches = taches;
        this.nbrHeure = nbrHeure;
        this.salaireH = salaireH;
    }
        public Ouvrier(int cin, String genre, String nom, String prenom, int numTel,int nbrHeure, double salaireH, String taches) {
        super(cin, genre, nom, prenom, numTel);
        this.taches = taches;
        this.nbrHeure = nbrHeure;
        this.salaireH = salaireH;
    }
        @Override
    public String toString(){
        String ch ;
        ch= super.toString()+"\nnombre d'heure Traivaillé : "+ getNbrHeure() +"\nsalaire par heure : "+getSalaireH();
        ch+="\nEnsemble des taches occupées : \n"+taches;
        return ch;
    }
    // ***********************  Manipulations ****************************  //

   public double calculSalaire() {
    ICalculSalaire calSalaire = (nbrHeure, salaireH) -> nbrHeure * salaireH;
    return calSalaire.calculSalaire(this.getNbrHeure(), this.getSalaireH());
    }
    // ***********************  getters et setters  ****************************  //

    public String getTaches() {
        return taches;
    }

    public void setTaches(String taches) {
        this.taches = taches;
    }

    public int getNbrHeure() {
        return nbrHeure;
    }

    public void setNbrHeure(int nbrHeure) {
        this.nbrHeure = nbrHeure;
    }

    public double getSalaireH() {
        return salaireH;
    }

    public void setSalaireH(double salaireH) {
        this.salaireH = salaireH;
    }
   

}

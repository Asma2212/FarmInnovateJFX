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
public class Ouvrier extends Personne{
    private ArrayList<String> taches;
    private int nbrHeure;
    private double salaireH ;
    // ***********************  Constructeurs ****************************  //
    public Ouvrier(){
        taches= new ArrayList();
    }
    public Ouvrier(int nbrHeure, double salaireH, int cin, String genre, String nom, String prenom, int numTel, Date dateNaiss, String email) {
        super(cin, genre, nom, prenom, numTel, dateNaiss, email);
        this.taches = new ArrayList();
        this.nbrHeure = nbrHeure;
        this.salaireH = salaireH;
    }
    
    // ***********************  CRUD ****************************  //
    @Override
    public void saisir(Scanner sc){
        super.saisir(sc);
        try{
        System.out.println("\nentrer le nombre d'heure");
        this.nbrHeure=sc.nextInt();
        System.out.println("\nentrer le salaire par heure");
        this.salaireH=sc.nextInt();            
        }catch(InputMismatchException e){
        System.out.println("vous n'avez pas inséré un entier"); 
        }

    }
    @Override
    public void modifier(Scanner sc){
        super.modifier(sc);
        int test;
        try{
            System.out.println("si vous voulez changer le nombre d'heure tapez 1 : ");
            test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer le nouveau nombre d'heure");
                this.nbrHeure = sc.nextInt();
            }
            System.out.println("si vous voulez changer le salaire par heure tapez 1 : ");
            test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer le salaire par heure");
                this.salaireH = sc.nextDouble();
            }
            System.out.println("modification effectuée avec succée");
        }catch(InputMismatchException e){
            System.out.println("vous n'avez pas inséré un entier"); 
        }
    }
        @Override
    public String toString(){
        String ch ;
        ch= super.toString()+"\nnombre d'heure Traivaillé : "+ getNbrHeure() +"\nsalaire par heure : "+getSalaireH();
        ch+="\nEnsemble des taches occupées : \n";
            for (String tache : getTaches()) {
                ch+="- "+tache+"\n";
            }
        return ch;
    }
    // ***********************  Manipulations ****************************  //

   public double calculSalaire() {
    ICalculSalaire calSalaire = (nbrHeure, salaireH) -> nbrHeure * salaireH;
    return calSalaire.calculSalaire(this.getNbrHeure(), this.getSalaireH());
    }
    // ***********************  getters et setters  ****************************  //

    public ArrayList<String> getTaches() {
        return taches;
    }

    public void setTaches(ArrayList<String> taches) {
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

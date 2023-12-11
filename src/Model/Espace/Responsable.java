/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Espace;

import Model.Date;
import Model.Inventaire.Batiment;
import Model.Inventaire.Plante;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author ADMIN
 */
public final class Responsable extends Personne{

    private String motDePasse ;
    private int nbrHeure;
    private double salaireH ;
    private int codeS ;
    private Set<Ouvrier> ouvriers ;
    private Set<Batiment> batiments;
    private Set<Plante> plantes;
    private Map<String,Production> productions;
    
    // ***********************  Constructeurs ****************************  //
    
    public Responsable() {
        this.productions = new HashMap<>();
        this.ouvriers = new HashSet<>();
        this.plantes = new HashSet<>();
        this.batiments = new HashSet<>();
    }

    
    public Responsable(String motdepasse, int nbrHeure,double salaireH,int codeS, int cin, String genre, String nom, String prenom, int numTel, Date dateNaiss, String email) {
        super(cin, genre, nom, prenom, numTel, dateNaiss, email);
        this.codeS = codeS;
        this.productions = new HashMap<>();
        this.ouvriers = new HashSet<>();
        this.plantes = new HashSet<>();
        this.batiments = new HashSet<>();
        this.motDePasse = motdepasse;
        this.nbrHeure = nbrHeure;
        this.salaireH=salaireH;
    }
        public Responsable( int cin,String nom, String prenom, String genre,  int numTel, String email,String motdepasse,int nbrHeure,double salaireH,int codeS) {
        super(cin, genre, nom, prenom, numTel, null, email);
        this.motDePasse = motdepasse;
        this.codeS = codeS;
        this.productions = new HashMap<>();
        this.ouvriers = new HashSet<>();
        this.plantes = new HashSet<>();
        this.batiments = new HashSet<>();
        this.nbrHeure = nbrHeure;
        this.salaireH=salaireH;
    }
    @Override
    public String toString(){
        String ch ;
        ch= super.toString()+"\nnombre d'heure Traivaillé : "+ getNbrHeure() +"\nsalaire par heure : "+getSalaireH()+"\n=>Salaire = "+calculSalaire();
        return ch;
    }
   
    public double calculSalaire() {
    ICalculSalaire calSalaire = (nbHeure, salaireHeure) -> nbHeure * salaireHeure;
    return calSalaire.calculSalaire(this.getNbrHeure(), this.getSalaireH());
    }
    // ***********************  getters et setters  ****************************  //

    
    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motDePasse = motdepasse;
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

    public Set<Ouvrier> getOuvriers() {
        return ouvriers;
    }

    public void setOuvriers(Set<Ouvrier> ouvriers) {
        this.ouvriers = ouvriers;
    }

    public int getCodeS() {
        return codeS;
    }

    public void setCodeS(int codeS) {
        this.codeS = codeS;
    }
    
    

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Espace;

import Model.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author ADMIN
 */
public final class Fermier extends Personne{

    private String motDePasse;
    private double revenue;
    private double actions;
    private HashSet<Secteur> secteurs = new HashSet();
    private Map<Integer,Responsable> respParSec = new HashMap();
    private TreeSet<Client> clients = new TreeSet();
    // ***********************  Constructeurs ****************************  //
    public Fermier() {
        
    }
    public Fermier(String motDePasse, double revenue, double actions, int cin, String genre, String nom, String prenom, int numTel, Date dateNaiss, String email) {
        super(cin, genre, nom, prenom, numTel, dateNaiss, email);
        this.motDePasse = motDePasse;
        this.revenue = revenue;
        this.actions = actions;
    }
    @Override
    public String toString(){
        String ch ;
        ch= super.toString()+"revenue : "+getRevenue()+" actions "+getActions();
        return ch;
    }
     
    public void ajouterResponsable(int codeS,Responsable rep){
        getRespParSec().put(codeS,rep);
    }
    public Responsable getResponsableParSecteur(int codeS){
        return getRespParSec().get(codeS);
    }
        public void consulterRespParSec(){
        for (Map.Entry<Integer, Responsable> respSect : respParSec.entrySet()){
            int code = respSect.getKey();
            Responsable resp = respSect.getValue();
            System.out.println("code Secteur : "+code);
            
            System.out.println("Responsable : "+resp+" salaire :"+resp.calculSalaire());   
        }
    }
    public void modifierResponsable(Responsable rp,int codeS){
        respParSec.remove(codeS);
        respParSec.put(codeS, rp);
    }
    // ***********************  getters et setters  ****************************  //

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getActions() {
        return actions;
    }

    public void setActions(float actions) {
        this.actions = actions;
    }

    public HashSet<Secteur> getSecteurs() {
        return secteurs;
    }
    /*public Secteur getSecteurByCode(int codeS) {
        
    }*/

    public void setSecteurs(HashSet<Secteur> secteurs) {
        this.secteurs = secteurs;
    }

    public Map<Integer,Responsable> getRespParSec() {
        return respParSec;
    }

    public void setRespParSec(Map<Integer,Responsable> respParSec) {
        this.respParSec = respParSec;
    }
    
    public ArrayList<Responsable> getResponsables(){
        return new ArrayList<>(respParSec.values());
    }

    public TreeSet<Client> getClients() {
        return clients;
    }

    public void setClients(TreeSet<Client> clients) {
        this.clients = clients;
    }
    
}

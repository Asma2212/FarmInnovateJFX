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
public class Responsable extends Personne implements IUser<Responsable>{

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
        public Responsable( int cin,String nom, String prenom, String genre,  int numTel, String email,int nbrHeure,double salaireH,int codeS) {
        super(cin, genre, nom, prenom, numTel, null, email);
        this.codeS = codeS;
        this.productions = new HashMap<>();
        this.ouvriers = new HashSet<>();
        this.plantes = new HashSet<>();
        this.batiments = new HashSet<>();
        this.nbrHeure = nbrHeure;
        this.salaireH=salaireH;
    }
    
    // ***********************  CRUD ****************************  //
    @Override
    public void saisir(Scanner sc){
        try{
        super.saisir(sc);
            System.out.println("\nentrer la mot de passe : ");
            this.motDePasse = sc.next();
            System.out.println("\nentrer le nombre d'heure");
            this.nbrHeure=sc.nextInt();
            System.out.println("\nentrer le salaire par heure");
            this.salaireH=sc.nextInt();
        }catch(InputMismatchException e){
            
        }
        
    }
    @Override
    public void modifier(Scanner sc){
        super.modifier(sc);
        int test;
        try{
            System.out.println("si vous voulez changer la mot de passe tapez 1 : ");
            test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer la nouvelle mot de passe");
                this.motDePasse = sc.next();
            }
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
        ch= super.toString()+"\nnombre d'heure Traivaillé : "+ getNbrHeure() +"\nsalaire par heure : "+getSalaireH()+"\n=>Salaire = "+calculSalaire();
        return ch;
    }
    // ***********************  Manipulations ****************************  //
    // ********************** Gestion des Ouvriers *************************//
    public void ajouterOuvrier(Ouvrier o){
        getOuvriers().add(o);
    }
    
    public Ouvrier getOuvrierById(int id){
        for (Ouvrier ouvrier : getOuvriers()) {
            if(ouvrier.getIdP()==id)
                return ouvrier ;
        }
        return null;
    }
    public void supprimerOuvrier(int id){
        for (Ouvrier ouvrier : getOuvriers()) {
            if(ouvrier.getIdP()==id){
                getOuvriers().remove(ouvrier) ;
                System.out.println("suppression effectuée avec succée");
            }
        }
    }
    public void modifierOuvrier(int id,Scanner sc){
        boolean test=false;
        Ouvrier o = new Ouvrier();
         Iterator<Ouvrier> it = ouvriers.iterator(); 
         while (it.hasNext()&&!test){ 
                  o = it.next();
             if(o.getIdP()==id)
                 test = true;         
        }
         if(test){
             ouvriers.remove(o);
             o.modifier(sc);
             ouvriers.add(o);
         }
    }
    public void consulterOuvriers(){
        System.out.println("Liste des ouvriers :");
        if(ouvriers.isEmpty())
            System.out.println("vide!");
        else
        ouvriers.forEach(System.out::println);
    }
    //***************************** Gestion des Batiments ********************//
    @Override
    public void consulterBatiments() {
        
    }
    //************************** Gestion de production ***********************//
  /*  public void ajouterProduction(Production p){
        productions.put(p.getRefP(),p);
    }
    public void modifierProduction(int refP,Scanner sc){
        if (productions.containsKey(refP)) {
            Production prod = productions.get(refP);
            prod.modifier(sc);
            productions.remove(refP);
            productions.put(prod.getRefP(), prod);
        }
    }
    public void supprimerProduction(int refP){
        productions.remove(refP);
    }*/
    @Override
    public void consulterProduction() {
        System.out.println("Productions :");
        if(productions.isEmpty())
            System.out.println("vide!");
        else
        for (Map.Entry<String, Production> prod : productions.entrySet()){
            //int ref = prod.getKey();
            Production p = prod.getValue();
            System.out.println(p);   
        }
    }
    // **************************** Gestion de Profil *****************************//
    @Override
    public void sinscrire(Collection<Responsable> responsables){
            if(! responsables.contains(this))
            responsables.add(this);
        else
            System.out.println("vous avez deja un compte");
    };
    @Override
        public boolean sauthentifier(Scanner sc,Collection<Responsable> responsables){
        String email;
        String mp;
        System.out.println("donner votre email");
        email=sc.next();
        System.out.println("donner votre mot de passe");
        mp=sc.next();
        for (Responsable resp : responsables) {
            if(email.equals(resp.getEmail())&& mp.equals(resp.getMotDePasse()))   
                return true;
        }
        return false;
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

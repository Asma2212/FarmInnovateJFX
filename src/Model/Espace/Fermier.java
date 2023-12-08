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
public class Fermier extends Personne implements IUser<Fermier>{

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
    // ***********************  CRUD ****************************  //
    @Override
    public void saisir(Scanner sc){
        super.saisir(sc);
        try{
        System.out.println("\nentrer la mot de passe : ");
        this.motDePasse = sc.next();
        System.out.println("\nentrer le revenue");
        this.revenue=sc.nextDouble();
        System.out.println("\nentrer l'action");
        this.actions=sc.nextDouble();             
        }catch(InputMismatchException e){
            System.out.println("vous n'avez pas inséré un entier"); 
        }
       

    }
    @Override
    public void modifier(Scanner sc){
        int test;
        super.modifier(sc);
        try{
            System.out.println("si vous voulez changer la mot de passe tapez 1 : ");
            test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer la nouvelle mot de passe");
                this.motDePasse = sc.next();
            }
            System.out.println("si vous voulez changer le revenue : ");
            test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer le revenue");
                this.revenue = sc.nextDouble();
            }
            System.out.println("si vous voulez changer l'action : ");
            test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer l'action");
                this.actions = sc.nextDouble();
            }
            System.out.println("modification effectuée avec succée");
        }catch(InputMismatchException e){
            System.out.println("vous n'avez pas inséré un entier"); 
        }
    }
    @Override
    public String toString(){
        String ch ;
        ch= super.toString()+"revenue : "+getRevenue()+" actions "+getActions();
        return ch;
    }
    // ***********************  Manipulations ****************************  //
    
    //**********************Gestion des Secteurs **************************//
  /*  public void ajouterSecteur(Secteur s){
        getSecteurs().add(s);
    }
    public void modifierSecteur(int codeS,Scanner sc){
        boolean test=false;
        Secteur s = new Secteur();
         Iterator<Secteur> it = secteurs.iterator(); 
         while (it.hasNext()&&!test){ 
                  s = it.next();
             if(s.getCodeS()==codeS)
                 test = true;         
        }
         if(test){
             secteurs.remove(s);
             s.modifier(sc);
             secteurs.add(s);
         }       
    }
    public void supprimerSecteur(int codeS){
        boolean test=false;
        Secteur s = new Secteur();
         Iterator<Secteur> it = secteurs.iterator(); 
         while (it.hasNext()&&!test){ 
                  s = it.next();
             if(s.getCodeS()==codeS)
                 test = true;         
        }
         if(test){
             secteurs.remove(s);
             System.out.println("suppression effectuée avec succée");
         }
    }
    public void consulterSecteurs(){
        System.out.println("Liste des secteurs :"); 
        if(secteurs.isEmpty())
            System.out.println("vide");
        else
       getSecteurs().forEach(System.out::print);
    }
    public boolean existSecteur(int codeS){
        boolean test=false;
        Secteur s = new Secteur();
        Iterator<Secteur> it = secteurs.iterator(); 
         while (it.hasNext()&&!test){ 
                  s = it.next();
             if(s.getCodeS()==codeS)
                 test = true;         
        }
        return test;
    }*/
    //**********************Gestion des responsables **************************//
    
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
    //**********************Gestion du Profil **************************//
    @Override
        public void sinscrire(Collection<Fermier> fermiers){
            if(! fermiers.contains(this))
            fermiers.add(this);
        else
            System.out.println("vous avez deja un compte");
    };
    @Override
        public boolean sauthentifier(Scanner sc,Collection<Fermier> fermiers){
        String email;
        String mp;
        System.out.println("donner votre email");
        email=sc.next();
        System.out.println("donner votre mot de passe");
        mp=sc.next();
        for (Fermier fermier : fermiers) {
                        if(email.equals(fermier.getEmail())&& mp.equals(fermier.getMotDePasse()))
                    return true;   
        }
        return false;
    }
    public void consulterClients(){
        System.out.println("Liste des clients :");
        getClients().forEach(System.out::print);
    }
    @Override
    public void consulterBatiments() {
        
    }

    @Override
    public void consulterProduction() {
        
    }
  /*  public int menu(Scanner sc){
        int choix ;
        do{
        System.out.println("\n********* Gestion des secteurs **************\n ");
        System.out.println("1-Ajouter un secteur\n");
        System.out.println("2-Modifier un secteur\n");
        System.out.println("3-Supprimer un secteur\n");
        System.out.println("4-Consulter les secteurs\n");
        System.out.println("********* Gestion des responsables **************\n ");
        System.out.println("5-Ajouter un responsable Par Secteur\n");
        System.out.println("6-Consulter les responsables\n");
        System.out.println("7-modifier un responsable\n");
        System.out.println("8-Supprimer un responsable\n");
        System.out.println("********* Gestion de profil **************\n ");
        System.out.println("9-Modifier mes donneés\n");
        System.out.println("10-Consulter mon profil\n");
        System.out.println("11- retour \n");
        choix=sc.nextInt();
        switch(choix){
            case 1 -> {
                Secteur s= new Secteur();
                s.saisir(sc);
                this.ajouterSecteur(s);
            }
            case 2 -> {
                System.out.println("entrer le code secteur à modifier ");
                int codeS = sc.nextInt();
                this.modifierSecteur(codeS,sc);
            }
            case 3 -> {
                System.out.println("entrer le code secteur à supprimer ");
                int codeS = sc.nextInt();
                this.supprimerSecteur(codeS);
            }
            case 4 -> {
                consulterSecteurs();
            }
            case 5 -> {
                System.out.println("entrer le code secteur adequat ");
                int codeS = sc.nextInt();
                if(!existSecteur(codeS))
                    System.out.println("secteur inexistant !! ");
                else
                {Responsable resp = new Responsable();
                resp.saisir(sc);
                this.ajouterResponsable(codeS,resp);
                }
            }
            case 6 -> {
                consulterRespParSec();
            }
            case 7 -> {
                System.out.println("donner le code secteur"); 
                int codeS = sc.nextInt();
                modifierSecteur(codeS, sc); 
            }
            case 8 -> {
                System.out.println("donner le code secteur"); 
                int codeS = sc.nextInt();
                supprimerSecteur(codeS);  
            }
            case 9 -> {
                modifier(sc);
            }
            case 10 -> {
                System.out.println(this.toString());
            }
        }
        }while (choix!=11);
        return -1 ; 
    }*/
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

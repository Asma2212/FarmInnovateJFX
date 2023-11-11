/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Espace;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author ADMIN
 */
public class Client extends Personne implements IUser<Client>,Comparator<Client>{
    private String motDePasse ;
    private Date dateInscription ;
    private Map<Date,Commande> commandes = new HashMap(){};

    // ***********************  Constructeurs ****************************  //
    public Client() {
    }

    public Client(String motDePasse ,int cin, String genre, String nom, String prenom, int numTel, Model.Date dateNaiss, String email) {
        super(cin, genre, nom, prenom, numTel, dateNaiss, email);
        this.motDePasse = motDePasse;
    }

    // ***********************  CRUD ****************************  //
    @Override
    public void saisir(Scanner sc){
        super.saisir(sc);
        System.out.println("donner la mot de passe : ");
        this.motDePasse = sc.next();      
    }
    @Override
    public void modifier(Scanner sc){
        int test;
        super.modifier(sc);
        System.out.println("si vous voulez changer la mot de passe tapez 1 : ");
        test = sc.nextInt();
            if(test==1){
                System.out.println("\nentrer la nouvelle mot de passe");
                this.motDePasse = sc.next();
            }
        System.out.println("modification effectuée avec succée");
    }
    @Override
    public String toString(){
        String ch;
        ch = super.toString()+"\nDate inscription : "+dateInscription;
        return ch;
    }
    // ***********************  Manipulation ****************************  //
    @Override
    public void sinscrire(Collection<Client> clients){
            if(! clients.contains(this))
            clients.add(this);
        else
            System.out.println("vous avez deja un compte");
    };
    @Override
    public boolean sauthentifier(Scanner sc,Collection<Client> clients){
        String mail;
        String mp;
        System.out.println("donner votre email");
        mail=sc.next();
        System.out.println("donner votre mot de passe");
        mp=sc.next();
        for (Client clt : clients) {
            if(mail.equals(clt.getEmail())&& mp.equals(clt.getMotDePasse()))
               return true;   
        }
        return false;
    }
    @Override
    public void consulterBatiments() {}
    
    @Override
    public void consulterProduction() {
        
    }
    @Override
    public int compare(Client c1, Client c2) {
      return c1.getDateInscription().compareTo(c2.getDateInscription());
    }
    // ***********************  getters et setters  ****************************  //

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Map<Date,Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Map<Date,Commande> commandes) {
        this.commandes = commandes;
    }
     
}

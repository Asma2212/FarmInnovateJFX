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
public final class Client extends Personne implements Comparator<Client>{
    private String motDePasse ;
    private Date dateInscri ;
    private Map<Date,Commande> commandes = new HashMap(){};

    // ***********************  Constructeurs ****************************  //
    public Client() {
    }

    public Client(String motDePasse ,int cin, String genre, String nom, String prenom, int numTel, Model.Date dateNaiss, String email) {
        super(cin, genre, nom, prenom, numTel, dateNaiss, email);
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString(){
        String ch;
        ch = super.toString()+"\nDate Inscri : "+dateInscri;
        return ch;
    }
    @Override
    public int compare(Client c1, Client c2) {
      return c1.getDateInscri().compareTo(c2.getDateInscri());
    }
    // ***********************  getters et setters  ****************************  //

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Date getDateInscri() {
        return dateInscri;
    }

    public void setDateInscri(Date dateInscri) {
        this.dateInscri = dateInscri;
    }

    public Map<Date,Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Map<Date,Commande> commandes) {
        this.commandes = commandes;
    }
     
}

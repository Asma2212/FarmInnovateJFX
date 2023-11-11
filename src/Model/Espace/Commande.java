/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Espace;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Commande implements Comparator<Commande>{
   private int numC;
   private Date dateCom;
   private ModesPaiement modePaiement ;
   private List<LigneCommande> lignes = new ArrayList<>();

    public Commande() {
    }

    public Commande(int numC, Date dateCom, ModesPaiement modePaiement) {
        this.numC = numC;
        this.dateCom = dateCom;
        this.modePaiement = modePaiement;
    }
    
    @Override
    public int compare(Commande c1, Commande c2) {
      return c1.getDateCom().compareTo(c2.getDateCom());
    }

    public int getNumC() {
        return numC;
    }

    public void setNumC(int numC) {
        this.numC = numC;
    }

    public List<LigneCommande> getLignes() {
        return lignes;
    }

    public void setLignes(List<LigneCommande> lignes) {
        this.lignes = lignes;
    }

    public Date getDateCom() {
        return dateCom;
    }

    public ModesPaiement getModePaiement() {
        return modePaiement;
    }

    public void setDateCom(Date dateCom) {
        this.dateCom = dateCom;
    }

    public void setModePaiement(ModesPaiement modePaiement) {
        this.modePaiement = modePaiement;
    }
   
}

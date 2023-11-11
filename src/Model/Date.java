package Model;

import java.util.Scanner;

public class Date {
    private int jour;
    private int mois;
    private int annee;
  
 //************************Constructers**********************   
    public Date(int jour, int mois, int annee){
        this.jour=jour;
        this.mois=mois;
        this.annee=annee;
    }
    public Date(){}
//************************Getters and Setters*****************

    public int getJour() { return jour;}

    public void setJour(int jour) { this.jour = jour; }
    
    public int getMois() { return mois; }

    public void setMois(int mois) { this.mois = mois; }

    public int getAnnee() { return annee; }

    public void setAnnee(int annee) { this.annee = annee; }

//************************Saisie*****************
   public void saisir(Scanner sc) {
    do {
        System.out.println("jour: ");
        this.jour = sc.nextInt();
    } while (this.jour < 1 || this.jour > 31);

    do {
        System.out.println("mois: ");
        this.mois = sc.nextInt();
    } while (this.mois < 1 || this.mois > 12);

    do {
        System.out.println("annee: ");
        this.annee = sc.nextInt();
    } while (this.annee < 0);
}

 //************************Affichage*****************
   @Override
   public String toString(){
       return jour+"/"+mois+"/"+annee;
   }
   
}

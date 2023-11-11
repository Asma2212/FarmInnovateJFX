package Model.Inventaire;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Plante {
    
    private String idPlante;
    private String culture;
    private String nature;
    private String saisonRecolte;
    private double qtEauJour;
    
 //************************Constructers*******************   
    public Plante(String idPlante, String culture, String nature, String saisonRecolte, double qtEaujour){
        this.idPlante=idPlante;
        this.culture= culture;
        this.nature=nature;
        this.saisonRecolte=saisonRecolte;
        this.qtEauJour=(double) qtEauJour;
    }
    public Plante(){}
    
//************************Getters and Setters*******************   

    public String getIdPlante(){ return idPlante; }
    
    public void setIdPlante(String idPlante){ this.idPlante=idPlante; }
    
    public String getCulture() { return culture; }

    public void setCulture(String culture) { this.culture = culture; }

    public String getNature() { return nature; }

    public void setNature(String nature) { this.nature = nature; }

    public String getSaisonRecolte() { return saisonRecolte; }

    public void setSaisonRecolte(String saisonRecolte) { this.saisonRecolte = saisonRecolte; }

    public double getQtEauJour() { return qtEauJour; }

    public void setQtEauJour(double qtEauJour) { this.qtEauJour = qtEauJour; }
    
//***********************Saisie******************** 
    public void saisir(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("entrez l'identifiant de la plante");
            this.idPlante = "plante"+sc.nextInt();
            sc.nextLine();
            System.out.println("entrez la culture");
            this.culture=sc.nextLine();
            int x;
           do{ 
            System.out.println("selectionnez une nature: 1.pérenne, 2.annuelle");
            x=sc.nextInt();}
            while(x != 1 && x != 2);
            if(x==1) this.nature="pérenne";
            else this.nature="annuelle";
            sc.nextLine();
            System.out.println("entrez la saison de recolte");
            this.saisonRecolte=sc.nextLine();
            System.out.println("entrez la quantite d'eau nessecaire par jour");
            this.qtEauJour= sc.nextDouble();}
        catch(InputMismatchException e){
            System.out.println("La valeur entrée n'est pas valide.");
        }
    }
//***********************Affichage********************
    @Override 
    public String toString(){
        return "idPlante: "+this.idPlante+", culture: "+this.culture+", nature: "+this.nature+", saisonRecolte: "+this.saisonRecolte+", Quantite d'eau par jour: "+this.qtEauJour;
    }
//***********************Modification********************    
    public void modifier(){
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("modification de la plante");
            int x;
           do{ 
            System.out.println("selectionnez une nature: 1.pérenne, 2.annuelle");
            x=sc.nextInt();}
            while(x != 1 && x != 2);
            if(x==1) this.nature="pérenne";
            else this.nature="annuelle";
            sc.nextLine();
            System.out.println("entrez la saison de recolte");
            this.saisonRecolte=sc.nextLine();
            System.out.println("entrez la quantite d'eau nessecaire par jour");
            this.qtEauJour= sc.nextDouble();}
        catch(InputMismatchException e){
            System.out.println("La valeur entrée n'est pas valide.");
        }
    }
  
    
}

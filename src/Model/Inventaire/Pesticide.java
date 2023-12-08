package Model.Inventaire;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Pesticide extends Produit{

    private TypePesticide typepest;
    private String subActive;
    private String cultures ;
    private String ravageurs ;
    private int DAR;
    
    
    //constructeur
    public Pesticide(){}
    
    public Pesticide(TypePesticide typepest, String subActive, String cultures,String ravageurs ){
        super();
        this.typepest=typepest;
        this.subActive=subActive;
        this.cultures=cultures;
        this.ravageurs=ravageurs;
    }
    
    //Getters and Setters
    public TypePesticide getTypepest() { return typepest; }

    public void setTypepest(TypePesticide typepest) { this.typepest = typepest; }

    public String getSubActive() { return subActive; }

    public void setSubActive(String subActive) { this.subActive = subActive; }

    public String getCultures() { return cultures; }
 
    public void setCultures(String cultures) { this.cultures = cultures; }

    public String getRavageurs() { return ravageurs; }

    public void setRavageurs(String ravageurs) { this.ravageurs = ravageurs; }

    public int getDAR() { return DAR; }

    public void setDAR(int DAR) { this.DAR = DAR; }
    
    
    
    //toString 
    @Override
    public String toString() {
        return super.toString() + ", typepest: " + getTypepest() + ", subActive: " + getSubActive() + ", cultures:["+cultures+"], ravageurs:["+ravageurs+ "], DAR: " + getDAR();
    }
  
}

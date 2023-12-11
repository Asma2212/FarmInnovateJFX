package Model.Inventaire;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class Pesticide extends Produit{

    private TypePesticide type;
    private int DAR;
    
    
    //constructeur
    
    
    public Pesticide(String idProduit, String formulation, String periodeApp, double doseRec, double qtNet, int nbrProd,TypePesticide type,int DAR){
        super(idProduit, formulation,  periodeApp, doseRec, qtNet, nbrProd);
        this.type=type;
        this.DAR=DAR;
    }
    public Pesticide(){}
    //Getters and Setters
    public TypePesticide getType() { return type; }

    public void setType(TypePesticide typepest) { this.type = typepest; }


    public int getDAR() { return DAR; }

    public void setDAR(int DAR) { this.DAR = DAR; }
    
    
    
    //toString 
    @Override
    public String toString() {
        return super.toString() + ", typepest: " + getType() + "], DAR: " + getDAR();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pesticide other = (Pesticide) obj;
        return Objects.equals(idProduit, other.idProduit);
    }
}

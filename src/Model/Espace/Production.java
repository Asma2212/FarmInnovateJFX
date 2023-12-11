/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Espace;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public abstract class Production {
    protected String refP;
    protected int qteStock;
    protected Double prix ;

    public Production() {
    }

    public Production(String refP, int qteStock, Double prix) {
        this.refP = refP;
        this.qteStock = qteStock;
        this.prix = prix;
    }
    @Override 
    public String toString(){
        return "RefP : "+refP+" Qte Stock : "+ qteStock+" prix : "+prix;
    }
    public String getRefP() {
        return refP;
    }

    public void setRefP(String refP) {
        this.refP = refP;
    }

    public int getQteStock() {
        return qteStock;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
    
    
    
}

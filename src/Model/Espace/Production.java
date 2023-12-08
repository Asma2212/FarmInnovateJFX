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
    private String refP;
    private int qteStock;
    private Double prix ;

    public Production() {
    }

    public Production(String refP, int qteStock, Double prix) {
        this.refP = refP;
        this.qteStock = qteStock;
        this.prix = prix;
    }

 /*   public void saisir(Scanner sc){
        try{
            System.out.println("entrer la reference du produit : ");
            this.refP=sc.next();
            System.out.println("entrer la quantité en stock : ");
            this.qteStock=sc.nextInt();
            System.out.println("entrer son prix : ");
            this.prix=sc.nextDouble();
        }catch(InputMismatchException e){
            System.out.println("vous n'avez pas inséré un entier");  
        }
    }
    public void modifier(Scanner sc){
       try{
            System.out.println("entrer la nouvelle reference : ");
            this.refP=sc.next();
            System.out.println("entrer la nouvelle quantité : ");
            this.qteStock=sc.nextInt();
            System.out.println("entrer le nouveau prix : ");
            this.prix=sc.nextDouble();
        }catch(InputMismatchException e){
            System.out.println("vous n'avez pas inséré un entier");  
        } 
    }*/
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

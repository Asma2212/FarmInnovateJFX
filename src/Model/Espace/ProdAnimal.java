/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Espace;

import Model.Date;
import Model.Exception.ValeurHorsIntervalleException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class ProdAnimal extends Production{
    private NatureProdAnimal nature;
    private Date dateProd;

    public ProdAnimal() {
        this.dateProd=new Date();
    }

    public ProdAnimal(NatureProdAnimal nature, Date dateProd, String refP, int qteStock, Double prix) {
        super(refP, qteStock, prix);
        this.nature = nature;
        this.dateProd=dateProd;
    }
    
    
 /*   @Override 
    public void saisir(Scanner sc){
        int count =1;
        int choixNat;
        super.saisir(sc);
        try{
            System.out.println("entrer la nature : ");
            for(NatureProdAnimal nat:NatureProdAnimal.values()){
                System.out.println(count+"- "+nat);
                count++;
            }
            choixNat=sc.nextInt();
            if(choixNat>5 || choixNat<1)
                throw new ValeurHorsIntervalleException("la nature doit etre entre 1 et 5");
            switch(choixNat){
                case 1 ->{nature = NatureProdAnimal.Lait;}
                case 2 ->{nature = NatureProdAnimal.Viande;}
                case 3 ->{nature = NatureProdAnimal.Apicole;}
                case 4 ->{nature = NatureProdAnimal.Oeuf;}
                case 5 ->{nature = NatureProdAnimal.Animal;}
            }
            System.out.println("entrer la date de production");
            this.dateProd.saisir(sc);
        }catch(InputMismatchException e){
            System.out.println("vous n'avez pas inséré un entier");  
        }catch(ValeurHorsIntervalleException ve){
            System.out.println(ve.getMessage());
        }
    }
    @Override 
    public void modifier(Scanner sc){
        super.modifier(sc);
           int count =1;
            int choixNat;
        try{
            System.out.println("entrer la nouvelle nature : ");
            for(NatureProdAnimal nat:NatureProdAnimal.values()){
                System.out.println(count+"- "+nat);
                count++;
            }
            choixNat=sc.nextInt();
            if(choixNat>5 || choixNat<1)
                throw new ValeurHorsIntervalleException("la nature doit etre entre 1 et 5");
            switch(choixNat){
                case 1 ->{nature = NatureProdAnimal.Lait;}
                case 2 ->{nature = NatureProdAnimal.Viande;}
                case 3 ->{nature = NatureProdAnimal.Apicole;}
                case 4 ->{nature = NatureProdAnimal.Oeuf;}
                case 5 ->{nature = NatureProdAnimal.Animal;}
            }
            System.out.println("entrer la nouvelle date de production");
            this.dateProd.saisir(sc);
        }catch(InputMismatchException e){
            System.out.println("vous n'avez pas inséré un entier");  
        }catch(ValeurHorsIntervalleException ve){
            System.out.println(ve.getMessage());
        }
    }*/
    @Override 
    public String toString(){
        
        return super.toString()+" nature : "+getNature()+" date Production : "+ getDateProd();
    }

    public NatureProdAnimal getNature() {
        return nature;
    }

    public void setNature(NatureProdAnimal nature) {
        this.nature = nature;
    }

    public Date getDateProd() {
        return dateProd;
    }

    public void setDateProd(Date dateProd) {
        this.dateProd = dateProd;
    }
    
    
}

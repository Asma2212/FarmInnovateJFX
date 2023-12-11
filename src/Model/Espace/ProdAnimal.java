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

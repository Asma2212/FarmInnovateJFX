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

public class Secteur {
    private int codeS ;
    private String nomSecteur ;
   // private String libelle ;
// ******************** Constructeurs ***********************//
    public Secteur() {
    }

    public Secteur(int codeS, String nomSecteur) {
        this.codeS = codeS;
        this.nomSecteur = nomSecteur;
    }
// ******************** CRUD ***********************//   
    public void saisir(Scanner sc){
        try{
        System.out.println("entrer le code secteur");
        this.codeS = sc.nextInt();
        System.out.println("entrer le nom du secteur");
        this.nomSecteur=sc.next();
        }catch(InputMismatchException e){
            System.out.println("vous n'avez pas inséré un entier");  
        }
        
    }
    public void modifier(Scanner sc){
        System.out.println("entrer le nouveau code du secteur");
        codeS=sc.nextInt();
        System.out.println("entrer le nouveau nom du secteur");
        nomSecteur=sc.next();
    }
    @Override
    public String toString(){
        return "\ncode Secteur : "+codeS+" nom Secteur : "+nomSecteur;
    }
// ******************** Manipulation ***********************//
    
    
// ******************** Getters et Setters ***********************//
    public int getCodeS() {
        return codeS;
    }

    public void setCodeS(int codeS) {
        this.codeS = codeS;
    }

    public String getNomSecteur() {
        return nomSecteur;
    }

    public void setNomSecteur(String nomSecteur) {
        this.nomSecteur = nomSecteur;
    }
    
    
}

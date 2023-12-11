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

public record Secteur(int codeS,String nomSecteur){

    public int getCodeS() {
        return codeS;
    }
    public String getNomSecteur() {
        return nomSecteur;
    }

    
    
}

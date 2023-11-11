/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Espace;

/**
 *
 * @author ADMIN
 */
public class LigneCommande {
    private Production prod;
    private int Qte;

    public LigneCommande() {
    }

    public LigneCommande(Production prod, int Qte) {
        this.prod = prod;
        this.Qte = Qte;
    }

    public Production getProd() {
        return prod;
    }

    public void setProd(Production prod) {
        this.prod = prod;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int Qte) {
        this.Qte = Qte;
    }
    
    
}

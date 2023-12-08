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
    private int qte;

    public LigneCommande() {
    }

    public LigneCommande(Production prod, int qte) {
        this.prod = prod;
        this.qte = qte;
    }

    public Production getProd() {
        return prod;
    }

    public void setProd(Production prod) {
        this.prod = prod;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
    
    
}

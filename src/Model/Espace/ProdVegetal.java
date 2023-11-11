/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Espace;

import Model.Date;

/**
 *
 * @author ADMIN
 */
public class ProdVegetal extends Production{
    private String nature ;
    private Date dateRecolte = new Date();
    private Date dateSemis = new Date();
    

    public ProdVegetal() {
    }

    public ProdVegetal(String nature, Date dateRecolte,Date dateSemis, int refP, int qteStock, Double prix) {
        super(refP, qteStock, prix);
        this.nature = nature;
        this.dateRecolte = dateRecolte;
        this.dateSemis = dateSemis;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Date getDateRecolte() {
        return dateRecolte;
    }

    public void setDateRecolte(Date dateRecolte) {
        this.dateRecolte = dateRecolte;
    }

    public Date getDateSemis() {
        return dateSemis;
    }

    public void setDateSemis(Date dateSemis) {
        this.dateSemis = dateSemis;
    }
    
    
}

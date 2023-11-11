package Model.Inventaire;

import java.util.Scanner;

import Model.Date;



public class Materiel {
    
    private String numM;
    private String libelle;
    private Date dateAchat;
    private int nbrMat;
    
    public Materiel(String numM, String libelle, Date dateAchat, int nbrMat){
       this.numM = numM;            
       this.libelle = libelle;      
       this.dateAchat = dateAchat;  
       this.nbrMat = nbrMat; 
    }
    public Materiel(){dateAchat=new Date();}
    
    // Getter and setter methods for instance variables
    public String getNumM() { return numM; }

    public void setNumM(String numM) { this.numM = numM; }

    public String getLibelle() { return libelle; }

    public void setLibelle(String libelle) { this.libelle = libelle; }

    public Date getDateAchat() { return dateAchat; }

    public void setDateAchat(Date dateAchat) { this.dateAchat = dateAchat; }

    public int getNbrMat() { return nbrMat; }

    public void setNbrMat(int nbrMat) { this.nbrMat = nbrMat; }
    
    //saisie d'un materiel
   public void saisir() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez le numéro du matériel: ");
        numM = sc.nextLine();
        System.out.print("Entrez le libellé: ");
        libelle = sc.nextLine();
        System.out.println("Entrez la date d'achat");
        dateAchat.saisir(sc);
        System.out.print("Entrez le nombre de matériel: ");
        nbrMat = sc.nextInt();
        }
   //modification d'un materiel
   public void modifier() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nouveau numéro de matériel (ou appuyez sur Entrée pour conserver l'ancien): ");
        String newNumM = sc.nextLine();
        if (!newNumM.isEmpty()) {
            numM = newNumM;
        }

        System.out.print("Nouveau libellé (ou appuyez sur Entrée pour conserver l'ancien): ");
        String newLibelle = sc.nextLine();
        if (!newLibelle.isEmpty()) {
            libelle = newLibelle;
        }

        System.out.print("Nouvelle date d'achat (ou appuyez sur Entrée pour conserver l'ancienne): ");
        String newDateInput = sc.nextLine();
        if (!newDateInput.isEmpty()) {
            dateAchat.saisir(new Scanner(newDateInput));
        }

        System.out.print("Nouveau nombre de matériel (ou -1 pour conserver l'ancien) : ");
        int newNbrMat = sc.nextInt();
        if (newNbrMat != -1) {
            nbrMat = newNbrMat;
        }
    }
   
   //toString
   @Override
    public String toString() {
        return "Numéro de matériel: " + numM + ", Libellé: " + libelle + ", Date d'achat: " + dateAchat + ", Nombre de matériel: " + nbrMat;
    }
}

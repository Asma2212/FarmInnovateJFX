package Model.Inventaire;

import Model.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Animal {
    
    private String idAnimal;
    private Espece espece;
    private String genre;
    private Date dateNaiss;
    private double poids;
    
 //***************************constructeur***************************
    
    public Animal(String idAnimal, Espece espece, String genre, Date dateNaiss, double poids){
        this.idAnimal=idAnimal;
        this.espece=espece;
        this.genre=genre;
        this.dateNaiss=dateNaiss;
        this.poids= poids;
    }
    
    public Animal(){this.dateNaiss=new Date();}
//***************************getters and setters***************************
   
    public String getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
    }
    
    public Espece getEspece() {
        return espece;
    }
    
    public void setEspece(Espece espece) {
        this.espece = espece;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date age) {
        this.dateNaiss = (Date) dateNaiss;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }
    
 /***************************Saisir***************************/ 
    public void saisir(){
        Scanner sc= new Scanner(System.in);
        try{
            System.out.println("Entrez l'identifiant");
            this.idAnimal="Animal"+sc.nextInt();
            System.out.println("Selectionnez une espece: ");
            for (Espece esp : Espece.values()) {
                System.out.println(esp.ordinal() + 1 + "." + esp);
            }
            int x = sc.nextInt();
            if (x >= 1 && x <= Espece.values().length) {
                this.espece = Espece.values()[x - 1];
                System.out.println("Vous avez selectinner: " + espece);
            } else {
                System.out.println("Selection invalide");
            }
            sc.nextLine();
            //Scanner sc1= new Scanner(System.in);
            do {
            System.out.println("Enterez 'F' for female or 'M' for male: ");
            this.genre = sc.nextLine().toUpperCase();
             } while (!"F".equals(this.genre) && !"M".equals(this.genre));        
            System.out.println("entrez la date de naissance ");
            this.dateNaiss.saisir(sc);
            System.out.println("entrez le poids en kilogramme");
            this.poids=sc.nextDouble();}
        catch(InputMismatchException e){
            System.out.println("La valeur entrée n'est pas valide.");
        }
    }
    
//************************Modifier***********************************
    public void modifier() {
        Scanner sc = new Scanner(System.in);
        try{
            System.out.println("Modification de l'animal");
            System.out.print("Nouvelle date de naissance (ou appuyez sur Entrée pour conserver l'ancienne): ");
            String newDateInput = sc.nextLine();
            if (!newDateInput.isEmpty()) {
                dateNaiss.saisir(new Scanner(newDateInput));
            }
            sc.nextLine();
             do {
            System.out.println("Entrez 'F' for female or 'M' for male: ");
            this.genre = sc.nextLine().toUpperCase();
            } while (!"F".equals(this.genre) && !"M".equals(this.genre));

            System.out.print("Nouvelle valeur du poids (ou -1 pour conserver l'ancienne) : ");
            double newpoids = sc.nextDouble();
            if (newpoids != -1) {
                poids = newpoids;}
            }
        catch(InputMismatchException e){
            System.out.println("La valeur entrée n'est pas valide.");
        }
    }
 /***************************toString***************************/ 
    @Override
    public String toString(){
        String str = "idAnimal: "+idAnimal+", Espece: ";

      if (genre != null) {
        if ("M".equals(genre)) {
            switch (espece) {
                case VACHE -> str += "TAUREAU";
                case POULE -> str += "COQ";
                case DINDE -> str += "DINDON";
                case CHEVRE -> str += "BOUC";
                case ABEILLE -> str += "FAUX BOURDON";
                default -> {
                }
            }
        } else if ("F".equals(genre)) {
            switch (espece) {
                case CHEVAL -> str += "JUMENT";
                case MOUTON -> str += "BREBIS";
                case LAPIN -> str += "LAPINE";
                default -> {str+=String.valueOf(espece);}
            }
        } 
        str+=", genre: " + genre + ", date de naissance: " + dateNaiss + ", poids: " + poids;
    }
        return str;
    }
    
    
}

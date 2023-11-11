package Model.Inventaire;

import Model.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public  class Programme {
    private String idProg;
    private String libelle;
    private Date dateApp;
    private String culture;
    private int qtProduit;
    private  int superficieTraite;
    
    // Constructeurs
    public Programme(String idProg, String libelle, Date dateApp, String culture,int qtProduit, int superficieTraite) {
        this.idProg = idProg;
        this.libelle = libelle;
        this.dateApp = dateApp;
        this.culture = culture;
        this.qtProduit=qtProduit;
        this.superficieTraite = superficieTraite;
    }
    public Programme(){}
    
    // Getters
    public String getIdProg() { return idProg; }

    public String getLibelle() { return libelle; }

    public Date getDateApp() { return dateApp; }

    public String getCulture() { return culture; }
    
    public int getQtProduit(){ return qtProduit; }

    public int getSuperficieTraite() { return superficieTraite; }

    // Setters
    public void setIdProg(String idProg) { this.idProg = idProg; }

    public void setLibelle(String libelle) { this.libelle = libelle; }

    public void setDateApp(Date dateApp) { this.dateApp = dateApp; }

    public void setCulture(String culture) { this.culture = culture; }
    
    public void setQtProduit(int qtProduit){ this.qtProduit=qtProduit; }

    public void setSuperficieTraite(int superficieTraite) { this.superficieTraite = superficieTraite; }
    
    //saisie du programme
    public void saisir() {
        Scanner sc = new Scanner(System.in);
        try{
            System.out.print("Entrez idProg: ");
            this.idProg = "prog"+sc.nextInt();

            System.out.println("Choisisez un programme ");
            int x;
            do{ 
                System.out.println("selectionnez une nature: 1.Phytosanitaire, 2.irrigation");
                x=sc.nextInt();}
            while(x != 1 && x != 2);
            if(x==1) this.libelle = "il sagit du programme Phytosanitaire";
            else this.libelle="il sagit du programme d'irrigation";
            
            sc.nextLine();
            System.out.print("Entrez culture: ");
            culture = sc.nextLine();

            System.out.print("Entrez superficieTraite: ");
            this.superficieTraite = sc.nextInt();}
        catch(InputMismatchException e){
            System.out.println("La valeur entrée n'est pas valide.");
        }
    }
    //calculer la quantite de produit recommende
    public double caluleQt(double superficie, double dose){
        return superficie*dose*10;
    }
    
    //modifier un programme
    public void modifier(){
        Scanner sc = new Scanner(System.in);
        try{

            System.out.println("Choisisez un programme ");
            int x;
            do{ 
                System.out.println("selectionnez une nature: 1.Phytosanitaire, 2.irrigation");
                x=sc.nextInt();}
            while(x != 1 && x != 2);
            if(x==1) this.libelle = "il sagit du programme Phytosanitaire";
            else this.libelle="il sagit du programme d'irrigation";
            
            sc.nextLine();
            System.out.print("Entrez culture: ");
            culture = sc.nextLine();

            System.out.print("Entrez superficieTraite: ");
            this.superficieTraite = sc.nextInt();}
        catch(InputMismatchException e){
            System.out.println("La valeur entrée n'est pas valide.");
        }
    }
    //toString 
    @Override
    public String toString(){
        String str="idProg: "+idProg+", libelle: "+libelle+"";
        return str;
    }
    
    
}

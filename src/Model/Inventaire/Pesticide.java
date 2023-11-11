package Model.Inventaire;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Pesticide extends Produit{

    private TypePesticide typepest;
    private String subActive;
    private ArrayList<String> cultures ;
    private ArrayList<String> ravageurs ;
    private int DAR;
    
    
    //constructeur
    public Pesticide(){}
    
    public Pesticide(TypePesticide typepest, String subActive, ArrayList<String> cultures,ArrayList<String> ravageurs ){
        super();
        this.typepest=typepest;
        this.subActive=subActive;
        this.cultures=cultures;
        this.ravageurs=ravageurs;
    }
    
    //Getters and Setters
    public TypePesticide getTypepest() { return typepest; }

    public void setTypepest(TypePesticide typepest) { this.typepest = typepest; }

    public String getSubActive() { return subActive; }

    public void setSubActive(String subActive) { this.subActive = subActive; }

    public ArrayList<String> getCultures() { return cultures; }
 
    public void setCultures(ArrayList<String> cultures) { this.cultures = cultures; }

    public ArrayList<String> getRavageurs() { return ravageurs; }

    public void setRavageurs(ArrayList<String> ravageurs) { this.ravageurs = ravageurs; }

    public int getDAR() { return DAR; }

    public void setDAR(int DAR) { this.DAR = DAR; }
    
    //saisie d'un pesticide
    @Override
    public void saisir() {
        Scanner sc = new Scanner(System.in);
        super.saisir();
        try{
            //saisie du type de pesticide
            System.out.println("selectionner le type de pesticide");
            String str="";
            for (TypePesticide type : TypePesticide.values()) {
                str+=type.ordinal() + 1 + "." + type+", ";
            }
            System.out.println(str);
            int x = sc.nextInt();
            if (x >= 1 && x <= TypePesticide.values().length) {
                this.setTypepest(TypePesticide.values()[x - 1]);
                System.out.println("Vous avez selectinner: " + getTypepest());
            } else {
                System.out.println("Selection invalide");
            }
            sc.nextLine();
            //saisie subActive
            System.out.println("entrez le subActive");
            this.setSubActive(sc.nextLine());
            //saisie des cultures à traite
            this.setCultures(inputList("cultures",sc));
            //saisie des ravageurs à traite
            this.setRavageurs(inputList("ravageurs",sc));
            System.out.println("Entrez le délai avant récolte (appuyez sur Entrée pour conserver l'ancienne) :");
            this.setDAR(sc.nextInt());}
        catch(InputMismatchException e){
            System.out.println("La valeur entrée n'est pas valide.");
        }
    }
    //modifier pesticide
    @Override
    public void modifier() {
        Scanner sc = new Scanner(System.in);
        super.modifier();

        System.out.println("Sélectionnez le type de pesticide(appyuez sur 9 pour conserver la valeur ancinne) :");
        String str = "";
        for (TypePesticide type : TypePesticide.values()) {
            str+=(type.ordinal() + 1) + ". " + type+", ";
        }
        System.out.println(str);
        int x = sc.nextInt();
        if (x >= 1 && x <= TypePesticide.values().length) {
            this.setTypepest(TypePesticide.values()[x - 1]);
            System.out.println("Vous avez sélectionné : " + getTypepest());
        } else {
            System.out.println("Sélection invalide. La valeur précédente sera conservée.");
        }
        sc.nextLine();

        System.out.println("Entrez le subActive :");
        String newSubActive = sc.nextLine();
        if (!newSubActive.isEmpty()) {
            this.setSubActive(newSubActive);
        }

        this.setCultures(inputList("culture", sc));

        this.setRavageurs(inputList("ravageur", sc));

        try {
            System.out.println("Entrez le délai avant récolte :");
            int newDAR = sc.nextInt();
            this.setDAR(newDAR);
        } catch (InputMismatchException e) {
            System.out.println("Entrée invalide pour le délai avant récolte. La valeur précédente sera conservée.");
                sc.nextLine();  
        }
    }
    
    //methode pour entrer une list 
    private ArrayList<String> inputList(String itemName, Scanner sc) {
        ArrayList<String> items = new ArrayList<>();
        char rep;

        do {
            System.out.println("Entrez un " + itemName + " (ou une lettre pour arrêter) :");
            String item = sc.nextLine().trim();
            if (!item.isEmpty()) {
                items.add(item);
            } 
            System.out.println("Voulez-vous ajouter un autre " + itemName + " (o/n) ?");
            rep = sc.next().charAt(0);
            sc.nextLine();
        } while (rep == 'o' || rep == 'O');

        return items;
    }
    
    //toString 
    @Override
    public String toString() {
        String str = super.toString() + ", typepest: " + getTypepest() + ", subActive: " + getSubActive() + ", cultures:[";

        // Concatenation des elements du tableau cultures
        Iterator<String> it = getCultures().iterator();
        while (it.hasNext()) {
            str += it.next();
            if (it.hasNext()) {
                str += ", "; 
            }
        }

        str += "], ravageurs:[ ";
        
        // Concatenation des elements du tableau ravageurs
        Iterator<String> it1 = getRavageurs().iterator();
        while (it1.hasNext()) {
            str += it1.next();
            if (it1.hasNext()) {
                str += ", "; 
            }
        }
        str += "], DAR: " + getDAR();
        return str;
    }

    
    
}

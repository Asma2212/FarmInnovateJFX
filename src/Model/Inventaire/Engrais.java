package Model.Inventaire;

import java.util.Scanner;


public class Engrais extends Produit{
    private TypeEngrais type;
    private String composition;
    
    
    //constructeur
    public Engrais(String idProduit, String formulation, String periodeApp, double doseRec, double qtNet, int nbrProd,TypeEngrais type, String composition){ 
        super(idProduit, formulation,  periodeApp, doseRec, qtNet, nbrProd);
        this.type=type;
        this.composition=composition;
    }
    public Engrais(){}
    
    public TypeEngrais getType() { return type; }

    public void setType(TypeEngrais type) {this.type = type; }

    public String getComposition() { return composition; }
    
    public void setComposition(String composition) { this.composition = composition;}
    
    //saisie d'un engrais
    @Override 
    public void saisir(){
        Scanner sc = new Scanner(System.in);
        super.saisir();
        //saisie du type d'engrais
        this.setType(input(sc,"."));
        sc.nextLine();
        System.out.println("entrez la composition");
        this.setComposition(sc.nextLine());
    }
    //modification d'un engrais
    @Override
    public void modifier(){
        super.modifier();
        Scanner sc = new Scanner(System.in);

        System.out.println("Sélectionnez le type d'engrais :");
        
        this.setType(input(sc,".(appuyez sur Entrée pour conserver l'ancienne):"));

        sc.nextLine();  // Consume the newline character

        System.out.println("Une nouvelle composition(appuyez sur Entrée pour conserver l'ancienne):  ");
        String newComposition = sc.nextLine();
        if (!newComposition.isEmpty()) {
            this.setComposition(newComposition);
        }
    }
    //methode pour enter le type d'engrais
    private TypeEngrais input(Scanner sc, String st){
        System.out.println("Sélectionnez le type d'engrais :");
        String str="";
       for (TypeEngrais t : TypeEngrais.values()) {
            str+=(t.ordinal() + 1) + ". " + t;
        }
        System.out.println(str);
        int x = sc.nextInt();
        TypeEngrais typ = null;
        if (x >= 1 && x <= TypeEngrais.values().length) {
             typ = TypeEngrais.values()[x - 1];
            System.out.println("Vous avez sélectionné : " + typ);
        } else {
            System.out.println("Sélection invalide"+st);
        }
        return typ;
    }
    
    
    @Override 
    public String toString(){
        return super.toString()+ "\n type: " + getType() + "\n composition: " + getComposition() ;
    }

}

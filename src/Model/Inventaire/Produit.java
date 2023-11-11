package Model.Inventaire;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Produit {
    
    private String idProduit;
    private String formulation;
    private String periodeApp;
    private double doseRec;
    private double qtNet;
    private int nbrProd;
    
    public Produit(String idProduit, String formulation, String periodeApp, double doseRec, double qtNet, int nbrProd){
        this.idProduit=idProduit;
        this.formulation=formulation;
        this.periodeApp=periodeApp;
        this.doseRec=doseRec;
        this.qtNet=qtNet;
        this.nbrProd=nbrProd;
    }
    public Produit() {
    }
    public String getIdProduit() { return idProduit; }

    public void setIdProduit(String idProduit) { this.idProduit = idProduit; }

    public String getFormulation() { return formulation; }

    public void setFormulation(String formulation) { this.formulation = formulation; }

    public String getPeriodeApp() { return periodeApp; }

    public void setPeriodeApp(String periodeApp) { this.periodeApp = periodeApp; }

    public double getDoseRec() { return doseRec; }

    public void setDoseRec(double doseRec) { this.doseRec = doseRec; }

    public double getQtNet() { return qtNet; }

    public void setQtNet(double qtNet) { this.qtNet = qtNet; }

    public int getNbrProd() { return nbrProd; }

    public void setNbrProd(int nbrProd) { this.nbrProd = nbrProd; }
    
    
    
    // Common input method
    private <T> T getInputWithRetain(String message, Scanner sc, T currentVal, InputParser<T> parser) {
        System.out.println(message);
        String input = sc.nextLine().trim();

        if (!input.isEmpty()) {
            input = input.replace(',', '.');
            try {
                return parser.parse(input);
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("La valeur entrée n'est pas valide. La valeur précédente a été conservée.");
            } catch (Exception ex) {
                Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }

        return currentVal;
    }

    public void saisir() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez l'identifiant du produit");
        this.setIdProduit("produit" + sc.nextLine());

        int y;
        do {
            y = getInputWithRetain("Sélectionnez une formulation : 1. liquide, 2. poudre, 3. granulé", sc, 0, Integer::parseInt);
        } while (y < 1 || y > 3);

        switch (y) {
            case 1 -> this.setFormulation("liquide");
            case 2 -> this.setFormulation("poudre");
            case 3 -> this.setFormulation("granulé");
        }

        System.out.println("entez la periode d'application");
        this.setPeriodeApp(sc.nextLine());

        this.setDoseRec((double) getInputWithRetain("Entrez la dose recommandée :", sc, 0.0, Double::parseDouble));

        this.setQtNet((double) getInputWithRetain("Entrez la quantité nette :", sc, 0.0, Double::parseDouble));

        this.setNbrProd((int) getInputWithRetain("Entrez le nombre de produit :", sc, 0, Integer::parseInt));
    }

    public void modifier() {
        Scanner sc = new Scanner(System.in);

        // saisie de la formulation
        int y = getInputWithRetain("Sélectionnez une formulation : 1. liquide, 2. poudre, 3. granulé (appuyez sur Entrée pour conserver l'ancienne) :", sc, 0, Integer::parseInt);

        switch (y) {
            case 1 -> this.setFormulation("liquide");
            case 2 -> this.setFormulation("poudre");
            case 3 -> this.setFormulation("granulé");
        }
        sc.nextLine(); 
        
        this.setPeriodeApp(getInputWithRetain("Entrez la période d'application (appuyez sur Entrée pour conserver l'ancienne) :", sc, this.getPeriodeApp(), String::new));
        
        this.setDoseRec((double) getInputWithRetain("Entrez la dose recommandée (appuyez sur Entrée pour conserver l'ancienne) :", sc, this.getDoseRec(), Double::parseDouble));
     
        this.setQtNet((double) getInputWithRetain("Entrez la quantité nette (appuyez sur Entrée pour conserver l'ancienne) :", sc, this.getQtNet(), Double::parseDouble));

        this.setNbrProd((int) getInputWithRetain("Entrez le nombre de produits (appuyez sur Entrée pour conserver l'ancienne) :", sc, this.getNbrProd(), Integer::parseInt));
    }


    @Override
    public String toString() {
        return "idProduit: " + getIdProduit() + ", formulation: " + getFormulation() + ", periode d'application: " + getPeriodeApp() + ", doseRec: " + getDoseRec() + ", qtNet: " + getQtNet() + ", nbrProd: " + getNbrProd();
    }

    

}

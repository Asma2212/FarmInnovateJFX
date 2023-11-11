package Model.Inventaire;

import java.util.ArrayList;
import java.util.Scanner;


public class Batiment {
    private String idBatiment;
    private String nomBatiment;
    private ArrayList<Object> list; 
    
    // Constructor without parameters
    public Batiment() {list = new ArrayList<>();}

    // Constructor with idBatiment and nomBatiment
    public Batiment(String idBatiment, String nomBatiment) {
        this.idBatiment = idBatiment;
        this.nomBatiment = nomBatiment; 
        list = new ArrayList<>();
    } 
    
    // Getters and Setters
    public String getIdBatiment(){ return idBatiment; }

    public void setIdBatiment(String idBatiment) { this.idBatiment = idBatiment; }

    public String getNomBatiment() { return nomBatiment; }

    public void setNomBatiment(String nomBatiment) { this.nomBatiment = nomBatiment; }
    
    public ArrayList<Object> getList() { return list; }

    public void setList(ArrayList<Object> list) {this.list = list; }
   
    //saisie batiment
    public void saisie(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter l'idBatiment: ");
        idBatiment = "bat"+sc.nextInt();
        sc.nextLine();
        System.out.print("Enter le nom du Batiment: ");
        nomBatiment = sc.nextLine();       
    }
    //modifier le batiment 
    public void modifier(){
        //nouveau nom du batiment 
       Scanner sc = new Scanner(System.in);
       System.out.print("nouveau nom du Batiment (appuiyer sur entrée pour conserver l'ancien) ");
       String nouvnomBat = sc.nextLine(); 
       if (!nouvnomBat.isEmpty()) {
            nomBatiment = nouvnomBat;
        }
        System.out.println("pour effacer la list existante enter un mot(appuiyer sur entrée pour conserver l'ancienne)");
        String input = sc.nextLine(); 
        if (!input.isEmpty()) {
             list.clear();
             System.out.println("la list est efface avec succés ");
         }
    }
    
    //ajouter des elements a la list 
    public void ajouter(Object obj) {
    if (list.isEmpty() || list.get(0).getClass().isInstance(obj)) {
        list.add(obj);
        System.out.println("Objet ajouté");
    } else {
        System.out.println("Objet non compatible avec les autres éléments de la liste");
     }
    }
    //supprimer un element de la list
    public void supprimer(Object obj){
        if(list.get(0).getClass().isInstance(obj) && list.contains(obj)){
            list.remove(obj);
            System.out.println("l'element a été supprimé avec succés");
        }
        else
            System.out.println("l'element a supprimer n'existe pas dans la list");
    }
    
    // toString 
     @Override
    public String toString() {
        String str="idBatiment: "+idBatiment+"\n"+"nomBatiment: "+nomBatiment+"\n"+"elements=[";
        for (Object element : list) {
            str+=element.toString()+"\n ";
        }
        str+="]";
        return str;
    }   
       
}
    


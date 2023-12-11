/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller.Inventaire;

import Model.Date;
import Model.Inventaire.Animal;
import Model.Inventaire.Batiment;
import Model.Inventaire.Espece;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abir
 */
public class BatimentAnimalController implements Initializable {

    @FXML
    private Label idLabel;
    @FXML
    private ComboBox<Espece> combobox;
    @FXML
    private RadioButton fembtn;
    @FXML
    private RadioButton malbtn;

    @FXML
    private DatePicker dateTf;
    @FXML
    private TextField poidsTf;
    @FXML
    private Button resetbtn;
    @FXML
    private Button savebtn;
    @FXML
    private TableView<Animal> animalTableView;
    @FXML
    private ImageView returnbtn;
    @FXML
    private TableColumn<Animal, String> idColonne;
    @FXML
    private TableColumn<Animal, Espece> especeColonne;
    @FXML
    private TableColumn<Animal, String> genreColonne;
    @FXML
    private TableColumn<Animal, Date> dateNaissColonne;
    @FXML
    private TableColumn<Animal, Double> poidsColonne;
    
    private ObservableList<Animal> animalList;
    
    private static int currentId = 1;
     
    private  Batiment batiment;
    @FXML
    private Text errorText;
    @FXML
    private MenuItem suppbtn;
    

     /**
     * This method accepts a batiment
     * @param batiment
     */
     public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
        ArrayList<Animal> animal = new ArrayList<>();
            for(Object ani :batiment.getList())
                animal.add((Animal) ani);
        animalList = FXCollections.observableArrayList(animal);
        animalTableView.setItems(animalList);
        
    }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         returnbtn.setOnMouseClicked(event -> returnBtnHandler());
         
         ObservableList<Espece> especes = FXCollections.observableArrayList();
         for(Espece esp : Espece.values()) {
                especes.add(esp);
            }
         combobox.setItems(especes);      
         idColonne.setCellValueFactory(new PropertyValueFactory<>("idAnimal"));
         especeColonne.setCellValueFactory(new PropertyValueFactory<>("espece"));   
         genreColonne.setCellValueFactory(new PropertyValueFactory<>("genre"));
         dateNaissColonne.setCellValueFactory(new PropertyValueFactory<>("dateNaiss"));   
         poidsColonne.setCellValueFactory(new PropertyValueFactory<>("poids"));
         
         
         animalTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        idLabel.setText(newValue.getIdAnimal());
                        combobox.setValue(newValue.getEspece()); 
                        dateTf.setValue(newValue.getDateNaiss().toLocalDate());
                        poidsTf.setText(String.valueOf(newValue.getPoids()));
                        String gender = newValue.getGenre();

                        if ("Femelle".equals(gender)) {
                            fembtn.setSelected(true);
                            malbtn.setSelected(false);
                        } else if ("Male".equals(gender)) {
                            fembtn.setSelected(false);
                            malbtn.setSelected(true);
                        } else {
                            fembtn.setSelected(false);
                            malbtn.setSelected(false);
                        }
                    }
                }); 

    }   

    @FXML
    private void saveBtnHandler(ActionEvent event) throws InvalidWeightException {
        Espece espece = combobox.getValue();
        String genre = fembtn.isSelected() ? fembtn.getText() : malbtn.getText();

        try{
            Double poids = null;
            String poidsText = poidsTf.getText();
            if (poidsText == null || poidsText.isEmpty()) {
                throw new InvalidWeightException("Entrez un poids valide!");
            }

            poids = Double.parseDouble(poidsText);
            LocalDate myDate = dateTf.getValue();
            if (myDate == null) {
                errorText.setText("Il faut remplir le champ date de naissance");
                return;
            }

            int year = myDate.getYear();
            int month = myDate.getMonthValue();
            int day = myDate.getDayOfMonth();
            Date date = new Date(day, month, year);

            // Valider la saisie
            if (!validationFields(espece, poids)) return;
            Animal selectedAnimal = animalTableView.getSelectionModel().getSelectedItem();

            if (selectedAnimal != null && animalTableView.getItems().contains(selectedAnimal)) {
                // mise a jour de l'objet animal selectionné
                selectedAnimal.setEspece(espece);
                selectedAnimal.setGenre(genre);
                selectedAnimal.setDateNaiss(date);
                selectedAnimal.setPoids(poids);
                updateAlert(selectedAnimal);
                animalTableView.refresh();
                animalTableView.getSelectionModel().clearSelection();
            } else {
                // Créer un nouveau Animal objet avec l'id generé
                Animal animal = new Animal("animal" + String.valueOf(currentId), espece, genre, date, poids);
                saveAlert(animal);
                animalTableView.getItems().add(animal);
                batiment.getList().add(animal);
                currentId++;
            }
            
            clearFields();}
            catch (NumberFormatException e) {
                errorAlert();
            } catch (InvalidWeightException e) {
                errorText.setText(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }
    
    private boolean validationFields(Espece espece, Double poids){
        if(espece ==null) {errorText.setText("Il faut selectionner un type!!!"); return false;}
        if(!fembtn.isSelected() && !malbtn.isSelected()){errorText.setText("Il faut selectionner le genre!!!"); return false;}
        return true;
    }
    
    @FXML
    private void resestBtnHandler(ActionEvent event) {
      clearFields();   
    }

    
    private void saveAlert(Animal animal){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Animal crée avec succès");
        alert.setHeaderText(null);
        alert.setContentText("l'id de cet animal"+animal.getEspece()+"est : "+animal.getIdAnimal());
        alert.showAndWait();
    }
    private void updateAlert(Animal animal){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Animal mis à jour avec succès");
        alert.setHeaderText(null);
        alert.setContentText("l'animal '"+animal.getIdAnimal()+"' a été mis à jour avec succès ");
        alert.showAndWait();
    }
    private void errorAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Le champ poids doit être un nombre valide");
        alert.showAndWait();
    }
    
    private void clearFields(){
        idLabel.setText("");  
       // combobox.getSelectionModel().clearSelection();
        fembtn.setSelected(false);
        malbtn.setSelected(false);
        dateTf.setValue(null);
        poidsTf.clear(); 
        errorText.setText("");
    }
    
    @FXML
    private void MouseClicked(MouseEvent event) {
        if (animalTableView.getSelectionModel().getSelectedItem() != null) {
        animalTableView.getSelectionModel().clearSelection();
        clearFields();
       }  
    }
    
    @FXML
    private void suppBtnHandler(ActionEvent event) {
        Animal selectedAnimal = animalTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("etes vous sure de supprimer cet element");
        Optional<ButtonType> action =alert.showAndWait();
        if(action.get() == ButtonType.OK){  
            animalTableView.getItems().remove(selectedAnimal);
            batiment.getList().remove(selectedAnimal);
        }
        clearFields();
    }  
    
    @FXML
    private void returnBtnHandler() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Inventaire/Batiments.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) returnbtn.getScene().getWindow();
            currentStage.setScene(scene);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public class InvalidWeightException extends Exception {
    public InvalidWeightException(String message) {
        super(message);
    }
}
    
}

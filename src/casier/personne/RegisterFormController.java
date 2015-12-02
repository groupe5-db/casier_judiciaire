/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.personne;

import casier.entities.Personne;
import casier.entities.UserSexe;
import casier.entities.UserSituationMatrimoniale;
import casier.entities.embed.Adresse;
import casier.services.PersonneResource;
import casier.services.Resource;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author armel
 */
public class RegisterFormController implements Initializable {
    
    @FXML
    private TextField acte_f;
    
    private String acte;
    
    @FXML
    private TextField nom_f;
    
    private String nom;
    
    @FXML
    private TextField prenom_f;
    
    private String prenom;
    
    @FXML
    private ComboBox<String> situation_f;
    
    private UserSituationMatrimoniale situation;
    
    @FXML
    private TextField pere_f;
    
    private String pere;
    
    @FXML
    private TextField mere_f;
    
    private String mere;
    
    @FXML
    private TextField profession_f;
    
    private String profession;
    
    @FXML
    private TextField domicile_f;
    
    private String domicile;
    
    @FXML
    private DatePicker date_f;
    
    private Date date;
    
    @FXML
    private TextField lieu_f;
    
    private String lieu;
    
    @FXML
    private ComboBox<String> sexe_f;
    
    private UserSexe sexe;
    
    @FXML
    private TextField nationalite_f;
    
    private String nationalite;
    
    @FXML
    private TextField departement_f;
    
    private String departement;
    
    @FXML
    private TextField arrondissement_f;
    
    private String arrondissement;
    
    @FXML
    private TextField ville_f;
    
    private String ville;
    
    @FXML
    private TextField quartier_f;
    
    private String quartier;
    
    @FXML
    private TextField bp_f;
    
    private String bp;
    
    @FXML
    private TextField teint_f;
    
    private String teint;
    
    @FXML
    private TextField cheveux_f;
    
    private String cheveux;
    
    @FXML
    private TextField taille_f;
    
    private Double taille;
    
    @FXML
    private TextField signe_f;
    
    private String signe;
    
    @FXML
    private TextField child_f;
    
    private Integer child;
    
    @FXML
    private TextField eye_f;
    
    private String eye;
    
    @FXML
    private VBox error;
    
    private Personne personne;
    
    private Adresse adresse;
    
    private final Resource personneRs = new PersonneResource();
    
    private List<String> errors = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void savePerson(){
        getProperties();
        
        if(validation()){
            setPersonValues();
            personneRs.add(personne);
        }else{
            for(String er : errors){
                Label lr = new Label(er);
                error.getChildren().add(lr);
            }
        }
        
    }
    
    private void getProperties(){
        
         
        
        
        acte = validString(acte_f.getText(), "Acte de naissance");
        nom = validString(nom_f.getText(), "Nom");
        prenom = prenom_f.getText();
        situation = UserSituationMatrimoniale.valueOf(situation_f.getValue());
        pere = validString(pere_f.getText(), "Nom du père");
        mere = validString(mere_f.getText(), "Nom de la mère");
        profession = validString(profession_f.getText(), "Profession");
        sexe = UserSexe.valueOf(sexe_f.getValue());
        domicile = validString(domicile_f.getText(), "Domicile");
        validDate(date_f.getValue());
        lieu = validString(lieu_f.getText(), "Lieu de naissance");
        nationalite = validString(nationalite_f.getText(), "Nationalité");
        departement = validString(departement_f.getText(), "Département d'origine");
        arrondissement = validString(arrondissement_f.getText(), "Arrondissement d'origine");
        ville = validString(ville_f.getText(), "Ville");
        quartier = validString(quartier_f.getText(), "Quartier");
        bp = bp_f.getText();
        teint = validString(teint_f.getText(), "Teint");
        cheveux = validString(cheveux_f.getText(), "Couleur des cheveux");
        taille = fomatStringToDouble(taille_f.getText());
        signe = signe_f.getText();
        child = fomatStringToInteger(taille_f.getText());
        eye = validString(eye_f.getText(), "Couleur des yeux");
        
        
    }
    
    private boolean validation(){
        
        return acte != null && nom != null && mere != null 
                && quartier != null && ville != null && departement != null
                && arrondissement != null && taille != null && child != null
                && date != null && eye != null;
    }
    
    private Double fomatStringToDouble(String taille){
        Double doub = null;
        try {
            doub = Double.valueOf(taille);
        } catch (NumberFormatException e) {
            System.out.println("Veuillez saisir un nombre correct");
            errors.add("Veuillez saisir un nombre correct dans le champ Taille");
            doub = null;
        }
        
        
        return doub;
    }
    
     private Integer fomatStringToInteger(String taille){
        Integer doub = null;
        try {
            doub = Integer.getInteger(taille);
        } catch (NumberFormatException e) {
            errors.add("Veuillez saisir un nombre correct dans le champ Nombre d'enfant");
            doub = null;
        }
        
        
        return doub;
    }
     
     private String validString(String value, String field){
         if(value == null || value.equals("")){
             errors.add("Le champ "+field+" est obligatoire");
             return null;
         }
         return value;
     }
     
     private void validDate(LocalDate date){
         if(date == null){
             errors.add("Le champ Date de naissance est obligatoire");
         }else{
         LocalDate local = date;
         Instant inst = Instant.from(local.atStartOfDay(ZoneId.systemDefault()));
         this.date = Date.from(inst);
         
     }
     }
     
     private void setPersonValues(){
         adresse.setBP(bp);
         adresse.setQuartier(quartier);
         adresse.setVille(ville);
         
         personne.setAdresse(adresse);
         personne.setArrondissement(arrondissement);
         personne.setCouleurDesCheveux(cheveux);
         personne.setCouleurDesYeux(eye);
         personne.setDateDeNaissance(date);
         personne.setDepartement(departement);
         personne.setLieuDeNaissance(lieu);
         personne.setNationalite(nationalite);
         personne.setNom(nom);
         personne.setNomDeLaMere(mere);
         personne.setNomDuPere(pere);
         personne.setNombreEnfant(child);
         personne.setPrenom(prenom);
         personne.setProfession(profession);
         personne.setSexe(sexe);
         personne.setSignesParticuliers(quartier);
         personne.setSituationMatrimoniale(situation);
         personne.setTaille(taille);
         personne.setTeint(teint);
         
     }
    
}

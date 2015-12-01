/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.pdf;

import casier.entities.Peine;
import casier.entities.Personne;
import casier.entities.embed.Adresse;
import casier.services.CasierInfosProvider;
import casier.services.CasierInfosProviderImpl;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author armel
 */
public class FormController implements Initializable {
    
    private Personne personne;
    
    private Adresse adr;
    
    private List<Peine> peines;
    
    CasierInfosProvider provider;
    
    @FXML
    private TextField acte_f;
    
    @FXML
    private TextField nom_f;
    
    @FXML
    private TextField prenom_f;
    
    @FXML
    private ComboBox<String> situation_f;
    
    @FXML
    private TextField pere_f;
    
    @FXML
    private TextField mere_f;
    
    @FXML
    private TextField profession_f;
    
    @FXML
    private TextField domicile_f;
    
    @FXML
    private DatePicker date_f;
    
    @FXML
    private TextField lieu_f;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO   
    }
    
    @FXML
    private void handleOpenForm(ActionEvent event){
        try {
            PdfViewer viewer = new PdfViewer();
            Stage stage = new Stage();
            stage.setTitle("Visualiseur de casier");
            setPersonneValues();
            viewer.run(stage, personne, peines);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void setPersonneValues(){
        provider = new CasierInfosProviderImpl(acte_f.getText());
        personne = provider.getPersonne();
        peines = provider.getPeines();
        
        if(personne == null){
            peines = null;
        personne = new Personne();
        adr = new Adresse();
        LocalDate local = date_f.getValue();
        Instant inst = Instant.from(local.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(inst);
        
        
        adr.setQuartier(domicile_f.getText() == null || domicile_f.getText().equals("")? null : domicile_f.getText());
        
        personne.setNumeroActe(acte_f.getText() == null || acte_f.getText().equals("")? null : acte_f.getText());
        personne.setNom(nom_f.getText() == null || nom_f.getText().equals("")? null : nom_f.getText());
        personne.setPrenom(prenom_f.getText() == null || prenom_f.getText().equals("")? null : prenom_f.getText());
        personne.setSituationMatrimoniale(situation_f.getValue() == null || situation_f.getValue().equals("")? null : situation_f.getValue());
        personne.setNomDuPere(pere_f.getText() == null || pere_f.getText().equals("")? null : pere_f.getText());
        personne.setNomDeLaMere(mere_f.getText() == null || mere_f.getText().equals("")? null : mere_f.getText());
        personne.setProfession(profession_f.getText() == null || profession_f.getText().equals("")? null : profession_f.getText());
        personne.setAdresse(adr);
        personne.setDateDeNaissance(date== null? null : date);
        personne.setLieuDeNaissance(lieu_f.getText() == null || lieu_f.getText().equals("")? null : lieu_f.getText());
        personne.setNationalite("Camerounaise");
        }
        
    }
    
}

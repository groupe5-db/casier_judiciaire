/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier;

import casier.pdf.Form;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author armel
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Form form = new Form();
        Stage stage = new Stage();
                try {
                    form.start(stage);
                    ((Node)event.getSource()).getScene().getWindow().hide();
                } catch (IOException ex) {
                    Logger.getLogger(Casier.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
    }
    
    @FXML
    private void openPersonRegisterForm(ActionEvent event) throws IOException {
        Parent root;
                try {
                        
                        root = FXMLLoader.load(getClass().getResource("personne/RegisterForm.fxml"));
                        Scene scene = new Scene(root, Double.MAX_VALUE, Double.MAX_VALUE);
                        Stage stage = new Stage();
                        stage.setTitle("Enregistrement");
                        stage.setScene(scene);
                        stage.show();
                    ((Node)event.getSource()).getScene().getWindow().hide();
                } catch (IOException ex) {
                    Logger.getLogger(Casier.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

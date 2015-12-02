/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.controller;

import java.net.URL;
import java.util.ResourceBundle;

import casier.services.LoginService;
import casier.util.enums.StringsUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author armel
 */
public class LoginController implements Initializable {

    @FXML
    private Button loginBtn;

    @FXML
    private TextField matricule;

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader

    @FXML
    private Button cancelBtn;

    @FXML
    private Label message;

    private LoginService loginService = LoginService.builder();

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'Login.fxml'.";
        assert matricule != null : "fx:id=\"matricule\" was not injected: check your FXML file 'Login.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'Login.fxml'.";
        assert cancelBtn != null : "fx:id=\"cancelBtn\" was not injected: check your FXML file 'Login.fxml'.";
        assert message != null : "fx:id=\"message\" was not injected: check your FXML file 'Login.fxml'.";
    }

    @FXML
    void onLogin(ActionEvent event) {
        String matricule = this.matricule.getText();
        String password = this.password.getText();

        if(StringsUtils.checkValidString(matricule) && StringsUtils.checkValidString(password)){
            String messageResponse = loginService.login(matricule, password);
            message.setText(messageResponse);
        }else {
            message.setText("Error in input parameters !" + "\n" + "Please Check It.");
        }
    }

}

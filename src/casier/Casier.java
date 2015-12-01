/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier;

import casier.DAO.UtilisateurDAO;
import casier.DAO.init.InitUsers;
import casier.entities.Utilisateur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

/**
 *
 * @author armel
 */
public class Casier extends Application {
    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ui/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UtilisateurDAO uDao = new UtilisateurDAO();
        List<Utilisateur> utilisateurs = uDao.findAll();
        // System.out.println(utilisateurs);

        if(utilisateurs.size() == 0){
            try {
                InitUsers.initUsers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        launch(args);
    }
    
}

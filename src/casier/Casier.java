/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier;

import casier.DAO.UtilisateurDAO;
import casier.DAO.init.InitUsers;
import casier.entities.Utilisateur;
import casier.pdf.Form;
import casier.pdf.PdfViewer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;

/**
 *
 * @author armel
 */
public class Casier extends Application {
    private static final String APP_TITLE = "Casier Judiciaire";

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ui/Login.fxml"));
        Button btn = new Button("casier");
//        HBox hbtn = new HBox(10);
//        hbtn.setAlignment(Pos.CENTER);
//        hbtn.getChildren().add(btn);
//        
//        GridPane grid = new GridPane();
//        
//        grid.setPadding(new Insets(5, 5, 5, 5));
//        grid.add(hbtn, 4, (int)((grid.getHeight()/2)+6));
        
        stage.setTitle(APP_TITLE);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
//                Form form = new Form();
//                
//                try {
//                    form.start(stage);
//                } catch (IOException ex) {
//                    Logger.getLogger(Casier.class.getName()).log(Level.SEVERE, null, ex);
//                }
}
        });

        Scene scene = new Scene(root, 300, 250);
        
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

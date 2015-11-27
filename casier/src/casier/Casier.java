/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier;

import casier.DAO.UtilisateurDAO;
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

/**
 *
 * @author armel
 */
public class Casier extends Application {
    
    private static final String APP_TITLE = "Casier Judiciaire";
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Button btn = new Button("casier");
        HBox hbtn = new HBox(10);
        hbtn.setAlignment(Pos.CENTER);
        hbtn.getChildren().add(btn);
        
        GridPane grid = new GridPane();
        
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(hbtn, 4, (int)((grid.getHeight()/2)+6));
        
        stage.setTitle(APP_TITLE);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                PdfViewer pdf = new PdfViewer();
                try {
                    pdf.run(stage, null, null);
//                if(resultat.getText().length() != 0)
//                    resultat.setText(null);
//                resultat.setFill(Color.FIREBRICK);
//                nom = (!(loginInput.getText().equals(null) || loginInput.getText().equals("")))? loginInput.getText() : "";
//                if(nom.equals(""))
//                    resultat.setText("Vous n'avez pas rempli le nom");
//                else
//                    resultat.setText(nom);
//                
//                System.out.println(nom);
                    //System.out.println("Hello World!");
                } catch (IOException ex) {
                    Logger.getLogger(Casier.class.getName()).log(Level.SEVERE, null, ex);
                }
}
        });

        
        Scene scene = new Scene(grid, 300, 250);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

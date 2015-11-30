/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier;

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
//                PdfViewer pdf = new PdfViewer();
//                try {
//                    pdf.run(stage, null, null); // Création d'un casier judiciaire vierge
//                } catch (IOException ex) {
//                    Logger.getLogger(Casier.class.getName()).log(Level.SEVERE, null, ex);
//                }
                
                Form form = new Form();
                
                try {
                    form.start(stage);
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

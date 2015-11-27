/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.pdf;

import casier.entities.Peine;
import casier.entities.Personne;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author armel
 */
public class PdfTemplate {
    
    private static final String REP_C_FR = "REPUBLIQUE DU CAMEROUN";
    private static final String REP_C_EN = "REPUBLIC OF CAMEROON";
    private static final String DEVISE_FR = "Paix - Travail - Patrie";
    private static final String DEVISE_EN = "Peace - Work - Fatherland";
    private static final String COURS_FR = "COURS D'APPEL DU LITTORAL";
    private static final String COURS_EN = "COURT OF APPEAL OF LITTORAL";
    private static final String TRIBUNAL_FR = "TRIBUNAL DE PREMIERE INSTANCE DE DOUALA-BONANJO";
    
    
    private static Document casier;
    private static String filename = "document.pdf";
    
    public static File getCasierLikePdf(Personne personne, List<Peine> peine){
        
        PdfTemplate.filename = personne.equals(null) ? PdfTemplate.filename : personne.getNom()+".pdf";
        
        casier = new Document();
        
        try {
            PdfWriter.getInstance(casier, new FileOutputStream(filename));
        } catch (FileNotFoundException | DocumentException e) {
            System.out.println("Erreur lors de la création du casier!");
            return null;
            
        }
        
        try {
            casier.open();
            casier.add(new Paragraph("Prototype de fichier casier judiciaire généré"));
            casier.close();
        } catch (DocumentException e) {
            System.out.println("Impossible d'ouvrir le fichier!");
            return null;
        }
        
        
        return new File(filename);
    }
    
    public static File getCasierLikePdf(String filename){
        
        PdfTemplate.filename = filename;
        
        casier = new Document();
        
        try {
            PdfWriter.getInstance(casier, new FileOutputStream(filename));
        } catch (FileNotFoundException | DocumentException e) {
            System.out.println("Erreur lors de la création du casier!");
            return null;
            
        }
        
        try {
            casier.open();
            casier.add(new Paragraph("Prototype de fichier casier judiciaire généré"));
            casier.close();
        } catch (DocumentException e) {
            System.out.println("Impossible d'ouvrir le fichier!");
            return null;
        }
        
        
        return new File(filename);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.pdf;

import casier.entities.Peine;
import casier.entities.Personne;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
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
    
    // HEADER
    private static final String REP_C_FR = "REPUBLIQUE DU CAMEROUN";
    private static final String REP_C_EN = "REPUBLIC OF CAMEROON";
    private static final String DEVISE_FR = "Paix - Travail - Patrie";
    private static final String DEVISE_EN = "Peace - Work - Fatherland";
    private static final String COURS_FR = "COURS D'APPEL DU LITTORAL";
    private static final String COURS_EN = "COURT OF APPEAL OF LITTORAL";
    private static final String TRIBUNAL_FR = "TRIBUNAL DE PREMIERE INSTANCE DE";
    private static final String TRIBUNAL_EN = "COURT OF FIRST INSTANCE OF";
    private static final String DOUALA = "DOUALA-BONANJO";
    private static final String CASIER_FR = "CASIER JUDICIAIRE";
    private static final String CASIER_EN = "CRIMINAL RECORD";
    private static final String NUM = "NO:";
    
    private static final String BULLETIN_FR = "BULLETIN NUMERO 3";
    private static final String BULLETIN_EN = "BULLETIN NUMBER 3";
    
    private static final String MSG_1_FR = "Relevé des condamnations à des peines privatives de liberté non éffacées par l'amnistie ou la réhabilitation "
            + "et des condamnations relatives à la circulation routière art. 581(1) (c)";
    private static final String MSG_1_EN = "Information sheet on imprisonment not expurged by amnesty or Rehabilitation and on penalties related to "
            + "traffic offences Sect. 581(1)(c)";
    
    // Informations of the concerning
    
    private static final String NAME_FR = "Concernant M.";
    private static final String NAME_EN = "Concerning M";
    private static final String FATHER_NAME_FR = "Fils/fille de";
    private static final String FATHER_NAME_EN = "Son/daugther of";
    private static final String MOTHER_NAME_FR = "et de";
    private static final String MOTHER_NAME_EN = "and of";
    private static final String BIRTHDAY_FR = "Né(e) le";
    private static final String BIRTHDAY_EN = "Burn on";
    private static final String BIRTHCITY_FR = "à";
    private static final String BIRTHCITY_EN = "at";
    private static final String RESIDENT_FR = "Domicilié(e) à";
    private static final String RESIDENT_EN = "Resident at";
    private static final String CIVIL_STATUS_FR = "Etat civil et de famille";
    private static final String CIVIL_STATUS_EN = "Civil status";
    private static final String PROFESSION_FR = "Profession";
    private static final String PROFESSION_EN = "Profession";
    private static final String NATIONALITE_FR = "Nationalité";
    private static final String NATIONALITE_EN = "Nationality";
    
    // Informations of the table of penalties
    
    private static final String CONDAMNATION_DAY_FR = "Date de(s) condamnations";
    private static final String CONDAMNATION_DAY_EN = "Date of conviction(s)";
    private static final String COURT_FR = "Cours ou tribunaux";
    private static final String COURT_EN = "Courts";
    private static final String OFFENCES_FR = "Nature des infractions";
    private static final String OFFENCES_EN = "Nature of offences";
    private static final String OFFENCES_DATE_FR = "Date précise de commission de(s) infraction(s)";
    private static final String OFFENCES_DATE_EN = "Precise date of commission of offence(s)";
    private static final String MANDAT_FR = "Date et nature de(s) mandat(s)";
    private static final String MANDAT_EN = "Date and type(s) of warrant(s)";
    private static final String OBSERVATIONS_FR = "Observations condamnation(s) relative à la circulation routière";
    private static final String OBSERVATIONS_EN = "Remarks convinctions of traffic offences";
    
    // Footer
    
    private static final String COST_FR = "Coût du bulletin";
    private static final String COST_EN = "Cost of bulletin";
    private static final String SEARCH_FR = "Recherche et rédaction";
    private static final String SEARCH_EN = "Search and drafting";
    private static final String STAMP_FR = "Timbre du tribunal";
    private static final String STAMP_EN = "Court's stamp";
    private static final String PRICE_FR = "Prix total";
    private static final String PRICE_EN = "Total cost";
    
    private static final String TODAY_FR = "Vu au parquet le ";
    private static final String TODAY_EN = "Approved by the legal Department on ";
    private static final String PROCUREUR_FR = "Le Procureur de la République";
    private static final String PROCUREUR_EN = "The State Counsel";
    
    private static final String EXTRACT_FR = "Pour extrait conforme";
    private static final String EXTRACT_EN = "Certified copy";
    private static final String GREFFIER_FR = "Le Greffier en Chef";
    private static final String GREFFIER_EN = "The registrar-in-chief";
    
    private static Document casier;
    private static String filename = "document.pdf";
    private static PdfWriter writer;
    private static PdfContentByte canvas;
    private static final Paragraph INTERLIGNE = new Paragraph(Chunk.NEWLINE);
    private static Paragraph bul_fr;
    private static Paragraph bul_en;
    private static Paragraph msg;
    private static final Font style = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);
    private static final Font style2 = new Font(Font.TIMES_ROMAN, 10, Font.NORMAL);
    private static final Font style3 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
    private static final Font style4 = new Font(Font.TIMES_ROMAN, 12, Font.BOLDITALIC);
    private static final Font style5 = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);
    
    public static File getCasierLikePdf(Personne personne, List<Peine> peine){
        
        PdfTemplate.filename = personne.equals(null) ? PdfTemplate.filename : personne.getNom()+".pdf";
        
        casier = new Document();
        
        try {
            writer = PdfWriter.getInstance(casier, new FileOutputStream(filename));
        } catch (FileNotFoundException | DocumentException e) {
            System.out.println("Erreur lors de la création du casier!");
            return null;
            
        }
        
        casier.open();
        setHeaderInformations();
        casier.close();
        
        
        return new File(filename);
    }
    
    public static File getCasierLikePdf(String filename){
        
        PdfTemplate.filename = filename;
        
        casier = new Document();
        
        try {
            writer = PdfWriter.getInstance(casier, new FileOutputStream(filename));
        } catch (FileNotFoundException | DocumentException e) {
            System.out.println("Erreur lors de la création du casier!");
            return null;
            
        }
        
        casier.open();
        setHeaderInformations();
        try {
            setBodyInformations();
        } catch (DocumentException e) {
        }
        casier.close();
        
        
        return new File(filename);
    }
    
    private static void setHeaderInformations(){
        canvas = writer.getDirectContentUnder();
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(REP_C_FR, style), casier.left() , casier.top(), 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase(REP_C_EN, style), casier.right() , casier.top(), 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(DEVISE_FR, style2), casier.left()+40, casier.top()-10, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase(DEVISE_EN, style2), casier.right()-20, casier.top()-10, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(COURS_FR, style), casier.left() , casier.top()-20, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase(COURS_EN, style), casier.right(), casier.top()-20, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(TRIBUNAL_FR, style), casier.left() , casier.top()-30, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(DOUALA, style), casier.left() , casier.top()-40, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase(TRIBUNAL_EN, style), casier.right(), casier.top()-30, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase(DOUALA, style), casier.right(), casier.top()-40, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(CASIER_FR, style), casier.left() , casier.top()-50, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase(CASIER_EN, style), casier.right(), casier.top()-50, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(NUM, style), casier.left() , casier.top()-70, 0);
        
    }
    
    private static void setBodyInformations() throws DocumentException{
        casier.add(INTERLIGNE);
        casier.add(INTERLIGNE);
        casier.add(INTERLIGNE);
        casier.add(INTERLIGNE);
        casier.add(INTERLIGNE);
        casier.add(INTERLIGNE);
        
        bul_fr = new Paragraph(new Chunk(BULLETIN_FR, style3));
        bul_en = new Paragraph(new Chunk(BULLETIN_EN, style4));
        
        bul_fr.setAlignment(Element.ALIGN_CENTER);
        bul_en.setAlignment(Element.ALIGN_CENTER);
        
        bul_fr.setSpacingAfter(-5);
        
        msg = new Paragraph(new Phrase(MSG_1_FR, style5));
        
        casier.add(bul_fr);
        casier.add(bul_en);
        casier.add(new Phrase(MSG_1_FR, style5));
        casier.add(new Phrase(MSG_1_EN, style5));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.pdf;

import casier.entities.Peine;
import casier.entities.Personne;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private static final String NAME_FR = "Concernant M. ";
    private static final String NAME_EN = "Concerning M";
    private static final String FATHER_NAME_FR = "Fils/fille de ";
    private static final String FATHER_NAME_EN = "Son/daugther of";
    private static final String MOTHER_NAME_FR = " et de ";
    private static final String MOTHER_NAME_EN = "and of";
    private static final String BIRTHDAY_FR = "Né(e) le ";
    private static final String BIRTHDAY_EN = "Burn on";
    private static final String BIRTHCITY_FR = " à ";
    private static final String BIRTHCITY_EN = "at";
    private static final String RESIDENT_FR = "Domicilié(e) à ";
    private static final String RESIDENT_EN = "Resident at";
    private static final String CIVIL_STATUS_FR = "Etat civil et de famille ";
    private static final String CIVIL_STATUS_EN = "Civil status";
    private static final String PROFESSION_FR = "Profession ";
    private static final String PROFESSION_EN = "Profession";
    private static final String NATIONALITE_FR = "Nationalité ";
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
    private static final Font style6 = new Font(Font.TIMES_ROMAN, 12, Font.ITALIC);
    private static final Font style7 = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
    private static final Font style8 = new Font(Font.TIMES_ROMAN, 8, Font.ITALIC);
    private static final Font style9 = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);
    
    private static PdfPTable table;
    private static PdfPCell date_c;
    private static PdfPCell court_c;
    private static PdfPCell nat_c;
    private static PdfPCell off_c;
    private static PdfPCell mandat_c;
    private static PdfPCell obs_c;
    
    private static Personne personne;
    private static List<Peine> peines;
    private static final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    
    public static File getCasierLikePdf(Personne personne, List<Peine> peines) {
        
        PdfTemplate.personne = personne;
        PdfTemplate.peines = peines;
        
        PdfTemplate.filename = personne == null ? PdfTemplate.filename : personne.getNom()+".pdf";
        
        casier = new Document();
        
        try {
            writer = PdfWriter.getInstance(casier, new FileOutputStream(filename));
        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println("Erreur lors de la création du casier!");
            return null;
            
        }
        
        casier.open();
        setHeaderInformations();
        try {
            setBodyInformations();
            setTableInformations();
        } catch (DocumentException e) {
        } catch (IOException ex) {
            Logger.getLogger(PdfTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
        setFooterInformations();
        casier.close();
        
        
        return new File(filename);
    }
    
    public static File getCasierLikePdf(String filename){
        
        PdfTemplate.filename = filename;
        
        casier = new Document();
        
        try {
            writer = PdfWriter.getInstance(casier, new FileOutputStream(filename));
        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("Erreur lors de la création du casier!");
            return null;
            
        }
        
        casier.open();
        setHeaderInformations();
        try {
            setBodyInformations();
            setTableInformations();
        } catch (DocumentException e) {
        } catch (IOException ex) {
            Logger.getLogger(PdfTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
        setFooterInformations();
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
        
        String parentSpacing = "";
        String birthSpacing = "";
        
        if(personne == null){
            parentSpacing = "                                 ";
            birthSpacing = "                                 ";
        }else{
            for(int i = 0; i<=(FATHER_NAME_FR.length()+personne.getNomDuPere().length()-FATHER_NAME_EN.length()); i++){
                parentSpacing +=" ";
            }
            for(int i = 0; i<=(BIRTHDAY_FR.length()+personne.getDateDeNaissance().toString().length()-BIRTHDAY_EN.length()); i++){
                birthSpacing +=" ";
            }
        }
        
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
        casier.add(msg);
        casier.add(new Phrase(MSG_1_EN, style5));
        
        casier.add(INTERLIGNE);
        
        casier.add(new Paragraph(personne == null? NAME_FR : NAME_FR+personne.getNom()+" "+personne.getPrenom(), style));
        casier.add(new Paragraph(NAME_EN, style6));
        
        casier.add(new Paragraph(personne == null? 
                FATHER_NAME_FR+ "                                 "+MOTHER_NAME_FR : 
                FATHER_NAME_FR+personne.getNomDuPere()+" "+MOTHER_NAME_FR+personne.getNomDeLaMere(), style));
        casier.add(new Paragraph(FATHER_NAME_EN+parentSpacing+MOTHER_NAME_EN, style6));
        
        casier.add(new Paragraph(personne == null? 
                BIRTHDAY_FR+ "                                 "+BIRTHCITY_FR : 
                BIRTHDAY_FR+format.format(personne.getDateDeNaissance())+" "+BIRTHCITY_FR+personne.getLieuDeNaissance(), style));
        casier.add(new Paragraph(BIRTHDAY_EN+birthSpacing+BIRTHCITY_EN, style6));
        
        casier.add(new Paragraph(personne == null? RESIDENT_FR : RESIDENT_FR+personne.getAdresse().getQuartier(), style));
        casier.add(new Paragraph(RESIDENT_EN, style6));
        
        casier.add(new Paragraph(personne == null? CIVIL_STATUS_FR : CIVIL_STATUS_FR+personne.getSituationMatrimoniale(), style));
        casier.add(new Paragraph(CIVIL_STATUS_EN, style6));
        
        casier.add(new Paragraph(personne == null? PROFESSION_FR : PROFESSION_FR+personne.getProfession(), style));
        casier.add(new Paragraph(PROFESSION_EN, style6));
        
        casier.add(new Paragraph(personne == null? NATIONALITE_FR : NATIONALITE_FR+personne.getNationalite(), style));
        casier.add(new Paragraph(NATIONALITE_EN, style6));
        
        casier.add(INTERLIGNE);
        casier.add(INTERLIGNE);
    }
    
    private static void setTableInformations() throws DocumentException, BadElementException, IOException{
        table = new PdfPTable(6);
        
        date_c = new PdfPCell(new Phrase(CONDAMNATION_DAY_FR+"      "+CONDAMNATION_DAY_EN, style7));
        court_c = new PdfPCell(new Phrase(COURT_FR+"      "+COURT_EN, style7));
        nat_c = new PdfPCell(new Phrase(OFFENCES_FR+"      "+OFFENCES_EN, style7));
        off_c = new PdfPCell(new Phrase(OFFENCES_DATE_FR+"      "+OFFENCES_DATE_EN, style7));
        mandat_c = new PdfPCell(new Phrase(MANDAT_FR+"      "+MANDAT_EN, style7));
        obs_c = new PdfPCell(new Phrase(OBSERVATIONS_FR+"      "+OBSERVATIONS_EN, style7));
        
        table.addCell(date_c);
        table.addCell(court_c);
        table.addCell(nat_c);
        table.addCell(off_c);
        table.addCell(mandat_c);
        table.addCell(obs_c);
        
        setPeines(peines);
        
        casier.add(table);
    }
    
    private static void setPeines(List<Peine> peines) throws BadElementException, IOException, DocumentException{
            Image img = Image.getInstance("neant.png");
            img.scaleAbsoluteWidth(300);
        img.scaleAbsoluteHeight(300);
        img.setAbsolutePosition((casier.getPageSize().getWidth()-img.getWidth()+casier.left()), ((casier.getPageSize().getHeight()-img.getHeight()+casier.bottom())/2));
            
        if(peines != null){
            if(peines.size()>0){
                peines.stream().forEach((peine) -> {
                    date_c = new PdfPCell(new Phrase(peine.getDateJugement().toString(), style));
                    court_c = new PdfPCell(new Phrase(peine.getNomCours(), style));
                    nat_c = new PdfPCell(new Phrase(peine.getInfraction().getNatureInfraction(), style));
                    off_c = new PdfPCell(new Phrase(peine.getInfraction().getDateInfraction().toString(), style));
                    mandat_c = new PdfPCell(new Phrase(peine.getCondamnation().getTypeDeMandat(), style));
                    obs_c = new PdfPCell(new Phrase(peine.getInfraction().getTexts(), style));
                    
                    table.addCell(court_c);
                    table.addCell(nat_c);
                    table.addCell(off_c);
                    table.addCell(mandat_c);
                    table.addCell(obs_c);
                });
            }
            else{
                    date_c = new PdfPCell(new Phrase("  "));
                    court_c = new PdfPCell(new Phrase("  "));
                    nat_c = new PdfPCell(new Phrase("  "));
                    off_c = new PdfPCell(new Phrase("  "));
                    mandat_c = new PdfPCell(new Phrase("  "));
                    obs_c = new PdfPCell(new Phrase("  "));
                    
                    table.addCell(date_c);
                    table.addCell(court_c);
                    table.addCell(nat_c);
                    table.addCell(off_c);
                    table.addCell(mandat_c);
                    table.addCell(obs_c);
                    
                    casier.add(img);
            }
        }
        else{
                    date_c = new PdfPCell(new Phrase("  "));
                    court_c = new PdfPCell(new Phrase("  "));
                    nat_c = new PdfPCell(new Phrase("  "));
                    off_c = new PdfPCell(new Phrase("  "));
                    mandat_c = new PdfPCell(new Phrase("  "));
                    obs_c = new PdfPCell(new Phrase("  "));
                    
                    table.addCell(date_c);
                    table.addCell(court_c);
                    table.addCell(nat_c);
                    table.addCell(off_c);
                    table.addCell(mandat_c);
                    table.addCell(obs_c);
                    
                    casier.add(img);
        }
    }
    
    private static void setFooterInformations(){
        Date d = new Date();
        
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(COST_FR, style9), casier.left() , casier.bottom()+50, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(COST_EN, style8), casier.left() , casier.bottom()+40, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(SEARCH_FR, style9), casier.left() , casier.bottom()+30, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(SEARCH_EN, style8), casier.left() , casier.bottom()+20, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(STAMP_FR, style9), casier.left() , casier.bottom()+10, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(STAMP_EN, style8), casier.left() , casier.bottom(), 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(PRICE_FR, style9), casier.left() , casier.bottom()-10, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(PRICE_EN, style8), casier.left() , casier.bottom()-20, 0);
        
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(TODAY_FR+format.format(d), style), casier.left()+200 , casier.bottom()+50, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(TODAY_EN, style6), casier.left()+200 , casier.bottom()+40, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(PROCUREUR_FR, style7), casier.left()+200 , casier.bottom()+20, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(PROCUREUR_EN, style2), casier.left()+220 , casier.bottom()+10, 0);
        
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase(EXTRACT_FR, style7), casier.right() , casier.bottom()+50, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase(EXTRACT_EN, style2), casier.right() , casier.bottom()+40, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase(GREFFIER_FR, style7), casier.right() , casier.bottom()+20, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, new Phrase(GREFFIER_EN, style2), casier.right() , casier.bottom()+10, 0);
    }
}

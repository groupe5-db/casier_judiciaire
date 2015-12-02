package casier.DAO.init;

import casier.DAO.PersonneDAO;
import casier.DAO.UtilisateurDAO;
import casier.entities.*;
import casier.entities.embed.Adresse;

import java.util.Date;

/**
 * Created by dassi on 01/12/15.
 */
public class InitUsers {
    private static UtilisateurDAO utilisateurDAO = UtilisateurDAO.builder();
    private static PersonneDAO personneDAO = PersonneDAO.builder();

    public static void initUsers() throws Exception {
        Personne personne = new Personne();
        personne.setNumeroActe("16465");
        personne.setNom("Admin");
        personne.setPrenom("admino");
        personne.setDateDeNaissance(new Date());
        personne.setLieuDeNaissance("Douala");
        personne.setArrondissement("Douala 5");
        personne.setDepartement("Wourri");
        personne.setNomDeLaMere("Admina Mother");
        personne.setSituationMatrimoniale(UserSituationMatrimoniale.CELIBATAIRE);
        personne.setNombreEnfant(5);
        personne.setAdresse(new Adresse("Douala", "Village", "ZR"));
        personne.setNationalite("CAMEROUNAISE");
        personne.setTaille(1.65);
        personne.setCouleurDesYeux("WHITE");
        personne.setCouleurDesCheveux("BLACK");
        personne.setTeint("FULL BLACK");
        personne.setSexe(UserSexe.MASCULIN);

        personneDAO.create(personne);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("admin@yahoo.fr");
        utilisateur.setMatricule("13I13529");
        utilisateur.setPassword("admin");
        utilisateur.setRole(UserRole.ADMIN);
        utilisateur.setTelephone("672252530");
        utilisateur.setFonction(UserFonction.OFFICE_MANAGER.name());
        utilisateur.setPersonne(personne);

        utilisateurDAO.create(utilisateur);

    }
}

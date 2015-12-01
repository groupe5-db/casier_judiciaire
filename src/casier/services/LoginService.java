package casier.services;

import casier.DAO.UtilisateurDAO;
import casier.entities.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by dassi on 01/12/15.
 */
public class LoginService {

    private UtilisateurDAO utilisateurDAO = UtilisateurDAO.builder();

    public static LoginService builder(){
        return new LoginService();
    }

    public String login(String matricule, String password){
        StringBuilder message = new StringBuilder();
        Utilisateur utilisateur = utilisateurDAO.findById(matricule);

        if(utilisateur == null){
            message.append("Successfull perform login");
        }else {
            message.append("know User with this matricule : " + matricule + "\n");
            String utilisateurPassword = utilisateur.getPassword();
            String encryptPwd = BCrypt.hashpw(password, Utilisateur.SALT); // TODO : Check PWD here !
            if(!utilisateurPassword.equals(encryptPwd)){
                message.append("But, Password don't match !");
            }
        }
        return message.toString();
    }

}

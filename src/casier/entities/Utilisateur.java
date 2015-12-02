/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author armel
 */
@Entity
public class Utilisateur extends BaseEntity{
    
    @Transient
    public static final String SALT = BCrypt.gensalt(12);

    @Id
    private String matricule;
    
    @NotNull
    private String password;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;
    
    private String fonction;
    
    private String email;
    
    @NotNull
    @Size(min = 9, max = 9)
    private String telephone;
    
    @NotNull
    @OneToOne(cascade = CascadeType.DETACH, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "numeroActe", referencedColumnName = "numeroActe")
    private Personne personne;
    
    @Version
    private int version;

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String id) {
        this.matricule = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, SALT);
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matricule != null ? matricule.hashCode() : 0);
        return hash;
    }

    @PostPersist
    public void  encryptPassword(){
        this.password = BCrypt.hashpw(this.password, SALT);
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        return (this.matricule != null || other.matricule == null) && (this.matricule == null || this.matricule.equals(other.matricule));
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "matricule='" + matricule + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", fonction='" + fonction + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", personne=" + personne +
                ", version=" + version +
                '}';
    }
}

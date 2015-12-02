/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.entities;

import casier.entities.embed.Condamnation;
import casier.entities.embed.Infraction;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 *
 * @author armel
 */
@Entity
public class Peine extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String nomCours;
    
    @NotNull
    private String nomTribunal;
    
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dateJugement;
    
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dateExpiratoinPeine;
    
    @Temporal(TemporalType.DATE)
    private Date datePayementAmende;
    
    //Décision de grace
    
//    @Column(columnDefinition = "Decision de grace")
    private String remiseDePeine;
    
//    @Column(columnDefinition = "Decret suivant lequel la remise de la peine a pu etre possible")
    private String numeroDecret;
    
    @Temporal(TemporalType.DATE)
//    @Column(columnDefinition = "Date du decret suivant lequel la remise da le peine a pu etre possible")
    private Date dateDecret;
    
    @Temporal(TemporalType.DATE)
//    @Column(columnDefinition = "Date de réhabilitation")
    private Date dateRehabilitation;
    
    @Temporal(TemporalType.DATE)
//    @Column(columnDefinition = "Date du jugement donnant droit à la rehabilitation")
    private Date dateJugementRehabilitation;
    
    @Embedded
    @NotNull
    private Condamnation condamnation;
    
    @Embedded
    @NotNull
    private Infraction infraction;
    
    @NotNull
    @JoinColumn(name = "numeroActe", referencedColumnName = "numeroActe")
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    private Personne personne;
    
    @NotNull
    @JoinColumn(name = "matriculeAgent", referencedColumnName = "matricule")
    @ManyToOne(cascade = CascadeType.MERGE, optional = false, fetch = FetchType.LAZY)
    private Utilisateur utilisateur;
    
    @Version
    private int version;

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public String getNomTribunal() {
        return nomTribunal;
    }

    public void setNomTribunal(String nomTribunal) {
        this.nomTribunal = nomTribunal;
    }

    public Date getDateJugement() {
        return dateJugement;
    }

    public void setDateJugement(Date dateJugement) {
        this.dateJugement = dateJugement;
    }

    public Date getDateExpiratoinPeine() {
        return dateExpiratoinPeine;
    }

    public void setDateExpiratoinPeine(Date dateExpiratoinPeine) {
        this.dateExpiratoinPeine = dateExpiratoinPeine;
    }

    public Date getDatePayementAmende() {
        return datePayementAmende;
    }

    public void setDatePayementAmende(Date datePayementAmende) {
        this.datePayementAmende = datePayementAmende;
    }

    public String getRemiseDePeine() {
        return remiseDePeine;
    }

    public void setRemiseDePeine(String remiseDePeine) {
        this.remiseDePeine = remiseDePeine;
    }

    public String getNumeroDecret() {
        return numeroDecret;
    }

    public void setNumeroDecret(String numeroDecret) {
        this.numeroDecret = numeroDecret;
    }

    public Date getDateDecret() {
        return dateDecret;
    }

    public void setDateDecret(Date dateDecret) {
        this.dateDecret = dateDecret;
    }

    public Date getDateRehabilitation() {
        return dateRehabilitation;
    }

    public void setDateRehabilitation(Date dateRehabilitation) {
        this.dateRehabilitation = dateRehabilitation;
    }

    public Date getDateJugementRehabilitation() {
        return dateJugementRehabilitation;
    }

    public void setDateJugementRehabilitation(Date dateJugementRehabilitation) {
        this.dateJugementRehabilitation = dateJugementRehabilitation;
    }

    public Condamnation getCondamnation() {
        return condamnation;
    }

    public void setCondamnation(Condamnation condamnation) {
        this.condamnation = condamnation;
    }

    public Infraction getInfraction() {
        return infraction;
    }

    public void setInfraction(Infraction infraction) {
        this.infraction = infraction;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setAgent(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peine)) {
            return false;
        }
        Peine other = (Peine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "casier.entities.Peine[ id=" + id + " ]";
    }
    
}

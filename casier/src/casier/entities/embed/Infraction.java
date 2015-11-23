/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.entities.embed;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author armel
 */
@Embeddable
public class Infraction implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotNull
    private String qualificationInfraction;
    
    @NotNull
    private String natureInfraction;
    
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dateInfraction;
    
    private String texts;
    
//    @Column(columnDefinition = "antecedant")
    private String recidive;
    
    private String voiesDeRecours;

    public String getQualificationInfraction() {
        return qualificationInfraction;
    }

    public void setQualificationInfraction(String qualificationInfraction) {
        this.qualificationInfraction = qualificationInfraction;
    }

    public String getNatureInfraction() {
        return natureInfraction;
    }

    public void setNatureInfraction(String natureInfraction) {
        this.natureInfraction = natureInfraction;
    }

    public Date getDateInfraction() {
        return dateInfraction;
    }

    public void setDateInfraction(Date dateInfraction) {
        this.dateInfraction = dateInfraction;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    public String getRecidive() {
        return recidive;
    }

    public void setRecidive(String recidive) {
        this.recidive = recidive;
    }

    public String getVoiesDeRecours() {
        return voiesDeRecours;
    }

    public void setVoiesDeRecours(String voiesDeRecours) {
        this.voiesDeRecours = voiesDeRecours;
    }

    @Override
    public int hashCode() {
        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Infraction)) {
            return false;
        }
        Infraction other = (Infraction) object;
        return (this.qualificationInfraction.equals(other.qualificationInfraction)) && 
                (this.natureInfraction.equals(other.natureInfraction)) &&
                (this.dateInfraction.equals(other.dateInfraction)) &&
                (this.texts.equals(other.texts)) &&
                (this.recidive.equals(other.recidive)) &&
                (this.voiesDeRecours.equals(other.voiesDeRecours));
    }

    @Override
    public String toString() {
        return natureInfraction+" : "+dateInfraction;
    }
    
}

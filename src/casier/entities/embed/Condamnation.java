/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.entities.embed;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author armel
 */
@Embeddable
public class Condamnation implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @NotNull
    private String typeEmprisonnement;
    
    @NotNull
    private String typeDeMandat;
    
    private String mainLevee;
    
    @NotNull
    private String typeAmende;
    
    private Double prixAmende;

    public String getTypeEmprisonnement() {
        return typeEmprisonnement;
    }

    public void setTypeEmprisonnement(String typeEmprisonnement) {
        this.typeEmprisonnement = typeEmprisonnement;
    }

    public String getTypeDeMandat() {
        return typeDeMandat;
    }

    public void setTypeDeMandat(String typeDeMandat) {
        this.typeDeMandat = typeDeMandat;
    }

    public String getMainLevee() {
        return mainLevee;
    }

    public void setMainLevee(String mainLevee) {
        this.mainLevee = mainLevee;
    }

    public String getTypeAmende() {
        return typeAmende;
    }

    public void setTypeAmende(String typeAmende) {
        this.typeAmende = typeAmende;
    }

    public Double getPrixAmende() {
        return prixAmende;
    }

    public void setPrixAmende(Double prixAmende) {
        this.prixAmende = prixAmende;
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
        if (!(object instanceof Condamnation)) {
            return false;
        }
        Condamnation other = (Condamnation) object;
        return (this.typeEmprisonnement.equals(other.typeEmprisonnement)) && 
                (this.typeDeMandat.equals(other.typeDeMandat)) &&
                (this.mainLevee.equals(other.mainLevee)) &&
                (this.typeAmende.equals(other.typeAmende)) &&
                (this.prixAmende.equals(other.prixAmende));
    }

    @Override
    public String toString() {
        return typeEmprisonnement+" Amende : "+prixAmende;
    }
    
}

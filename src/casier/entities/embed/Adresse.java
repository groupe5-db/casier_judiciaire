/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.entities.embed;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author armel
 */
@Embeddable
public class Adresse implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false)
    private String ville;
    
    @Column(nullable = false)
    private String quartier;
    
    private String BP;

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getBP() {
        return BP;
    }

    public void setBP(String BP) {
        this.BP = BP;
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
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        return (this.ville.equals(other.ville)) && (this.quartier.equals(other.quartier) && (this.BP.equals(other.BP)));
    }

    @Override
    public String toString() {
        return "Ville : "+ville+" Quartier : "+quartier+" BP : "+BP;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casier.entities;

import casier.entities.embed.Adresse;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author armel
 */
@Entity
public class Personne extends BaseEntity {
    @Id
    private String numeroActe;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String nom;
    
    @Size(min = 3, max = 50)
    private String prenom;
    
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date dateDeNaissance;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String lieuDeNaissance;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String arrondissement;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String departement;

    @Size(min = 3, max = 50)
    private String nomDuPere;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String nomDeLaMere;
    
    @Size(min = 3, max = 50)
    private String profession;
    
    @NotNull
    @Size(min = 3, max = 50)
    @Enumerated(EnumType.STRING)
    private UserSituationMatrimoniale situationMatrimoniale;
    
    @NotNull
    private int nombreEnfant;
    
    @NotNull
    @Embedded
    private Adresse adresse;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String nationalite;
    
    @NotNull
    private double taille;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String couleurDesYeux;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String couleurDesCheveux;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String teint;
    
    @NotNull
    @Size(min = 1, max = 9)
    @Enumerated(EnumType.STRING)
    private UserSexe sexe;
    
    private String signesParticuliers;
    

    public String getNumeroActe() {
        return numeroActe;
    }

    public void setNumeroActe(String id) {
        this.numeroActe = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public String getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getNomDuPere() {
        return nomDuPere;
    }

    public void setNomDuPere(String nomDuPere) {
        this.nomDuPere = nomDuPere;
    }

    public String getNomDeLaMere() {
        return nomDeLaMere;
    }

    public void setNomDeLaMere(String nomDeLaMere) {
        this.nomDeLaMere = nomDeLaMere;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public UserSituationMatrimoniale getSituationMatrimoniale() {
        return situationMatrimoniale;
    }

    public void setSituationMatrimoniale(UserSituationMatrimoniale situationMatrimoniale) {
        this.situationMatrimoniale = situationMatrimoniale;
    }

    public int getNombreEnfant() {
        return nombreEnfant;
    }

    public void setNombreEnfant(int nombreEnfant) {
        this.nombreEnfant = nombreEnfant;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public String getCouleurDesYeux() {
        return couleurDesYeux;
    }

    public void setCouleurDesYeux(String couleurDesYeux) {
        this.couleurDesYeux = couleurDesYeux;
    }

    public String getCouleurDesCheveux() {
        return couleurDesCheveux;
    }

    public void setCouleurDesCheveux(String couleurDesCheveux) {
        this.couleurDesCheveux = couleurDesCheveux;
    }

    public String getTeint() {
        return teint;
    }

    public void setTeint(String teint) {
        this.teint = teint;
    }

    public UserSexe getSexe() {
        return sexe;
    }

    public void setSexe(UserSexe sexe) {
        this.sexe = sexe;
    }

    public String getSignesParticuliers() {
        return signesParticuliers;
    }

    public void setSignesParticuliers(String signesParticuliers) {
        this.signesParticuliers = signesParticuliers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroActe != null ? numeroActe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        return (this.numeroActe != null || other.numeroActe == null) && (this.numeroActe == null || this.numeroActe.equals(other.numeroActe));
    }

    @Override
    public String toString() {
        return nom+" "+prenom+" numero acte de naissance: "+numeroActe;
    }
    
}

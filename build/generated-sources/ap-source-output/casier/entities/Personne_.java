package casier.entities;

import casier.entities.embed.Adresse;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Personne.class)
public abstract class Personne_ {

	public static volatile SingularAttribute<Personne, String> profession;
	public static volatile SingularAttribute<Personne, String> nationalite;
	public static volatile SingularAttribute<Personne, String> numeroActe;
	public static volatile SingularAttribute<Personne, UserSituationMatrimoniale> situationMatrimoniale;
	public static volatile SingularAttribute<Personne, String> departement;
	public static volatile SingularAttribute<Personne, String> couleurDesYeux;
	public static volatile SingularAttribute<Personne, String> arrondissement;
	public static volatile SingularAttribute<Personne, String> lieuDeNaissance;
	public static volatile SingularAttribute<Personne, UserSexe> sexe;
	public static volatile SingularAttribute<Personne, String> teint;
	public static volatile SingularAttribute<Personne, String> nom;
	public static volatile SingularAttribute<Personne, Date> dateDeNaissance;
	public static volatile SingularAttribute<Personne, Double> taille;
	public static volatile SingularAttribute<Personne, String> nomDuPere;
	public static volatile SingularAttribute<Personne, String> couleurDesCheveux;
	public static volatile SingularAttribute<Personne, String> signesParticuliers;
	public static volatile SingularAttribute<Personne, Adresse> adresse;
	public static volatile SingularAttribute<Personne, String> nomDeLaMere;
	public static volatile SingularAttribute<Personne, String> prenom;
	public static volatile SingularAttribute<Personne, Integer> nombreEnfant;

}


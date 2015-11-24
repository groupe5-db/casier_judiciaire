package casier.entities;

import casier.entities.embed.Condamnation;
import casier.entities.embed.Infraction;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Peine.class)
public abstract class Peine_ {

	public static volatile SingularAttribute<Peine, Date> datePayementAmende;
	public static volatile SingularAttribute<Peine, Date> dateJugementRehabilitation;
	public static volatile SingularAttribute<Peine, Personne> personne;
	public static volatile SingularAttribute<Peine, String> nomCours;
	public static volatile SingularAttribute<Peine, Date> dateExpiratoinPeine;
	public static volatile SingularAttribute<Peine, Integer> version;
	public static volatile SingularAttribute<Peine, Date> dateDecret;
	public static volatile SingularAttribute<Peine, String> remiseDePeine;
	public static volatile SingularAttribute<Peine, Date> dateRehabilitation;
	public static volatile SingularAttribute<Peine, Infraction> infraction;
	public static volatile SingularAttribute<Peine, Condamnation> condamnation;
	public static volatile SingularAttribute<Peine, Utilisateur> utilisateur;
	public static volatile SingularAttribute<Peine, String> nomTribunal;
	public static volatile SingularAttribute<Peine, Integer> id;
	public static volatile SingularAttribute<Peine, String> numeroDecret;
	public static volatile SingularAttribute<Peine, Date> dateJugement;

}


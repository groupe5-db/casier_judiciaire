package casier.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Utilisateur.class)
public abstract class Utilisateur_ {

	public static volatile SingularAttribute<Utilisateur, String> password;
	public static volatile SingularAttribute<Utilisateur, UserRole> role;
	public static volatile SingularAttribute<Utilisateur, String> matricule;
	public static volatile SingularAttribute<Utilisateur, Personne> personne;
	public static volatile SingularAttribute<Utilisateur, String> fonction;
	public static volatile SingularAttribute<Utilisateur, String> telephone;
	public static volatile SingularAttribute<Utilisateur, Integer> version;
	public static volatile SingularAttribute<Utilisateur, String> email;

}


package persistencia;


import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import persistencia.Singleton;


public class SingletonConexion {
	protected Singleton singleton;
	protected static PersistenceManagerFactory pmf;

	/**
	 * Constructor de la clase que nos garantiza que sea una unica instancia a
	 * traves del singleton.
	 */
	public SingletonConexion() {
		singleton = Singleton.getInstance();
		pmf = JDOHelper.getPersistenceManagerFactory("MYSQL");
	}

	public static PersistenceManagerFactory getPmf() {
		return pmf;
	}

	public static void setPmf(PersistenceManagerFactory pmf) {
		SingletonConexion.pmf = pmf;
	}

}

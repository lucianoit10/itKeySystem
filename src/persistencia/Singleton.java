package persistencia;

public class Singleton {

	/**
	 * Constructor de la clase.
	 */
	private Singleton() {
	}

	/**
	 * Obtiene una instancia unica de la base de datos.
	 */
	public static Singleton getInstance() {
		return NewSingletonHolder.INSTANCE;
	}

	/**
	 * Crea una nueva instancia de la clase Singleton.
	 */
	private static class NewSingletonHolder {
		private static final Singleton INSTANCE = new Singleton();
	}
}

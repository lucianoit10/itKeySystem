package persistencia.dominio;


public class Version{
	protected Long id;
	protected Producto producto; 
	protected String version;
	protected String keyDecript;
	protected Boolean tiene_posterior = false;
	protected Boolean eliminada = false;
	
	public Version(Producto producto, String version, String keyDecript) {
		super();
		this.producto = producto;
		this.version = version;
		this.keyDecript = keyDecript;
	}

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public String getKeyDecript() {
		return keyDecript;
	}

	public void setKeyDecript(String keyDecript) {
		this.keyDecript = keyDecript;
	}

	public Boolean getTiene_posterior() {
		return tiene_posterior;
	}

	public void setTiene_posterior(Boolean tiene_posterior) {
		this.tiene_posterior = tiene_posterior;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEliminada() {
		return eliminada;
	}

	public void setEliminada(Boolean eliminada) {
		this.eliminada = eliminada;
	}
	
}

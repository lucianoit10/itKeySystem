package persistencia.dominio;

public class Sistema {
	protected Long id;
	protected String nombre;
	protected String url;
	protected String ip;
	protected String pathLogo;
	
	public Sistema(Long id, String nombre, String url, String ip,String pathLogo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.url = url;
		this.ip = ip;
		this.pathLogo = pathLogo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPathLogo() {
		return pathLogo;
	}

	public void setPathLogo(String pathLogo) {
		this.pathLogo = pathLogo;
	}
	
}

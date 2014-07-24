package persistencia.dominio;

public class Maquina {
	protected Long id;
	protected String mac;
	protected String ip;
	protected Boolean baneado = false;
	protected Boolean eliminada = false;		
	
	public Maquina(String mac, String ip) {
		super();
		this.mac = mac;
		this.ip = ip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Boolean getBaneado() {
		return baneado;
	}

	public void setBaneado(Boolean baneado) {
		this.baneado = baneado;
	}

	public Boolean getEliminada() {
		return eliminada;
	}

	public void setEliminada(Boolean eliminada) {
		this.eliminada = eliminada;
	}
	
}

package utils;

public class Mensaje_para_validar {

	private byte[] key;
	private String id_copia;
	private String mac;
	
	public Mensaje_para_validar(byte[] key, String id_copia, String mac) {
		this.key = key;
		//equivalente al id de relacion de la persona y el producto
		this.id_copia = id_copia; 
		this.mac = mac;
	}

	public byte[] getKey() {
		return key;
	}

	public void setKey(byte[] key) {
		this.key = key;
	}

	public String getId_copia() {
		return id_copia;
	}

	public void setId_copia(String id_copia) {
		this.id_copia = id_copia;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	
}

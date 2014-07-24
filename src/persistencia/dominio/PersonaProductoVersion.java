package persistencia.dominio;

import java.sql.Timestamp;

public class PersonaProductoVersion {
	protected Long id;
	protected Persona propietario;
	protected Version producto_version;
	protected Timestamp fecha_de_adquisicion;
	protected Timestamp fecha_de_caducacion;
	protected Boolean baneada = false;
	protected Boolean fuera_circulacion = false;	
	
	public PersonaProductoVersion(Persona propietario, Version producto,
			Timestamp fecha_de_adquisicion) {
		super();
		this.propietario = propietario;
		this.producto_version = producto;
		this.fecha_de_adquisicion = fecha_de_adquisicion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Persona getPropietario() {
		return propietario;
	}

	public void setPropietario(Persona propietario) {
		this.propietario = propietario;
	}

	public Timestamp getFecha_de_adquisicion() {
		return fecha_de_adquisicion;
	}

	public void setFecha_de_adquisicion(Timestamp fecha_de_adquisicion) {
		this.fecha_de_adquisicion = fecha_de_adquisicion;
	}

	public Timestamp getFecha_de_caducacion() {
		return fecha_de_caducacion;
	}

	public void setFecha_de_caducacion(Timestamp fecha_de_caducacion) {
		this.fecha_de_caducacion = fecha_de_caducacion;
	}

	public Version getProducto_version() {
		return producto_version;
	}

	public void setProducto_version(Version producto_version) {
		this.producto_version = producto_version;
	}

	public Boolean getBaneada() {
		return baneada;
	}

	public void setBaneada(Boolean baneada) {
		this.baneada = baneada;
	}

	public Boolean getFuera_circulacion() {
		return fuera_circulacion;
	}

	public void setFuera_circulacion(Boolean fuera_circulacion) {
		this.fuera_circulacion = fuera_circulacion;
	}
	
	
}

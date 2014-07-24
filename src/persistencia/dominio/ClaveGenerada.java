package persistencia.dominio;

import java.sql.Timestamp;

public class ClaveGenerada {
	protected Long id;
	protected Clave datos;
	protected String clave;
	protected Timestamp fecha_de_generacion;
	
	public ClaveGenerada(Clave datos, String clave,
			Timestamp fecha_de_generacion) {
		super();
		this.id = id;
		this.datos = datos;
		this.clave = clave;
		this.fecha_de_generacion = fecha_de_generacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Clave getDatos() {
		return datos;
	}

	public void setDatos(Clave datos) {
		this.datos = datos;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Timestamp getFecha_de_generacion() {
		return fecha_de_generacion;
	}

	public void setFecha_de_generacion(Timestamp fecha_de_generacion) {
		this.fecha_de_generacion = fecha_de_generacion;
	}
	
}

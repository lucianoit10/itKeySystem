package persistencia.dominio;

import java.sql.Timestamp;

public class Clave {

	protected Long id;
	protected PersonaProductoVersion persona_producto;
	protected PersonaMaquina persona_maquina;
	protected String clave;
	protected Boolean activa = false;
	protected Timestamp fecha_de_generacion;
	protected int cant_de_activaciones =0;
	protected int cant_de_generaciones =0;
	protected Boolean baneado = false;
	protected Boolean eliminado = false;
	
	public Clave(PersonaProductoVersion persona_producto, PersonaMaquina persona_maquina, 
			String clave, Timestamp fecha_de_generacion) {
		this.persona_producto = persona_producto;
		this.persona_maquina = persona_maquina;
		this.clave = clave;
		this.fecha_de_generacion = fecha_de_generacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonaMaquina getPersona_maquina() {
		return persona_maquina;
	}

	public void setPersona_maquina(PersonaMaquina persona_maquina) {
		this.persona_maquina = persona_maquina;
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

	public int getCant_de_activaciones() {
		return cant_de_activaciones;
	}

	public void setCant_de_activaciones(int cant_de_activaciones) {
		this.cant_de_activaciones = cant_de_activaciones;
	}

	public Boolean getActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	public int getCant_de_generaciones() {
		return cant_de_generaciones;
	}

	public void setCant_de_generaciones(int cant_de_generaciones) {
		this.cant_de_generaciones = cant_de_generaciones;
	}

	public PersonaProductoVersion getPersona_producto() {
		return persona_producto;
	}

	public void setPersona_producto(PersonaProductoVersion persona_producto) {
		this.persona_producto = persona_producto;
	}

	public Boolean getBaneado() {
		return baneado;
	}

	public void setBaneado(Boolean baneado) {
		this.baneado = baneado;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}
	
}

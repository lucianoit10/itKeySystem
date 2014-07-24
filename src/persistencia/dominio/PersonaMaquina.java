package persistencia.dominio;

import java.sql.Timestamp;

public class PersonaMaquina {
	protected Long id;
	protected Persona propietario;
	protected Maquina maquina;
	protected Timestamp fecha_de_asociacion;
	
	public PersonaMaquina(Persona propietario, Maquina maquina,
			Timestamp fecha_de_asociacion) {
		super();
		this.propietario = propietario;
		this.maquina = maquina;
		this.fecha_de_asociacion = fecha_de_asociacion;
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

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Timestamp getFecha_de_asociacion() {
		return fecha_de_asociacion;
	}

	public void setFecha_de_asociacion(Timestamp fecha_de_asociacion) {
		this.fecha_de_asociacion = fecha_de_asociacion;
	}
	
	
}

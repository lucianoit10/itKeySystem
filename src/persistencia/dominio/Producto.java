package persistencia.dominio;

import java.sql.Date;

public class Producto {
	protected Long id;
	protected String nombre;
	protected String autores;
	protected String empresa;
	protected Date fecha_de_lanzamiento;
	protected Boolean eliminado = false;
	
	public Producto(String nombre, String autores,
			String empresa, Date fecha_de_lanzamiento) {
		super();
		this.nombre = nombre;
		this.autores = autores;
		this.empresa = empresa;
		this.fecha_de_lanzamiento = fecha_de_lanzamiento;
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
	public String getAutores() {
		return autores;
	}
	public void setAutores(String autores) {
		this.autores = autores;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public Date getFecha_de_lanzamiento() {
		return fecha_de_lanzamiento;
	}
	public void setFecha_de_lanzamiento(Date fecha_de_lanzamiento) {
		this.fecha_de_lanzamiento = fecha_de_lanzamiento;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

}

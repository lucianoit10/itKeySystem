package persistencia.dominio;

public class Persona {

	protected Long id;
	protected String nombre;		//obligatorio
	protected String apellido;
	protected String email;			//obligatorio
	protected String dni_cuil_cuit;	//obligatorio
	protected String tel_contacto;	//obligatorio
	protected String registro;		
	protected Boolean baneado = false;
	protected Boolean eliminado = false;		
	
		
	public Persona(String nombre, String apellido, String email,
			String registro, String dni_cuil_cuit, String tel_contacto) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.registro = registro;
		this.tel_contacto= tel_contacto;
		this.dni_cuil_cuit = dni_cuil_cuit;
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getTel_contacto() {
		return tel_contacto;
	}

	public void setTel_contacto(String tel_contacto) {
		this.tel_contacto = tel_contacto;
	}

	public String getDni_cuil_cuit() {
		return dni_cuil_cuit;
	}

	public void setDni_cuil_cuit(String dni_cuil_cuit) {
		this.dni_cuil_cuit = dni_cuil_cuit;
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
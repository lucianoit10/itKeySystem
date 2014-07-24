package persistencia.dominio;

public class Usuario extends Persona{
	protected String nombre_usuario;
	protected String contrasena;
	protected Boolean cambiar_contrasena = false;
	protected int permiso = 0;
	
	/* Permisos:
	 * 0 -> usuario de bajos permisos - cliente
	 * 1 ->	admin de clientes - maquinas - copias
	 * 2 -> administrador global
	 * */
	
	public Usuario(String nombre, String apellido, String email,
			String registro, String tel_contacto,String dni_cuit_cuil, 
			String nombre_usuario,String contrasena) {
		super(nombre, apellido, email, registro,dni_cuit_cuil, tel_contacto);
		this.nombre_usuario = nombre_usuario;
		this.contrasena = contrasena;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getPermiso() {
		return permiso;
	}

	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}

	public Boolean getCambiar_contrasena() {
		return cambiar_contrasena;
	}

	public void setCambiar_contrasena(Boolean cambiar_contrasena) {
		this.cambiar_contrasena = cambiar_contrasena;
	}
}

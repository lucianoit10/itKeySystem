package control;

import java.util.Collection;

import persistencia.AccesoBD;
import persistencia.dominio.Usuario;
import utils.Constantes;
import utils.GeneracionDeClaves;

public class ControlUsuario {

	AccesoBD abd;
	
	
	public ControlUsuario(AccesoBD abd) {
		super();
		this.abd = abd;
	}
	

	/* *************************** EXISTE *********************************** */

	//si existe la usuario con tal id
	public boolean existe_usuario (Long id){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_priv(id);
			abd.concretarTransaccion();
			return user != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}

	//si existe la usuario con tal dni_cuit_cuil
	public boolean existe_usuario (String dni_cuil_cuit){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_priv(dni_cuil_cuit);
			abd.concretarTransaccion();
			return user != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//si existe la usuario con tal registro
	public boolean existe_usuario_registro (String registro){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_registro_priv(registro);
			abd.concretarTransaccion();
			return user != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//si existe la usuario con tal nombre_usuario
	public boolean existe_usuario_nombre_usuario (String nombre_usuario){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_nombre_usuario_priv(nombre_usuario);
			abd.concretarTransaccion();
			return user != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}

	//si existe la usuario con tal mail
	public boolean existe_usuario_mail (String mail){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_mail_priv(mail);
			abd.concretarTransaccion();
			return user != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	/* ***************************** BUSCAR ******************************* */

	//retorna la Usuario con tal id si no existe retorna Null
	public Usuario buscar_usuario (Long id){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_priv(id);
			abd.concretarTransaccion();
			return user;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	//retorna la Usuario con tal dni_cuil_cuit si no existe retorna Null
	public Usuario buscar_usuario (String dni_cuil_cuit){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_priv(dni_cuil_cuit);
			abd.concretarTransaccion();
			return user;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	//retorna la Usuario con tal registro si no existe retorna Null
	public Usuario buscar_usuario_registro (String registro){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_registro_priv(registro);
			abd.concretarTransaccion();
			return user;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	//retorna la Usuario con tal mail si no existe retorna Null
	public Usuario buscar_usuario_mail (String mail){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_mail_priv(mail);
			abd.concretarTransaccion();
			return user;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* ************************** CONSULTAS ******************************** */
	public Usuario login (String user_mail, String pass){
		Usuario user = null;
		try{
			abd.iniciarTransaccion();
			//busco el usuario
			user = buscar_usuario_nombre_usuario_priv(user_mail);
			if (user==null)  user = buscar_usuario_mail_priv(user_mail);
			//encontre ese usuario
			if (user!=null)
				//me fijo si coinciden las contrasenas si no es asi retorno null
				if (!user.getContrasena().equals(GeneracionDeClaves.MD5(pass)))
					user = null;
			abd.concretarTransaccion();
			return user;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}


	/* ************************** CREAR *********************************** */
	
	public Usuario crear_usuario (	String nombre, String apellido, String email,	String registro, 
									String dni_cuil_cuit, String tel_contacto, String nombre_usuario, String contrasena){
		try{
			abd.iniciarTransaccion();
			//si los campos obligatorios no estan rellenos o los campos unicos estan repetidos returna null
			if(dni_cuil_cuit.isEmpty() || buscar_usuario_priv(dni_cuil_cuit)!=null)					return null;
			if(registro.isEmpty() || buscar_usuario_registro_priv(registro)!=null)					return null;
			if(nombre_usuario.isEmpty() || buscar_usuario_nombre_usuario_priv(nombre_usuario)!=null)return null;
			if(nombre.isEmpty() || email.isEmpty() || tel_contacto.isEmpty()|| contrasena.isEmpty())return null;
			//sino hubo errores crea la Usuario
			Usuario user = new Usuario( nombre, apellido, email, registro, dni_cuil_cuit, 
										tel_contacto,nombre_usuario, GeneracionDeClaves.MD5(contrasena));
			user = (Usuario) abd.hacerPersistente(user);
			abd.concretarTransaccion();
			return user;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* *************************** MODIFICAR *********************************** */
	
	public Usuario modificar_usuario (	Long id, String nombre, String apellido, String email,
										String registro, String dni_cuil_cuit, String tel_contacto){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_priv(id);
			if (user != null){
				user.setApellido(apellido);
				user.setDni_cuil_cuit(dni_cuil_cuit);
				user.setEmail(email);
				user.setNombre(nombre);
				user.setRegistro(registro);
				user.setTel_contacto(tel_contacto);
				user = (Usuario) abd.hacerPersistente(user);	
			}
			abd.concretarTransaccion();
			return user;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Usuario modificar_nombre_usuario (Long id, String nombre_usuario){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_priv(id);
			if (user != null){
				if (buscar_usuario_nombre_usuario_priv(nombre_usuario)==null){
					user.setNombre_usuario(nombre_usuario);
					user = (Usuario) abd.hacerPersistente(user);
				}	
			}
			abd.concretarTransaccion();
			return user;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Usuario modificar_contrasena (Long id, String pass){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_priv(id);
			if (user != null){
				//encripto la clave
				user.setContrasena(GeneracionDeClaves.MD5(pass));
				//si habia recuperado la contraseña aca la cambio
				if (user.getCambiar_contrasena()) user.setCambiar_contrasena(false);
				user = (Usuario) abd.hacerPersistente(user);
			}
			abd.concretarTransaccion();
			return user;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Usuario modificar_permiso (Long id, int permiso){
		try{
			abd.iniciarTransaccion();
			Usuario user = null;			
			if (permiso>=0 && permiso<=Constantes.MAX_NIVEL_PERMISO){
				user = buscar_usuario_priv(id);
				if (user != null){
					user.setPermiso(permiso);
					user = (Usuario) abd.hacerPersistente(user);
				}				
			}
			abd.concretarTransaccion();
			return user;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	
	public String recuperar_contrasena (Long id){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_priv(id);
			String clave =null; 
			if (user != null){
				//encripto la clave
				clave =GeneracionDeClaves.nuevaClaveUsuario();
				user.setContrasena(GeneracionDeClaves.MD5(clave));
				//indico q cuando se logee la cambie
				user.setCambiar_contrasena(true);
				user = (Usuario) abd.hacerPersistente(user);
			}
			abd.concretarTransaccion();
			return clave;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* *************************** BANEADO *********************************** */
	
	public Usuario banear_usuario (Long id){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_priv(id);
			if (user != null){
				user.setBaneado(true);
				user = (Usuario) abd.hacerPersistente(user);	
			}
			abd.concretarTransaccion();
			return user;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Usuario desbanear_usuario (Long id){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_priv(id);
			if (user != null){
				user.setBaneado(false);
				user = (Usuario) abd.hacerPersistente(user);	
			}
			abd.concretarTransaccion();
			return user;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* *************************** ELIMINAR *********************************** */
	
	//elimina una Usuario logicamente
	public Boolean eliminar_usuario (Long id){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_priv(id);
			if (user != null){
				user.setEliminado(true);
				user = (Usuario) abd.hacerPersistente(user);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//reactiva una Usuario que haya sido eliminada logicamente
	public Boolean reactivar_usuario (Long id){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_priv(id);
			if (user != null){
				user.setEliminado(false);
				user = (Usuario) abd.hacerPersistente(user);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//Elimina definitvamente la Usuario y borra todo registro de ella en la base de datos
	public Boolean eliminar_usuario_definitivamente (Long id){
		try{
			abd.iniciarTransaccion();
			Usuario user = buscar_usuario_priv(id);
			if (user != null){
				abd.eliminar(user);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}

	/* *************************** LISTAR *********************************** */
	
	public Collection<Usuario> listar_todas_usuarios (){
		try{
			abd.iniciarTransaccion();
			Collection<Usuario> lista = abd.listar(Usuario.class);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	public Collection<Usuario> listar_usuarios (String filtro){
		try{
			abd.iniciarTransaccion();
			Collection<Usuario> lista =  abd.buscarPorFiltro(Usuario.class,filtro);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Collection<Usuario> listar_usuarios (String filtro,String orden, String agrupar){
		try{
			abd.iniciarTransaccion();
			Collection<Usuario> lista =  abd.getObjectosOrdenadosYAgrupados(Usuario.class,filtro,orden,agrupar);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* ******************** METODOS PRIVADOS ********************* */
	//retorna la Usuario con tal id si no existe retorna Null	
	private Usuario buscar_usuario_priv (Long id) throws Exception {
		return (Usuario) abd.buscarPorFiltro(Usuario.class, "id==" +id);
	}	
	//retorna la Usuario con tal dni_cuit_cuil si no existe retorna Null
	private Usuario buscar_usuario_priv (String dni_cuil_cuit) throws Exception {
		return (Usuario) abd.buscarPorFiltro(Usuario.class, "dni_cuil_cuit.equals(\""+dni_cuil_cuit+"\")");
	}
	//retorna la Usuario con tal registro si no existe retorna Null
	private Usuario buscar_usuario_registro_priv (String registro) throws Exception {
		return (Usuario) abd.buscarPorFiltro(Usuario.class, "registro.equals(\""+registro+"\")");
	}
	//retorna la Usuario con tal nombre_usuario si no existe retorna Null
	private Usuario buscar_usuario_nombre_usuario_priv (String user) throws Exception {
		return (Usuario) abd.buscarPorFiltro(Usuario.class, "nombre_usuario.equals(\""+user+"\")");
	}
	//retorna la Usuario con tal mail si no existe retorna Null
	private Usuario buscar_usuario_mail_priv(String user_mail) throws Exception {
		return (Usuario) abd.buscarPorFiltro(Usuario.class, "mail.equals(\""+user_mail+"\")");
	}
}
package control;

import java.util.Collection;

import persistencia.AccesoBD;
import persistencia.dominio.Persona;

public class ControlPersona {

	AccesoBD abd;
	
	
	public ControlPersona(AccesoBD abd) {
		super();
		this.abd = abd;
	}
	

	/* *************************** EXISTE *********************************** */

	//si existe la persona con tal id
	public boolean existe_persona (Long id){
		try{
			abd.iniciarTransaccion();
			Persona pers = buscar_persona_priv(id);
			abd.concretarTransaccion();
			return pers != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}

	//si existe la persona con tal dni_cuit_cuil
	public boolean existe_persona (String dni_cuil_cuit){
		try{
			abd.iniciarTransaccion();
			Persona pers = buscar_persona_priv(dni_cuil_cuit);
			abd.concretarTransaccion();
			return pers != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//si existe la persona con tal registro
		public boolean existe_persona_registro (String registro){
			try{
				abd.iniciarTransaccion();
				Persona pers = buscar_persona_registro_priv(registro);
				abd.concretarTransaccion();
				return pers != null;
			}catch(Exception e){
				abd.rollbackTransaccion();
				return false;
			}
		}

		//si existe la persona con tal mail
		public boolean existe_persona_mail (String mail){
			try{
				abd.iniciarTransaccion();
				Persona user = buscar_persona_mail_priv(mail);
				abd.concretarTransaccion();
				return user != null;
			}catch(Exception e){
				abd.rollbackTransaccion();
				return false;
			}
		}
		
	/* ***************************** BUSCAR ******************************* */

	//retorna la persona con tal id si no existe retorna Null
	public Persona buscar_persona (Long id){
		try{
			abd.iniciarTransaccion();
			Persona pers = buscar_persona_priv(id);
			abd.concretarTransaccion();
			return pers;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	//retorna la persona con tal dni_cuil_cuit si no existe retorna Null
	public Persona buscar_persona (String dni_cuil_cuit){
		try{
			abd.iniciarTransaccion();
			Persona pers = buscar_persona_priv(dni_cuil_cuit);
			abd.concretarTransaccion();
			return pers;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	//retorna la persona con tal registro si no existe retorna Null
	public Persona buscar_persona_registro (String registro){
		try{
			abd.iniciarTransaccion();
			Persona pers = buscar_persona_registro_priv(registro);
			abd.concretarTransaccion();
			return pers;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	//retorna la persona con tal mail si no existe retorna Null
	public Persona buscar_persona_mail (String mail){
		try{
			abd.iniciarTransaccion();
			Persona user = buscar_persona_mail_priv(mail);
			abd.concretarTransaccion();
			return user;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	/* ************************** CREAR *********************************** */
	
	public Persona crear_persona (	String nombre, String apellido, String email,	String registro, 
									String dni_cuil_cuit, String tel_contacto){
		try{
			abd.iniciarTransaccion();
			//si los campos obligatorios no estan rellenos o los campos unicos estan repetidos returna null
			if(dni_cuil_cuit.isEmpty() || buscar_persona_priv(dni_cuil_cuit)!=null)	return null;
			if(registro.isEmpty() || buscar_persona_registro_priv(registro)!=null)	return null;
			if(nombre.isEmpty() || email.isEmpty() || tel_contacto.isEmpty())		return null;
			//sino hubo errores crea la persona
			Persona pers = new Persona(nombre, apellido, email, registro, dni_cuil_cuit, tel_contacto);
			pers = (Persona) abd.hacerPersistente(pers);
			abd.concretarTransaccion();
			return pers;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* *************************** MODIFICAR *********************************** */
	
	public Persona modificar_persona (	Long id, String nombre, String apellido, String email,
										String registro, String dni_cuil_cuit, String tel_contacto){
		try{
			abd.iniciarTransaccion();
			Persona pers = buscar_persona_priv(id);
			if (pers != null){
				pers.setApellido(apellido);
				pers.setDni_cuil_cuit(dni_cuil_cuit);
				pers.setEmail(email);
				pers.setNombre(nombre);
				pers.setRegistro(registro);
				pers.setTel_contacto(tel_contacto);
				pers = (Persona) abd.hacerPersistente(pers);	
			}
			abd.concretarTransaccion();
			return pers;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* *************************** BANEADO *********************************** */
	
	public Persona banear_persona (Long id){
		try{
			abd.iniciarTransaccion();
			Persona pers = buscar_persona_priv(id);
			if (pers != null){
				pers.setBaneado(true);
				pers = (Persona) abd.hacerPersistente(pers);	
			}
			abd.concretarTransaccion();
			return pers;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Persona desbanear_persona (Long id){
		try{
			abd.iniciarTransaccion();
			Persona pers = buscar_persona_priv(id);
			if (pers != null){
				pers.setBaneado(false);
				pers = (Persona) abd.hacerPersistente(pers);	
			}
			abd.concretarTransaccion();
			return pers;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* *************************** ELIMINAR *********************************** */
	
	//elimina una persona logicamente
	public Boolean eliminar_persona (Long id){
		try{
			abd.iniciarTransaccion();
			Persona pers = buscar_persona_priv(id);
			if (pers != null){
				pers.setEliminado(true);
				pers = (Persona) abd.hacerPersistente(pers);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//reactiva una persona que haya sido eliminada logicamente
	public Boolean reactivar_persona (Long id){
		try{
			abd.iniciarTransaccion();
			Persona pers = buscar_persona_priv(id);
			if (pers != null){
				pers.setEliminado(false);
				pers = (Persona) abd.hacerPersistente(pers);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//Elimina definitvamente la persona y borra todo registro de ella en la base de datos
	public Boolean eliminar_persona_definitivamente (Long id){
		try{
			abd.iniciarTransaccion();
			Persona pers = buscar_persona_priv(id);
			if (pers != null){
				abd.eliminar(pers);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}

	/* *************************** LISTAR *********************************** */
	
	public Collection<Persona> listar_todas_personas (){
		try{
			abd.iniciarTransaccion();
			Collection<Persona> lista = abd.listar(Persona.class);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	public Collection<Persona> listar_personas (String filtro){
		try{
			abd.iniciarTransaccion();
			Collection<Persona> lista =  abd.buscarPorFiltro(Persona.class,filtro);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Collection<Persona> listar_personas (String filtro,String orden, String agrupar){
		try{
			abd.iniciarTransaccion();
			Collection<Persona> lista =  abd.getObjectosOrdenadosYAgrupados(Persona.class,filtro,orden,agrupar);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* ******************** METODOS PRIVADOS ********************* */
	//retorna la persona con tal id si no existe retorna Null	
	private Persona buscar_persona_priv (Long id) throws Exception {
		return (Persona) abd.buscarPorFiltro(Persona.class, "id==" +id);
	}	
	//retorna la persona con tal dni_cuit_cuil si no existe retorna Null
	private Persona buscar_persona_priv (String dni_cuil_cuit) throws Exception {
		return (Persona) abd.buscarPorFiltro(Persona.class, "dni_cuil_cuit.equals(\""+dni_cuil_cuit+"\")");
	}
	//retorna la persona con tal registro si no existe retorna Null
	private Persona buscar_persona_registro_priv (String registro) throws Exception {
		return (Persona) abd.buscarPorFiltro(Persona.class, "registro.equals(\""+registro+"\")");
	}
	//retorna la persona con tal mail si no existe retorna Null
	private Persona buscar_persona_mail_priv(String user_mail) throws Exception {
		return (Persona) abd.buscarPorFiltro(Persona.class, "mail.equals(\""+user_mail+"\")");
	}
}

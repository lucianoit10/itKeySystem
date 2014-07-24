package control;

import java.util.Collection;

import persistencia.AccesoBD;
import persistencia.dominio.Persona;
import persistencia.dominio.PersonaProductoVersion;
import persistencia.dominio.Version;
import utils.Fechas;

public class ControlPersonaProductoVersion {

	AccesoBD abd;

	public ControlPersonaProductoVersion(AccesoBD abd) {
		super();
		this.abd = abd;
	}

	/* *************************** BUSCAR *********************************** */

	//retorna la PersonaProductoVersion con tal id
	public PersonaProductoVersion buscar_copia (Long id){
		try{
			abd.iniciarTransaccion();
			PersonaProductoVersion ppv = buscar_copia_priv(id);
			abd.concretarTransaccion();
			return ppv;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	/* *************************** EXISTE *********************************** */

	//si existe la PersonaProductoVersion con tal id
	public boolean existe_copia (Long id){
		try{
			abd.iniciarTransaccion();
			PersonaProductoVersion ppv = buscar_copia_priv(id);
			abd.concretarTransaccion();
			return ppv != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}

	/* ************************** CREAR *********************************** */
	
	public PersonaProductoVersion crear_copia (Persona propietario, Version version){
		try{
			abd.iniciarTransaccion();
			//si los campos obligatorios no estan rellenos  returna null;
			if(propietario == null || version == null)return null;
			//sino hubo errores crea la PersonaProductoVersion
			PersonaProductoVersion ppv = new PersonaProductoVersion(propietario,version, Fechas.fechaActualCompletaSql());
			ppv = (PersonaProductoVersion) abd.hacerPersistente(ppv);
			abd.concretarTransaccion();
			return ppv;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	/* *************************** MODIFICAR *********************************** */
	
	public PersonaProductoVersion transferir_copia (Long id, Persona propietario, boolean reset_vaues){
		try{
			abd.iniciarTransaccion();
			PersonaProductoVersion ppv = buscar_copia_priv(id);
			if (ppv != null){
				ppv.setPropietario(propietario);
				ppv.setFecha_de_adquisicion(Fechas.fechaActualCompletaSql());
				if (reset_vaues){
					ppv.setFecha_de_caducacion(null);
					ppv.setBaneada(false);
					ppv.setFuera_circulacion(false);
				}
				ppv = (PersonaProductoVersion) abd.hacerPersistente(ppv);	
			}
			abd.concretarTransaccion();
			return ppv;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	/* *************************** BANEADO *********************************** */
	
	public PersonaProductoVersion banear_copia (Long id){
		try{
			abd.iniciarTransaccion();
			PersonaProductoVersion ppv = buscar_copia_priv(id);
			if (ppv != null){
				ppv.setBaneada(true);
				ppv = (PersonaProductoVersion) abd.hacerPersistente(ppv);	
			}
			abd.concretarTransaccion();
			return ppv;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public PersonaProductoVersion desbanear_copia (Long id){
		try{
			abd.iniciarTransaccion();
			PersonaProductoVersion ppv = buscar_copia_priv(id);
			if (ppv != null){
				ppv.setBaneada(false);
				ppv = (PersonaProductoVersion) abd.hacerPersistente(ppv);	
			}
			abd.concretarTransaccion();
			return ppv;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* *************************** ELIMINAR *********************************** */
	
	//desactiva una copia
	public PersonaProductoVersion desactivar_copia (Long id){
		try{
			abd.iniciarTransaccion();
			PersonaProductoVersion ppv = buscar_copia_priv(id);
			if (ppv != null){
				ppv.setFuera_circulacion(true);
				ppv = (PersonaProductoVersion) abd.hacerPersistente(ppv);
			}
			abd.concretarTransaccion();
			return ppv;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}	
	

	//reactiva una copia
	public PersonaProductoVersion reactivar_copia (Long id){
		try{
			abd.iniciarTransaccion();
			PersonaProductoVersion ppv = buscar_copia_priv(id);
			if (ppv != null){
				ppv.setFuera_circulacion(false);
				ppv = (PersonaProductoVersion) abd.hacerPersistente(ppv);
			}
			abd.concretarTransaccion();
			return ppv;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}	
	
/* *************************** LISTAR *********************************** */
	
	public Collection<PersonaProductoVersion> listar_todas_copias (){
		try{
			abd.iniciarTransaccion();
			Collection<PersonaProductoVersion> lista = abd.listar(PersonaProductoVersion.class);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	public Collection<PersonaProductoVersion> listar_copias (String filtro){
		try{
			abd.iniciarTransaccion();
			Collection<PersonaProductoVersion> lista =  abd.buscarPorFiltro(PersonaProductoVersion.class,filtro);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Collection<PersonaProductoVersion> listar_copias (String filtro,String orden, String agrupar){
		try{
			abd.iniciarTransaccion();
			Collection<PersonaProductoVersion> lista =  abd.getObjectosOrdenadosYAgrupados(PersonaProductoVersion.class,filtro,orden,agrupar);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}


	/* ******************** METODOS PRIVADOS ********************* */
	//retorna la PersonaMaquina con tal id si no existe retorna Null	
	private PersonaProductoVersion buscar_copia_priv (Long id) throws Exception {
		return (PersonaProductoVersion) abd.buscarPorFiltro(PersonaProductoVersion.class, "id==" +id);
	}	
	
}

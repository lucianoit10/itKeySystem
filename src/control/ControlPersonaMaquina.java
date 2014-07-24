package control;

import java.util.Collection;

import persistencia.AccesoBD;
import persistencia.dominio.Maquina;
import persistencia.dominio.Persona;
import persistencia.dominio.PersonaMaquina;
import utils.Fechas;

public class ControlPersonaMaquina {

	AccesoBD abd;

	public ControlPersonaMaquina(AccesoBD abd) {
		super();
		this.abd = abd;
	}
	
	/* *************************** BUSCAR *********************************** */

	//retorna la PersonaMaquina con tal id
	public PersonaMaquina buscar_persona_maquina (Long id){
		try{
			abd.iniciarTransaccion();
			PersonaMaquina pm = buscar_persona_maquina_priv(id);
			abd.concretarTransaccion();
			return pm;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	/* *************************** EXISTE *********************************** */

	//si existe la PersonaMaquina con tal id
	public boolean existe_persona_maquina (Long id){
		try{
			abd.iniciarTransaccion();
			PersonaMaquina pm = buscar_persona_maquina_priv(id);
			abd.concretarTransaccion();
			return pm != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}

	/* ************************** CREAR *********************************** */
	
	public PersonaMaquina crear_persona_maquina (Persona propietario, Maquina maquina){
		try{
			abd.iniciarTransaccion();
			//si los campos obligatorios no estan rellenos  returna null;
			if(propietario == null || maquina == null)return null;
			//sino hubo errores crea la Usuario
			PersonaMaquina pm = new PersonaMaquina(propietario,maquina, Fechas.fechaActualCompletaSql());
			pm = (PersonaMaquina) abd.hacerPersistente(pm);
			abd.concretarTransaccion();
			return pm;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}


/* *************************** ELIMINAR *********************************** */
	
	//Elimina definitvamente la Version y borra todo registro de ella en la base de datos
	public Boolean eliminar_persona_maquina (Long id){
		try{
			abd.iniciarTransaccion();
			PersonaMaquina pm = buscar_persona_maquina_priv(id);
			if (pm != null){
				abd.eliminar(pm);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}	
		
	
/* *************************** LISTAR *********************************** */
	
	public Collection<PersonaMaquina> listar_todas_persona_maquina (){
		try{
			abd.iniciarTransaccion();
			Collection<PersonaMaquina> lista = abd.listar(PersonaMaquina.class);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	public Collection<PersonaMaquina> listar_persona_maquina (String filtro){
		try{
			abd.iniciarTransaccion();
			Collection<PersonaMaquina> lista =  abd.buscarPorFiltro(PersonaMaquina.class,filtro);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Collection<PersonaMaquina> listar_persona_maquina (String filtro,String orden, String agrupar){
		try{
			abd.iniciarTransaccion();
			Collection<PersonaMaquina> lista =  abd.getObjectosOrdenadosYAgrupados(PersonaMaquina.class,filtro,orden,agrupar);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}


	/* ******************** METODOS PRIVADOS ********************* */
	//retorna la PersonaMaquina con tal id si no existe retorna Null	
	private PersonaMaquina buscar_persona_maquina_priv (Long id) throws Exception {
		return (PersonaMaquina) abd.buscarPorFiltro(PersonaMaquina.class, "id==" +id);
	}	
	
}

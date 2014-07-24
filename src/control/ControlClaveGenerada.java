package control;

import java.util.Collection;

import persistencia.AccesoBD;
import persistencia.dominio.Clave;
import persistencia.dominio.ClaveGenerada;

public class ControlClaveGenerada {
	
	private AccesoBD abd;

	public ControlClaveGenerada(AccesoBD abd) {
		super();
		this.abd = abd;
	}
	
	/* *************************** BUSCAR *********************************** */

	//retorna la ClaveGenerada con tal id
	public ClaveGenerada buscar_clave (Long id){
		try{
			abd.iniciarTransaccion();
			ClaveGenerada c = buscar_clave_priv(id);
			abd.concretarTransaccion();
			return c;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	//retorna la ClaveGenerada con tal clave
	public ClaveGenerada buscar_clave (String key){
		try{
			abd.iniciarTransaccion();
			ClaveGenerada c = buscar_clave_priv(key);
			abd.concretarTransaccion();
			return c;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	/* *************************** EXISTE *********************************** */

	//si existe la ClaveGenerada con tal id
	public boolean existe_clave (Long id){
		try{
			abd.iniciarTransaccion();
			ClaveGenerada c = buscar_clave_priv(id);
			abd.concretarTransaccion();
			return c != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}

	//retorna la ClaveGenerada con tal clave
	public boolean existe_clave (String key){
		try{
			abd.iniciarTransaccion();
			ClaveGenerada c = buscar_clave_priv(key);
			abd.concretarTransaccion();
			return c != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	/* *************************** CREAR *********************************** */
	
	public ClaveGenerada se_genero_clave (Clave datos){
		try{
			abd.iniciarTransaccion();
			//si los campos obligatorios no estan rellenos  returna null;
			if(datos == null || datos.getId() <=0)return null;
			//sino hubo errores crea la PersonaProductoVersion
			ClaveGenerada c = new ClaveGenerada(datos,datos.getClave(),datos.getFecha_de_generacion());
			c = (ClaveGenerada) abd.hacerPersistente(c);
			abd.concretarTransaccion();
			return c;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* *************************** LISTAR *********************************** */
	
	public Collection<ClaveGenerada> listar_todas_claves (){
		try{
			abd.iniciarTransaccion();
			Collection<ClaveGenerada> lista = abd.listar(ClaveGenerada.class);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	public Collection<ClaveGenerada> listar_claves (String filtro){
		try{
			abd.iniciarTransaccion();
			Collection<ClaveGenerada> lista =  abd.buscarPorFiltro(ClaveGenerada.class,filtro);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Collection<ClaveGenerada> listar_claves (String filtro,String orden, String agrupar){
		try{
			abd.iniciarTransaccion();
			Collection<ClaveGenerada> lista =  abd.getObjectosOrdenadosYAgrupados(ClaveGenerada.class,filtro,orden,agrupar);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}


	/* ******************** METODOS PRIVADOS ********************* */
	//retorna la PersonaMaquina con tal id si no existe retorna Null	
	private ClaveGenerada buscar_clave_priv (Long id) throws Exception {
		return (ClaveGenerada) abd.buscarPorFiltro(ClaveGenerada.class, "id==" +id);
	}	
	//retorna la PersonaMaquina con tal id si no existe retorna Null	
	private ClaveGenerada buscar_clave_priv (String key) throws Exception {
		return (ClaveGenerada) abd.buscarPorFiltro(ClaveGenerada.class, "clave.equals(\""+key+"\")");
	}	
}

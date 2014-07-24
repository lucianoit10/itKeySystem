package control;

import java.util.Collection;

import persistencia.AccesoBD;
import persistencia.dominio.Maquina;

public class ControlMaquina {
	AccesoBD abd;
	
	
	public ControlMaquina(AccesoBD abd) {
		super();
		this.abd = abd;
	}
	

	/* *************************** EXISTE *********************************** */

	//si existe la maquina con tal id
	public boolean existe_maquina (Long id){
		try{
			abd.iniciarTransaccion();
			Maquina maq = buscar_maquina_priv(id);
			abd.concretarTransaccion();
			return maq != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}

	//si existe la maquina con tal dni_cuit_cuil
	public boolean existe_maquina (String mac){
		try{
			abd.iniciarTransaccion();
			Maquina maq = buscar_maquina_priv(mac);
			abd.concretarTransaccion();
			return maq != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	/* *************************** BUSCAR *********************************** */

	//si existe la maquina con tal id
	public Maquina buscar_maquina (Long id){
		try{
			abd.iniciarTransaccion();
			Maquina maq = buscar_maquina_priv(id);
			abd.concretarTransaccion();
			return maq;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	//si existe la maquina con tal dni_cuit_cuil
	public Maquina buscar_maquina (String mac){
		try{
			abd.iniciarTransaccion();
			Maquina maq = buscar_maquina_priv(mac);
			abd.concretarTransaccion();
			return maq;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
/* ************************** CREAR *********************************** */
	
	public Maquina crear_maquina (String mac, String ip){
		try{
			abd.iniciarTransaccion();
			//si los campos obligatorios no estan rellenos o 
			//los campos unicos estan repetidos returna null
			if(mac.isEmpty() || buscar_maquina_priv(mac)!=null)return null;
			if(ip.isEmpty())return null;
			//sino hubo errores crea la Usuario
			Maquina maq = new Maquina(mac,ip);
			maq = (Maquina) abd.hacerPersistente(maq);
			abd.concretarTransaccion();
			return maq;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
/* *************************** MODIFICAR *********************************** */
	
	public Maquina modificar_usuario (Long id, String mac, String ip){
		try{
			abd.iniciarTransaccion();
			Maquina maq = buscar_maquina_priv(id);
			maq.setMac(mac);
			maq.setIp(ip);					
			maq = (Maquina) abd.hacerPersistente(maq);	
			abd.concretarTransaccion();
			return maq;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
/* *************************** BANEADO *********************************** */
	
	public Maquina banear_maquina (Long id){
		try{
			abd.iniciarTransaccion();
			Maquina maq = buscar_maquina_priv(id);
			if (maq != null){
				maq.setBaneado(true);
				maq = (Maquina) abd.hacerPersistente(maq);	
			}
			abd.concretarTransaccion();
			return maq;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Maquina desbanear_maquina (Long id){
		try{
			abd.iniciarTransaccion();
			Maquina maq = buscar_maquina_priv(id);
			if (maq != null){
				maq.setBaneado(false);
				maq = (Maquina) abd.hacerPersistente(maq);	
			}
			abd.concretarTransaccion();
			return maq;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
/* *************************** ELIMINAR *********************************** */
	
	//elimina una Maquina logicamente
	public Boolean eliminar_maquina (Long id){
		try{
			abd.iniciarTransaccion();
			Maquina maq = buscar_maquina_priv(id);
			if (maq != null){
				maq.setEliminada(true);
				maq = (Maquina) abd.hacerPersistente(maq);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//reactiva una Maquina que haya sido eliminada logicamente
	public Boolean reactivar_maquina (Long id){
		try{
			abd.iniciarTransaccion();
			Maquina maq = buscar_maquina_priv(id);
			if (maq != null){
				maq.setEliminada(false);
				maq = (Maquina) abd.hacerPersistente(maq);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//Elimina definitvamente la Usuario y borra todo registro de ella en la base de datos
	public Boolean eliminar_maquina_definitivamente (Long id){
		try{
			abd.iniciarTransaccion();
			Maquina maq = buscar_maquina_priv(id);
			if (maq != null){
				abd.eliminar(maq);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}

/* *************************** LISTAR *********************************** */
	
	public Collection<Maquina> listar_todas_maquinas (){
		try{
			abd.iniciarTransaccion();
			Collection<Maquina> lista = abd.listar(Maquina.class);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	public Collection<Maquina> listar_maquinas (String filtro){
		try{
			abd.iniciarTransaccion();
			Collection<Maquina> lista =  abd.buscarPorFiltro(Maquina.class,filtro);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Collection<Maquina> listar_maquinas (String filtro,String orden, String agrupar){
		try{
			abd.iniciarTransaccion();
			Collection<Maquina> lista =  abd.getObjectosOrdenadosYAgrupados(Maquina.class,filtro,orden,agrupar);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}


	/* ******************** METODOS PRIVADOS ********************* */
	//retorna la Maquina con tal id si no existe retorna Null	
	private Maquina buscar_maquina_priv (Long id) throws Exception {
		return (Maquina) abd.buscarPorFiltro(Maquina.class, "id==" +id);
	}	
	//retorna la Maquina con tal dni_cuit_cuil si no existe retorna Null
	private Maquina buscar_maquina_priv (String mac) throws Exception {
		return (Maquina) abd.buscarPorFiltro(Maquina.class, "mac.equals(\""+mac+"\")");
	}
}

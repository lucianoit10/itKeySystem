package control;

import java.util.Collection;


import persistencia.AccesoBD;
import persistencia.dominio.Producto;
import persistencia.dominio.Version;
import utils.GeneracionDeClaves;

public class ControlVersion {

	AccesoBD abd;

	public ControlVersion(AccesoBD abd) {
		super();
		this.abd = abd;
	}
	
	/* *************************** BUSCAR *********************************** */

	//retorna la Version con tal id
	public Version buscar_version (Long id){
		try{
			abd.iniciarTransaccion();
			Version vers = buscar_version_priv(id);
			abd.concretarTransaccion();
			return vers;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	/* *************************** EXISTE *********************************** */

	//si existe la Version con tal id
	public boolean existe_version (Long id){
		try{
			abd.iniciarTransaccion();
			Version vers = buscar_version_priv(id);
			abd.concretarTransaccion();
			return vers != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}

	/* ************************** CREAR *********************************** */
	
	public Version crear_version (Producto producto, String version){
		try{
			abd.iniciarTransaccion();
			//si los campos obligatorios no estan rellenos  returna null;
			if(producto == null || version.isEmpty())return null;
			//sino hubo errores crea la Usuario
			Version vers = new Version(producto,version, "");
			vers = (Version) abd.hacerPersistente(vers);
			//genera la clave para encriptar y desencriptor
			String key = GeneracionDeClaves.generar_encryption_key((int)((vers.getId()/1)));
			vers.setKeyDecript(key);
			vers = (Version) abd.hacerPersistente(vers);
			abd.concretarTransaccion();
			return vers;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

/* ************************** MODIFICAR  *********************************** */
	
	public Version modificar_version (Long id, Producto producto, String version){
		try{
			abd.iniciarTransaccion();
			//si los campos obligatorios no estan rellenos  returna null;
			if(producto == null || version.isEmpty())return null;
			//sino hubo errores crea la Usuario
			Version vers = buscar_version_priv(id);
			if (vers != null) {
				vers.setProducto(producto);
				vers.setVersion(version);
				vers = (Version) abd.hacerPersistente(vers);
			} 
			abd.concretarTransaccion();
			return vers;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Version existe_version_posterior (Long id){
		try{
			abd.iniciarTransaccion();
			Version vers = buscar_version_priv(id);
			if (vers != null) {
				vers.setTiene_posterior(true);
				vers = (Version) abd.hacerPersistente(vers);
			} 
			abd.concretarTransaccion();
			return vers;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	

	public Version es_ultima_version (Long id){
		try{
			abd.iniciarTransaccion();
			Version vers = buscar_version_priv(id);
			if (vers != null) {
				vers.setTiene_posterior(false);
				vers = (Version) abd.hacerPersistente(vers);
			} 
			abd.concretarTransaccion();
			return vers;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
/* *************************** ELIMINAR *********************************** */
	
	//elimina una Version logicamente
	public Boolean eliminar_version (Long id){
		try{
			abd.iniciarTransaccion();
			Version vers = buscar_version_priv(id);
			if (vers != null){
				vers.setEliminada(true);
				vers = (Version) abd.hacerPersistente(vers);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//reactiva una Version que haya sido eliminada logicamente
	public Boolean reactivar_version (Long id){
		try{
			abd.iniciarTransaccion();
			Version vers = buscar_version_priv(id);
			if (vers != null){
				vers.setEliminada(false);
				vers = (Version) abd.hacerPersistente(vers);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//Elimina definitvamente la Version y borra todo registro de ella en la base de datos
	public Boolean eliminar_version_definitivamente (Long id){
		try{
			abd.iniciarTransaccion();
			Version vers = buscar_version_priv(id);
			if (vers != null){
				abd.eliminar(vers);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}	
	
/* *************************** LISTAR *********************************** */
	
	public Collection<Version> listar_todas_versiones (){
		try{
			abd.iniciarTransaccion();
			Collection<Version> lista = abd.listar(Version.class);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	public Collection<Version> listar_versiones (String filtro){
		try{
			abd.iniciarTransaccion();
			Collection<Version> lista =  abd.buscarPorFiltro(Version.class,filtro);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Collection<Version> listar_versiones (String filtro,String orden, String agrupar){
		try{
			abd.iniciarTransaccion();
			Collection<Version> lista =  abd.getObjectosOrdenadosYAgrupados(Version.class,filtro,orden,agrupar);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* ******************** METODOS PRIVADOS ********************* */
	//retorna la Version con tal id si no existe retorna Null	
	private Version buscar_version_priv (Long id) throws Exception {
		return (Version) abd.buscarPorFiltro(Version.class, "id==" +id);
	}	
	
}

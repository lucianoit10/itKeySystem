package control;

import java.util.Collection;

import persistencia.AccesoBD;
import persistencia.dominio.Clave;
import persistencia.dominio.ClaveGenerada;
import persistencia.dominio.Persona;
import persistencia.dominio.PersonaMaquina;
import persistencia.dominio.PersonaProductoVersion;
import utils.AES;
import utils.Fechas;
import utils.GeneracionDeClaves;

public class ControlClave {
	
	private AccesoBD abd;

	public ControlClave(AccesoBD abd) {
		super();
		this.abd = abd;
	}
	
	/* *************************** BUSCAR *********************************** */
	
	//retorna la ClaveGenerada con tal id
	public Clave buscar_clave (Long id){
		try{
			abd.iniciarTransaccion();
			Clave c = buscar_clave_priv(id);
			abd.concretarTransaccion();
			return c;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	//retorna la ClaveGenerada con tal id
		public Clave buscar_clave (String key){
			try{
				abd.iniciarTransaccion();
				Clave c = buscar_clave_priv(key);
				abd.concretarTransaccion();
				return c;
			}catch(Exception e){
				abd.rollbackTransaccion();
				return null;
			}
		}

	//retorna la ClaveGenerada con tal id
	public Clave buscar_clave (PersonaProductoVersion persona_producto, PersonaMaquina persona_maquina){
		try{
			abd.iniciarTransaccion();
			Clave c = buscar_clave_priv(persona_producto,persona_maquina);
			abd.concretarTransaccion();
			return c;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	/* *************************** EXISTE *********************************** */
	
	//retorna la ClaveGenerada con tal id
	public boolean existe_clave (Long id){
		try{
			abd.iniciarTransaccion();
			Clave c = buscar_clave_priv(id);
			abd.concretarTransaccion();
			return c!= null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	

	//retorna la ClaveGenerada con tal id
	public boolean existe_clave (String key){
		try{
			abd.iniciarTransaccion();
			Clave c = buscar_clave_priv(key);
			abd.concretarTransaccion();
			return c!= null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//retorna la ClaveGenerada con tal id
	public boolean existe_clave (PersonaProductoVersion persona_producto, PersonaMaquina persona_maquina){
		try{
			abd.iniciarTransaccion();
			Clave c = buscar_clave_priv(persona_producto,persona_maquina);
			abd.concretarTransaccion();
			return c !=null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	/* *************************** CREAR *********************************** */
	public String nueva_clave (PersonaProductoVersion persona_producto, PersonaMaquina persona_maquina){
		try{
			String clave = "";
			//si los campos obligatorios no estan rellenos  returna null;
			if(persona_producto == null || persona_maquina == null)return null;
			if(	persona_producto.getPropietario()==null ||persona_maquina.getPropietario()==null || 
				(persona_maquina.getPropietario().getId()!=persona_maquina.getPropietario().getId()))
				return null;
			//sino hubo errores crea la PersonaProductoVersion
			boolean no_genera_antes = true;
			ControlClaveGenerada ccg = new ControlClaveGenerada(abd);
			while (no_genera_antes){
				no_genera_antes = true;
				clave = GeneracionDeClaves.generar_key();
				if (ccg.existe_clave(clave)) no_genera_antes = false;
			}
			Clave c = crear_clave(persona_producto, persona_maquina, clave);
			if (c != null){
				ClaveGenerada cg = ccg.se_genero_clave(c);
				if (cg != null){
					String encriptada = new String (AES.encrypt(clave, c.getPersona_producto().getProducto_version().getKeyDecript()));
					return encriptada;
				}
			}
			return null;
		}catch (Exception e){return null;}
	}
	
	/* *************************** RECUPERAR *********************************** */
	public String recuperar_clave (PersonaProductoVersion persona_producto, PersonaMaquina persona_maquina){
		try{
			String clave = "";
			//si los campos obligatorios no estan rellenos  returna null;
			if(persona_producto == null || persona_maquina == null)return null;
			if(	persona_producto.getPropietario()==null ||persona_maquina.getPropietario()==null || 
				(persona_maquina.getPropietario().getId()!=persona_maquina.getPropietario().getId()))
				return null;
			Clave c=  buscar_clave(persona_producto, persona_maquina);
			if (c != null){
				//sino hubo errores crea la PersonaProductoVersion
				boolean no_genera_antes = true;
				ControlClaveGenerada ccg = new ControlClaveGenerada(abd);
				while (no_genera_antes){
					no_genera_antes = true;
					clave = GeneracionDeClaves.generar_key();
					if (ccg.existe_clave(clave)) no_genera_antes = false;
				}
				c.setClave(clave);
				c.setActiva(false);
				c.setFecha_de_generacion(Fechas.fechaActualCompletaSql());
				c.setCant_de_generaciones(c.getCant_de_generaciones()+1);
				c = guardar_cambios_claves(c);
				if (c != null){
					ClaveGenerada cg = ccg.se_genero_clave(c);
					if (cg != null){
						String encriptada = new String (AES.encrypt(clave, c.getPersona_producto().getProducto_version().getKeyDecript()));
						return encriptada;
					}
				}
			}
			return null;
		}catch (Exception e){return null;}
	}

	/* *************************** ACTIVAR *********************************** */
	public Clave activar_clave (PersonaProductoVersion persona_producto, PersonaMaquina persona_maquina, String clave){
		try{
			//si los campos obligatorios no estan rellenos  returna null;
			if(persona_producto == null || persona_maquina == null)return null;
			if(	persona_producto.getPropietario()==null ||persona_maquina.getPropietario()==null || 
				(persona_maquina.getPropietario().getId()!=persona_maquina.getPropietario().getId()))
				return null;
			Clave c=  buscar_clave(persona_producto, persona_maquina);
			if (c != null){
				//sino hubo errores crea la PersonaProductoVersion
				if (c.equals(AES.decrypt(clave.getBytes(), c.getPersona_producto().getProducto_version().getKeyDecript()))){
					c.setActiva(true);
					c.setCant_de_activaciones(c.getCant_de_activaciones()+1);
					c = guardar_cambios_claves(c);
					return c;
				}
			}
			return null;
		}catch (Exception e){return null;}
	}

	/* *************************** BANEADO *********************************** */
	
	public Clave banear_clave (Long id){
		try{
			abd.iniciarTransaccion();
			Clave clave = buscar_clave_priv(id);
			if (clave != null){
				clave.setBaneado(true);
				clave = (Clave) abd.hacerPersistente(clave);	
			}
			abd.concretarTransaccion();
			return clave;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Clave desbanear_clave (Long id){
		try{
			abd.iniciarTransaccion();
			Clave clave = buscar_clave_priv(id);
			if (clave != null){
				clave.setBaneado(false);
				clave = (Clave) abd.hacerPersistente(clave);	
			}
			abd.concretarTransaccion();
			return clave;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* *************************** ELIMINAR *********************************** */
	
	//elimina una persona logicamente
	public Boolean eliminar_clave (Long id){
		try{
			abd.iniciarTransaccion();
			Clave clave = buscar_clave_priv(id);
			if (clave != null){
				clave.setEliminado(true);
				clave = (Clave) abd.hacerPersistente(clave);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//reactiva una persona que haya sido eliminada logicamente
	public Boolean reactivar_clave (Long id){
		try{
			abd.iniciarTransaccion();
			Clave clave = buscar_clave_priv(id);
			if (clave != null){
				clave.setEliminado(false);
				clave = (Clave) abd.hacerPersistente(clave);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//Elimina definitvamente la persona y borra todo registro de ella en la base de datos
	public Boolean eliminar_clave_definitivamente (Long id){
		try{
			abd.iniciarTransaccion();
			Clave clave = buscar_clave_priv(id);
			if (clave != null){
				abd.eliminar(clave);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}

	/* *************************** LISTAR *********************************** */
	
	public Collection<Clave> listar_todas_claves (){
		try{
			abd.iniciarTransaccion();
			Collection<Clave> lista = abd.listar(Clave.class);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	public Collection<Clave> listar_claves (String filtro){
		try{
			abd.iniciarTransaccion();
			Collection<Clave> lista =  abd.buscarPorFiltro(Clave.class,filtro);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Collection<Clave> listar_claves (String filtro,String orden, String agrupar){
		try{
			abd.iniciarTransaccion();
			Collection<Clave> lista =  abd.getObjectosOrdenadosYAgrupados(Clave.class,filtro,orden,agrupar);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	/* ******************** METODOS PRIVADOS ********************* */
	//retorna la PersonaMaquina con tal id si no existe retorna Null	
	private Clave buscar_clave_priv (Long id) throws Exception {
		return (Clave) abd.buscarPorFiltro(Clave.class, "id==" +id);
	}	
	//retorna la PersonaMaquina con tal id si no existe retorna Null	
	private Clave buscar_clave_priv (String key) throws Exception {
		return (Clave) abd.buscarPorFiltro(Clave.class, "clave.equals(\""+key+"\")");
	}	
	//retorna la PersonaMaquina con tal id si no existe retorna Null	
	private Clave buscar_clave_priv (PersonaProductoVersion pers_prod, PersonaMaquina pers_maq) throws Exception {
		return (Clave) abd.buscarPorFiltro(Clave.class, "persona_producto.id==" +pers_prod.getId()+"&& persona_maquina.id==" +pers_maq.getId());
	}	
	
	private Clave guardar_cambios_claves(Clave c) {
		try{
			abd.iniciarTransaccion();
			c = (Clave) abd.hacerPersistente(c);
			abd.concretarTransaccion();
			return c;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	private Clave crear_clave (PersonaProductoVersion persona_producto, PersonaMaquina persona_maquina, String clave){
		try{
			abd.iniciarTransaccion();
			Clave c = new Clave(persona_producto,persona_maquina,clave,Fechas.fechaActualCompletaSql());
			c.setCant_de_generaciones(c.getCant_de_generaciones()+1);
			c = (Clave) abd.hacerPersistente(c);
			abd.concretarTransaccion();
			return c;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
}

package control;

import java.sql.Date;
import java.util.Collection;

import persistencia.AccesoBD;
import persistencia.dominio.Producto;

public class ControlProducto {

	AccesoBD abd;

	public ControlProducto(AccesoBD abd) {
		super();
		this.abd = abd;
	}
	/* *************************** EXISTE *********************************** */

	//si existe la Producto con tal id
	public boolean existe_producto (Long id){
		try{
			abd.iniciarTransaccion();
			Producto prod = buscar_producto_priv(id);
			abd.concretarTransaccion();
			return prod != null;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	/* ************************** CREAR *********************************** */
	
	public Producto crear_producto (String nombre, String autores, String empresa, Date fecha_de_lanzamiento){
		try{
			abd.iniciarTransaccion();
			//si los campos obligatorios no estan rellenos  returna null;
			if(nombre.isEmpty() || autores.isEmpty() || empresa.isEmpty() || fecha_de_lanzamiento==null)return null;
			//sino hubo errores crea la Usuario
			Producto prod = new Producto(nombre,autores,empresa,fecha_de_lanzamiento);
			prod = (Producto) abd.hacerPersistente(prod);
			abd.concretarTransaccion();
			return prod;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

/* *************************** MODIFICAR *********************************** */
	public Producto modificar_usuario (Long id, String nombre, String autores, String empresa, Date fecha_de_lanzamiento){
		try{
			abd.iniciarTransaccion();
			Producto prod = buscar_producto_priv(id);
			if (prod != null){
				prod.setNombre(nombre);
				prod.setAutores(autores);
				prod.setEmpresa(empresa);
				prod.setFecha_de_lanzamiento(fecha_de_lanzamiento);
				prod = (Producto) abd.hacerPersistente(prod);				
			}	
			abd.concretarTransaccion();
			return prod;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

/* *************************** ELIMINAR *********************************** */
	
	//elimina una Producto logicamente
	public Boolean eliminar_maquina (Long id){
		try{
			abd.iniciarTransaccion();
			Producto prod = buscar_producto_priv(id);
			if (prod != null){
				prod.setEliminado(true);
				prod = (Producto) abd.hacerPersistente(prod);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//reactiva una Producto que haya sido eliminada logicamente
	public Boolean reactivar_producto (Long id){
		try{
			abd.iniciarTransaccion();
			Producto prod = buscar_producto_priv(id);
			if (prod != null){
				prod.setEliminado(false);
				prod = (Producto) abd.hacerPersistente(prod);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
	//Elimina definitvamente la Producto y borra todo registro de ella en la base de datos
	public Boolean eliminar_producto_definitivamente (Long id){
		try{
			abd.iniciarTransaccion();
			Producto prod = buscar_producto_priv(id);
			if (prod != null){
				abd.eliminar(prod);	
			}
			abd.concretarTransaccion();
			return true;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return false;
		}
	}
	
/* *************************** LISTAR *********************************** */
	
	public Collection<Producto> listar_todas_productos (){
		try{
			abd.iniciarTransaccion();
			Collection<Producto> lista = abd.listar(Producto.class);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	public Collection<Producto> listar_productos (String filtro){
		try{
			abd.iniciarTransaccion();
			Collection<Producto> lista =  abd.buscarPorFiltro(Producto.class,filtro);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}

	public Collection<Producto> listar_productos (String filtro,String orden, String agrupar){
		try{
			abd.iniciarTransaccion();
			Collection<Producto> lista =  abd.getObjectosOrdenadosYAgrupados(Producto.class,filtro,orden,agrupar);
			abd.concretarTransaccion();
			return lista;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}


	/* ******************** METODOS PRIVADOS ********************* */
	//retorna la Producto con tal id si no existe retorna Null	
	private Producto buscar_producto_priv (Long id) throws Exception {
		return (Producto) abd.buscarPorFiltro(Producto.class, "id==" +id);
	}	
	

}

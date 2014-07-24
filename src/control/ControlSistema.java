package control;

import persistencia.AccesoBD;
import persistencia.dominio.Sistema;

public class ControlSistema {
	
	private AccesoBD abd;

	public ControlSistema(AccesoBD abd) {
		super();
		this.abd = abd;
	}
	
	public Sistema valores_por_defecto_sistema (){
		try{
			abd.iniciarTransaccion();
			Sistema sist = new Sistema(new Long(1), "itKeySystem", "", "","");
			sist = (Sistema) abd.hacerPersistente(sist);
			abd.concretarTransaccion();
			return sist;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	public Sistema buscar_sistema (){
		try{
			abd.iniciarTransaccion();
			Sistema sist = (Sistema) abd.buscarPorId(Sistema.class, new Long(1));
			abd.concretarTransaccion();
			return sist;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
	
	public Sistema modificar_sistema (String nomb, String url, String ip, String pathLogo){
		try{
			abd.iniciarTransaccion();
			Sistema sist = (Sistema) abd.buscarPorId(Sistema.class, new Long(1));
			sist.setNombre(nomb);
			sist.setUrl(url);
			sist.setIp(ip);
			sist.setPathLogo(pathLogo);
			sist = (Sistema) abd.hacerPersistente(sist);
			abd.concretarTransaccion();
			return sist;
		}catch(Exception e){
			abd.rollbackTransaccion();
			return null;
		}
	}
}

package control;

import persistencia.AccesoBD;
import persistencia.dominio.Auditoria;
import persistencia.dominio.Maquina;
import persistencia.dominio.Persona;
import persistencia.dominio.Producto;
import persistencia.dominio.Usuario;
import utils.Fechas;

public class ControlAuditoria {

	private AccesoBD abd;

	public ControlAuditoria(AccesoBD abd) {
		super();
		this.abd = abd;
	}
	
	public void nueva_auditoria (String accion, Usuario auditor, Persona solicitante, Maquina maquina, Producto producto, String extra, boolean satisfactorio){
		try{
			abd.iniciarTransaccion();
			nueva_auditoria_sin_transaccion(accion,auditor,solicitante,maquina,producto,extra,satisfactorio);
			abd.concretarTransaccion();
		}catch(Exception e){
			abd.rollbackTransaccion();
		}
	}
	
	public void nueva_auditoria_sin_transaccion (String accion, Usuario auditor, Persona solicitante, Maquina maquina, Producto producto, String extra, boolean satisfactorio){
		String armado = accion+":\n";
		if (auditor!=null)armado +="auditor: "+auditor.getNombre_usuario()+" ("+auditor.getEmail()+")\n";
		if (solicitante!=null){
			armado +="solicitante: "+solicitante.getNombre();
			if(solicitante.getApellido()!=null) armado +=" "+solicitante.getApellido();
			 armado +=" ("+solicitante.getEmail()+")\n";
		}
		if (maquina!=null)armado +="maquina: "+maquina.getMac()+"\n";
		if (producto!=null)armado +="producto: "+producto.getNombre()+"\n";
		if (extra!=null)armado +="extra: "+extra+"\n";
		Auditoria a = new Auditoria(Fechas.fechaActualCompletaSql(), accion, auditor, solicitante, maquina, producto, armado, satisfactorio);
		abd.hacerPersistente(a);
	}
}

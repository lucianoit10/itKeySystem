package persistencia.dominio;

import java.sql.Timestamp;

public class Auditoria {

	protected Long id;
	protected Timestamp fecha;
	protected String accion;
	protected Usuario auditor;
	protected Persona solicitante;
	protected Maquina maquina;
	protected Producto producto;
	protected String extra; //aclaraciones o datos informativos
	protected boolean satisfactorio= false;
	
	
	public Auditoria(Timestamp fecha, String accion, Usuario auditor,
			Persona solicitante, Maquina maquina, Producto producto,
			String extra, boolean satisfactorio) {
		super();
		this.fecha = fecha;
		this.accion = accion;
		this.auditor = auditor;
		this.solicitante = solicitante;
		this.maquina = maquina;
		this.producto = producto;
		this.extra = extra;
		this.satisfactorio = satisfactorio;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Timestamp getFecha() {
		return fecha;
	}


	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}


	public String getAccion() {
		return accion;
	}


	public void setAccion(String accion) {
		this.accion = accion;
	}


	public Usuario getAuditor() {
		return auditor;
	}


	public void setAuditor(Usuario auditor) {
		this.auditor = auditor;
	}


	public Persona getSolicitante() {
		return solicitante;
	}


	public void setSolicitante(Persona solicitante) {
		this.solicitante = solicitante;
	}


	public Maquina getMaquina() {
		return maquina;
	}


	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public String getExtra() {
		return extra;
	}


	public void setExtra(String extra) {
		this.extra = extra;
	}


	public boolean isSatisfactorio() {
		return satisfactorio;
	}


	public void setSatisfactorio(boolean satisfactorio) {
		this.satisfactorio = satisfactorio;
	}
	
	
}

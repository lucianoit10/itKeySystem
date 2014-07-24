package funcionalidades;

import persistencia.dominio.*;

public abstract class funcionalidades {
	
	//persona - producto
	public abstract Boolean es_propietario (Persona persona, Producto producto);
	public abstract Boolean asignar_propietario (Persona persona, Producto producto);
	public abstract Boolean desvincular_propietario (Persona persona, Producto producto);
	//persona - maquina
	public abstract Boolean es_propietario_maquina (Persona persona, Maquina maquina);
	public abstract Boolean asignar_propietario_maquina (Persona persona, Maquina maquina);
	public abstract Boolean desvincular_propietario_maquina (Persona persona, Maquina maquina);	
	//esta activa
	public abstract Boolean esta_activada (PersonaProductoVersion persona_producto,Maquina maquina);	
	//activar
	public abstract Boolean activar (PersonaProductoVersion persona_producto,Maquina maquina, String key);

}
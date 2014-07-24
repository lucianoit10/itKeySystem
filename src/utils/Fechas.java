package utils;

public final class Fechas {

	public static java.util.Date fechaActual(){
		return new java.util.Date();
	}
	
	public static java.sql.Date fechaActualSql(){
		return new java.sql.Date(fechaActual().getTime());
	}

	public static java.sql.Timestamp fechaActualCompletaSql(){
		return new java.sql.Timestamp(fechaActual().getTime());
	}
	
	
	//Toma como parametro el getTime() de cualquier tipo de fecha
	
	public static java.util.Date toUtilDate(long fecha){
		return new java.util.Date (fecha);
	}

	public static java.sql.Date toSqlDate(long fecha){
		return new java.sql.Date (fecha);
	}

	public static java.sql.Timestamp toTimestampDate(long fecha){
		return new java.sql.Timestamp (fecha);
	}
}

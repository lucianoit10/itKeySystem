package utils;

import org.json.simple.*;

public class Json {
    
    /* *********************************** *
     * IMPLEMENTACION DEL LADO DEL CLIENTE *
     * *********************************** */
  
	//arma el mensaje con los datos para la activacion
    public static String convertirKeySolicitudAJson(byte[] key, String id_copia, String mac){
        JSONObject obj = new JSONObject();
        obj.put("contenido", key);
        obj.put("id_copia", id_copia);
        obj.put("mac", mac);
        return obj.toJSONString();
    }
      
    //interpreta el resultado de la operacion
    public static Boolean interpretarResultadoOperacionAJson(String res){
        try{
        	JSONObject cont = (JSONObject)JSONValue.parse(res);
        	return(Boolean) cont.get("resultado");
        } catch (Exception e){
            return false;
        }
    }

    /* *********************************** *
     * 		IMPLEMENTACION COMPARTIDO	   *
     * *********************************** */

    //interpreta la key
    public static byte[] interpretarKeyAJson(String key){
        try{
        	JSONObject cont = (JSONObject)JSONValue.parse(key);
        	return(byte[]) cont.get("contenido");
        } catch (Exception e){
            return null;
        }
    }
    
    /* *********************************** *
     * IMPLEMENTACION DEL LADO DEL SERVER *
     * *********************************** */

    //para pasar la key encriptada
    public static String convertirKeyAJson(byte[] key){
        JSONObject obj = new JSONObject();
        obj.put("contenido", key);
        return obj.toJSONString();
    }
    
    //para informar el exito o fracaso de la operacion
    public static String convertirResultadoOperacionAJson(Boolean res){
        JSONObject obj = new JSONObject();
        obj.put("contenido", res);
        return obj.toJSONString();
    }
    
    //recupera los datos necesarios para la validacion
    public static Mensaje_para_validar interpretarKeySolicitudAJson(String keys){
        try{
        	JSONObject cont = (JSONObject)JSONValue.parse(keys);
        	byte[] key = (byte[]) cont.get("contenido");
        	String id_copia = (String) cont.get("id_copia");
        	String mac = (String) cont.get("mac");
        	return new Mensaje_para_validar(key,id_copia,mac);
        } catch (Exception e){
            return null;
        }
    }
    
}

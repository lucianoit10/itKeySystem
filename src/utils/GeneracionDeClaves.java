package utils;

import java.security.MessageDigest;
import java.util.LinkedList;

public final class GeneracionDeClaves {

		private static String encryptionKey = "0123456789abcdef";	


		/* ***************************************************** *
		 * ***********   para claves de usuarios   ************* * 
		 * ***************************************************** *
		 * ***************************************************** *
		 */
		
		public static String MD5 (String cadena) throws Exception{
			byte[] bytesOfMessage = cadena.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			return new String(thedigest);
		}


		public static String nuevaClaveUsuario() {
			String keyAux = "";
			for (int i=0;i<7;i++){
				Integer aux = (int)(Math.random()*10/1);;
				keyAux+=aux.toString();
			}
			return keyAux;
		}

		/* ***************************************************** *
		 * ***************************************************** * 
		 * ***************************************************** *
		 * ***************************************************** *
		 */
		
		
		//genera la clave del producto 
		public static String generar_key (){
			try{
				return armarKey();
			}catch(Exception e){
				return encryptionKey;
			}
		} 		
		
		private static String armarKey() {
			String keyAux = "";
			int leng = 12; //son 12 dado q agrego 3 "-" y 1 "#"
			//completar la longitud
			for (int i=0;i<leng;i++){
				Integer aux = (int)(Math.random()*10/1);;
				keyAux+=aux.toString();
			}
			//dividir con -
			String split1 = keyAux.substring(0, 3);
			String split2 = keyAux.substring(3, 6);
			String split3 = keyAux.substring(6, 9);
			String split4 = keyAux.substring(9, 12);
			keyAux = split1+"-"+split2+"-"+split3+"-"+split4;
			//agrega caracter extra
			return "#"+keyAux;
		}



		/* ***************************************************** *
		 * ***************************************************** * 
		 * ***************************************************** *
		 * ***************************************************** *
		 */
		
		
		//genera la clave para encriptar y desencriptar la key del producto 
		public static String generar_encryption_key (int index){
			try{
				//index es el id de la copia o el producto (var si es clave de encriptacion por copia o producto)
				return perm().get(index);
			}catch(Exception e){
				return encryptionKey;
			}
		} 		
		
		private static LinkedList<String>  perm (){
			String initialValue = encryptionKey;
			int n = initialValue.length();   //Tipos para escoger
	        int r = initialValue.length();   //Elementos elegidos
	        LinkedList<String> res = new LinkedList<String> ();
	        PermRecursivo(initialValue.toCharArray(),"",res,n,r);
			return res;
		}

	    private static void PermRecursivo(char[] elem, String act,LinkedList<String>  res, int n, int r) {
	        if (n == 0) {
	            res.addLast(act);
	        } else {
	            for (int i = 0; i < r; i++) {
	            	// Controla que no haya repeticiones (que no este previamente en la cadena)
	                if (act.indexOf(elem[i]) < 0) { 
	                	PermRecursivo(elem, act + elem[i],res, n - 1, r);
	                }
	            }
	        }
	    }


}

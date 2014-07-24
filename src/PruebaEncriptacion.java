

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
 
import javax.crypto.Cipher;
 
public class PruebaEncriptacion {
  static String IV = "AAAAAAAAAAAAAAAA";
   
  public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return cipher.doFinal(plainText.getBytes("UTF-8"));
  }
 
  public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception{
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return new String(cipher.doFinal(cipherText),"UTF-8");
  }
  
  static String plaintext = 	"ddddde555fefeess"; /*Note null padding*/
  static String encryptionKey = "01234d56789acbef";
  
  
//genera la clave del producto 
		public static String generar_key (){
			try{
				String key;
				boolean repetida;
				do{
					repetida = false;
					key = completarKey();					
					if (esta_repetida(key.toString())){
						repetida = true;
					}
				}while(repetida);
				
				return key.toString();
			}catch(Exception e){
				return encryptionKey;
			}
		} 		
		
		
		
		private static String completarKey() {
			String keyAux = "";
			int leng = 12;
			//completar la longitud de
			for (int i=0;i<leng;i++){
				Integer aux = (int)(Math.random()*10/1);
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



		private static boolean esta_repetida(String key) {
			// TODO Auto-generated method stub
			//Chequea en base de datos si ya fue generada
			return false;
		}

  
  
  public static void main(String [] args) {
   /* try {
      plaintext=generar_key ();
      System.out.println("==Java==");
      System.out.println("plain:   " + plaintext);
 
      byte[] cipher = encrypt(plaintext, encryptionKey);
 
      System.out.print("cipher:  ");
      for (int i=0; i<cipher.length; i++)
        System.out.print(new Integer(cipher[i])+" ");
      System.out.println("");
 
      String decrypted = decrypt(cipher, encryptionKey);
 
      System.out.println("decrypt: " + decrypted);
 
    } catch (Exception e) {
      e.printStackTrace();
    } */
	  
  }
}
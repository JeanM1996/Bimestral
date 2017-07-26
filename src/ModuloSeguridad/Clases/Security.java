
package ModuloSeguridad.Clases;

/**
 *Clase Security.
 * Implementa metodos y llave para que sea efectivo el algoritmo de encriptacion
 * y desencriptacion AES128
 * @author Jean Paul Mosquera Arevalo
 */


public class Security {
    //Llave para desencriptar y encriptar.
    public static String key= "EncriptadoConAES";
    /**
     * Metodo para encriptar 
     * @param key llave para encriptar
     * @param clearText el texto a encriptar
     * @return  una cadena encriptada
     */
    public static String encodeWithAES(String key, String clearText) {
        try {
            return Aes128.encrypt(key, clearText);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    /**
     * Metodo para desencriptar
     * @param key llave para encriptar
     * @param cipherText una cadena encriptada
     * @return  una cadena desencriptada
     */
        public static String decodeWithAES(String key, String cipherText) {
        try {
            return Aes128.decrypt2(key, cipherText);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}

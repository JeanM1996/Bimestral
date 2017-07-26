package ModuloSeguridad.Clases;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Aes128 {
    private static final String CHAR_ENCODING = "UTF-8";
    private static final String HEX = "0123456789ABCDEF";

    public static String encrypt(String seed, String cleartext) throws Exception {
        return encrypt(seed, cleartext.getBytes(CHAR_ENCODING));
    }

    public static String encrypt(String seed, byte[] cleartext) throws Exception {
        return toHex(encrypt(getKey(seed), cleartext));
    }

    public static byte[] decrypt(String seed, String encrypted) throws Exception {
        return decrypt(getKey(seed), toByte(encrypted));
    }

    public static String decrypt2(String seed, String encrypted) throws Exception {
        return new String(decrypt(seed, encrypted), CHAR_ENCODING);
    }

    private static SecretKeySpec getKey(String myKey) throws Exception {
        return new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("SHA-1").digest(myKey.getBytes(CHAR_ENCODING)), 16), "AES");
    }

    private static byte[] encrypt(SecretKeySpec key, byte[] clear) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, key);
        return cipher.doFinal(clear);
    }

    private static byte[] decrypt(SecretKeySpec key, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(2, key);
        return cipher.doFinal(encrypted);
    }

    public static String toHex(String txt) {
        return toHex(txt.getBytes());
    }

    public static String fromHex(String hex) {
        return new String(toByte(hex));
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(i * 2, (i * 2) + 2), 16).byteValue();
        }
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null) {
            return "";
        }
        StringBuffer result = new StringBuffer(buf.length * 2);
        for (byte appendHex : buf) {
            appendHex(result, appendHex);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 15)).append(HEX.charAt(b & 15));
    }
}

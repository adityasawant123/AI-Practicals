//public class DES {
//}
import javax.crypto.*;
        import javax.crypto.spec.*;
public class DES {
    public static void main(String[] args) {
        try {
            String plainText = "Hello, DES!";
            String keyString = "12345678"; // 8-byte key for DES
            byte[] keyData = keyString.getBytes();
            SecretKeySpec key = new SecretKeySpec(keyData, "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedData = cipher.doFinal(plainText.getBytes());
            System.out.println("Encrypted: " + bytesToHex(encryptedData));
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedData = cipher.doFinal(encryptedData);
            System.out.println("Decrypted: " + new String(decryptedData));
        } catch (Exception e) {
            e.printStackTrace();
        }  }    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));  }
        return result.toString();
    }}

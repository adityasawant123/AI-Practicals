//public class AES {
//}
import javax.crypto.Cipher;
        import javax.crypto.KeyGenerator;
        import java.security.Key;
        import java.util.Base64;
        
public class AES {
    public static void main(String[] args) throws Exception {
        Key secretKey = generateSecretKey();
        String originalMessage = "Hello, AES!";
        System.out.println("Original Message: " + originalMessage);
        String encryptedMessage = encrypt(originalMessage, secretKey);
        System.out.println("Encrypted Message: " + encryptedMessage);
        String decryptedMessage = decrypt(encryptedMessage, secretKey);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }private static Key generateSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // 128-bit AES key size
        return keyGenerator.generateKey();
    } private static String encrypt(String plainText, Key secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    } private static String decrypt(String encryptedText, Key secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}

public class rail {
    public static String encrypt(String text, int rails) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < rails; i++) {
            for (int j = i; j < text.length(); j += rails * 2 - 2) {
                encrypted.append(text.charAt(j));
                if (i > 0 && i < rails - 1 && j + (rails - i - 1) * 2 < text.length()) {
                    encrypted.append(text.charAt(j + (rails - i - 1) * 2));
                }}}
        return encrypted.toString();
    }
    public static String decrypt(String text, int rails) {
        char[] decrypted = new char[text.length()];
        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = i; j < text.length(); j += rails * 2 - 2) {
                decrypted[j] = text.charAt(index++);
                if (i > 0 && i < rails - 1 && j + (rails - i - 1) * 2 < text.length()) {
                    decrypted[j + (rails - i - 1) * 2] = text.charAt(index++);
                }}}
        return new String(decrypted);
    }
    public static void main(String[] args) {
        String message = "Aditya";
        int rails = 3;
        String encrypted = encrypt(message.replaceAll("\\s+", ""), rails); // Remove spaces
        System.out.println("Encrypted: " + encrypted);
        String decrypted = decrypt(encrypted, rails);
        System.out.println("Decrypted: " + decrypted);
    }}

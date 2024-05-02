
import java.util.Arrays;
        import java.util.HashMap;
        import java.util.Map;
public class rail2 {
    static final String key = "Happy";
    static Map<Character, Integer> keyMap = new HashMap<>();
    static void setPermutationOrder() {
        Character[] sortedKeys = key.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        Arrays.sort(sortedKeys);
        for (int i = 0; i < key.length(); i++) {
            keyMap.put(sortedKeys[i], i);
        }
    }static String encryptMessage(String msg) {
        int col = key.length();
        int row = (int) Math.ceil((double) msg.length() / col);
        char[][] matrix = new char[row][col];
        StringBuilder cipher = new StringBuilder();
        int k = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (k < msg.length()) {
                    matrix[i][j] = msg.charAt(k++);
                } else {
                    matrix[i][j] = '_'; // Padding
                }}}
// Read off the matrix column-wise using the permuted key order
        for (int n = 0; n < col; n++) {
            int columnIndex = keyMap.get(key.charAt(n));
            for (int i = 0; i < row; i++) {
                cipher.append(matrix[i][columnIndex]);}}
        return cipher.toString();
    } static String decryptMessage(String cipher) {
        int col = key.length();
        int row = cipher.length() / col;
        char[][] cipherMat = new char[row][col];
        StringBuilder msg = new StringBuilder();
// Decrypt using the permuted key
        int k = 0;
        for (Map.Entry<Character, Integer> entry : keyMap.entrySet()) {
            int columnIndex = entry.getValue();
            for (int i = 0; i < row; i++) {
                cipherMat[i][columnIndex] = cipher.charAt(k++);
            }}
// Read the message row-wise
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (cipherMat[i][j] != '_') {
                    msg.append(cipherMat[i][j]);
                }}}
        return msg.toString();
    }
    public static void main(String[] args) {
        String msg = "Aditya";
        setPermutationOrder();
// Encrypt the message
        String cipher = encryptMessage(msg);
        System.out.println("Encrypted Message: " + cipher);
// Decrypt the message
        String decryptedMsg = decryptMessage(cipher);
        System.out.println("Decrypted Message: " + decryptedMsg);
    }}

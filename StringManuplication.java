//public class StringManuplication {
//}
public class StringManuplication{
        public static void main(String[] args)
    {
    // Define the string
    String inputString ="Hello World";
    // Apply bitwise AND and XOR operations on each character
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < inputString.length(); i++)
        {
        char originalChar = inputString.charAt(i);
        // Apply bitwise AND with 127
        char andResult = (char) (originalChar & 127);
        // Apply bitwise XOR with 127
        char xorResult = (char) (originalChar^ 127);
        System.out.println("Original: " + originalChar+" AND Result: " + andResult + ", XOR Result: " + xorResult);
                result.append(xorResult); }
 System.out.println("\nFinal Result: "+result.toString()); }}
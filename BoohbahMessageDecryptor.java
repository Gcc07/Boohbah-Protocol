import java.io.*;

/**
 * Boohbah Message Decryptor
 * 
 * STORY: The Boohbah have intercepted a secret message from an unknown enemy.
 * Intelligence suggests it's encrypted with a Caesar cipher (shift unknown).
 * Your mission: Read the encrypted message, decrypt it, and write the 
 * decrypted message to a file. Then determine who is attacking!
 */
public class BoohbahMessageDecryptor {
    
    /**
     * Decrypts a Caesar cipher encrypted message by shifting each letter back by the given shift amount.
     * Non-letter characters remain unchanged.
     * 
     * @param encrypted the encrypted message
     * @param shift the number of positions to shift back
     * @return the decrypted message
     */
    public static String decrypt(String encrypted, int shift) {
        StringBuilder decrypted = new StringBuilder();
        
        for (char c : encrypted.toCharArray()) {
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    // Shift uppercase letters back by the given amount
                    char shifted = (char) (c - shift);
                    if (shifted < 'A') {
                        shifted = (char) (shifted + 26); // Wrap around
                    }
                    decrypted.append(shifted);
                } else {
                    // Shift lowercase letters back by the given amount
                    char shifted = (char) (c - shift);
                    if (shifted < 'a') {
                        shifted = (char) (shifted + 26); // Wrap around
                    }
                    decrypted.append(shifted);
                }
            } else {
                // Keep non-letter characters as-is
                decrypted.append(c);
            }
        }
        
        return decrypted.toString();
    }
    
    public static void main(String[] args) {
        String inputFile = "encrypted_message.txt";
        String outputFile = "decrypted_message.txt";
        // Include file operations within the try parentheses so resources are automatically closed
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile)); FileWriter writer = new FileWriter(outputFile)){
            // Read the encrypted message from the file (one line)
            String encryptedMessage;
            while((encryptedMessage = reader.readLine()) != null){
                System.out.println(encryptedMessage);
            for (int i = 1; i < 26; i++) {
                System.out.println(decrypt(encryptedMessage, i));
            }
            int correctShift = 3;
            String decryptedMessage = decrypt(encryptedMessage, correctShift);
            writer.write(decryptedMessage);

            System.out.print("=== BOOHBAH INTELLIGENCE REPORT ===" + //
            "\nEncrypted message: " + encryptedMessage + //
            "\nDecrypted message: " + decryptedMessage + //
            "\nDecrypted message saved to: " + outputFile + "\n" + //
            "STATUS: THREAT IDENTIFIED!");
            }
        }   catch(IOException e){
            System.out.println("Something went wrong: " + e);
        }
            
    
    }
}

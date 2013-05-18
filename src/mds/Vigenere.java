package mds;
import static java.lang.System.*;

/**
 *
 * @author Gabriel Budau
 */
public class Vigenere extends SimpleCipher {
 
    private final char[] key;
 
    public Vigenere(String key) {
        this.key = key.toCharArray();
    }
 
    @Override
    public String encrypt(String plainText) {
        int keyshift = 0;
        String encrypted = "";
 
        for (char c : plainText.toCharArray()) {
            int pos = this.getValidCharacterIndex(c);
            if (pos != -1) {
                int keyIndex = this.getValidCharacterIndex(key[keyshift++ % key.length]);
 
                encrypted += this.getValidCharacterForIndex((keyIndex + pos) % 26);
 
            } else {
                encrypted += c;
            }
        }
        return encrypted;
    }
 
    @Override
    public String decrypt(String encrypted) {
        int keyshift = 0;
        String plainText = "";
 
        for (char c : encrypted.toCharArray()) {
            int pos = this.getValidCharacterIndex(c);
            if (pos != -1) {
                int keyIndex = this.getValidCharacterIndex(key[keyshift++ % key.length]);
                int r = (pos - keyIndex) % 26;
                if (r < 0) {
                    r += 26;
                }
                plainText += this.getValidCharacterForIndex(r);
 
            } else {
                plainText += c;
            }
        }
        return plainText;
    }
    

}

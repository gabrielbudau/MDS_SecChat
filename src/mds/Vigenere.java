package mds;

import static java.lang.System.*;

/**
 *
 * @author Gabriel Budau
 */
public class Vigenere {
    
    static String encrypt(String text, final String key) {
        String res = "";
        String key2 = "";
        
        while(text.length() > key2.length()){
            key2 += key;
        }
        System.out.println(key2);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            res += (char) ((c + key2.charAt(i)) % 256);
        }
        return res;
    }

    static String decrypt(String text, final String key) {
        String res = "";
        String key2 = "";
        while(text.length() > key2.length()){
            key2 += key;
        }
        System.out.println(key2);
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            res += (char) ((c - key2.charAt(i)) % 256);
        }
        return res;
    }
}

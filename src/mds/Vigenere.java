package mds;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Budau
 */
public class Vigenere {

    private static final String charset = "`1234567890-=qwertyuiop[]\';lkjhgfdsazxcvbn\"m,./~!@# $%^&*()_+|}{POIUYTREWQASDFGHJKL:?><MNBVCXZ";

    private static int contains(char c) {
        for (int i = 0; i < charset.length(); i++) {
            if (c == charset.charAt(i)) {
                return i;
            }
        }
        return -1;
    }

    static String encrypt(String text, final String key) {
        String res = "";
        String key2 = "";

        while (text.length() > key2.length()) {
            key2 += key;
        }
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index;
            if ((index = Vigenere.contains(c)) != -1) {
                res += charset.charAt((index + Vigenere.contains(key2.charAt(i))) % charset.length());
            }
        }
        return res;
    }

    static String decrypt(String text, final String key) {
        String res = "";
        String key2 = "";
        while (text.length() > key2.length()) {
            key2 += key;
        }
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index;
            if ((index = Vigenere.contains(c)) != -1) {
                res += charset.charAt(Math.abs((index - Vigenere.contains(key2.charAt(i))) % charset.length()));
            }
            //res += (char) ((c - key2.charAt(i)) % 256);
        }
        return res;
    }
    
    public static void main(String[] args){
        try {
            Date ddd = new Date();
            String key = Date.makeSHA1Hash(ddd.dateString);
            String mes = "gabriel budau a facut tot proiectul";
            String enc = Vigenere.encrypt(mes, key);
            String dec = Vigenere.decrypt(enc, key);
            System.out.println("Mesaj: " + mes);
            System.out.println("Key: " + key);
            System.out.println("Ecnrypted: " + enc);
            System.out.println("Decripted: " + dec);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Vigenere.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package mds;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Budau
 */
public class Date {

    Calendar c = null;
    java.util.Date date = null;
    String dateString = null;

    Date() {
        c = Calendar.getInstance();
        date = c.getTime();
        this.dateString = this.getDateString();
    }

    private String getDateString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }
    
/*
    public static void main(String[] args) {
        try {
            Date date = new Date();
            System.out.println(Date.makeSHA1Hash(date.dateString));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Date.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
    public static String makeSHA1Hash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.reset();
        byte[] buffer = input.getBytes();
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (int i = 0; i < digest.length; i++) {
            hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
        }
        return hexStr;
    }
}
/*
 * 
 * 7da1abc09bdebaf7e8566c4035bb2c8c6efcff27
 * 
 */
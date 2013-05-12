package mds;

import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JLabel;

/**
 *
 * @author Gabriel Budau
 */
public class Utils {

    private static String IV = "AAAAAAAAAAAAAAAA";

    /*
     * Seteaza status-ul user-ului "_username" din BD cu "_status" 
     * optiuni posibile _status = offline | online
     */
    public static boolean changePass(String user, String pass, String email) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "root");
            PreparedStatement pst = null;
            String update = "update `users` set `user_pass` = '" + pass + "' where `user_name` = '" + user + "' and `email` = '" + email + "';";
            pst = con.prepareStatement(update);
            int i = pst.executeUpdate();
            if (i == 0) {
                return false;
            } else {
                return true;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            return false;
        }
    }

    public static void setStatus(String _username, String _status) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "root");
            PreparedStatement pst = null;
            String update = "update `users` set `status` = '" + _status + "' where `user_name` = '" + _username + "';";
            pst = con.prepareStatement(update);
            pst.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Populeaza "List" cu toti useri din BD
     * sunt ordonati alfabetic dupa user_name
     */
    public static void getList(javax.swing.JList List) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con;

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "root");
            PreparedStatement pst = null;
            String query = "SELECT user_name, status FROM users ORDER By 1 ;";
            pst = con.prepareStatement(query);

            ResultSet rs = pst.executeQuery();
            final ArrayList<String> strings = new ArrayList<>();
            final ArrayList<Component> cmp = new ArrayList<>();

            while (rs.next()) {
                String username = rs.getString("user_name");
                String status = rs.getString("status");
                JLabel tmp = new JLabel();
                if (status.equals("online")) {
                    tmp.setText("<html><font color=green><p style = \"padding: 3px 0px 3px 10px;\">" + username + "</font></html>");
                    cmp.add(tmp);
                } else {
                    tmp.setText("<html><font color=blue><p style = \"padding: 3px 0px 3px 10px;\">" + username + "</font></html>");
                    cmp.add(tmp);
                }

            }
            List.setModel(new javax.swing.AbstractListModel() {
                @Override
                public int getSize() {
                    return cmp.size();
                }

                @Override
                public Object getElementAt(int i) {
                    return ((JLabel) cmp.get(i)).getText();
                }
            });

            rs.close();
            con.close();
            pst.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
    /*
     * Intoarce true daca exista username-ul in BD
     * altfel intoarce false daca nu exista
     */

    public static boolean checkUser(String user, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "root");
            PreparedStatement pst = null;
            String query = "SELECT user_name FROM users WHERE user_name = '" + user + "' and user_pass = '" + pass + "' ;";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            rs.last();
            if (rs.getRow() == 1 /*&& rs.getString("status").equals("offline")*/) {
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return true;
    }
    /*
     * Adauga un nou user in baza de date
     */

    public static boolean addUser(String user, String pass, String first_name, String last_name, String email) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://uleimasline.ro:2083/uleimasl_users", "uleimasl_mdsSecC", "1ph{+HHvI]3W");
            PreparedStatement pst = null;
            //INSERT INTO `database`.`users` (`user_name`, `user_pass`, `status`, `first_name`, `last_name`, `email`) VALUES ('andrei', 'root', 'offline', 'Andrei', 'Butnaru', 'andreibutnaru@gmail.com');
            String insert = "insert into `uleimasl_users`.`users` (`user_name`, `user_pass`, `status`, `first_name`, `last_name`, `email`) values ("
                    + "'" + user + "', "
                    + "'" + pass + "', "
                    + "'offline', "
                    + "'" + first_name + "', "
                    + "'" + last_name + "', "
                    + "'" + email + "');";
            pst = con.prepareStatement(insert);
            pst.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /*
     * Seteaza atributele unui font ca fiind subliniat
     */
    public static Map setUnderline() {
        final Map attributes = (new Font("Consolas", 10, 12)).getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        return attributes;
    }

    /*
     * Seteaza atributele unui font ca fiind subliniat ingrosat
     */
    public static Map setUnderlineBold() {
        final Map attributes = (new Font("Consolas", 1, 12)).getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        return attributes;
    }

    public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(plainText.getBytes("UTF-8"));
    }

    public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(cipher.doFinal(cipherText), "UTF-8");
    }

    public static String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }
        System.out.println("Decimal : " + temp.toString());

        return sb.toString();
    }
}
package mds;

import java.awt.*;

import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import javax.swing.JLabel;

/**
 *
 * @author Gabriel Budau
 */
public class Utils {

    /*
     * Seteaza status-ul user-ului "_username" din BD cu "_status" 
     * optiuni posibile _status = offline | online
     */
    public static boolean changePass(String user, String pass, String email) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://89.42.216.160/uleimasl_users", "uleimasl_mdsSecC", "1ph{+HHvI]3W");
            PreparedStatement pst = null;
            String update = "update `users` set `user_pass` = '" + pass + "' where `user_name` = '" + user + "' and `email` = '" + email + "';";
            pst = con.prepareStatement(update);
            int i = pst.executeUpdate();
            if (i == 0) {
                return false;
            } else {
                return true;
            }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            return false;
        }
    }

    public static void setStatus(String _username, String _status) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://89.42.216.160/uleimasl_users", "uleimasl_mdsSecC", "1ph{+HHvI]3W");
            PreparedStatement pst = null;
            String update = "update `users` set `status` = '" + _status + "' where `user_name` = '" + _username + "';";
            pst = con.prepareStatement(update);
            pst.executeUpdate();

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
        }
    }

    /*
     * Populeaza "List" cu toti useri din BD
     * sunt ordonati alfabetic dupa user_name
     */
    public static void getList(javax.swing.JList List) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://89.42.216.160/uleimasl_users", "uleimasl_mdsSecC", "1ph{+HHvI]3W");
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
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
        }
    }
    /*
     * Intoarce true daca exista username-ul in BD
     * altfel intoarce false daca nu exista
     */

    public static boolean checkuserByusername(String user) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://89.42.216.160/uleimasl_users", "uleimasl_mdsSecC", "1ph{+HHvI]3W");
            PreparedStatement pst = null;
            String query = "SELECT user_name FROM users WHERE user_name = '" + user + "' ;";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            rs.last();
            if (rs.getRow() == 1 /*&& rs.getString("status").equals("offline")*/) {
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
        }
        return true;
    }

    public static boolean checkUser(String user, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://89.42.216.160/uleimasl_users", "uleimasl_mdsSecC", "1ph{+HHvI]3W");
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
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
        }
        return true;
    }
    /*
     * Adauga un nou user in baza de date
     */
    public static boolean addStat(String user) {
        try {
            Calendar c = null;
            java.util.Date date = null;
            c = Calendar.getInstance();
            date = c.getTime();
            String dateString = new SimpleDateFormat("dd-MM-yyyy").format(date);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://89.42.216.160/uleimasl_users", "uleimasl_mdsSecC", "1ph{+HHvI]3W");
            PreparedStatement pst = null;
            String select = "select user_id from users where user_name = '" + user + "';";
            pst = con.prepareStatement(select);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int user_id = rs.getInt("user_id");
            String insert = ""
                    + "insert into `uleimasl_users`.`stat` "
                    + "VALUES (" + user_id + ","
                    + " STR_TO_DATE('" + dateString + "', '%d-%m-%Y'), "
                    + "null, null, null, null);";
            PreparedStatement pst3 = con.prepareStatement(insert);
            pst3.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public static boolean addUser(String user, String pass, String first_name, String last_name, String email) {



        try {
            if (!Utils.checkuserByusername(user)) {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection("jdbc:mysql://89.42.216.160/uleimasl_users", "uleimasl_mdsSecC", "1ph{+HHvI]3W");
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
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
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
}
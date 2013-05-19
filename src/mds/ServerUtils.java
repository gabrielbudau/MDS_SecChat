package mds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gabriel Budau
 */
public class ServerUtils {

    public static String userInfo(String user) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://89.42.216.160/uleimasl_users",
                    "uleimasl_mdsSecC", "1ph{+HHvI]3W");
            PreparedStatement pst = null;
            String select = "select user_id from users where user_name = '" + user + "';";
            pst = con.prepareStatement(select);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int user_id = rs.getInt("user_id");
            
            select ="select signup_date, last_signup, times_loged_in, times_kicked, forgot_pass "
                    + "from stat "
                    + "where user_id = '" + user_id + "' ;";
            
            pst = con.prepareStatement(select);
            rs = pst.executeQuery();
            rs.next();
            String su = rs.getString("signup_date");
            String last_su = rs.getString("last_signup");
            String times_loged_in = rs.getString("times_loged_in");
            String times_kicked = rs.getString("times_kicked");
            String forgot = rs.getString("forgot_pass");
            String out = "SignUp date: " + su + "\n"
                    + "Last signUp: " + last_su + "\n"
                    + "Times logedIn: " + times_loged_in + "\n"
                    + "Times kicked: " + times_kicked + "\n"
                    + "Times forgot password: " + forgot + "\n";
            return out;
        } catch (InstantiationException | IllegalAccessException | SQLException | ClassNotFoundException ex) {
            //ex.printStackTrace();
        }
        return null;
    }
}

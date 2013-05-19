/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriel Budau
 */
public class ServerUtilsTest {
    
   
    @Test
    public void testUserInfo() {
        System.out.println("userInfo");
        String user = "angelica";
        String expResult = "SignUp date: 2013-05-19 Last signUp: null Times logedIn: null Times kicked: null Times forgot password: null ";
        String result = ServerUtils.userInfo(user);
        assertEquals(expResult, result);
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mds;

import java.util.Map;
import javax.swing.JList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriel
 */
public class UtilsTest {
    
    @Test
    public void testChangePass() {
        String user = "gabriel";
        String pass = "root";
        String email = "gabriel@budau.com";
        boolean expResult = true;
        boolean result = Utils.changePass(user, pass, email);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddUser() {
        String user = "gabriel";
        String pass = "root";
        String first_name = "Gabriel";
        String last_name = "Budau";
        String email = "gabriel@budau.com";
        boolean expResult = true;
        boolean result = Utils.addUser(user, pass, first_name, last_name, email);
        assertEquals(expResult, result);
    }
}
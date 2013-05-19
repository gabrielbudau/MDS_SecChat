/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mds;

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
    public void testAddStat() {
        String user = "angelica";
        boolean expResult = true;
        boolean result = Utils.addStat(user);
        assertEquals(expResult, result);
    }

    @Test
    public void testAddUser() {
        String user = "angelica";
        String pass = "root";
        String first_name = "Angelica";
        String last_name = "Neata";
        String email = "angelica@neata.com";
        boolean expResult = true;
        boolean result = Utils.addUser(user, pass, first_name, last_name, email);
        assertEquals(expResult, result);
    }
}
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
    
    public UtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of changePass method, of class Utils.
     */
    
    
    @Test
    public void testChangePass() {
        System.out.println("changePass");
        String user = "";
        String pass = "";
        String email = "";
        boolean expResult = false;
        boolean result = Utils.changePass(user, pass, email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class Utils.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String _username = "";
        String _status = "";
        Utils.setStatus(_username, _status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getList method, of class Utils.
     */
    @Test
    public void testGetList() {
        System.out.println("getList");
        JList List = null;
        Utils.getList(List);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUser method, of class Utils.
     */
    @Test
    public void testCheckUser() {
        System.out.println("checkUser");
        String user = "";
        String pass = "";
        boolean expResult = false;
        boolean result = Utils.checkUser(user, pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class Utils.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        String user = "gabriel";
        String pass = "root";
        String first_name = "Gabriel";
        String last_name = "Budau";
        String email = "gabriel@budau.com";
        boolean expResult = true;
        boolean result = Utils.addUser(user, pass, first_name, last_name, email);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUnderline method, of class Utils.
     */
    @Test
    public void testSetUnderline() {
        System.out.println("setUnderline");
        Map expResult = null;
        Map result = Utils.setUnderline();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUnderlineBold method, of class Utils.
     */
    @Test
    public void testSetUnderlineBold() {
        System.out.println("setUnderlineBold");
        Map expResult = null;
        Map result = Utils.setUnderlineBold();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encrypt method, of class Utils.
     */
    @Test
    public void testEncrypt() throws Exception {
        System.out.println("encrypt");
        String plainText = "";
        String encryptionKey = "";
        byte[] expResult = null;
        byte[] result = Utils.encrypt(plainText, encryptionKey);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decrypt method, of class Utils.
     */
    @Test
    public void testDecrypt() throws Exception {
        System.out.println("decrypt");
        byte[] cipherText = null;
        String encryptionKey = "";
        String expResult = "";
        String result = Utils.decrypt(cipherText, encryptionKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
}
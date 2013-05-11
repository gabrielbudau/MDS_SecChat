/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mds;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AESTest {
    
    public AESTest() {
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
     * Test of FFMul method, of class AES.
     */
    @Test
    public void testFFMul() {
        System.out.println("FFMul");
        byte a = 0;
        byte b = 0;
        byte expResult = 0;
        byte result = AES.FFMul(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encryptBloc method, of class AES.
     */
    @Test
    public void testEncryptBloc() {
        System.out.println("encryptBloc");
        byte[] in = null;
        byte[] expResult = null;
        byte[] result = AES.encryptBloc(in);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decryptBloc method, of class AES.
     */
    @Test
    public void testDecryptBloc() {
        System.out.println("decryptBloc");
        byte[] in = null;
        byte[] expResult = null;
        byte[] result = AES.decryptBloc(in);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encrypt method, of class AES.
     */
    @Test
    public void testEncrypt() {
        try {
            System.out.println("encrypt");
            byte[] in = "gabrielbudau".getBytes("US-ASCII");
            byte[] key = "password".getBytes("US-ASCII");
            byte[] expResult = "KwFofhWkjlEmJqrGTIFrNg/IViU=".getBytes("US-ASCII");
            byte[] result = AES.encrypt(in, key);
            assertArrayEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(AESTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of decrypt method, of class AES.
     */
    @Test
    public void testDecrypt() {
        System.out.println("decrypt");
        byte[] in = null;
        byte[] key = null;
        byte[] expResult = null;
        byte[] result = AES.decrypt(in, key);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class AES.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        AES.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
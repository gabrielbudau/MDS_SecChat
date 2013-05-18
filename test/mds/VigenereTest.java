/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mds;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriel Budau
 */
public class VigenereTest {
    
    public VigenereTest() {
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
     * Test of encrypt method, of class Vigenere.
     */
    @Test
    public void testEncrypt() {
        System.out.println("encrypt");
        String plainText = "mesajDeCripriptat";
        Vigenere instance = new Vigenere("password");
        String expResult = "beksfDsCilerahpok";
        String result = instance.encrypt(plainText);
        assertEquals(expResult, result);
    }

    /**
     * Test of decrypt method, of class Vigenere.
     */
    @Test
    public void testDecrypt() {
        System.out.println("decrypt");
        String encrypted = "beksfDsCilerahpok";
        Vigenere instance = new Vigenere("password");
        String expResult = "mesajDeCripriptat";
        String result = instance.decrypt(encrypted);
        assertEquals(expResult, result);
        
    }
}
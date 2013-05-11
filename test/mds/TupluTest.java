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
 * @author Gabriel
 */
public class TupluTest {
    
    public TupluTest() {
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
     * Test of getX method, of class Tuplu.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Tuplu instance = new Tuplu();
        Object expResult = null;
        Object result = instance.getX();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getY method, of class Tuplu.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Tuplu instance = new Tuplu();
        Object expResult = null;
        Object result = instance.getY();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setX method, of class Tuplu.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        Object _x = null;
        Tuplu instance = new Tuplu();
        instance.setX(_x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setY method, of class Tuplu.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        Object _y = null;
        Tuplu instance = new Tuplu();
        instance.setY(_y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
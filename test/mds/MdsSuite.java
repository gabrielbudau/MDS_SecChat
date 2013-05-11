/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mds;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Gabriel
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({mds.ClientTest.class, mds.ForgotMyPasswordTest.class, mds.LoginTest.class, mds.UtilsTest.class, mds.SignUpTest.class, mds.TupluTest.class, mds.AESTest.class})
public class MdsSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
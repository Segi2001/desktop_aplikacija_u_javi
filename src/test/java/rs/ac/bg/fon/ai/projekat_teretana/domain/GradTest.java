/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Stefan
 */
public class GradTest {

    Grad g;

    public GradTest() {
    }

    @BeforeEach
    public void setUp() {
        g = new Grad();
    }

    @AfterEach
    public void tearDown() {
        g = null;
    }

    /**
     * Test of getIdGrada method, of class Grad.
     */
    @Test
    void testGrad() {

        assertNotNull(g);
        assertNull(g.getNaziv());
        assertEquals(0, g.getIdGrada());

    }

    @Test
    void testGradParam() {

        g = new Grad(0, "Beograd");

        assertNotNull(g);
        assertEquals("Beograd", g.getNaziv());
        assertEquals(0, g.getIdGrada());

    }

    @Test
    void testSetNaziv() {

        g.setNaziv("Beograd");

        assertEquals("Beograd", g.getNaziv());
    }

    @Test
    void testSetNazivNull() {

        assertThrows(java.lang.NullPointerException.class, () -> g.setNaziv(null));
    }

    @Test
    void testSetNazivPrazan() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> g.setNaziv(""));

    }
    
    @Test
    void testStringGrad(){
        
        g.setNaziv("Beograd");
        
        String s=g.toString();
        
        assertTrue(s.contains("Beograd"));
        
        
        
    }
    
    @ParameterizedTest
    @CsvSource({
        
        "Beograd,Beogradd,false",
        "Beograd,Beograd,true"
    })
    
    void testEquals(String naziv1,String naziv2,boolean eq){
        
        g.setNaziv(naziv1);
        
        Grad g2=new Grad();
        g2.setNaziv(naziv2);
        
        assertEquals(eq, g.equals(g2));
    }
    
    @Test
    void testEqualsObject() {

        Grad g2 = g;

        assertTrue(g.equals(g2));
    }

    @Test
    void testEqualsObjectNull() {

        assertFalse(g.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {

        assertFalse(g.equals(new Trening()));
    }

}

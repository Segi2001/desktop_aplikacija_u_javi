/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.domain;

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
public class StatistikaTest {

    Statistika s;

    public StatistikaTest() {
    }

    @BeforeEach
    public void setUp() {
        s = new Statistika();
    }

    @AfterEach
    public void tearDown() {
        s = null;
    }

    @Test
    void testStat() {

        assertNotNull(s);
        assertEquals(0, s.getId());
        assertEquals(0, s.getProcenatMasti());
        assertEquals(0, s.getProcenatMisica());
        assertEquals(0, s.getTezinaUKG());

    }

    @Test
    void testStatParam() {

        s = new Statistika(0, 25, 37, 100);
        assertNotNull(s);
        assertEquals(0, s.getId());
        assertEquals(25, s.getProcenatMasti());
        assertEquals(37, s.getProcenatMisica());
        assertEquals(100, s.getTezinaUKG());

    }

    @Test
    void testSetMasti() {

        s.setProcenatMasti(25);

        assertEquals(25, s.getProcenatMasti());

    }

    @Test
    void testSetMastiManjeOdNule() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> s.setProcenatMasti(-10));

    }

    @Test
    void testSetMisici() {

        s.setProcenatMisica(35);;

        assertEquals(35, s.getProcenatMisica());
    }

    @Test
    void testSetMisiciManjeOdNule() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> s.setProcenatMisica(-10));

    }

    @Test
    void testSetTezine() {

        s.setTezinaUKG(100);

        assertEquals(100, s.getTezinaUKG());
    }

    @Test
    void testSetTezineNull() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> s.setTezinaUKG(-10));

    }

    @Test
    void testString() {

        s = new Statistika(0, 25, 37, 100);

        String str = s.toString();

        assertTrue(str.contains("25"));
        assertTrue(str.contains("37"));
        assertTrue(str.contains("100"));

    }

    @ParameterizedTest
    @CsvSource({
        "25,35,100,26,35,100,false",
        "25,35,100,25,36,100,false",
        "25,35,100,25,35,101,false",
        "25,35,100,26,36,100,false",
        "25,35,100,26,35,101,false",
        "25,35,100,25,36,101,false",
        "25,35,100,26,36,101,false",
        "25,35,100,25,35,100,true",
    })
    
    void testEquals(double mast1,double misici1,double tezina1,double mast2,double misici2,double tezina2,boolean eq){
        
        s.setProcenatMasti(mast1);
        s.setProcenatMisica(misici1);
        s.setTezinaUKG(tezina1);
        
        Statistika s2=new Statistika();
        s2.setProcenatMasti(mast2);
        s2.setProcenatMisica(misici2);
        s2.setTezinaUKG(tezina2);
        
        assertEquals(eq,s.equals(s2));
        
    }
    
    @Test
    void testEqualsObject() {

        Statistika s2=s;

        assertTrue(s2.equals(s));
    }

    @Test
    void testEqualsObjectNull() {

        assertFalse(s.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {

        assertFalse(s.equals(new Trener()));
    }
    


}

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
public class TipTreningaTest {

    TipTreninga tip;

    public TipTreningaTest() {
    }

    @BeforeEach
    public void setUp() {
        tip = new TipTreninga();
    }

    @AfterEach
    public void tearDown() {
        tip = null;
    }

    /**
     * Test of getIdTipa method, of class TipTreninga.
     */
    @Test
    void testTipTr() {

        assertNotNull(tip);
        assertEquals(0, tip.getIdTipa());
        assertNull(tip.getNazivTipa());
        assertNull(tip.getOpis());

    }

    @Test
    void testTipParam() {

        tip=new TipTreninga(0, "Kondicioni trening", "Kondicija");
        
        assertNotNull(tip);
        assertEquals(0, tip.getIdTipa());
        assertEquals("Kondicioni trening",tip.getNazivTipa());
        assertEquals("Kondicija", tip.getOpis());
        
        

    }
    
    @Test
    void testSetNaziv(){
        
        tip.setNazivTipa("Kondicioni trening");
        
        assertEquals("Kondicioni trening", tip.getNazivTipa());
    }
    
    @Test
    void testSetNazivNull() {

        assertThrows(java.lang.NullPointerException.class, () -> tip.setNazivTipa(null));
    }

    @Test
    void testSetNazivPrazan() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> tip.setNazivTipa(""));

    }
    
    
    
     @Test
    void testSetOpis(){
        
        tip.setOpis("Kondicija");
        
        assertEquals("Kondicija", tip.getOpis());
    }
    
    @Test
    void testSetOpisNull() {

        assertThrows(java.lang.NullPointerException.class, () -> tip.setOpis(null));
    }

    @Test
    void testSetOpisPrazan() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> tip.setOpis(""));

    }
    
    @Test
    void testString(){
        
        tip.setNazivTipa("Kondicioni trening");
        
        String s=tip.toString();
        
        assertTrue(s.contains("Kondicioni trening"));
        
        
        
    }
    
    @ParameterizedTest
    @CsvSource({
        
        "Trening snage,Trening snagee,false",
        "Trening snage,Trening snage,true"
    })
    
    void testEquals(String naziv1,String naziv2,boolean eq){
        
        tip.setNazivTipa(naziv1);
        
        TipTreninga tip2=new TipTreninga();
        
        
        tip2.setNazivTipa(naziv2);
        
        assertEquals(eq, tip.equals(tip2));
        
    }
    
    @Test
    void testEqualsObject() {

        TipTreninga tip2=tip;

        assertTrue(tip.equals(tip2));
    }

    @Test
    void testEqualsObjectNull() {

        assertFalse(tip.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {

        assertFalse(tip.equals(new Trening()));
    }
    

}

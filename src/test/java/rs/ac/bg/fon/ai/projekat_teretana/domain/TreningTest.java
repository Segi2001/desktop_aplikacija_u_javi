/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.domain;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class TreningTest {

    Trening t;

    public TreningTest() {
    }

    @BeforeEach
    public void setUp() {
        t = new Trening();
    }

    @AfterEach
    public void tearDown() {
        t = null;
    }

    /**
     * Test of getIdTreninga method, of class Trening.
     */
    /**
     * Test of tableName method, of class Trening.
     */
    @Test
    void testTrening() {

        assertNotNull(t);
        assertNull(t.getTrener());
        assertNull(t.getTip());
        assertNull(t.getDatumTreninga());
        assertEquals(0, t.getIdTreninga());
        assertEquals(0, t.getTrajanjeUMin());
        assertEquals(0, t.getCena());

    }

    @Test
    void testTreningParam() {

        Date date = new Date(2024, 6, 10);
        t = new Trening(0, 1500, date, 60,
                new TipTreninga(0, "Kondicioni trening", "Kondicija"),
                new Trener(0, "Stefan", "Segrt", "0617334567", true, 10));

        assertNotNull(t);
        assertEquals(0, t.getIdTreninga());
        assertEquals(1500, t.getCena());
        assertEquals(date, t.getDatumTreninga());
        assertEquals(60, t.getTrajanjeUMin());
        assertEquals(0, t.getTip().getIdTipa());
        assertEquals("Kondicioni trening", t.getTip().getNazivTipa());
        assertEquals("Kondicija", t.getTip().getOpis());
        assertEquals(0, t.getTrener().getIdTrenera());
        assertEquals("Stefan", t.getTrener().getIme());
        assertEquals("Segrt", t.getTrener().getPrezime());
        assertEquals("0617334567", t.getTrener().getKontakt());
        assertEquals(true, t.getTrener().isSertifikat());
        assertEquals(10, t.getTrener().getGodineIskustva());

    }

    @Test
    void testSetCena() {

        t.setCena(1500);

        assertEquals(1500, t.getCena());

    }

    @Test
    void testSetCenaManjeOdNule() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> t.setCena(-5));

    }

    @Test
    void testSetDate() throws ParseException {

        String datumStr = "2022-15-6";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date datum = sdf.parse(datumStr);
        t.setDatumTreninga(datum);

        assertEquals(datum, t.getDatumTreninga());

    }

    @Test
    void testSetDateNull() {

        assertThrows(java.lang.NullPointerException.class, () -> t.setDatumTreninga(null));

    }

    @Test
    void testSetDateBuducnost() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> t.setDatumTreninga(new Date(2040, 3, 2)));

    }

    @Test
    void testSetTrajanje() {

        t.setTrajanjeUMin(60);

        assertEquals(60, t.getTrajanjeUMin());

    }

    @Test
    void testSetTrajanjeManjeOdNule() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> t.setTrajanjeUMin(-5));

    }

    @Test
    void testSetTip() {

        t.setTip(new TipTreninga(0, "Trening snage", "Snaga"));

        assertEquals("Trening snage", t.getTip().getNazivTipa());
        assertEquals("Snaga", t.getTip().getOpis());
    }

    @Test
    void testSetTipNull() {

        assertThrows(java.lang.NullPointerException.class, () -> t.setTip(null));

    }

    @Test
    void testSetTrener() {

        t.setTrener(new Trener(0, "Stefan", "Segrt", "0617334567", true, 5));

        assertEquals("Stefan", t.getTrener().getIme());
        assertEquals("Segrt", t.getTrener().getPrezime());
        assertEquals("0617334567", t.getTrener().getKontakt());
        assertEquals(true, t.getTrener().isSertifikat());
        assertEquals(5, t.getTrener().getGodineIskustva());

    }

    @Test
    void testSetTrenerNull() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> t.setTrener(null));

    }

    @Test
    void testString() throws ParseException {

        t.setTrener(new Trener(0, "Stefan", "Segrt", "0617334567", true, 5));
        t.setTip(new TipTreninga(0, "Trening snage", "Snaga"));
        String datumStr = "15-06-2022";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date datum = sdf.parse(datumStr);
        t.setDatumTreninga(datum);
        
        String s=t.toString();
        
        System.out.println(datum);
        System.out.println(s);
        assertTrue(s.contains("Trening snage"));
        assertTrue(s.contains("15.06.2022"));
        assertTrue(s.contains("Stefan"));
        assertTrue(s.contains("Segrt"));
        
        
        

    }
    
    @ParameterizedTest
    @CsvSource({
        
        "22.06.2024,22.06.2023,false",
        "22.06.2024,22.06.2024,true"
    })
    void testEquals(String datumStr1,String datumStr2,boolean eq) throws ParseException{
        
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        Date datum1=sdf.parse(datumStr1);
        
        t.setDatumTreninga(datum1);
        
        Trening trening=new Trening();
        Date datum2=sdf.parse(datumStr2);
        trening.setDatumTreninga(datum2);
        
        assertEquals(eq, t.equals(trening));
        
        
    }
    
    @Test
    void testEqualsObject() {

        Trening t2 = t;

        assertTrue(t.equals(t2));
    }

    @Test
    void testEqualsObjectNull() {

        assertFalse(t.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {

        assertFalse(t.equals(new Trener()));
    }
    
    

}

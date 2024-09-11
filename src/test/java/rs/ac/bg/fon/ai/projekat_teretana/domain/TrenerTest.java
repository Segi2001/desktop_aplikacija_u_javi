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
public class TrenerTest {

    Trener t;

    public TrenerTest() {
    }

    @BeforeEach
    public void setUp() {
        t = new Trener();
    }

    @AfterEach
    public void tearDown() {
        t = null;
    }

    /**
     * Test of getIdTrenera method, of class Trener.
     */
    @Test
    void testTrener() {
        assertNotNull(t);
        assertNull(t.getIme());
        assertNull(t.getPrezime());
        assertNull(t.getKontakt());
        assertEquals(0, t.getIdTrenera());
        assertEquals(null, t.isSertifikat());
        assertEquals(0, t.getGodineIskustva());
    }

    @Test
    void testTrenerParam() {

        t = new Trener(0, "Stefan", "Segrt", "0617338711", true, 5);

        assertNotNull(t);
        assertEquals("Stefan", t.getIme());
        assertEquals("Segrt", t.getPrezime());
        assertEquals("0617338711", t.getKontakt());
        assertEquals(true, t.isSertifikat());
        assertEquals(0, t.getIdTrenera());
        assertEquals(5, t.getGodineIskustva());

    }

    @Test
    void testSetIme() {

        t.setIme("Stefan");

        assertEquals("Stefan", t.getIme());
    }

    @Test
    void testSetImeNull() {

        assertThrows(java.lang.NullPointerException.class, () -> t.setIme(null));
    }

    @Test
    void testSetImeBroj() {
        assertThrows(java.lang.IllegalArgumentException.class, () -> t.setIme("D5"));

    }

    @Test
    void testSetImeMaloSlovo() {
        assertThrows(java.lang.IllegalArgumentException.class, () -> t.setIme("stefan"));

    }

    @Test
    void testSetPrezime() {

        t.setPrezime("Segrt");

        assertEquals("Segrt", t.getPrezime());

    }

    @Test
    void testSetPrezimeNull() {

        assertThrows(java.lang.NullPointerException.class, () -> t.setPrezime(null));

    }

    @Test
    void testPrezimeBroj() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> t.setPrezime("D5"));

    }

    @Test
    void testPrezimeMaloSlovo() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> t.setPrezime("segrt"));

    }

    @Test
    void testSetKontakt() {

        t.setKontakt("0617334567");

        assertEquals("0617334567", t.getKontakt());
    }

    @Test
    void testSetKontaktNull() {

        assertThrows(java.lang.NullPointerException.class, () -> t.setKontakt(null));

    }

    @Test
    void testSetKontaktSlovo() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> t.setKontakt("0617334567a"));

    }

    @Test
    void testSetKontaktManjiBrojCifara() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> t.setKontakt("06173345"));

    }

    @Test
    void testSetKontaktPogresanPocetak() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> t.setKontakt("9617334567"));

    }

    @Test
    void testSetSertifikat() {

        t.setSertifikat(true);

        assertEquals(true, t.isSertifikat());
    }

    @Test
    void testSetGodineIsk() {

        t.setGodineIskustva(5);

        assertEquals(5, t.getGodineIskustva());

    }

    @Test
    void testSetGodineIskManjeOdNule() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> t.setGodineIskustva(-2));

    }

    @Test
    void testString() {

        t = new Trener(0, "Stefan", "Segrt", "0617338711", true, 5);

        String s = t.toString();

        System.out.println(s);

        assertTrue(s.contains("Stefan"));
        assertTrue(s.contains("Segrt"));

    }

    @ParameterizedTest
    @CsvSource({
        "Stefan,Segrt,Stefan,Segrtt,false",
        "Stefan,Segrt,Stefann,Segrt,false",
        "Stefan,Segrt,Stefann,Segrtt,false",
        "Stefan,Segrt,Stefan,Segrt,true"

    })

    void testEquals(String ime1, String prezime1, String ime2, String prezime2, boolean eq) {

        t.setIme(ime1);
        t.setPrezime(prezime1);

        Trener t2 = new Trener();

        t2.setIme(ime2);
        t2.setPrezime(prezime2);

        assertEquals(eq, t.equals(t2));
    }

    @Test
    void testEqualsObject() {

        Trener t2 = t;

        assertTrue(t.equals(t2));
    }

    @Test
    void testEqualsObjectNull() {

        assertFalse(t.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {

        assertFalse(t.equals(new Trening()));
    }

}

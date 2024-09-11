/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class KorisnikTest {

    Korisnik k;

    public KorisnikTest() {
    }

    @BeforeEach
    public void setUp() {
        k = new Korisnik();
    }

    @AfterEach
    public void tearDown() {
        k = null;
    }

    /**
     * Test of getIdKorisnika method, of class Korisnik.
     */
    @Test
    void testKorisnik() {

        assertNotNull(k);
        assertNull(k.getIme());
        assertNull(k.getPrezime());
        assertNull(k.getKontakt());
        assertNull(k.getDatumRodjenja());

        assertEquals(0, k.getIdKorisnika());
        assertNull(k.getAdresa());
        assertEquals(0, k.getTipovi().size());
        assertNull(k.getGrad());

    }

    @Test
    void testKorisnikParam() {

        TipTreninga tip1 = new TipTreninga(0, "Trening snage", "Snaga");
        TipTreninga tip2 = new TipTreninga(1, "Trening kondicije", "Kondicija");

        List<TipTreninga> tipovi = new ArrayList<>();
        tipovi.add(tip1);
        tipovi.add(tip2);
        Date date = new Date(2001, 6, 10);

        k = new Korisnik(0, "Stefan", "Segrt", date, "Hercegovacka",
                "0617334567", new Grad(0, "Beograd"), tipovi);

        assertNotNull(k);
        assertEquals("Stefan", k.getIme());
        assertEquals("Segrt", k.getPrezime());
        assertEquals("0617334567", k.getKontakt());
        assertEquals(date, k.getDatumRodjenja());

        assertEquals(0, k.getIdKorisnika());
        assertEquals("Hercegovacka", k.getAdresa());
        assertEquals(tip1, k.getTipovi().get(0));
        assertEquals(tip2, k.getTipovi().get(1));

        assertEquals(2, k.getTipovi().size());
        assertEquals("Beograd", k.getGrad().getNaziv());

    }

    @Test
    void testSetIme() {

        k.setIme("Stefan");

        assertEquals("Stefan", k.getIme());
    }

    @Test
    void testSetImeNull() {

        assertThrows(java.lang.NullPointerException.class, () -> k.setIme(null));
    }

    @Test
    void testSetImeBroj() {
        assertThrows(java.lang.IllegalArgumentException.class, () -> k.setIme("D5"));

    }

    @Test
    void testSetImeMaloSlovo() {
        assertThrows(java.lang.IllegalArgumentException.class, () -> k.setIme("stefan"));

    }

    @Test
    void testSetPrezime() {

        k.setPrezime("Segrt");

        assertEquals("Segrt", k.getPrezime());

    }

    @Test
    void testSetPrezimeNull() {

        assertThrows(java.lang.NullPointerException.class, () -> k.setPrezime(null));

    }

    @Test
    void testPrezimeBroj() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> k.setPrezime("D5"));

    }

    @Test
    void testPrezimeMaloSlovo() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> k.setPrezime("segrt"));

    }

    @Test
    void testSetKontakt() {

        k.setKontakt("0617334567");

        assertEquals("0617334567", k.getKontakt());
    }

    @Test
    void testSetKontaktNull() {

        assertThrows(java.lang.NullPointerException.class, () -> k.setKontakt(null));

    }

    @Test
    void testSetKontaktSlovo() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> k.setKontakt("0617334567a"));

    }

    @Test
    void testSetKontaktManjiBrojCifara() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> k.setKontakt("06173345"));

    }

    @Test
    void testSetKontaktPogresanPocetak() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> k.setKontakt("9617334567"));

    }

    @Test
    void testSetDate() throws ParseException {

        String datumStr = "2022-15-6";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date datum = sdf.parse(datumStr);
        k.setDatumRodjenja(datum);

        assertEquals(datum, k.getDatumRodjenja());

    }

    @Test
    void testSetDateNull() {

        assertThrows(java.lang.NullPointerException.class, () -> k.setDatumRodjenja(null));

    }

    @Test
    void testSetDateBuducnost() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> k.setDatumRodjenja(new Date(2040, 3, 2)));

    }

    @Test
    void testSetAdresa() {

        k.setAdresa("Hercegovacka");

        assertEquals("Hercegovacka", k.getAdresa());
    }

    @Test
    void testSetAdresaNull() {

        assertThrows(java.lang.NullPointerException.class, () -> k.setAdresa(null));
    }

    @Test
    void testSetAdresaPrazna() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> k.setAdresa(""));

    }

    @Test
    void testSetGrad() {

        k.setGrad(new Grad(0, "Beograd"));

        assertEquals("Beograd", k.getGrad().getNaziv());

    }

    @Test
    void testSetGradNull() {

        assertThrows(java.lang.NullPointerException.class, () -> k.setGrad(null));

    }

    @Test
    void testSetTipovi1() {

        TipTreninga tip1 = new TipTreninga(0, "Trening snage", "Snaga");

        List<TipTreninga> tipovi = new ArrayList<>();

        tipovi.add(tip1);
        k.setTipovi(tipovi);

        assertEquals(tip1, tipovi.get(0));
        assertEquals(1, tipovi.size());

    }

    @Test
    void testSetTipovi2() {

        TipTreninga tip1 = new TipTreninga(0, "Trening snage", "Snaga");
        TipTreninga tip2 = new TipTreninga(1, "Trening kondicije", "Kondicija");

        List<TipTreninga> tipovi = new ArrayList<>();

        tipovi.add(tip1);
        tipovi.add(tip2);
        k.setTipovi(tipovi);

        assertEquals(tip1, k.getTipovi().get(0));
        assertEquals(tip2, k.getTipovi().get(1));
        assertEquals(2, tipovi.size());

    }

    @Test
    void testString() {

        k.setIme("Stefan");
        k.setPrezime("Segrt");

        String s = k.toString();

        assertTrue(s.contains("Stefan"));
        assertTrue(s.contains("Segrt"));

    }

    @ParameterizedTest
    @CsvSource({
        "Stefan,Segrt,0617334567,Stefa,Segrt,0617334567,false",
        "Stefan,Segrt,0617334567,Stefan,Segrtt,0617334567,false",
        "Stefan,Segrt,0617334567,Stefan,Segrt,061733456,false",
        "Stefan,Segrt,0617334567,Stefa,Segrtt,0617334567,false",
        "Stefan,Segrt,0617334567,Stefa,Segrt,061733456,false",
        "Stefan,Segrt,0617334567,Stefan,Segrtt,061733456,false",
        "Stefan,Segrt,0617334567,Stefa,Segrtt,061733456,false",
        "Stefan,Segrt,0617334567,Stefan,Segrt,0617334567,true"
    
    })
           
   void testEquals(String ime1,String prezime1,String kontakt1,String ime2,String prezime2,String kontakt2,boolean eq){
       
       
       k.setIme(ime1);
       k.setPrezime(prezime1);
       k.setKontakt(kontakt1);
       
       Korisnik korisnik=new Korisnik();
       
       korisnik.setIme(ime2);
       korisnik.setPrezime(prezime2);
       korisnik.setKontakt(kontakt2);
       
       assertEquals(eq,k.equals(korisnik));
       
   }
   
    @Test
    void testEqualsObject() {

        Korisnik k2=k;

        assertTrue(k.equals(k2));
    }

    @Test
    void testEqualsObjectNull() {

        assertFalse(k.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {

        assertFalse(k.equals(new Trening()));
    }



}

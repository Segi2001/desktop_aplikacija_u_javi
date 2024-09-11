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
public class RezultatiKorisnikaTest {

    RezultatiKorisnika rez;

    public RezultatiKorisnikaTest() {
    }

    @BeforeEach
    public void setUp() {
        rez = new RezultatiKorisnika();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    void testRezKorisnika() {

        assertNotNull(rez);
        assertNull(rez.getDatumRezultata());
        assertNull(rez.getStatistika());
        assertNull(rez.getKorisnik());
        assertEquals(0, rez.getIdRez());

    }

    @Test
    void testRezKorisnikaParam() {

        Statistika s = new Statistika(0, 25, 38, 100);
        TipTreninga tip1 = new TipTreninga(0, "Trening snage", "Snaga");
        TipTreninga tip2 = new TipTreninga(1, "Trening kondicije", "Kondicija");

        List<TipTreninga> tipovi = new ArrayList<>();
        tipovi.add(tip1);
        tipovi.add(tip2);
        Date date = new Date(2001, 6, 10);

        Korisnik k = new Korisnik(0, "Stefan", "Segrt", date, "Hercegovacka",
                "0617334567", new Grad(0, "Beograd"), tipovi);

        Date datumRez = new Date(2024, 6, 10);

        rez = new RezultatiKorisnika(0, k, datumRez, s);

        assertNotNull(rez);
        assertEquals("Stefan", rez.getKorisnik().getIme());
        assertEquals("Segrt", rez.getKorisnik().getPrezime());
        assertEquals("0617334567", rez.getKorisnik().getKontakt());
        assertEquals(date, rez.getKorisnik().getDatumRodjenja());

        assertEquals(0, rez.getKorisnik().getIdKorisnika());
        assertEquals("Hercegovacka", rez.getKorisnik().getAdresa());
        assertEquals(tip1, rez.getKorisnik().getTipovi().get(0));
        assertEquals(tip2, rez.getKorisnik().getTipovi().get(1));

        assertEquals(2, rez.getKorisnik().getTipovi().size());
        assertEquals("Beograd", rez.getKorisnik().getGrad().getNaziv());

        assertEquals(0, rez.getIdRez());
        assertEquals(datumRez, rez.getDatumRezultata());
        assertEquals(25, rez.getStatistika().getProcenatMasti());
        assertEquals(38, rez.getStatistika().getProcenatMisica());
        assertEquals(100, rez.getStatistika().getTezinaUKG());

    }

    @Test
    void testSetKorisnik() {

        TipTreninga tip1 = new TipTreninga(0, "Trening snage", "Snaga");
        TipTreninga tip2 = new TipTreninga(1, "Trening kondicije", "Kondicija");

        List<TipTreninga> tipovi = new ArrayList<>();
        tipovi.add(tip1);
        tipovi.add(tip2);
        Date date = new Date(2001, 6, 10);

        Korisnik k = new Korisnik(0, "Stefan", "Segrt", date, "Hercegovacka",
                "0617334567", new Grad(0, "Beograd"), tipovi);

        rez.setKorisnik(k);

        assertEquals(2, rez.getKorisnik().getTipovi().size());

        assertEquals("Stefan", rez.getKorisnik().getIme());
        assertEquals("Segrt", rez.getKorisnik().getPrezime());
        assertEquals("0617334567", rez.getKorisnik().getKontakt());
        assertEquals(date, rez.getKorisnik().getDatumRodjenja());

        assertEquals(0, rez.getKorisnik().getIdKorisnika());
        assertEquals("Hercegovacka", rez.getKorisnik().getAdresa());
        assertEquals(tip1, rez.getKorisnik().getTipovi().get(0));
        assertEquals(tip2, rez.getKorisnik().getTipovi().get(1));

    }

    @Test
    void testSetKorisnikNull() {

        assertThrows(java.lang.NullPointerException.class, () -> rez.setKorisnik(null));
    }

    @Test
    void testSetStat() {

        Statistika s = new Statistika(0, 25, 38, 100);

        rez.setStatistika(s);
        assertEquals(25, rez.getStatistika().getProcenatMasti());
        assertEquals(38, rez.getStatistika().getProcenatMisica());
        assertEquals(100, rez.getStatistika().getTezinaUKG());

    }

    @Test
    void testSetStatNull() {

        assertThrows(java.lang.NullPointerException.class, () -> rez.setStatistika(null));

    }

    @Test
    void testSetDate() throws ParseException {

        String datumStr = "2022-15-6";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date datum = sdf.parse(datumStr);
        rez.setDatumRezultata(datum);

        assertEquals(datum, rez.getDatumRezultata());

    }

    @Test
    void testSetDateNull() {

        assertThrows(java.lang.NullPointerException.class, () -> rez.setDatumRezultata(null));

    }

    @Test
    void testSetDateBuducnost() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> rez.setDatumRezultata(new Date(2040, 3, 2)));

    }

    @Test
    void testToString() throws ParseException {

        Statistika stat = new Statistika(0, 25, 38, 100);
        TipTreninga tip1 = new TipTreninga(0, "Trening snage", "Snaga");
        TipTreninga tip2 = new TipTreninga(1, "Trening kondicije", "Kondicija");

        List<TipTreninga> tipovi = new ArrayList<>();
        tipovi.add(tip1);
        tipovi.add(tip2);
        Date date = new Date(2001, 6, 10);

        Korisnik k = new Korisnik(0, "Stefan", "Segrt", date, "Hercegovacka",
                "0617334567", new Grad(0, "Beograd"), tipovi);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String datumStr = "10.06.2024";
        Date datumRez = sdf.parse(datumStr);

        rez = new RezultatiKorisnika(0, k, datumRez, stat);

        String s = rez.toString();
        System.out.println(s);

        assertTrue(s.contains("Stefan"));
        assertTrue(s.contains("Segrt"));
        assertTrue(s.contains("25"));
        assertTrue(s.contains("38"));
        assertTrue(s.contains("100"));
        assertTrue(s.contains("Mon Jun 10 00:00:00 CEST 2024"));

    }

    @ParameterizedTest
    @CsvSource({
        "22.06.2024,Stefan,Segrt,22.06.2023,Stefan,Segrt,false",
        "22.06.2024,Stefan,Segrt,22.06.2024,Stefann,Segrt,false",
        "22.06.2024,Stefan,Segrt,22.06.2024,Stefan,Segrtt,false",
        "22.06.2024,Stefan,Segrt,22.06.2023,Stefann,Segrt,false",
        "22.06.2024,Stefan,Segrt,22.06.2023,Stefan,Segrtt,false",
        "22.06.2024,Stefan,Segrt,22.06.2024,Stefann,Segrtt,false",
        "22.06.2024,Stefan,Segrt,22.06.2023,Stefann,Segrtt,false",
        "22.06.2024,Stefan,Segrt,22.06.2024,Stefan,Segrt,true",
    
    })
    void testEquals(String datumStr1,String ime1,String prezime1, String datumStr2,String ime2,String prezime2, boolean eq) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date datum1 = sdf.parse(datumStr1);

        rez.setDatumRezultata(datum1);
        Korisnik k1=new Korisnik();
        k1.setIme(ime1);
        k1.setPrezime(prezime1);
        rez.setKorisnik(k1);

        RezultatiKorisnika rez2=new RezultatiKorisnika();
        
        Korisnik k2=new Korisnik();
        k2.setIme(ime2);
        k2.setPrezime(prezime2);
        
        Date datum2 = sdf.parse(datumStr2);
        rez2.setDatumRezultata(datum2);
        rez2.setKorisnik(k2);

        assertEquals(eq,rez.equals(rez2));

    }

    @Test
    void testEqualsObject() {

        RezultatiKorisnika rez2=rez;

        assertTrue(rez.equals(rez2));
    }

    @Test
    void testEqualsObjectNull() {

        assertFalse(rez.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {

        assertFalse(rez.equals(new Trener()));
    }

}

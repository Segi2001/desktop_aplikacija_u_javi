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
public class EvidentiranjePrisustvaTest {

    EvidentiranjePrisustva ep;

    public EvidentiranjePrisustvaTest() {
    }

    @BeforeEach
    public void setUp() {

        ep = new EvidentiranjePrisustva();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getTrening method, of class EvidentiranjePrisustva.
     */
    @Test
    void testEvidentiranjePrisustva() {

        assertNotNull(ep);
        assertNull(ep.getKorisnik());
        assertNull(ep.getTrening());

        assertEquals(0, ep.getOtkucajiSrca());
        assertEquals(0, ep.getPotroseneKal());

    }

    @Test
    void testEvidentiranjePrisustvaParam() {

        TipTreninga tip1 = new TipTreninga(0, "Trening snage", "Snaga");
        TipTreninga tip2 = new TipTreninga(1, "Trening kondicije", "Kondicija");

        List<TipTreninga> tipovi = new ArrayList<>();
        tipovi.add(tip1);
        tipovi.add(tip2);
        Date date = new Date(2001, 6, 10);

        Korisnik k = new Korisnik(0, "Stefan", "Segrt", date, "Hercegovacka",
                "0617334567", new Grad(0, "Beograd"), tipovi);

        Date datumTreninga = new Date(2024, 6, 10);

        Trening t = new Trening(0, 1000, datumTreninga, 60, tip1,
                new Trener(0, "Marko", "Markovic", "0654545138", true, 5));

        ep = new EvidentiranjePrisustva(t, k, 300, 120);

        assertNotNull(ep);
        assertEquals("Stefan", ep.getKorisnik().getIme());
        assertEquals("Segrt", ep.getKorisnik().getPrezime());
        assertEquals("0617334567", ep.getKorisnik().getKontakt());
        assertEquals(date, ep.getKorisnik().getDatumRodjenja());

        assertEquals(0, ep.getKorisnik().getIdKorisnika());
        assertEquals("Hercegovacka", ep.getKorisnik().getAdresa());
        assertEquals(tip1, ep.getKorisnik().getTipovi().get(0));
        assertEquals(tip2, ep.getKorisnik().getTipovi().get(1));

        assertEquals(2, ep.getKorisnik().getTipovi().size());
        assertEquals("Beograd", ep.getKorisnik().getGrad().getNaziv());

        assertEquals(0, ep.getTrening().getIdTreninga());
        assertEquals(1000, ep.getTrening().getCena());
        assertEquals(datumTreninga, ep.getTrening().getDatumTreninga());
        assertEquals(60, ep.getTrening().getTrajanjeUMin());
        assertEquals("Trening snage", ep.getTrening().getTip().getNazivTipa());
        assertEquals("Snaga", ep.getTrening().getTip().getOpis());
        assertEquals(0, ep.getTrening().getTrener().getIdTrenera());
        assertEquals("Marko", ep.getTrening().getTrener().getIme());
        assertEquals("Markovic", ep.getTrening().getTrener().getPrezime());
        assertEquals("0654545138", ep.getTrening().getTrener().getKontakt());
        assertEquals(true, ep.getTrening().getTrener().isSertifikat());
        assertEquals(5, ep.getTrening().getTrener().getGodineIskustva());

    }

    @Test
    void testSetTrening() {

        Date datumTreninga = new Date(2024, 6, 10);

        Trening t = new Trening(0, 1000, datumTreninga, 60,
                new TipTreninga(0, "Trening snage", "Snaga"),
                new Trener(0, "Marko", "Markovic", "0654545138", true, 5));

        ep.setTrening(t);

        assertEquals(0, t.getIdTreninga());
        assertEquals(1000, t.getCena());
        assertEquals(datumTreninga, t.getDatumTreninga());
        assertEquals(60, t.getTrajanjeUMin());
        assertEquals("Trening snage", t.getTip().getNazivTipa());
        assertEquals("Snaga", t.getTip().getOpis());
        assertEquals(0, t.getTrener().getIdTrenera());
        assertEquals("Marko", t.getTrener().getIme());
        assertEquals("Markovic", t.getTrener().getPrezime());
        assertEquals("0654545138", t.getTrener().getKontakt());
        assertEquals(true, t.getTrener().isSertifikat());
        assertEquals(5, t.getTrener().getGodineIskustva());

    }

    @Test
    void testSetTreningNull() {

        assertThrows(java.lang.NullPointerException.class, () -> ep.setTrening(null));

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

        ep.setKorisnik(k);

        assertEquals(tip1, ep.getKorisnik().getTipovi().get(0));
        assertEquals(tip2, ep.getKorisnik().getTipovi().get(1));

        assertEquals(2, ep.getKorisnik().getTipovi().size());
        assertEquals("Stefan", ep.getKorisnik().getIme());
        assertEquals("Segrt", ep.getKorisnik().getPrezime());
        assertEquals("0617334567", ep.getKorisnik().getKontakt());
        assertEquals(date, ep.getKorisnik().getDatumRodjenja());

        assertEquals(0, ep.getKorisnik().getIdKorisnika());
        assertEquals("Hercegovacka", ep.getKorisnik().getAdresa());
        assertEquals("Beograd", ep.getKorisnik().getGrad().getNaziv());

    }

    @Test
    void testSetKorisnikNull() {

        assertThrows(java.lang.NullPointerException.class, () -> ep.setKorisnik(null));

    }

    @Test
    void testSetKalorije() {

        ep.setPotroseneKal(200);

        assertEquals(200, ep.getPotroseneKal());

    }

    @Test
    void testSetKalorijeManjeOdNule() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> ep.setPotroseneKal(-1));

    }

    @Test
    void testSetOtkucaji() {

        ep.setOtkucajiSrca(150);

        assertEquals(150, ep.getOtkucajiSrca());
    }

    @Test
    void testSetOtkucajiManjeOdNule() {

        assertThrows(java.lang.IllegalArgumentException.class, () -> ep.setOtkucajiSrca(-1));

    }

    @Test
    void testString() throws ParseException {

        TipTreninga tip1 = new TipTreninga(0, "Trening snage", "Snaga");
        TipTreninga tip2 = new TipTreninga(1, "Trening kondicije", "Kondicija");

        List<TipTreninga> tipovi = new ArrayList<>();
        tipovi.add(tip1);
        tipovi.add(tip2);
        Date date = new Date(2001, 6, 10);

        Korisnik k = new Korisnik(0, "Stefan", "Segrt", date, "Hercegovacka",
                "0617334567", new Grad(0, "Beograd"), tipovi);

        SimpleDateFormat smf = new SimpleDateFormat("dd.MM.yyyy");
        String datumStr = "05.07.2024";
        Date datumTreninga = smf.parse(datumStr);

        Trening t = new Trening(0, 1000, datumTreninga, 60, tip1,
                new Trener(0, "Marko", "Markovic", "0654545138", true, 5));

        ep = new EvidentiranjePrisustva(t, k, 300, 120);

        String s = ep.toString();

        assertTrue(s.contains("Trening snage"));
        assertTrue(s.contains("05.07.2024"));
        assertTrue(s.contains("Marko Markovic"));
        assertTrue(s.contains("Stefan Segrt"));
        assertTrue(s.contains("300"));
        assertTrue(s.contains("120"));

        System.out.println(s);

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

    void testEquals(String datumStr1,String ime1,String prezime1,String datumStr2,String ime2,String prezime2,boolean eq) throws ParseException{
        
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        Date datum1=sdf.parse(datumStr1);
        
       Trening tr1=new Trening();
       tr1.setDatumTreninga(datum1);
       Korisnik k1=new Korisnik();
       k1.setIme(ime1);
       k1.setPrezime(prezime1);
       
       ep.setKorisnik(k1);
       ep.setTrening(tr1);
       
       EvidentiranjePrisustva ep1=new EvidentiranjePrisustva();
        
        Trening tr2=new Trening();
        Date datum2=sdf.parse(datumStr2);
        tr2.setDatumTreninga(datum2);
        Korisnik k2=new Korisnik();
        k2.setIme(ime2);
        k2.setPrezime(prezime2);
        ep1.setKorisnik(k2);
        ep1.setTrening(tr2);
        
        assertEquals(eq,ep.equals(ep1));
        
        
    }
    
     @Test
    void testEqualsObject() {

        EvidentiranjePrisustva ep2=ep;

        assertTrue(ep.equals(ep2));
    }

    @Test
    void testEqualsObjectNull() {

        assertFalse(ep.equals(null));
    }

    @Test
    void testEqualsDrugaKlasa() {

        assertFalse(ep.equals(new Trening()));
    }

}

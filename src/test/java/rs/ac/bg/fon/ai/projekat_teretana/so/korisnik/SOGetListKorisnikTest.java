/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.korisnik;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Korisnik;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SOGetListKorisnikTest {
    
    @Mock
    private DBBroker dbBroker;

    private SOGetListKorisnik soGetListKorisnik;
    private Korisnik korisnik;

    @BeforeEach
    public void setUp() throws Exception {
        soGetListKorisnik = new SOGetListKorisnik();
        korisnik = new Korisnik();

       
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, dbBroker);
    }

    @Test
    public void testExecuteUspesno() throws Exception {
        
        List<AbstractDomainObject> mockKorisnici = new ArrayList<>();
        Korisnik k1 = new Korisnik();
        k1.setIme("Stefan");
        k1.setPrezime("Petrovic");
        Korisnik k2 = new Korisnik();
        k2.setIme("Marko");
        k2.setPrezime("Markovic");
        mockKorisnici.add(k1);
        mockKorisnici.add(k2);

        when(dbBroker.selectList(korisnik)).thenReturn(mockKorisnici);

        
        soGetListKorisnik.execute(korisnik);
        
        List<Korisnik> rez=soGetListKorisnik.getKorisnici();
        
        assertNotNull(soGetListKorisnik.getKorisnici());
        assertEquals(2, rez.size());
        assertEquals(k1,rez.get(0) );
        assertEquals(k2, rez.get(1));
        verify(dbBroker, times(1)).selectList(korisnik);
    }

    @Test
    public void testExecuteNeuspesno() throws Exception {
        
        when(dbBroker.selectList(korisnik)).thenThrow(new Exception("Greska prilikom citanja korisnika"));

        
        Exception exception = assertThrows(Exception.class, () -> {
            soGetListKorisnik.execute(korisnik);
        });

        assertEquals("Greska prilikom citanja korisnika", exception.getMessage());
        verify(dbBroker, times(1)).selectList(korisnik);
    }
    
    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            soGetListKorisnik.validate(new Trening());
        });

        assertEquals("Prosledjeni objekat nije instanca klase Korisnik!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            soGetListKorisnik.validate(korisnik);
        });
    }

}

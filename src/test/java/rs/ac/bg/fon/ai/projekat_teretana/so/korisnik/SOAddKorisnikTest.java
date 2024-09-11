/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.korisnik;

import static com.google.protobuf.JavaFeaturesProto.java;
import java.lang.reflect.Field;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.KT;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Korisnik;
import rs.ac.bg.fon.ai.projekat_teretana.domain.TipTreninga;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SOAddKorisnikTest {

    @Mock
    private DBBroker dbBroker;

    private SOAddKorisnik soAddKorisnik;
    private Korisnik korisnik;
    private TipTreninga tip1;
    private TipTreninga tip2;

    @BeforeEach
    public void setUp() throws Exception {
        soAddKorisnik = new SOAddKorisnik();
        korisnik = new Korisnik(); 
        tip1 = new TipTreninga(); 
        tip2 = new TipTreninga(); 
        korisnik.setTipovi(Arrays.asList(tip1, tip2)); 

        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, dbBroker);
    }

    @Test
    public void testExecuteUspesno() throws Exception {
        
        when(dbBroker.insert(korisnik)).thenReturn(1);
        when(dbBroker.insert(any(KT.class))).thenReturn(1, 2);

        
        soAddKorisnik.execute(korisnik);

        
        assertEquals(1, soAddKorisnik.getIdKorisnik());
        assertEquals(Arrays.asList(1, 2), soAddKorisnik.getNizId());
        verify(dbBroker, times(1)).insert(korisnik);
        verify(dbBroker, times(2)).insert(any(KT.class));
    }

    @Test
    public void testExecuteNeuspesno() throws Exception {
        
        when(dbBroker.insert(korisnik)).thenReturn(1);
        when(dbBroker.insert(any(KT.class))).thenThrow(new Exception("Greska prilikom dodavanja tipova"));

        
        Exception exception = assertThrows(Exception.class, () -> {
            soAddKorisnik.execute(korisnik);
        });

        assertEquals("Greska prilikom dodavanja tipova", exception.getMessage());
        verify(dbBroker, times(1)).insert(korisnik);
        verify(dbBroker, times(1)).insert(any(KT.class));
    }
    
    
    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            soAddKorisnik.validate(new Trening());
        });

        assertEquals("Prosledjeni objekat nije instanca klase Korisnik!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            soAddKorisnik.validate(korisnik);
        });
    }
    
}

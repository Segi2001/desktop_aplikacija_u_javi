/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.korisnik;

import java.lang.reflect.Field;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
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
public class SOUpdateKorisnikTest {
    
    @Mock
    private DBBroker dbBroker;

    private SOUpdateKorisnik soUpdateKorisnik;
    private Korisnik korisnik;
    private TipTreninga tip1;
    private TipTreninga tip2;

    @BeforeEach
    public void setUp() throws Exception {
        soUpdateKorisnik = new SOUpdateKorisnik();
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
        
        when(dbBroker.update(korisnik)).thenReturn(1);
        when(dbBroker.delete(any(KT.class))).thenReturn(1);
        when(dbBroker.insert(any(KT.class))).thenReturn(1, 2);

        
        soUpdateKorisnik.execute(korisnik);

        
        assertEquals(1, soUpdateKorisnik.getAffectedRowsUpdate());
        assertEquals(1, soUpdateKorisnik.getAffectedRowsDelete());
        assertEquals(Arrays.asList(1, 2), soUpdateKorisnik.getNizId());
        verify(dbBroker, times(1)).update(korisnik);
        verify(dbBroker, times(1)).delete(any(KT.class));
        verify(dbBroker, times(2)).insert(any(KT.class));
    }

    @Test
    public void testExecuteNeuspesnoUpdate() throws Exception {
        
        when(dbBroker.update(korisnik)).thenThrow(new Exception("Greska prilikom aziriranja korisnika"));

       
        Exception exception = assertThrows(Exception.class, () -> {
            soUpdateKorisnik.execute(korisnik);
        });

        assertEquals("Greska prilikom aziriranja korisnika", exception.getMessage());
        verify(dbBroker, times(1)).update(korisnik);
        verify(dbBroker, times(0)).delete(any(KT.class));
        verify(dbBroker, times(0)).insert(any(KT.class));
    }

    @Test
    public void testExecuteNeuspesnoDelete() throws Exception {
        
        when(dbBroker.update(korisnik)).thenReturn(1);
        when(dbBroker.delete(any(KT.class))).thenThrow(new Exception("Greska prilikom brisanja KT"));

        
        Exception exception = assertThrows(Exception.class, () -> {
            soUpdateKorisnik.execute(korisnik);
        });

        assertEquals("Greska prilikom brisanja KT", exception.getMessage());
        verify(dbBroker, times(1)).update(korisnik);
        verify(dbBroker, times(1)).delete(any(KT.class));
        verify(dbBroker, times(0)).insert(any(KT.class));
    }

    @Test
    public void testExecuteNeuspesnoInsert() throws Exception {
        
        when(dbBroker.update(korisnik)).thenReturn(1);
        when(dbBroker.delete(any(KT.class))).thenReturn(1);
        when(dbBroker.insert(any(KT.class))).thenThrow(new Exception("Greska prilikom ubacivanja KT"));

        
        Exception exception = assertThrows(Exception.class, () -> {
            soUpdateKorisnik.execute(korisnik);
        });

        assertEquals("Greska prilikom ubacivanja KT", exception.getMessage());
        verify(dbBroker, times(1)).update(korisnik);
        verify(dbBroker, times(1)).delete(any(KT.class));
        verify(dbBroker, times(1)).insert(any(KT.class));
    }
    
    
    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            soUpdateKorisnik.validate(new Trening());
        });

        assertEquals("Prosledjeni objekat nije instanca klase Korisnik!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            soUpdateKorisnik.validate(korisnik);
        });
    }
    
    

    
}

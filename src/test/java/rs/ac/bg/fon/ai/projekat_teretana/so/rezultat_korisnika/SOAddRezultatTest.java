/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.rezultat_korisnika;

import static com.google.protobuf.JavaFeaturesProto.java;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.RezultatiKorisnika;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Statistika;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SOAddRezultatTest {
    
    public SOAddRezultatTest() {
    }
    
    @Mock
    private DBBroker dbBroker;
    
    private SOAddRezultat sOAddRezultat;
    
    private RezultatiKorisnika rz;
    
    
    
    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        sOAddRezultat = new SOAddRezultat();
        rz = new RezultatiKorisnika();
        rz.setStatistika(new Statistika());
        

        Field instanceBroker = DBBroker.class.getDeclaredField("instance");
        instanceBroker.setAccessible(true);
        instanceBroker.set(null, dbBroker);
    }
    
    
    @Test
    public void testExecuteUspesno() throws Exception {
        
        
        

        when(dbBroker.insert(any(Statistika.class))).thenReturn(2);
        when(dbBroker.insert(any(RezultatiKorisnika.class))).thenReturn(5);

        
        sOAddRezultat.execute(rz);

        
        assertEquals(2, sOAddRezultat.getIdStat());
        assertEquals(5, sOAddRezultat.getIdRez()); 
        verify(dbBroker, times(1)).insert(any(Statistika.class)); 
        verify(dbBroker, times(1)).insert(any(RezultatiKorisnika.class));


    
    }
    
    
    @Test
    public void testExecuteNeuspesnoStatistikaInsert() throws Exception {
        

        when(dbBroker.insert(any(Statistika.class))).thenThrow(new Exception("Greska prilikom unosenja statistike"));

        
        Exception exception = assertThrows(Exception.class, () -> {
            sOAddRezultat.execute(rz);
        });

        assertEquals("Greska prilikom unosenja statistike", exception.getMessage());
        verify(dbBroker, times(1)).insert(any(Statistika.class)); 
        verify(dbBroker, times(0)).insert(any(RezultatiKorisnika.class));
    }
    
    @Test
    public void testExecuteNeuspesnoRezultatiKorisnikaInsert() throws Exception {
        
       
        when(dbBroker.insert(any(Statistika.class))).thenReturn(1);
        when(dbBroker.insert(any(RezultatiKorisnika.class))).thenThrow(new Exception("Greska prilikom unosenja rezultata"));

        
        Exception exception = assertThrows(Exception.class, () -> {
            sOAddRezultat.execute(rz);
        });

        assertEquals("Greska prilikom unosenja rezultata", exception.getMessage());
        verify(dbBroker, times(1)).insert(any(Statistika.class)); 
        verify(dbBroker, times(1)).insert(any(RezultatiKorisnika.class));
    }
    
    
    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            sOAddRezultat.validate(new Trening());
        });

        assertEquals("Prosledjeni objekat nije instanca klase RezultatKorisnika!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            sOAddRezultat.validate(rz);
        });
    }
    
}

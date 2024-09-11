/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.evidentiranje_prisustva;

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
import rs.ac.bg.fon.ai.projekat_teretana.domain.EvidentiranjePrisustva;
import rs.ac.bg.fon.ai.projekat_teretana.domain.KT;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SOUpdatePrisustvaTest {

    private SOUpdatePrisustva sOUpdatePrisustva;
    @Mock
    private DBBroker dbBroker;

    EvidentiranjePrisustva ep1;
    EvidentiranjePrisustva ep2;

    public SOUpdatePrisustvaTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {

        ep1 = new EvidentiranjePrisustva();
        ep2 = new EvidentiranjePrisustva();
        sOUpdatePrisustva = new SOUpdatePrisustva();
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, dbBroker);
    }

    @Test
    public void testExecuteUspesno() throws Exception {

        when(dbBroker.delete(any(EvidentiranjePrisustva.class))).thenReturn(3);

        when(dbBroker.insert(any(EvidentiranjePrisustva.class))).thenReturn(-1, -1);
        
        

        sOUpdatePrisustva.execute(Arrays.asList(ep1, ep2));

        assertEquals(3, sOUpdatePrisustva.getAffectedRows());
        assertEquals(Arrays.asList(-1, -1), sOUpdatePrisustva.getNizId());
        verify(dbBroker, times(2)).insert(any(EvidentiranjePrisustva.class));
    }
    
    
    @Test
    public void testExecuteNeuspesnoDelete() throws Exception {
        
        when(dbBroker.delete(any(EvidentiranjePrisustva.class))).thenThrow
        (new Exception("Greska prilikom brisanja evidencija"));

       
        Exception exception = assertThrows(Exception.class, () -> {
            sOUpdatePrisustva.execute(Arrays.asList(ep1,ep2));
        });

        assertEquals("Greska prilikom brisanja evidencija", exception.getMessage());
        verify(dbBroker, times(1)).delete(any(EvidentiranjePrisustva.class));
        verify(dbBroker, times(0)).insert(any(EvidentiranjePrisustva.class));
        
    }
    
    @Test
    public void testExecuteNeuspesnoInsert() throws Exception {
        
        when(dbBroker.delete(any(EvidentiranjePrisustva.class))).thenReturn(3);
        when(dbBroker.insert(any(EvidentiranjePrisustva.class))).thenThrow
        (new Exception("Greska prilikom dodavanja evidencija"));

       
        Exception exception = assertThrows(Exception.class, () -> {
            sOUpdatePrisustva.execute(Arrays.asList(ep1,ep2));
        });

        assertEquals("Greska prilikom dodavanja evidencija", exception.getMessage());
        verify(dbBroker, times(1)).delete(any(EvidentiranjePrisustva.class));
        verify(dbBroker, times(1)).insert(any(EvidentiranjePrisustva.class));
        
    }
    
 @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            sOUpdatePrisustva.validate(Arrays.asList(new Trening(),new Trening()));
        });

        assertEquals("Prosledjeni objekat nije instanca klase EvidentiranjePrisustva!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            sOUpdatePrisustva.validate(Arrays.asList(ep1, ep2));
        });
    }
}

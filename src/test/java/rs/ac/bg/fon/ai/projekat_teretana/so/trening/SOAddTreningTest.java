/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.trening;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.TipTreninga;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trener;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SOAddTreningTest {

   @Mock
    private DBBroker dbBroker;

    private SOAddTrening sOAddTrening;
    private Trening trening;

    @BeforeEach
    public void setUp() throws Exception {
        sOAddTrening = new SOAddTrening();
        trening = new Trening();
        trening.setTrener(new Trener());
        trening.setTrajanjeUMin(60);
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        String datumTxt="22.06.2024";
        Date date=sdf.parse(datumTxt);
        trening.setDatumTreninga(date);
        trening.setCena(1000);
        trening.setTip(new TipTreninga());

        
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, dbBroker);
    }

    @Test
    public void testExecuteUspesno() throws Exception {
        
        when(dbBroker.insert(trening)).thenReturn(1);
        
        sOAddTrening.execute(trening);

        
        assertEquals(1, sOAddTrening.getId());
        verify(dbBroker, times(1)).insert(trening);
    }

    @Test
    public void testExecuteNeuspesno() throws Exception {
        
        when(dbBroker.insert(trening)).thenThrow(new Exception("Greska prilikom kreiranja treninga"));

        
        Exception exception = assertThrows(Exception.class, () -> {
            sOAddTrening.execute(trening);
        });

        assertEquals("Greska prilikom kreiranja treninga", exception.getMessage());
        verify(dbBroker, times(1)).insert(trening);
    }

    
    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            sOAddTrening.validate(new Trener());
        });

        assertEquals("Prosledjeni objekat nije instanca klase Trening!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            sOAddTrening.validate(trening);
        });
    }
    
}

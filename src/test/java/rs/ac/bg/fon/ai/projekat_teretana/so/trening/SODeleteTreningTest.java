/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.trening;

import static com.google.protobuf.JavaFeaturesProto.java;
import java.lang.reflect.Field;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trener;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SODeleteTreningTest {
    
    @Mock
    private DBBroker dbBroker;

    private SODeleteTrening soDeleteTrening;
    private Trening trening;

    @BeforeEach
    public void setUp() throws Exception {
        soDeleteTrening = new SODeleteTrening();
        trening = new Trening(); 

        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, dbBroker);
    }
    
    public SODeleteTreningTest() {
    }
    
   
    
    @AfterEach
    public void tearDown() {
    }

    
    @Test
    public void testExecuteUspesno() throws Exception {
        
        when(dbBroker.delete(trening)).thenReturn(1);

        
        soDeleteTrening.execute(trening);

        
        assertEquals(1, soDeleteTrening.getAffectedRows());
        verify(dbBroker, times(1)).delete(trening);
    }
    
    @Test
    public void testExecuteNeuspesno() throws Exception {
        
        when(dbBroker.delete(trening)).thenThrow(new Exception("Greska prilikom brisanja treninga iz baze"));

        
        Exception exception = assertThrows(Exception.class, () -> {
            soDeleteTrening.execute(trening);
        });

        assertEquals("Greska prilikom brisanja treninga iz baze", exception.getMessage());
        verify(dbBroker, times(1)).delete(trening);
    }
    
    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            soDeleteTrening.validate(new Trener());
        });

        assertEquals("Prosledjeni objekat nije instanca klase Trening!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            soDeleteTrening.validate(trening);
        });
    }
    
    
    
    
    
}

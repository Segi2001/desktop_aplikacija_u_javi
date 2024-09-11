/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.evidentiranje_prisustva;

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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.EvidentiranjePrisustva;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SOAddPrisustvaTest {

    private SOAddPrisustva soAddPrisustva;
    @Mock
    private DBBroker dbBroker;
    
    EvidentiranjePrisustva ep1;
    EvidentiranjePrisustva ep2;
    

    @BeforeEach
    public void setUp() throws Exception {
        
        ep1=new EvidentiranjePrisustva();
        ep2=new EvidentiranjePrisustva();
        soAddPrisustva = new SOAddPrisustva();
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, dbBroker);
    }

    @Test
    public void testExecuteUspesno() throws Exception {

        when(dbBroker.insert(any(EvidentiranjePrisustva.class))).thenReturn(-1,-1);

        
        soAddPrisustva.execute(Arrays.asList(ep1, ep2));

        
        assertEquals(Arrays.asList(-1, -1), soAddPrisustva.getNizId());
        verify(dbBroker, times(2)).insert(any(EvidentiranjePrisustva.class));
    }

    @Test
    public void testExecuteNeuspesno() throws Exception {
        

        when(dbBroker.insert(any(EvidentiranjePrisustva.class))).thenThrow
        (new Exception("Greska prilikom evidentiranja treninga!"));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            soAddPrisustva.execute(Arrays.asList(ep1, ep2));
        });

        assertEquals("Greska prilikom evidentiranja treninga!", exception.getMessage());
        verify(dbBroker, times(1)).insert(any(EvidentiranjePrisustva.class));
    }
    
    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            soAddPrisustva.validate(Arrays.asList(new Trening(),new Trening()));
        });

        assertEquals("Prosledjeni objekat nije instanca klase EvidentiranjePrisustva!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            soAddPrisustva.validate(Arrays.asList(ep1, ep2));
        });
    }

    
}

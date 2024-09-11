/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.trener;

import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
public class SOAddTrenerTest {

    @Mock
    private DBBroker dbBroker;

    private SOAddTrener soAddTrener;

    private Trener trener;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        soAddTrener = new SOAddTrener();
        trener = new Trener();

        Field instanceBroker = DBBroker.class.getDeclaredField("instance");
        instanceBroker.setAccessible(true);
        instanceBroker.set(null, dbBroker);
    }

    @Test
    public void testExecuteUspesno() throws Exception {

        when(dbBroker.insert(trener)).thenReturn(1);

        soAddTrener.execute(trener);

        assertEquals(1, soAddTrener.getId());
        verify(dbBroker, times(1)).insert(trener);
    }

    @Test
    public void testExecuteNeuspesno() throws Exception {

        when(dbBroker.insert(trener)).thenThrow(new Exception("Greska prilikom dodavanja trenera u bazu"));

        Exception exception = assertThrows(Exception.class, () -> {
            soAddTrener.execute(trener);
        });

        assertEquals("Greska prilikom dodavanja trenera u bazu", exception.getMessage());
        verify(dbBroker, times(1)).insert(trener);
    }

    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            soAddTrener.validate(new Trening());
        });

        assertEquals("Prosledjeni objekat nije instanca klase Trener!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            soAddTrener.validate(trener);
        });
    }
}

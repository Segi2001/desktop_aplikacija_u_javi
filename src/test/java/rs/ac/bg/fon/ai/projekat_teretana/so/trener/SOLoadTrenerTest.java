/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.trener;

import java.lang.reflect.Field;
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
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trener;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SOLoadTrenerTest {
    
    public SOLoadTrenerTest() {
    }
    
    @Mock
    private DBBroker dbBroker;

    private SOLoadTrener sOLoadTrener;

    private Trener trener;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        sOLoadTrener = new SOLoadTrener();
        trener = new Trener();
        trener.setIme("Marko");
        trener.setGodineIskustva(5);
        trener.setPrezime("Petrovic");
        trener.setKontakt("0617334567");
        trener.setSertifikat(true);

        Field instanceBroker = DBBroker.class.getDeclaredField("instance");
        instanceBroker.setAccessible(true);
        instanceBroker.set(null, dbBroker);
    }

    @Test
    public void testExecuteUspesno() throws Exception {

        when(dbBroker.selectObject(trener)).thenReturn(trener);

        sOLoadTrener.execute(trener);

        Trener trener2=sOLoadTrener.getTrener();
        assertEquals(trener.getIme(),trener2.getIme());
        assertEquals(trener.getPrezime(),trener2.getPrezime());
        assertEquals(trener.getGodineIskustva(),trener2.getGodineIskustva());
        assertEquals(trener.getKontakt(),trener2.getKontakt());
        assertEquals(trener.isSertifikat(),trener2.isSertifikat());
        
        verify(dbBroker, times(1)).selectObject(trener);
    }

    @Test
    public void testExecuteNeuspesno() throws Exception {

        when(dbBroker.selectObject(trener)).thenThrow(new Exception("Greska,treneri nisu pronadjeni!"));

        Exception exception = assertThrows(Exception.class, () -> {
            sOLoadTrener.execute(trener);
        });

        assertEquals("Greska,treneri nisu pronadjeni!", exception.getMessage());
        verify(dbBroker, times(1)).selectObject(trener);
    }

    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            sOLoadTrener.validate(new Trening());
        });

        assertEquals("Prosledjeni objekat nije instanca klase Trener!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            sOLoadTrener.validate(trener);
        });
    }
}

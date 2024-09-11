/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.trening;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.TipTreninga;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trener;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;
import rs.ac.bg.fon.ai.projekat_teretana.so.trener.SOGetListTrener;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SOGetListTreningTest {

    @Mock
    private DBBroker dbBroker;

    private SOGetListTrening sOGetListTrening;

    Trening trening;

    public SOGetListTreningTest() {
    }

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        sOGetListTrening = new SOGetListTrening();
        trening = new Trening();

        Field instanceBroker = DBBroker.class.getDeclaredField("instance");
        instanceBroker.setAccessible(true);
        instanceBroker.set(null, dbBroker);
    }

//    @AfterEach
//    public void tearDown() {
//    }
    @Test
    public void testExecuteUspesnoDuzine1() throws Exception {

        List<AbstractDomainObject> mockListaTreninzi = new ArrayList<>();
        Trening mockTrening = new Trening();
        mockTrening.setTrajanjeUMin(60);
        mockTrening.setCena(2000);
        mockTrening.setTip(new TipTreninga(0, "Trening snage", "Snaga"));
        mockListaTreninzi.add(mockTrening);
        when(dbBroker.selectList(trening)).thenReturn(mockListaTreninzi);

        sOGetListTrening.execute(trening);

        List<Trening> rez = sOGetListTrening.getTreninzi();
        assertNotNull(rez);
        assertEquals(1, rez.size());
        assertEquals(mockTrening, rez.get(0));
        verify(dbBroker, times(1)).selectList(trening);
    }

    @Test
    public void testExecuteUspesnoDuzine2() throws Exception {

        List<AbstractDomainObject> mockListaTreninzi = new ArrayList<>();
        Trening mockTrening = new Trening();
        mockTrening.setTrajanjeUMin(60);
        mockTrening.setCena(2000);
        mockTrening.setTip(new TipTreninga(0, "Trening snage", "Snaga"));
        Trening mockTrening2 = new Trening();
        mockTrening2.setTrajanjeUMin(50);
        mockTrening2.setCena(3000);
        mockTrening2.setTip(new TipTreninga(0, "Trening snage", "Snaga"));
        mockListaTreninzi.add(mockTrening);
        mockListaTreninzi.add(mockTrening2);

        when(dbBroker.selectList(trening)).thenReturn(mockListaTreninzi);

        sOGetListTrening.execute(trening);

        List<Trening> rez = sOGetListTrening.getTreninzi();
        assertNotNull(rez);
        assertEquals(2, rez.size());
        assertEquals(mockTrening, rez.get(0));
        assertEquals(mockTrening2, rez.get(1));
        verify(dbBroker, times(1)).selectList(trening);
    }

    @Test
    public void testExecuteNeuspesno() throws Exception {

        when(dbBroker.selectList(trening)).thenThrow(new Exception("Greska prilikom citanja treninga iz baze"));

        Exception exception = assertThrows(Exception.class, () -> {
            sOGetListTrening.execute(trening);
        });

        assertEquals("Greska prilikom citanja treninga iz baze", exception.getMessage());
        verify(dbBroker, times(1)).selectList(trening);
    }

    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            sOGetListTrening.validate(new Trener());
        });

        assertEquals("Prosledjeni objekat nije instanca klase Trening!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            sOGetListTrening.validate(trening);
        });
    }

}

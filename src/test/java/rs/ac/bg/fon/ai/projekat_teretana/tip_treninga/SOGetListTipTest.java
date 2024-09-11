/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.tip_treninga;

import static com.google.protobuf.JavaFeaturesProto.java;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
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
import rs.ac.bg.fon.ai.projekat_teretana.domain.Grad;
import rs.ac.bg.fon.ai.projekat_teretana.domain.TipTreninga;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SOGetListTipTest {

    @Mock
    private DBBroker dbBroker;

    private SOGetListTip sOGetListTip;

    private TipTreninga tip;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        sOGetListTip = new SOGetListTip();
        tip = new TipTreninga();

        Field instanceBroker = DBBroker.class.getDeclaredField("instance");
        instanceBroker.setAccessible(true);
        instanceBroker.set(null, dbBroker);
    }

    @Test
    public void testExecuteUspesnoDuzine1() throws Exception {

        List<AbstractDomainObject> mockListaTipovi = new ArrayList<>();
        TipTreninga mockTip = new TipTreninga();
        mockTip.setNazivTipa("Trening snage");

        mockListaTipovi.add(mockTip);
        when(dbBroker.selectList(tip)).thenReturn(mockListaTipovi);

        sOGetListTip.execute(tip);

        List<TipTreninga> rez = sOGetListTip.getTipovi();
        assertNotNull(rez);
        assertEquals(1, rez.size());
        assertEquals(mockTip, rez.get(0));
        verify(dbBroker, times(1)).selectList(tip);
    }

    @Test
    public void testExecuteUspesnoDuzine2() throws Exception {

        List<AbstractDomainObject> mockListaTipovi = new ArrayList<>();
        TipTreninga mockTip = new TipTreninga();
        mockTip.setNazivTipa("Trening snage");

        TipTreninga mockTip2 = new TipTreninga();
        mockTip2.setNazivTipa("Trening izdrzljivosti");

        mockListaTipovi.add(mockTip);
        mockListaTipovi.add(mockTip2);
        when(dbBroker.selectList(tip)).thenReturn(mockListaTipovi);

        sOGetListTip.execute(tip);

        List<TipTreninga> rez = sOGetListTip.getTipovi();
        assertNotNull(rez);
        assertEquals(2, rez.size());
        assertEquals(mockTip, rez.get(0));
        assertEquals(mockTip2, rez.get(1));

        verify(dbBroker, times(1)).selectList(tip);

        

    }
    
    
    @Test
    public void testExecuteNeuspesno() throws Exception {

        when(dbBroker.selectList(tip)).thenThrow(new Exception("Greska prilikom citanja tipova iz baze"));

        Exception exception = assertThrows(Exception.class, () -> {
            sOGetListTip.execute(tip);
        });

        assertEquals("Greska prilikom citanja tipova iz baze", exception.getMessage());
        verify(dbBroker, times(1)).selectList(tip);
    }

    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            sOGetListTip.validate(new Trening());
        });

        assertEquals("Prosledjeni objekat nije instanca klase TipTreninga!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            sOGetListTip.validate(tip);
        });
    }

}

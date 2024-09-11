/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.trener;

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
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trener;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SOSearchTrenerTest {

    @Mock
    private DBBroker dbBroker;

    private SOSearchTrener sOSearchTrener;

    Trener trener;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        sOSearchTrener = new SOSearchTrener();
        trener = new Trener();
        trener.setIme("Stefan");

        Field instanceBroker = DBBroker.class.getDeclaredField("instance");
        instanceBroker.setAccessible(true);
        instanceBroker.set(null, dbBroker);
    }

//    @AfterEach
//    public void tearDown() {
//    }
    @Test
    public void testExecuteUspesnoDuzine1() throws Exception {

        List<AbstractDomainObject> mockListaTreneri = new ArrayList<>();
        Trener mockTrener = new Trener();
        mockTrener.setIme("Stefan");
        mockTrener.setPrezime("Segrt");
        mockTrener.setGodineIskustva(5);
        mockListaTreneri.add(mockTrener);
        when(dbBroker.selectList(trener)).thenReturn(mockListaTreneri);

        sOSearchTrener.execute(trener);

        List<Trener> rez = sOSearchTrener.getTreneri();
        assertNotNull(rez);
        assertEquals(1, rez.size());
        assertEquals(mockTrener, rez.get(0));
        verify(dbBroker, times(1)).selectList(trener);
    }

    @Test
    public void testExecuteUspesnoDuzine2() throws Exception {

        List<AbstractDomainObject> mockListaTreneri = new ArrayList<>();
        Trener mockTrener = new Trener();
        mockTrener.setIme("Stefan");
        mockTrener.setPrezime("Segrt");
        mockTrener.setGodineIskustva(5);
        
        Trener mockTrener2 = new Trener();
        mockTrener2.setIme("Boban");
        mockTrener2.setPrezime("Jankovic");
        mockTrener2.setGodineIskustva(5);
        
        Trener mockTrener3 = new Trener();
        mockTrener3.setIme("Stefan");
        mockTrener3.setPrezime("Petovic");
        mockTrener3.setGodineIskustva(3);
        
        mockListaTreneri.add(mockTrener);
        mockListaTreneri.add(mockTrener2);
        mockListaTreneri.add(mockTrener3);
        
        List<AbstractDomainObject> mockListaTreneriIspunjenUslov=new ArrayList<>();
        
        for (AbstractDomainObject abstractDomainObject : mockListaTreneri) {
            Trener t=(Trener) abstractDomainObject;
            if(t.getIme().equals(trener.getIme()))
                mockListaTreneriIspunjenUslov.add(abstractDomainObject);
        }
        

        when(dbBroker.selectList(trener)).thenReturn(mockListaTreneriIspunjenUslov);

        sOSearchTrener.execute(trener);

        List<Trener> rez = sOSearchTrener.getTreneri();
        assertNotNull(rez);
        assertEquals(2, rez.size());
        assertEquals(mockTrener, rez.get(0));
        assertEquals(mockTrener3, rez.get(1));
        verify(dbBroker, times(1)).selectList(trener);
    }

    @Test
    public void testExecuteNeuspesno() throws Exception {

        when(dbBroker.selectList(trener)).thenThrow(new Exception("Greska prilikom citanja trenera iz baze"));

        Exception exception = assertThrows(Exception.class, () -> {
            sOSearchTrener.execute(trener);
        });

        assertEquals("Greska prilikom citanja trenera iz baze", exception.getMessage());
        verify(dbBroker, times(1)).selectList(trener);
    }

    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            sOSearchTrener.validate(new Trening());
        });

        assertEquals("Prosledjeni objekat nije instanca klase Trener!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            sOSearchTrener.validate(trener);
        });
    }

}

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
public class SOGetListTrenerTest {

    @Mock
    private DBBroker dbBroker;

    private SOGetListTrener sOGetListTrener;

    Trener trener;

    public SOGetListTrenerTest() {
    }

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        sOGetListTrener = new SOGetListTrener();
        trener = new Trener();

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

        
        sOGetListTrener.execute(trener);

        
        List<Trener> rez = sOGetListTrener.getTreneri();
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
        Trener mockTrener2=new Trener();
        mockTrener2.setIme("Boban");
        mockTrener2.setPrezime("Jankovic");
        mockTrener2.setGodineIskustva(5);
        mockListaTreneri.add(mockTrener);
        mockListaTreneri.add(mockTrener2);
        
        when(dbBroker.selectList(trener)).thenReturn(mockListaTreneri);

        
        sOGetListTrener.execute(trener);

        
        List<Trener> rez = sOGetListTrener.getTreneri();
        assertNotNull(rez);
        assertEquals(2, rez.size());
        assertEquals(mockTrener, rez.get(0));
        assertEquals(mockTrener2,rez.get(1));
        verify(dbBroker, times(1)).selectList(trener);
    }
    

    @Test
    public void testExecuteNeuspesno() throws Exception {

        when(dbBroker.selectList(trener)).thenThrow(new Exception("Greska prilikom citanja trenera iz baze"));

        Exception exception = assertThrows(Exception.class, () -> {
            sOGetListTrener.execute(trener);
        });

        assertEquals("Greska prilikom citanja trenera iz baze", exception.getMessage());
        verify(dbBroker, times(1)).selectList(trener);
    }

    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            sOGetListTrener.validate(new Trening());
        });

        assertEquals("Prosledjeni objekat nije instanca klase Trener!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            sOGetListTrener.validate(trener);
        });
    }

    
    
}

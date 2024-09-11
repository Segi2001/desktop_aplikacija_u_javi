/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.grad;

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
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SOGetListGradTest {

    public SOGetListGradTest() {
    }

    @Mock
    private DBBroker dbBroker;

    private SOGetListGrad sOGetListGrad;

    Grad g;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        sOGetListGrad = new SOGetListGrad();
        g = new Grad();

        Field instanceBroker = DBBroker.class.getDeclaredField("instance");
        instanceBroker.setAccessible(true);
        instanceBroker.set(null, dbBroker);
    }

//    @AfterEach
//    public void tearDown() {
//    }
    @Test
    public void testExecuteUspesnoDuzine1() throws Exception {

        List<AbstractDomainObject> mockListaGradovi = new ArrayList<>();
        Grad mockGrad = new Grad();
        mockGrad.setNaziv("Beograd");

        mockListaGradovi.add(mockGrad);
        when(dbBroker.selectList(g)).thenReturn(mockListaGradovi);

        sOGetListGrad.execute(g);

        List<Grad> rez = sOGetListGrad.getGradovi();
        assertNotNull(rez);
        assertEquals(1, rez.size());
        assertEquals(mockGrad, rez.get(0));
        verify(dbBroker, times(1)).selectList(g);
    }

    @Test
    public void testExecuteUspesnoDuzine2() throws Exception {

        List<AbstractDomainObject> mockListaGradovi = new ArrayList<>();
        Grad mockGrad = new Grad();
        mockGrad.setNaziv("Beograd");
        Grad mockGrad2 = new Grad();
        mockGrad2.setNaziv("Nis");

        mockListaGradovi.add(mockGrad);
        mockListaGradovi.add(mockGrad2);
        when(dbBroker.selectList(g)).thenReturn(mockListaGradovi);

        sOGetListGrad.execute(g);

        List<Grad> rez = sOGetListGrad.getGradovi();
        assertNotNull(rez);
        assertEquals(2, rez.size());
        assertEquals(mockGrad, rez.get(0));
        assertEquals(mockGrad2, rez.get(1));

        verify(dbBroker, times(1)).selectList(g);

        
    }

    @Test
    public void testExecuteNeuspesno() throws Exception {

        when(dbBroker.selectList(g)).thenThrow(new Exception("Greska prilikom citanja gradova iz baze"));

        Exception exception = assertThrows(Exception.class, () -> {
            sOGetListGrad.execute(g);
        });

        assertEquals("Greska prilikom citanja gradova iz baze", exception.getMessage());
        verify(dbBroker, times(1)).selectList(g);
    }

    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            sOGetListGrad.validate(new Trening());
        });

        assertEquals("Prosledjeni objekat nije instanca klase Grad!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            sOGetListGrad.validate(g);
        });
    }

}

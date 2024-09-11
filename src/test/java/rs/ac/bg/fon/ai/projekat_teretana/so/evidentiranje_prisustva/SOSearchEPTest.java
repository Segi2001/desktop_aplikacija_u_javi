/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.evidentiranje_prisustva;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.EvidentiranjePrisustva;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SOSearchEPTest {

    private SOSearchEP sOSearchEP;
    @Mock
    private DBBroker dbBroker;

    EvidentiranjePrisustva ep;

    public SOSearchEPTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {

        ep = new EvidentiranjePrisustva();
        sOSearchEP = new SOSearchEP();
        Trening t=new Trening();
        t.setCena(2000);
        ep.setTrening(t);
        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, dbBroker);
    }

    @Test
    public void testExecuteUspesno() throws Exception {

        List<AbstractDomainObject> mockEvidencije = new ArrayList<>();
        EvidentiranjePrisustva ep1 = new EvidentiranjePrisustva();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String datumTxt1 = "22.06.2024";
        String datumTxt2 = "30.05.2024";
        String datumTxt3 = "30.06.2024";

        Date date1 = sdf.parse(datumTxt1);
        Date date2 = sdf.parse(datumTxt2);
        Date date3 = sdf.parse(datumTxt3);
        ep1.setTrening(new Trening(0, 2000, date1, 60, null, null));
        EvidentiranjePrisustva ep2 = new EvidentiranjePrisustva();
        ep2.setTrening(new Trening(0, 2000, date2, 60, null, null));
        EvidentiranjePrisustva ep3 = new EvidentiranjePrisustva();
        ep3.setTrening(new Trening(0, 3000, date3, 60, null, null));
        
        mockEvidencije.add(ep1);
        mockEvidencije.add(ep2);
        mockEvidencije.add(ep3);
        
        List<AbstractDomainObject> mockListaEvidencijaSaUslovom=new ArrayList<>();
        
        for (AbstractDomainObject abstractDomainObject : mockEvidencije) {
            EvidentiranjePrisustva ev=(EvidentiranjePrisustva) abstractDomainObject;
            if(ep.getTrening().getCena()==ev.getTrening().getCena())
                mockListaEvidencijaSaUslovom.add(abstractDomainObject);
        }

        when(dbBroker.selectList(ep)).thenReturn(mockListaEvidencijaSaUslovom);

        
        sOSearchEP.execute(ep);
        
        
        List<EvidentiranjePrisustva> rez=sOSearchEP.getPrisustva();
        
        assertNotNull(rez);
        assertEquals(2, rez.size());
        assertEquals(ep1,rez.get(0));
        assertEquals(ep2,rez.get(1));
        
        verify(dbBroker, times(1)).selectList(ep);
    }
    
    
    @Test
    public void testExecuteNeuspesno() throws Exception {
        

        when(dbBroker.selectList(ep)).thenThrow
        (new Exception("Greska prilikom citanja evidencija!"));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            sOSearchEP.execute(ep);
        });

        assertEquals("Greska prilikom citanja evidencija!", exception.getMessage());
        verify(dbBroker, times(1)).selectList(ep);
    }
    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            sOSearchEP.validate(new Trening());
        });

        assertEquals("Prosledjeni objekat nije instanca klase EvidentiranjePrisustva!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            sOSearchEP.validate(ep);
        });
    }
    

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.korisnik;

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
import rs.ac.bg.fon.ai.projekat_teretana.domain.Grad;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Korisnik;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;

/**
 *
 * @author Stefan
 */
@ExtendWith(MockitoExtension.class)
public class SOLoadKorisnikTest {

    @Mock
    private DBBroker dbBroker;

    private SOLoadKorisnik sOLoadKorisnik;
    private Korisnik korisnik;

    @BeforeEach
    public void setUp() throws Exception {
        sOLoadKorisnik = new SOLoadKorisnik();
        korisnik = new Korisnik();
        korisnik.setIme("Stefan");
        korisnik.setPrezime("Petrovic");
        korisnik.setGrad(new Grad(0, "Beograd"));

        Field instanceField = DBBroker.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, dbBroker);
    }

    @Test
    public void testExecuteUspesno() throws Exception {

        when(dbBroker.selectObject(korisnik)).thenReturn(korisnik);

        sOLoadKorisnik.execute(korisnik);

        Korisnik kor = sOLoadKorisnik.getK();

        assertEquals(korisnik, kor);

        verify(dbBroker, times(1)).selectObject(korisnik);
    }

    @Test
    public void testExecuteNeuspesno() throws Exception {

        when(dbBroker.selectObject(korisnik)).thenThrow(new Exception("Greska prilikom citanja korisnika"));

        Exception exception = assertThrows(Exception.class, () -> {
            sOLoadKorisnik.execute(korisnik);
        });

        assertEquals("Greska prilikom citanja korisnika", exception.getMessage());
        verify(dbBroker, times(1)).selectObject(korisnik);
    }

    @Test
    public void testValidateInvalidObject() {

        Exception exception = assertThrows(Exception.class, () -> {
            sOLoadKorisnik.validate(new Trening());
        });

        assertEquals("Prosledjeni objekat nije instanca klase Korisnik!", exception.getMessage());
    }

    @Test
    public void testValidateValidObject() throws Exception {

        assertDoesNotThrow(() -> {
            sOLoadKorisnik.validate(korisnik);
        });
    }

}

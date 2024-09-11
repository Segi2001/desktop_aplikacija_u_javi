/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.trener;

import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trener;
import rs.ac.bg.fon.ai.projekat_teretana.so.AbstractSO;

/**
 * Klasa SOAddTrener je odgovorna za dodavanje nove instance klase Trener u bazu
 * podataka. Ova klasa prosiruje klasu AbstractSO i implementira potrebne metode
 * za validaciju i izvrsavanje.
 *
 * Ova klasa osigurava da je objekat prosledjen za unos instanca klase Trener, a
 * zatim ga ubacuje u bazu podataka.
 *
 * @author Stefan Segrt
 */
public class SOAddTrener extends AbstractSO {

    /**
     * id koji predstavlja id ubacenog trenera u bazu
     */
    private int id;

    /**
     * Validira prosledjeni objekat.
     *
     * @param obj Objekat koji treba da se validira.
     * @throws Exception ako prosledjeni objekat nije instanca klase Trener ili
     * ako je objekat null.
     */
    @Override
    protected void validate(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;

        if (ado == null || !(ado instanceof Trener)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trener!");
        }
    }

    /**
     * Izvrsava unos objekta u bazu podataka.
     *
     * @param obj Objekat koji treba da se unese u bazu podataka.
     * @throws Exception ako dodje do greske prilikom unosa u bazu podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;

        id = DBBroker.getInstance().insert(ado);
    }

    /**
     * Vraca id novounetog trenera.
     *
     * @return id novounetog trenera.
     */
    public int getId() {
        return id;
    }
}

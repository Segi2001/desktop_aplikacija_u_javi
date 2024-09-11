/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.trening;

import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;
import rs.ac.bg.fon.ai.projekat_teretana.so.AbstractSO;





/**
 * Klasa SOAddTrening je odgovorna za dodavanje nove instance klase 
 * Trening u bazu podataka. Ova klasa prosiruje klasu AbstractSO 
 * i implementira potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je objekat prosledjen za unos instanca 
 * klase Trening, a zatim ga ubacuje u bazu podataka.
 * 
 * @author Stefan Segrt
 */
public class SOAddTrening extends AbstractSO {

    /**
     * id treninga koji je unesen u bazu podataka
     */
    private int id;

    /**
     * Vraca id novounetog treninga.
     * 
     * @return id novounetog treninga.
     */
    public int getId() {
        return id;
    }

    /**
     * Validira prosledjeni objekat.
     * 
     * @param obj Objekat koji treba da se validira.
     * @throws Exception ako prosledjeni objekat nije instanca klase Trening
     *                   ili ako je objekat null.
     */
    @Override
    protected void validate(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        
        if (!(ado instanceof Trening)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trening!");
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
}

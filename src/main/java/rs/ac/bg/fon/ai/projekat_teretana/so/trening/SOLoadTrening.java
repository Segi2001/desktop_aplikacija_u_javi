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
 * Klasa SOLoadTrening je odgovorna za ucitavanje instance klase 
 * Trening iz baze podataka po odredjenom uslovu. Ova klasa prosiruje klasu AbstractSO 
 * i implementira potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je objekat prosledjen za ucitavanje instanca 
 * klase Trening, a zatim dobavlja tu instancu iz baze podataka.
 * 
 * @author Stefan Segrt
 */
public class SOLoadTrening extends AbstractSO {

    /**
     * Trening koji se ucitava iz baze podataka
     */
    private Trening trening;

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
     * Izvrsava ucitavanje objekta iz baze podataka.
     * 
     * @param obj Objekat koji treba da se ucita iz baze podataka.
     * @throws Exception ako dodje do greske prilikom ucitavanja iz baze podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        
        AbstractDomainObject object = DBBroker.getInstance().selectObject(ado);
        trening = (Trening) object;
    }

    /**
     * Vraca ucitani trening iz baze podataka.
     * 
     * @return Ucitani trening iz baze podataka.
     */
    public Trening getTrening() {
        return trening;
    }
}

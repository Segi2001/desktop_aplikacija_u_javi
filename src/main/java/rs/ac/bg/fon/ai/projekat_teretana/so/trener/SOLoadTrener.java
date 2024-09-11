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
 * Klasa SOLoadTrener je odgovorna za učitavanje instance klase 
 * Trener iz baze podataka. Ova klasa prosiruje klasu AbstractSO 
 * i implementira potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je objekat prosledjen za učitavanje instanca 
 * klase Trener, a zatim dobavlja tu instancu iz baze podataka.
 * 
 * @author Stefan Segrt
 */
public class SOLoadTrener extends AbstractSO {

    /**
     * Trener koji predstavlja rezultat citanja iz baze podataka
     */
    private Trener trener;

    /**
     * Validira prosledjeni objekat.
     * 
     * @param obj Objekat koji treba da se validira.
     * @throws Exception ako prosledjeni objekat nije instanca klase Trener
     *                   ili ako je objekat null.
     */
    @Override
    protected void validate(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        
        if (ado == null || !(ado instanceof Trener)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trener!");
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
        trener = (Trener) object;
    }

    /**
     * Vraca ucitanog trenera iz baze podataka.
     * 
     * @return Ucitani trener iz baze podataka.
     */
    public Trener getTrener() {
        return trener;
    }    
}

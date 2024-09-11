/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.evidentiranje_prisustva;

import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.EvidentiranjePrisustva;
import rs.ac.bg.fon.ai.projekat_teretana.so.AbstractSO;
import java.util.ArrayList;
import java.util.List;



/**
 * Klasa SOSearchEP je odgovorna za pretragu instanci klase 
 * EvidentiranjePrisustva iz baze podataka. Ova klasa prosiruje 
 * klasu AbstractSO i implementira potrebne metode za validaciju 
 * i izvrsavanje.
 * 
 * Ova klasa osigurava da je prosledjeni objekat instanca klase 
 * EvidentiranjePrisustva, a zatim dobavlja listu tih instanci 
 * iz baze podataka.
 * 
 * @author Stefan Segrt
 */
public class SOSearchEP extends AbstractSO {

    /**
     * Lista evidencija koje su dobijene nakon pretrage baze podataka po uslovu
     */
    List<EvidentiranjePrisustva> prisustva;

    /**
     * Vraca listu evidentiranja prisustva iz baze podataka.
     * 
     * @return Lista evidentiranja prisustva.
     */
    public List<EvidentiranjePrisustva> getPrisustva() {
        return prisustva;
    }

    /**
     * Validira prosledjeni objekat.
     * 
     * @param obj Objekat koji treba da se validira.
     * @throws Exception ako prosledjeni objekat nije instanca klase 
     *                   EvidentiranjePrisustva ili ako je objekat null.
     */
    @Override
    protected void validate(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        if (ado == null || !(ado instanceof EvidentiranjePrisustva)) {
            throw new Exception("Prosledjeni objekat nije instanca klase EvidentiranjePrisustva!");
        }
    }

    /**
     * Izvrsava pretragu objekata u bazi podataka.
     * 
     * @param obj Objekat koji se koristi kao uslov prilikom pretrage u bazi podataka.
     * @throws Exception ako dodje do greske prilikom pretrage u bazi podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        List<AbstractDomainObject> lista = DBBroker.getInstance().selectList(ado);
        prisustva = (ArrayList<EvidentiranjePrisustva>) (ArrayList<?>) lista;
    }

}

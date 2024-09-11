/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.trener;

import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trener;
import rs.ac.bg.fon.ai.projekat_teretana.so.AbstractSO;
import java.util.ArrayList;
import java.util.List;



/**
 * Klasa SOSearchTrener je odgovorna za pretragu instanci klase 
 * Trener iz baze podataka po odredjenom uslovu. Ova klasa prosiruje klasu AbstractSO 
 * i implementira potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je objekat prosledjen za pretragu instanca 
 * klase Trener, a zatim dobavlja listu tih instanci iz baze podataka.
 * 
 * @author Stefan Segrt
 */
public class SOSearchTrener extends AbstractSO {

    /**
     * Predstavlja listu trenera dobijenih kao rezultat pretrage baze podataka po odredjenom uslovu.
     */
    private List<Trener> treneri;

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
     * Izvrsava pretragu objekata u bazi podataka.
     * 
     * @param obj Objekat koji je uslov prilikom pretrage u bazi podataka.
     * @throws Exception ako dođe do greske prilikom pretrage u bazi podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        
        List<AbstractDomainObject> lista = DBBroker.getInstance().selectList(ado);
        treneri = (ArrayList<Trener>) (ArrayList<?>) lista;
    }

    /**
     * Vraca listu trenera iz baze podataka kao rezultat pretrage.
     * 
     * @return Lista trenera iz baze podataka.
     */
    public List<Trener> getTreneri() {
        return treneri;
    }
}

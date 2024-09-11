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
 * Klasa SOGetListTrener je odgovorna za dobavljanje liste instanci 
 * klase Trener iz baze podataka. Ova klasa prosiruje klasu AbstractSO 
 * i implementira potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je objekat prosledjen za selekciju instanca 
 * klase Trener, a zatim dobavlja listu tih instanci iz baze podataka.
 * 
 * @author Stefan Segrt
 */
public class SOGetListTrener extends AbstractSO {

    /**
     * Lista trenera koja predstavlja rezultat citanja iz baze
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
     * Izvrsava selekciju objekata iz baze podataka.
     * 
     * @param obj Objekat koji sluzi kao uslov prilikom selekcije iz baze podataka.
     * @throws Exception ako dođe do greske prilikom selekcije iz baze podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        
        List<AbstractDomainObject> lista = DBBroker.getInstance().selectList(ado);
        treneri = (ArrayList<Trener>) (ArrayList<?>) lista;
    }

    /**
     * Vraca listu trenera iz baze podataka.
     * 
     * @return Lista trenera iz baze podataka.
     */
    public List<Trener> getTreneri() {
        return treneri;
    }

}

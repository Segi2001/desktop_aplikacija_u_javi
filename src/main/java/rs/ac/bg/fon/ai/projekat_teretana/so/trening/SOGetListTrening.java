/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.trening;

import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;
import rs.ac.bg.fon.ai.projekat_teretana.so.AbstractSO;
import java.util.ArrayList;
import java.util.List;



/**
 * Klasa SOGetListTrening je odgovorna za dobavljanje liste instanci 
 * klase Trening iz baze podataka. Ova klasa prosiruje klasu AbstractSO 
 * i implementira potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je objekat prosledjen za selekciju instanca 
 * klase Trening, a zatim dobavlja listu tih instanci iz baze podataka.
 * 
 * @author Stefan Segrt
 */
public class SOGetListTrening extends AbstractSO {

    /**
     * Lista treninga koje vracamo iz baze(vracamo sve treninge)
     */
    private List<Trening> treninzi;

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
     * Izvrsava selekciju objekata iz baze podataka.
     * 
     * @param obj Objekat koji treba da se selektuje iz baze podataka.
     * @throws Exception ako dodje do greske prilikom selekcije iz baze podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        
        List<AbstractDomainObject> lista = DBBroker.getInstance().selectList(ado);
        treninzi = (ArrayList<Trening>) (ArrayList<?>) lista;
    }

    /**
     * Vraca listu treninga iz baze podataka.
     * 
     * @return Lista treninga iz baze podataka.
     */
    public List<Trening> getTreninzi() {
        return treninzi;
    }

    
}

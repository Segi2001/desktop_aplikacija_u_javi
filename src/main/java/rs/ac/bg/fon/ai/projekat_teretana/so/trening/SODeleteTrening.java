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
 * Klasa SODeleteTrening je odgovorna za brisanje instance klase 
 * Trening iz baze podataka. Ova klasa prosiruje klasu AbstractSO 
 * i implementira potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je objekat prosleđen za brisanje instanca 
 * klase Trening, a zatim ga briše iz baze podataka.
 * 
 * @author Stefan Segrt
 */
public class SODeleteTrening extends AbstractSO {

    /**
     * Broj redova koji su obrisani iz tabele u bazi podataka.
     */
    int affectedRows;

    /**
     * Vraca broj obrisanih redova u bazi podataka.
     * 
     * @return Broj obrisanih redova u bazi podataka.
     */
    public int getAffectedRows() {
        return affectedRows;
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
     * Izvrsava brisanje objekta iz baze podataka.
     * 
     * @param obj Objekat koji predstavlja uslov prilikom brisanja Treninga iz baze podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        
        affectedRows = DBBroker.getInstance().delete(ado);
    }
}

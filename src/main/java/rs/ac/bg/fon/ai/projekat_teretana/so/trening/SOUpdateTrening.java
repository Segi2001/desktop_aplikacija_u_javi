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
 * Klasa SOUpdateTrening je odgovorna za azuriranje instance klase 
 * Trening u bazi podataka. Ova klasa prosiruje klasu AbstractSO 
 * i implementira potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je objekat prosledjen za azuriranje instanca 
 * klase Trening, a zatim ga azurira u bazi podataka.
 * 
 * @author Stefan Segrt
 */
public class SOUpdateTrening extends AbstractSO {

    /**
     * Koliko je redova azurirano u bazi podataka
     */
    private int affectedRows;

    /**
     * Vraca broj azuriranih redova u bazi podataka.
     * 
     * @return Broj azuriranih redova u bazi podataka.
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

        Trening trening = (Trening) ado;

        if (trening.getTrener() == null) {
            throw new Exception("Niste selektovali ni jednog trenera");
        }

        if (trening.getTrajanjeUMin() <= 0 || trening.getTrajanjeUMin() > 90) {
            throw new Exception("Trajanje treninga mora biti broj veci od nule i manje od 90 min");
        }
    }

    /**
     * Izvrsava azuriranje objekta u bazi podataka.
     * 
     * @param obj Objekat koji treba da se azurira u bazi podataka.
     * @throws Exception ako dodje do greske prilikom azuriranja u bazi podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;

        affectedRows = DBBroker.getInstance().update(ado);
    }

    
}

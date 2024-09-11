/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.grad;

import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Grad;
import rs.ac.bg.fon.ai.projekat_teretana.so.AbstractSO;
import java.util.ArrayList;
import java.util.List;



/**
 * Klasa SOGetListGrad je odgovorna za dobijanje liste gradova 
 * iz baze podataka. Ova klasa prosiruje klasu AbstractSO i 
 * implementira potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je prosledjeni objekat instanca klase 
 * Grad, a zatim dobija listu gradova iz baze podataka.
 * 
 * @author Stefan Segrt
 */
public class SOGetListGrad extends AbstractSO {

    /**
     * Lista svih gradova dobijena nakon izvrsenja upita nad bazom.
     */
    private List<Grad> gradovi;

    /**
     * Vraca listu gradova.
     * 
     * @return Lista gradova.
     */
    public List<Grad> getGradovi() {
        return gradovi;
    }

    /**
     * Validira prosledjeni objekat.
     * 
     * @param obj Objekat koji treba da se validira.
     * @throws Exception ako prosledjeni objekat nije instanca klase 
     *                   Grad ili je null.
     */
    @Override
    protected void validate(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        if (ado == null || !(ado instanceof Grad)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Grad!");
        }
    }

    /**
     * Izvrsava dobijanje liste gradova iz baze podataka.
     * 
     * @param obj Objekat koji sadrži kriterijume za pretragu gradova.
     * @throws Exception ako dodje do greske prilikom preuzimanja iz baze podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        List<AbstractDomainObject> lista = DBBroker.getInstance().selectList(ado);
        gradovi = (ArrayList<Grad>) (ArrayList<?>) lista;
    }
}

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
 * Klasa SOAddPrisustva je odgovorna za dodavanje instanci klase 
 * EvidentiranjePrisustva u bazu podataka(evidentiramo vise prisustva za jedan trening). Ova klasa prosiruje 
 * klasu AbstractSO i implementira potrebne metode za validaciju 
 * i izvrsavanje.
 * 
 * Ova klasa osigurava da su svi objekti u prosledjenoj listi 
 * instance klase EvidentiranjePrisustva, a zatim dodaje te 
 * instance u bazu podataka.
 * 
 * @author Stefan Segrt
 */
public class SOAddPrisustva extends AbstractSO {

    /**
     * id evidencije
     */
    private int id;
    /**
     * Lista svih id-jeva evidencija
     */
    ArrayList<Integer> nizId = new ArrayList<>();

    /**
     * Vraca listu id-jeva uspesno dodatih prisustava.
     * 
     * @return Lista id-jeva dodatih prisustava.
     */
    public ArrayList<Integer> getNizId() {
        return nizId;
    }

    /**
     * Validira prosledjeni objekat.
     * 
     * @param obj Objekat koji treba da se validira.
     * @throws Exception ako prosledjeni objekat nije lista instanci 
     *                   klase EvidentiranjePrisustva ili sadrži null vrednosti.
     */
    @Override
    protected void validate(Object obj) throws Exception {
        List<AbstractDomainObject> lista = (List<AbstractDomainObject>) obj;
        for (AbstractDomainObject abstractDomainObject : lista) {
            if (abstractDomainObject == null || !(abstractDomainObject instanceof EvidentiranjePrisustva)) {
                throw new Exception("Prosledjeni objekat nije instanca klase EvidentiranjePrisustva!");
            }
        }
    }

    /**
     * Izvrsava dodavanje objekata u bazi podataka.
     * 
     * @param obj Objekat koji sadrži listu instanci koje treba dodati u bazu podataka.
     * @throws Exception ako dodavanje instanci u bazu podataka ne uspe.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        List<AbstractDomainObject> lista = (List<AbstractDomainObject>) obj;
        for (AbstractDomainObject abstractDomainObject : lista) {
            id = DBBroker.getInstance().insert(abstractDomainObject);
            nizId.add(id);
        }
    }


}

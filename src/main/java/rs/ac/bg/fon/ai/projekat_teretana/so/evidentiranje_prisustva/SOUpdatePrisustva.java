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
 * Klasa SOUpdatePrisustva je odgovorna za ažuriranje instanci klase 
 * EvidentiranjePrisustva u bazi podataka. Ova klasa prosiruje 
 * klasu AbstractSO i implementira potrebne metode za validaciju 
 * i izvrsavanje.
 * 
 * Ova klasa osigurava da su svi objekti u prosledjenoj listi 
 * instanca klase EvidentiranjePrisustva, briše prethodne instance, 
 * a zatim dodaje nove instance u bazu podataka.
 * 
 * @author Stefan Segrt
 */
public class SOUpdatePrisustva extends AbstractSO {

    /**
     * id evidencije
     */
    private int id;
    /**
     * Broj izbrisanih redova u tabeli
     */
    private int affectedRows;
    /**
     * Lista koja sadrzi sve id-jeve dodatih evidencija
     */
    ArrayList<Integer> nizId = new ArrayList<>();

    /**
     * Vraca listu id-jeva uspešno dodatih prisustava.
     * 
     * @return Lista id-jeva dodatih prisustava.
     */
    public ArrayList<Integer> getNizId() {
        return nizId;
    }

    /**
     * Vraca broj redova koji su izbrisani iz tabele.
     * 
     * @return Broj izbrisanih redova.
     */
    public int getAffectedRows() {
        return affectedRows;
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
     * Izvrsava azuriranje objekata u bazi podataka tako sto prvo obrise postojece evidencije pa doda nove.
     * 
     * @param obj Objekat koji sadrži listu instanci koje treba azurirati(odnosno dodati nakon brisanja) u bazi podataka.
     * @throws Exception ako dodje do greske prilikom azuriranja u bazi podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        List<AbstractDomainObject> lista = (List<AbstractDomainObject>) obj;
        if (lista.size() != 0) {
            AbstractDomainObject ado = lista.get(0);
            affectedRows = DBBroker.getInstance().delete(ado);
        }
        for (AbstractDomainObject abstractDomainObject : lista) {
            id = DBBroker.getInstance().insert(abstractDomainObject);
            nizId.add(id);
        }
        System.out.println(nizId.size());
    }

}

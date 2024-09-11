/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.korisnik;

import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Korisnik;
import rs.ac.bg.fon.ai.projekat_teretana.so.AbstractSO;
import java.util.ArrayList;
import java.util.List;



/**
 * Klasa SOSearchKorisnik je odgovorna za pretragu instanci klase 
 * Korisnik iz baze podataka po odredjenom uslovu. Ova klasa prosiruje klasu AbstractSO 
 * i implementira potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je objekat prosledjen za pretragu instanci 
 * klase Korisnik, a zatim dobavlja listu tih instanci iz baze podataka.
 * 
 * @author Stefan Segrt
 */
public class SOSearchKorisnik extends AbstractSO {

    /**
     * Lista korisnika dobijenih nakon pretrage baze podataka po odredjenom uslovu
     */
    List<Korisnik> korisnici;

    /**
     * Vraca listu korisnika iz baze podataka.
     * 
     * @return Lista korisnika iz baze podataka.
     */
    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    /**
     * Validira prosledjeni objekat.
     * 
     * @param obj Objekat koji treba da se validira.
     * @throws Exception ako prosledjeni objekat nije instanca klase Korisnik
     *                   ili ako je objekat null.
     */
    @Override
    protected void validate(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;

        if (ado == null || !(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
    }

    /**
     * Izvrsava pretragu objekata u bazi podataka.
     * 
     * @param obj Objekat koji treba da se pretrazi u bazi podataka.
     * @throws Exception ako dodje do greske prilikom pretrage u bazi podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;

        List<AbstractDomainObject> lista = DBBroker.getInstance().selectList(ado);
        korisnici = (ArrayList<Korisnik>) (ArrayList<?>) lista;
    }

}

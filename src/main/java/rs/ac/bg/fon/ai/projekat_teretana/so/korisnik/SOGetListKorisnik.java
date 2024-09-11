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
 * Klasa SOGetListKorisnik je odgovorna za dobijanje liste instanci 
 * klase Korisnik iz baze podataka(svih Korisnika). Ova klasa prosiruje klasu AbstractSO 
 * i implementira potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je objekat prosledjen za selekciju instanca 
 * klase Korisnik, a zatim dobavlja listu tih instanci iz baze podataka.
 *
 * @author Stefan Segrt
 */
public class SOGetListKorisnik extends AbstractSO {

    /**
     * Lista korisnika koja ce se dobiti kao rezultat izvrsenja upita nad bazom podataka.
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
     * Izvrsava selekciju objekata iz baze podataka.
     * 
     * @param obj Objekat koji treba da se selektuje iz baze podataka.
     * @throws Exception ako dodje do greske prilikom selekcije iz baze podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;

        List<AbstractDomainObject> lista = DBBroker.getInstance().selectList(ado);
        korisnici = (ArrayList<Korisnik>) (ArrayList<?>) lista;
    }


}

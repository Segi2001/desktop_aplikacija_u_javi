/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.korisnik;

import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Korisnik;
import rs.ac.bg.fon.ai.projekat_teretana.so.AbstractSO;



/**
 * Klasa SOLoadKorisnik je odgovorna za ucitavanje instance klase 
 * Korisnik iz baze podataka po odredjenom uslovu. Ova klasa prosiruje klasu AbstractSO 
 * i implementira potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je objekat prosledjen za ucitavanje instanca 
 * klase Korisnik, a zatim dobavlja tu instancu iz baze podataka.
 * 
 * @author Stefan Segrt
 */
public class SOLoadKorisnik extends AbstractSO {

    /**
     * Korisnik koji se dobija nakon izvrsenja upita nad bazom pod odredjenim uslovom
     */
    private Korisnik k;

    /**
     * Vraca ucitanog korisnika iz baze podataka.
     * 
     * @return Ucitani korisnik iz baze podataka.
     */
    public Korisnik getK() {
        return k;
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
     * Izvrsava ucitavanje objekta iz baze podataka.
     * 
     * @param obj Objekat koji treba da se ucita iz baze podataka.
     * @throws Exception ako dodje do greške prilikom ucitavanja iz baze podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;

        AbstractDomainObject object;
        object = DBBroker.getInstance().selectObject(ado);
        k = (Korisnik) object;
    }
}

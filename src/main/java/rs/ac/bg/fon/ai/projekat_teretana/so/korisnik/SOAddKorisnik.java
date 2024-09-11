/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.korisnik;

import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.KT;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Korisnik;
import rs.ac.bg.fon.ai.projekat_teretana.domain.TipTreninga;
import rs.ac.bg.fon.ai.projekat_teretana.so.AbstractSO;
import java.util.ArrayList;
import java.util.List;



/**
 * Klasa SOAddKorisnik je odgovorna za dodavanje nove instance klase 
 * Korisnik u bazu podataka kao i tipova treninga samog korisnika. Ova klasa prosiruje klasu AbstractSO 
 * i implementira potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je objekat prosledjen za unos instanca 
 * klase Korisnik, a zatim ga dodaje u bazu podataka, zajedno sa 
 * povezanim tipovima treninga.
 * 
 * @author Stefan Segrt
 */
public class SOAddKorisnik extends AbstractSO {

    /**
     * Id korisnika koji je dodat u bazu podataka.
     */
    private int idKorisnik;
    /**
     * Vraca id tipa koji je dodat u bazu podataka
     */
    private int idTipa;
    /**
     * Niz koji sluzi skup svih id-jeva tipa treninga koji su dodati
    */
    private ArrayList<Integer> nizId = new ArrayList<>();

    /**
     * Vraca id novounetog korisnika.
     * 
     * @return id novounetog korisnika.
     */
    public int getIdKorisnik() {
        return idKorisnik;
    }

    /**
     * Vraca listu id-jeva tipova treninga povezanih sa korisnikom.
     * 
     * @return Lista id-jeva tipova treninga.
     */
    public ArrayList<Integer> getNizId() {
        return nizId;
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
     * Izvrsava unos objekta u bazu podataka.
     * 
     * @param obj Objekat koji treba da se unese u bazu podataka.
     * @throws Exception ako dodje do greske prilikom unosa u bazu podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;

        idKorisnik = DBBroker.getInstance().insert(ado);
        Korisnik k = (Korisnik) ado;
        List<TipTreninga> tipovi = k.getTipovi();
        KT kt = new KT();
        kt.setKorisnik(k);
        kt.getKorisnik().setIdKorisnika(idKorisnik);

        for (TipTreninga tipTreninga : tipovi) {
            kt.setTip(tipTreninga);
            AbstractDomainObject ado1 = kt;
            idTipa = DBBroker.getInstance().insert(ado1);
            nizId.add(idTipa);
        }
    }

}

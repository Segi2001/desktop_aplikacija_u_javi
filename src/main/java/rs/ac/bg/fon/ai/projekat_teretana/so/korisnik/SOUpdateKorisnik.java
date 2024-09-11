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
 * Klasa SOUpdateKorisnik je odgovorna za ažuriranje instance klase Korisnik u
 * bazi podataka kao i azuriranje tipova treninga korisnika. Ova klasa prosiruje klasu AbstractSO i implementira potrebne
 * metode za validaciju i izvrsavanje.
 *
 * 
 * Ova klasa osigurava da je objekat prosledjen za azuriranje instanca klase
 * Korisnik, vrsi azuriranje u bazi podataka, kao i brisanje prethodnih
 * povezanih tipova treninga i dodavanje novih.
 *
 * @author Stefan Segrt
 */
public class SOUpdateKorisnik extends AbstractSO {

    /**
     * Vraca broj azuriranih kolona korisnika
     */
    int affectedRowsUpdate;
    /**
     * Vraca broj obrisanih redova (tipova treninga)
     */
    int affectedRowsDelete;
    /**
     * Id tipa treninga
     */
    int idTipa;
    /**
     * Lista ,odnosno skup svih id-jeva tipova treninga
     */
    private ArrayList<Integer> nizId = new ArrayList<>();

    /**
     * Vraca broj redova koji su azurirani prilikom izvrsavanja operacije.
     *
     * @return Broj azuriranih redova.
     */
    public int getAffectedRowsUpdate() {
        return affectedRowsUpdate;
    }

    /**
     * Vraca broj redova koji su obrisani prilikom izvrsavanja operacije.
     *
     * @return Broj obrisanih redova.
     */
    public int getAffectedRowsDelete() {
        return affectedRowsDelete;
    }

    /**
     * Vraća listu id-jeva tipova treninga povezanih sa korisnikom.
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
     * ili ako je objekat null.
     */
    @Override
    protected void validate(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;

        if (ado == null || !(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
    }

    /**
     * Izvrsava azuriranje objekta u bazi podataka.
     *
     * @param obj Objekat koji treba da se azurira u bazi podataka.
     * @throws Exception ako dodje do greske prilikom azuriranja ili brisanja u
     * bazi podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;

        affectedRowsUpdate = DBBroker.getInstance().update(ado);
        Korisnik k = (Korisnik) ado;
        KT kt = new KT();
        kt.setKorisnik(k);
        AbstractDomainObject ado1 = kt;
        affectedRowsDelete = DBBroker.getInstance().delete(ado1);

        List<TipTreninga> tipovi = k.getTipovi();
        for (TipTreninga tipTreninga : tipovi) {
            kt.setTip(tipTreninga);
            AbstractDomainObject ado2 = kt;
            idTipa = DBBroker.getInstance().insert(ado2);
            nizId.add(idTipa);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.so.rezultat_korisnika;

import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.RezultatiKorisnika;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Statistika;
import rs.ac.bg.fon.ai.projekat_teretana.so.AbstractSO;



/**
 * Klasa SOAddRezultat je odgovorna za dodavanje instanci klase 
 * RezultatiKorisnika u bazi podataka kao i Statistiku korisnika. Ova klasa prosiruje 
 * klasu AbstractSO i implementira potrebne metode za validaciju 
 * i izvrsavanje.
 * 
 * Ova klasa osigurava da je prosledjeni objekat instanca klase 
 * RezultatiKorisnika, a zatim dodaje rezultate korisnika u bazu 
 * podataka, ukljucujuci povezanu statistiku.
 * 
 * @author Stefan Segrt
 */
public class SOAddRezultat extends AbstractSO {

    /**
     * Id rezultata koji je ubacen u bazu.
     */
    private int idRez;
    /**
     * id statistike koja je ubacena u bazu.
     */
    private int idStat;

    /**
     * Vraca id statistike povezane sa rezultatom.
     * 
     * @return id statistike.
     */
    public int getIdStat() {
        return idStat;
    }

    /**
     * Vraca id rezultata korisnika.
     * 
     * @return id rezultata.
     */
    public int getIdRez() {
        return idRez;
    }

    /**
     * Validira prosledjeni objekat.
     * 
     * @param obj Objekat koji treba da se validira.
     * @throws Exception ako prosledjeni objekat nije instanca klase 
     *                   RezultatiKorisnika ili je null.
     */
    @Override
    protected void validate(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        if (ado == null || !(ado instanceof RezultatiKorisnika)) {
            throw new Exception("Prosledjeni objekat nije instanca klase RezultatKorisnika!");
        }
    }

    /**
     * Izvrsava dodavanje rezultata u bazi podataka zajedno sa statistikom rezultata.
     * 
     * @param obj Objekat koji sadrzi rezultate korisnika koje treba dodati.
     * @throws Exception ako dodje do greske prilikom dodavanja u bazi podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        RezultatiKorisnika rezKor = (RezultatiKorisnika) obj;
        Statistika s = rezKor.getStatistika();
        AbstractDomainObject ado1 = s;
        idStat = DBBroker.getInstance().insert(ado1);

        s.setId(idStat);
        rezKor.setStatistika(s);

        AbstractDomainObject ado2 = rezKor;
        idRez = DBBroker.getInstance().insert(ado2);
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.domain;

import com.google.gson.annotations.Expose;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Predstavlja klasu RezultatiKorisnika koja nasleđuje apstraktnu klasu
 * AbstractDomainObject za rad sa bazom podataka. Klasa sadrži atribute kao što
 * su id rezultata, korisnik, datum rezultata i statistika.
 *
 * @author Stefan Segrt
 */
public class RezultatiKorisnika extends AbstractDomainObject {

    /**
     * id rezultata
     */
    @Expose
    private int idRez;
    /**
     * Korisnik na koga se odnosi postignut rezultat
     */
    @Expose
    private Korisnik korisnik;

    /**
     * Datum kada je postignut taj rezultat,odnosno kada su izvrsena merenja
     */
    @Expose
    private Date datumRezultata;
    /**
     * Statistika samog rezultata,u kojoj se nalaze procenat masti,misica i sama
     * tezina korisnika
     */
    @Expose
    private Statistika statistika;

    /**
     * Podrazumevani konstruktor.
     */
    public RezultatiKorisnika() {
    }

    /**
     * Konstruktor sa parametrima.
     *
     * @param idRez id rezultata.
     * @param korisnik Korisnik koji je vezan za rezultat.
     * @param datumRezultata Datum kada je rezultat ostvaren.
     * @param statistika Statistika koja je vezana za rezultat.
     */
    public RezultatiKorisnika(int idRez, Korisnik korisnik, Date datumRezultata, Statistika statistika) {
        this.idRez = idRez;
        this.korisnik = korisnik;
        this.datumRezultata = datumRezultata;
        this.statistika = statistika;
    }

    /**
     * Vraća id rezultata.
     *
     * @return id rezultata.
     */
    public int getIdRez() {
        return idRez;
    }

    /**
     * Postavlja id rezultata.
     *
     * @param idRez id rezultata koji se postavlja.
     */
    public void setIdRez(int idRez) {
        this.idRez = idRez;
    }

    /**
     * Vraća korisnika koji je vezan za rezultat.
     *
     * @return Objekat korisnika.
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }

    /**
     * Postavlja korisnika koji je vezan za rezultat.
     *
     * @param korisnik Objekat korisnika koji se postavlja.
     * @throws NullPointerException ako je korisnik null.
     */
    public void setKorisnik(Korisnik korisnik) {
        if (korisnik == null) {
            throw new NullPointerException("Korisnik ne sme biti null");
        }
        this.korisnik = korisnik;
    }

    /**
     * Vraća datum rezultata.
     *
     * @return Datum kada je postignut rezultat.
     */
    public Date getDatumRezultata() {
        return datumRezultata;
    }

    /**
     * Postavlja datum rezultata.
     *
     * @param datumRezultata Datum rezultata koji se postavlja.
     * @throws NullPointerException ako je datumRezultata null.
     * @throws IllegalArgumentException ako je datumRezultata u budućnosti.
     */
    public void setDatumRezultata(Date datumRezultata) {
        if (datumRezultata == null) {
            throw new NullPointerException("Datum ne sme biti null");
        }

        if (datumRezultata.after(new Date())) {
            throw new IllegalArgumentException("Datum ne sme biti u budućnosti");
        }
        this.datumRezultata = datumRezultata;
    }

    /**
     * Vraća statistiku vezanu za rezultat.
     *
     * @return Objekat statistike u kome se nalazi procenat masti,misica i
     * tezina korisnika.
     */
    public Statistika getStatistika() {
        return statistika;
    }

    /**
     * Postavlja statistiku vezanu za rezultat.
     *
     * @param statistika Objekat statistike koji se postavlja koji sadrzi
     * procenat masti,misica i tezinu korisnika.
     * @throws NullPointerException ako je statistika null.
     */
    public void setStatistika(Statistika statistika) {
        if (statistika == null) {
            throw new NullPointerException("Statistika ne sme biti null");
        }
        this.statistika = statistika;
    }

    /**
     * Vraća string reprezentaciju objekta.
     *
     * @return String koji opisuje korisnika, datum rezultata i statistiku.
     */
    @Override
    public String toString() {
        return "RezultatiKorisnika{"
                + "korisnik=" + korisnik
                + ", datumRezultata=" + datumRezultata
                + ", statistika=" + statistika
                + '}';
    }

    /**
     * Vraća heš kod za objekat.
     *
     * @return heš kod.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    /**
     * Proverava da li su dva objekta jednaka.
     *
     * @param obj objekat sa kojim se poredi RezultatiKorisnika.
     * @return true - ako oba objekta imaju istu memorijsku lokaciju, ili ako su
     * iste klase i imaju istog korisnika i datum rezultata. false - u ostalim
     * slucajevima
     *
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RezultatiKorisnika other = (RezultatiKorisnika) obj;
        if (!Objects.equals(this.korisnik, other.korisnik)) {
            return false;
        }
        return Objects.equals(this.datumRezultata, other.datumRezultata);
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovom entitetu.
     *
     * @return naziv tabele kao String
     */
    @Override
    public String tableName() {
        return " postignuti_rezultati ";
    }

    /**
     * Vraća alijas koji se koristi za ovu tabelu u SQL upitima.
     *
     * @return alijas kao String
     */
    @Override
    public String alies() {
        return " pr ";
    }

    /**
     * Vraća SQL kod za JOIN sa drugim tabelama u SQL upitu.
     *
     * @return SQL kod za JOIN kao String
     */
    @Override
    public String textJoin() {
        return "";
    }

    /**
     * Vraća nazive kolona za umetanje u bazu podataka.
     *
     * @return String sa nazivima kolona.
     */
    @Override
    public String insertColumns() {
        return " (idKorisnika, datumRezultata, idStatistike) ";
    }

    /**
     * Vraća vrednosti za umetanje u bazu podataka.
     *
     * @return String sa vrednostima.
     */
    @Override
    public String insertValues() {
        return korisnik.getIdKorisnika() + ",'" + datumRezultata + "'" + "," + statistika.getId();
    }

    /**
     * Metoda za ažuriranje vrednosti koje se umetnu u bazu podataka.
     *
     * @return String sa vrednostima ukoliko ih ima.
     */
    @Override
    public String updateValues() {
        return "";
    }

    /**
     * Vraća uslov potreban za delete upit nad bazom podataka.
     *
     * @return String sa uslovom.
     */
    @Override
    public String requiredCondition() {
        return "";
    }

    /**
     * Vraća uslov za selektovanje podataka iz baze podataka.
     *
     * @return String sa uslovom za selekciju.
     */
    @Override
    public String conditionForSelect() {
        return "";
    }

    /**
     * Pravi listu objekata RezultatiKorisnika na osnovu rezultata iz
     * ResultSet-a.
     *
     * @param rs ResultSet iz baze podataka.
     * @return Lista objekata.
     * @throws SQLException ako dođe do greške prilikom pristupa ResultSet-u.
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        return null;
    }

    /**
     * Metoda za dobijanje uslova za izvršenje SELECT upita i vraćanje jednog
     * primerka entiteta.
     *
     * @return String kao uslov.
     */
    @Override
    public String getIdCondition() {
        return "";
    }

}

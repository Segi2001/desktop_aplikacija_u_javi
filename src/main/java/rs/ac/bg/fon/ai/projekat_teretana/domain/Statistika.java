/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.domain;

import com.google.gson.annotations.Expose;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Predstavlja klasu Statistika koja nasleđuje apstraktnu klasu
 * AbstractDomainObject za rad sa bazom podataka. Klasa sadrži atribute kao što
 * su id, procenat masti, procenat mišića i težina u kilogramima.
 *
 * @author Stefan Segrt
 */
public class Statistika extends AbstractDomainObject {

    /**
     * id statistike
     */
    @Expose
    private int id;

    /**
     * procenat masti koje je izmereno za korisnika
     */
    @Expose
    private double procenatMasti;

    /**
     * procenat misica koje je izmereno za korisnika
     */
    @Expose
    private double procenatMisica;

    /**
     * Tezina korisnika u kilogramima
     */
    @Expose
    private double tezinaUKG;

    /**
     * Podrazumevani konstruktor.
     */
    public Statistika() {
    }

    /**
     * Konstruktor sa parametrima.
     *
     * @param id id statistike.
     * @param procenatMasti Procenat masti u telu.
     * @param procenatMisica Procenat mišićne mase u telu.
     * @param tezinaUKG Težina u kilogramima.
     */
    public Statistika(int id, double procenatMasti, double procenatMisica, double tezinaUKG) {
        this.id = id;
        this.procenatMasti = procenatMasti;
        this.procenatMisica = procenatMisica;
        this.tezinaUKG = tezinaUKG;
    }

    /**
     * Vraća id statistike.
     *
     * @return id statistike.
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja id statistike.
     *
     * @param id id statistike koji se postavlja.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća procenat masti u telu.
     *
     * @return Procenat masti u telu.
     */
    public double getProcenatMasti() {
        return procenatMasti;
    }

    /**
     * Postavlja procenat masti u telu.
     *
     * @param procenatMasti Procenat masti koji se postavlja.
     * @throws IllegalArgumentException ako je procenatMasti manji od 0.
     */
    public void setProcenatMasti(double procenatMasti) {
        if (procenatMasti < 0) {
            throw new IllegalArgumentException("Procenat masti mora biti pozitivan broj");
        }
        this.procenatMasti = procenatMasti;
    }

    /**
     * Vraća procenat mišićne mase u telu.
     *
     * @return Procenat mišićne mase u telu.
     */
    public double getProcenatMisica() {
        return procenatMisica;
    }

    /**
     * Postavlja procenat mišićne mase u telu.
     *
     * @param procenatMisica Procenat mišićne mase koji se postavlja.
     * @throws IllegalArgumentException ako je procenatMisica manji od 0.
     */
    public void setProcenatMisica(double procenatMisica) {
        if (procenatMisica < 0) {
            throw new IllegalArgumentException("Procenat mišića mora biti pozitivan broj");
        }
        this.procenatMisica = procenatMisica;
    }

    /**
     * Vraća težinu u kilogramima.
     *
     * @return Težina u kilogramima.
     */
    public double getTezinaUKG() {
        return tezinaUKG;
    }

    /**
     * Postavlja težinu u kilogramima.
     *
     * @param tezinaUKG Težina u kilogramima koja se postavlja.
     * @throws IllegalArgumentException ako je tezinaUKG manja od 0.
     */
    public void setTezinaUKG(double tezinaUKG) {
        if (tezinaUKG < 0) {
            throw new IllegalArgumentException("Težina mora biti pozitivan broj");
        }
        this.tezinaUKG = tezinaUKG;
    }

    /**
     * Vraća string reprezentaciju objekta.
     *
     * @return String koji opisuje procenat masti, procenat mišića i težinu u
     * kilogramima.
     */
    @Override
    public String toString() {
        return "Statistika{"
                + "procenatMasti=" + procenatMasti
                + ", procenatMisica=" + procenatMisica
                + ", tezinaUKG=" + tezinaUKG
                + '}';
    }

    /**
     * Vraća heš kod za objekat.
     *
     * @return heš kod.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Proverava da li su dva objekta jednaka.
     *
     * @param obj objekat sa kojim se poredi Statistika.
     * @return true - ako oba objekta imaju istu memorijsku lokaciju, ili ako su
     * iste klase i imaju isti procenat masti, procenat mišića i težinu u
     * kilogramima. false - u ostalim slučajevima
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
        final Statistika other = (Statistika) obj;
        if (Double.doubleToLongBits(this.procenatMasti) != Double.doubleToLongBits(other.procenatMasti)) {
            return false;
        }
        if (Double.doubleToLongBits(this.procenatMisica) != Double.doubleToLongBits(other.procenatMisica)) {
            return false;
        }
        return Double.doubleToLongBits(this.tezinaUKG) == Double.doubleToLongBits(other.tezinaUKG);
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovom entitetu.
     *
     * @return naziv tabele kao String
     */
    @Override
    public String tableName() {
        return "statistika";
    }

    /**
     * Vraća alijas koji se koristi za ovu tabelu u SQL upitima.
     *
     * @return alijas kao String
     */
    @Override
    public String alies() {
        return "s";
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
        return " (procenatMasti, procenatMisica, tezinaUKG) ";
    }

    /**
     * Vraća vrednosti za umetanje u bazu podataka.
     *
     * @return String sa vrednostima.
     */
    @Override
    public String insertValues() {
        return procenatMasti + "," + procenatMisica + "," + tezinaUKG;
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
     * Metoda za dobijanje uslova za izvršenje SELECT upita i vraćanje jednog
     * primerka entiteta.
     *
     * @return String kao uslov.
     */
    @Override
    public String getIdCondition() {
        return "";
    }

    /**
     * Pravi listu objekata Statistika na osnovu rezultata iz ResultSet-a.
     *
     * @param rs ResultSet iz baze podataka.
     * @return Lista objekata.
     * @throws SQLException ako dođe do greške prilikom pristupa ResultSet-u.
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        return null;
    }

}

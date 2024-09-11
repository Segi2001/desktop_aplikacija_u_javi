/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.domain;

import com.google.gson.annotations.Expose;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Predstavlja klasu Grad koja nasledjuje apstraktnu AbstractDomainObject za rad
 * sa bazom podataka. Klasa sadrzi atribut id i naziv grada.
 *
 * @author Stefan Segrt
 */
public class Grad extends AbstractDomainObject {

    /**
     * id odredjenog grada
     */
    @Expose
    private int idGrada;

    /**
     * Naziv odredjenog grada
     */
    @Expose
    private String naziv;

    /**
     * Podrazumevani konstruktor.
     */
    public Grad() {
    }

    /**
     * Konstruktor sa parametrima.
     *
     * @param idGrada ID grada.
     * @param naziv Naziv grada.
     */
    public Grad(int idGrada, String naziv) {
        this.idGrada = idGrada;
        this.naziv = naziv;
    }

    /**
     * Vraća ID grada.
     *
     * @return ID grada.
     */
    public int getIdGrada() {
        return idGrada;
    }

    /**
     * Postavlja ID grada.
     *
     * @param idGrada ID grada koji se postavlja.
     */
    public void setIdGrada(int idGrada) {
        this.idGrada = idGrada;
    }

    /**
     * Vraća naziv grada.
     *
     * @return Naziv grada.
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * Postavlja naziv grada.
     *
     * @param naziv Naziv grada koji se postavlja.
     * @throws NullPointerException ako je naziv null.
     * @throws IllegalArgumentException ako je naziv prazan string.
     */
    public void setNaziv(String naziv) {
        if (naziv == null) {
            throw new NullPointerException("Naziv ne sme biti null");
        }
        if (naziv.isEmpty()) {
            throw new IllegalArgumentException("Naziv ne sme biti prazan string");
        }
        this.naziv = naziv;
    }

    /**
     * Vraća string reprezentaciju objekta.
     *
     * @return String koji predstavlja naziv grada.
     */
    @Override
    public String toString() {
        return naziv;
    }

    /**
     * Vraća heš kod za objekat.
     *
     * @return int koji predstavlja heš kod.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Proverava da li su dva objekta jednaka
     *
     * @param obj objekat sa kojim se poredi Grad
     * @return true - ako oba objekta imaju istu memorijsku lokaciju, ili ako su
     * iste klase i imaju istu naziva false - u ostalim slucajevima
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
        final Grad other = (Grad) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovom entitetu.
     *
     * @return naziv tabele kao String
     */
    @Override
    public String tableName() {
        return " grad ";
    }

    /**
     * Vraća alijas koji se koristi za ovu tabelu u SQL upitima.
     *
     * @return alijas kao String
     */
    @Override
    public String alies() {
        return " g ";
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
        return "";
    }

    /**
     * Vraća vrednosti za umetanje u bazu podataka.
     *
     * @return String sa vrednostima.
     */
    @Override
    public String insertValues() {
        return "";
    }

    /**
     * Metoda za ažuriranje vrednosti koje se umecu u bazu podataka.
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
     * Pravi listu objekata Grad na osnovu rezultata ResultSet-a.
     *
     * @param rs ResultSet iz baze podataka.
     * @return Lista objekata.
     * @throws SQLException ako dođe do greške prilikom pristupa ResultSet-u.
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            int idGrada = rs.getInt("idGrada");
            String nazivGrada = rs.getString("nazivGrada");

            Grad g = new Grad(idGrada, nazivGrada);

            lista.add(g);
        }
        rs.close();
        return lista;
    }

    /**
     * Metoda za dobijanje uslova za izvrsenje select upita i vracanje jednog
     * primerka entiteta.
     *
     * @return String kao uslov.
     */
    @Override
    public String getIdCondition() {
        return "";
    }
}

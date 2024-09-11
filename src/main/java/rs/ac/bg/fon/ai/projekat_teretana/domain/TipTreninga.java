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
 * Predstavlja klasu TipTreninga koja nasleđuje apstraktnu klasu
 * AbstractDomainObject za rad sa bazom podataka. Klasa sadrži atribute kao što
 * su id tipa treninga, naziv tipa treninga i opis tipa treninga.
 *
 * @author Stefan Segrt
 */
public class TipTreninga extends AbstractDomainObject {

    /**
     * id tipa treninga
     */
    @Expose
    private int idTipa;

    /**
     * Naziv tipa treninga
     */
    @Expose
    private String nazivTipa;
    /**
     * Opis tog tipa treninga,odnosno njehov detaljan opis i prednosti
     */
    @Expose
    private String opis;
    private static final long serialVersionUID = 1234567890;

    /**
     * Podrazumevani konstruktor.
     */
    public TipTreninga() {
    }

    /**
     * Konstruktor sa parametrima.
     *
     * @param idTipa id tipa treninga.
     * @param nazivTipa Naziv tipa treninga.
     * @param opis Opis tipa treninga.
     */
    public TipTreninga(int idTipa, String nazivTipa, String opis) {
        this.idTipa = idTipa;
        this.nazivTipa = nazivTipa;
        this.opis = opis;
    }

    /**
     * Vraća id tipa treninga.
     *
     * @return id tipa treninga.
     */
    public int getIdTipa() {
        return idTipa;
    }

    /**
     * Postavlja id tipa treninga.
     *
     * @param idTipa id tipa treninga koji se postavlja.
     */
    public void setIdTipa(int idTipa) {
        this.idTipa = idTipa;
    }

    /**
     * Vraća naziv tipa treninga.
     *
     * @return Naziv tipa treninga.
     */
    public String getNazivTipa() {
        return nazivTipa;
    }

    /**
     * Postavlja naziv tipa treninga.
     *
     * @param nazivTipa Naziv tipa treninga koji se postavlja.
     * @throws NullPointerException ako je nazivTipa null.
     * @throws IllegalArgumentException ako je nazivTipa prazan String.
     */
    public void setNazivTipa(String nazivTipa) {
        if (nazivTipa == null) {
            throw new NullPointerException("Naziv ne sme biti null");
        }
        if (nazivTipa.isEmpty()) {
            throw new IllegalArgumentException("Naziv ne sme biti prazan string");
        }
        this.nazivTipa = nazivTipa;
    }

    /**
     * Vraća opis tipa treninga.
     *
     * @return Opis tipa treninga.
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Postavlja opis tipa treninga.
     *
     * @param opis Opis tipa treninga koji se postavlja.
     * @throws NullPointerException ako je opis null.
     * @throws IllegalArgumentException ako je opis prazan String.
     */
    public void setOpis(String opis) {
        if (opis == null) {
            throw new NullPointerException("Opis ne sme biti null");
        }
        if (opis.isEmpty()) {
            throw new IllegalArgumentException("Opis ne sme biti prazan string");
        }
        this.opis = opis;
    }

    /**
     * Vraća string reprezentaciju naziva tipa treninga.
     *
     * @return String koji predstavlja naziv tipa treninga.
     */
    @Override
    public String toString() {
        return nazivTipa;
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
     * @param obj objekat sa kojim se poredi TipTreninga.
     * @return true - ako oba objekta imaju istu memorijsku lokaciju, ili ako
     * imaju isti naziv tipa treninga. false - u ostalim slučajevima
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
        final TipTreninga other = (TipTreninga) obj;
        return Objects.equals(this.nazivTipa, other.nazivTipa);
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovom entitetu.
     *
     * @return naziv tabele kao String
     */
    @Override
    public String tableName() {
        return "tip_treninga";
    }

    /**
     * Vraća alijas koji se koristi za ovu tabelu u SQL upitima.
     *
     * @return alijas kao String
     */
    @Override
    public String alies() {
        return "tt";
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
     * Vraća listu objekata TipTreninga na osnovu ResultSet-a.
     *
     * @param rs ResultSet iz kojeg se čitaju podaci.
     * @return Lista objekata TipTreninga.
     * @throws SQLException ako dođe do greške prilikom čitanja podataka iz
     * ResultSet-a.
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("idTipa");
            String naziv = rs.getString("nazivTipa");
            String opis = rs.getString("opis");

            TipTreninga tipTreninga = new TipTreninga(id, naziv, opis);
            lista.add(tipTreninga);
        }

        rs.close();
        return lista;
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

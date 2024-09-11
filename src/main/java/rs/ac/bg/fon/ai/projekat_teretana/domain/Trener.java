/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.domain;

import com.google.gson.annotations.Expose;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Predstavlja klasu Trener koja nasleđuje apstraktnu klasu AbstractDomainObject
 * za rad sa bazom podataka. Klasa sadrži atribute kao što su ID trenera, ime,
 * prezime, kontakt, sertifikat i godine iskustva.
 *
 * @author Stefan SegrtS
 */
public class Trener extends AbstractDomainObject {

    /**
     * id trenera
     */
    @Expose
    private int idTrenera;

    /**
     * Ime trenera
     */
    @Expose
    private String ime;

    /**
     * Prezime trenera
     */
    @Expose
    private String prezime;

    /**
     * Kontakt telefon trenera
     */
    @Expose
    private String kontakt;

    /**
     * Da li trener ima sertifikat ili ne
     */
    @Expose
    private Boolean sertifikat;

    /**
     * Koliko godina iskustva ima trener u svom poslu
     */
    @Expose
    private int godinaIskustva;

    /**
     * Podrazumevani konstruktor.
     */
    public Trener() {
    }

    /**
     * Konstruktor sa parametrima.
     *
     * @param idTrenera id trenera.
     * @param ime Ime trenera.
     * @param prezime Prezime trenera.
     * @param kontakt Kontakt trenera.
     * @param sertifikat Da li trener poseduje sertifikat.
     * @param godineIskustva Godine iskustva trenera.
     */
    public Trener(int idTrenera, String ime, String prezime, String kontakt, Boolean sertifikat, int godineIskustva) {
        this.idTrenera = idTrenera;
        this.ime = ime;
        this.prezime = prezime;
        this.kontakt = kontakt;
        this.sertifikat = sertifikat;
        this.godinaIskustva = godineIskustva;
    }

    /**
     * Vraća id trenera.
     *
     * @return id trenera.
     */
    public int getIdTrenera() {
        return idTrenera;
    }

    /**
     * Postavlja id trenera.
     *
     * @param idTrenera id trenera koji se postavlja.
     */
    public void setIdTrenera(int idTrenera) {
        this.idTrenera = idTrenera;
    }

    /**
     * Vraća ime trenera.
     *
     * @return Ime trenera.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime trenera.
     *
     * @param ime Ime trenera koje se postavlja.
     * @throws IllegalArgumentException ako ime nije u dobrom formatu.
     * @throws NullPointerException ako je ime null.
     */
    public void setIme(String ime) {
        if (!validateIme(ime)) {
            throw new IllegalArgumentException("Ime nije u dobrom formatu");
        }
        if (ime == null) {
            throw new NullPointerException("Ime ne sme biti null");
        }
        this.ime = ime;
    }

    /**
     * Vraća prezime trenera.
     *
     * @return Prezime trenera.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime trenera.
     *
     * @param prezime Prezime trenera koje se postavlja.
     * @throws IllegalArgumentException ako prezime nije u dobrom formatu.
     * @throws NullPointerException ako je prezime null.
     */
    public void setPrezime(String prezime) {
        if (!validatePrezime(prezime)) {
            throw new IllegalArgumentException("Prezime nije u dobrom formatu");
        }
        if (prezime == null) {
            throw new NullPointerException("Prezime ne sme biti null");
        }
        this.prezime = prezime;
    }

    /**
     * Vraća kontakt trenera.
     *
     * @return Kontakt trenera.
     */
    public String getKontakt() {
        return kontakt;
    }

    /**
     * Postavlja kontakt trenera.
     *
     * @param kontakt Kontakt trenera koji se postavlja.
     * @throws IllegalArgumentException ako kontakt nije u dobrom formatu.
     * @throws NullPointerException ako je kontakt null.
     */
    public void setKontakt(String kontakt) {
        if (!validateKontakt(kontakt)) {
            throw new IllegalArgumentException("Kontakt nije u dobrom formatu");
        }
        if (kontakt == null) {
            throw new NullPointerException("Kontakt ne sme biti null");
        }
        this.kontakt = kontakt;
    }

    /**
     * Proverava da li trener poseduje sertifikat.
     *
     * @return true ako trener poseduje sertifikat, false inače.
     */
    public Boolean isSertifikat() {
        return sertifikat;
    }

    /**
     * Postavlja da li trener poseduje sertifikat.
     *
     * @param sertifikat true ako trener poseduje sertifikat, false inače.
     */
    public void setSertifikat(Boolean sertifikat) {
        this.sertifikat = sertifikat;
    }

    /**
     * Vraća broj godina iskustva trenera.
     *
     * @return Godine iskustva trenera.
     */
    public int getGodineIskustva() {
        return godinaIskustva;
    }

    /**
     * Postavlja broj godina iskustva trenera.
     *
     * @param godineIskustva Godine iskustva trenera koje se postavljaju.
     * @throws IllegalArgumentException ako su godine iskustva manje od nule.
     */
    public void setGodineIskustva(int godineIskustva) {
        if (godineIskustva < 0) {
            throw new IllegalArgumentException("Godine ne smeju biti manje od nule");
        }
        this.godinaIskustva = godineIskustva;
    }

    /**
     * Vraća string reprezentaciju trenera u obliku "ime prezime".
     *
     * @return String koji predstavlja ime i prezime trenera.
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Vraća heš kod za objekat.
     *
     * @return heš kod.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * Proverava da li su dva objekta jednaka.
     *
     * @param obj objekat sa kojim se poredi Trener.
     * @return true - ako oba objekta imaju istu memorijsku lokaciju, ili ako
     * imaju isto ime i prezime trenera. false - u ostalim slučajevima
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
        final Trener other = (Trener) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        return Objects.equals(this.prezime, other.prezime);
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovom entitetu.
     *
     * @return naziv tabele kao String
     */
    @Override
    public String tableName() {
        return "trener";
    }

    /**
     * Vraća alijas koji se koristi za ovu tabelu u SQL upitima.
     *
     * @return alijas kao String
     */
    @Override
    public String alies() {
        return "t";
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
        return " (ime, prezime, kontakt, sertifikat, godinaIskustva) ";
    }

    /**
     * Vraća vrednosti za umetanje u bazu podataka.
     *
     * @return String sa vrednostima.
     */
    @Override
    public String insertValues() {
        System.out.println(kontakt);
        return "'" + ime + "', '" + prezime + "', '"
                + kontakt + "', " + sertifikat + ", "
                + godinaIskustva;

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

        if (ime == null && prezime == null && kontakt == null && sertifikat == null && godinaIskustva == 0) {
            return "";
        }
        if ((ime == null || ime.isEmpty()) && sertifikat != null) {
            return " WHERE sertifikat=" + sertifikat;
        }
        if (!ime.isEmpty() && sertifikat == null) {
            return " WHERE ime LIKE " + "'" + ime + "%" + "'";
        }

        return " WHERE ime LIKE " + "'" + ime + "%" + "'" + " and " + " sertifikat= " + sertifikat;

    }

    /**
     * Vraća listu objekata Trener na osnovu ResultSet-a.
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

            int id = rs.getInt("idTrenera");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String kontakt = rs.getString("kontakt");
            boolean sertifikat = rs.getBoolean("sertifikat");
            int godinaIsk = rs.getInt("godinaIskustva");

            Trener t = new Trener(id, ime, prezime, kontakt, sertifikat, godinaIsk);

            lista.add(t);

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

        return " WHERE t.idTrenera= " + idTrenera;
    }

    /**
     * Validira ime korisnika.
     *
     * @param ime Ime korisnika koje se validira.
     * @return true ako je ime sastavljeno samo od slova i pocinje velikim
     * pocetnim slovom, false ako nije.
     */
    private boolean validateIme(String ime) {

        if (!ime.matches("[a-zA-Z]+") || !Character.isUpperCase(ime.charAt(0))) {
            return false;
        }
        return true;
    }

    /**
     * Validira prezime korisnika.
     *
     * @param prezime Prezime korisnika koje se validira.
     * @return true ako je prezime sastavljeno samo od slova i pocinje velikim
     * pocetnim slovom, false ako nije.
     */
    private boolean validatePrezime(String prezime) {

        if (!prezime.matches("[a-zA-Z]+") || !Character.isUpperCase(prezime.charAt(0))) {
            return false;
        }
        return true;

    }

    /**
     * Validira kontakt korisnika.
     *
     * @param kontakt Kontakt korisnika koji se validira.
     * @return true ako je kontakt sastavljen samo od brojeva,pocinje sa 06 i
     * duzine je 9 ili 10 cifara, false ako nije.
     */
    private boolean validateKontakt(String kontakt) {

        if (!kontakt.matches("[\\d]+") || !kontakt.startsWith("06")
                || (kontakt.length() != 9 && kontakt.length() != 10)) {
            return false;
        }
        return true;
    }

}

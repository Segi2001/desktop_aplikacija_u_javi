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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Predstavlja klasu Korisnik koja nasledjuje apstraktnu AbstractDomainObject za
 * rad sa bazom podataka. Klasa sadrzi atribute id, ime, prezime, datum
 * rodjenja, adresu, kontakt, grad, i listu tipova treninga.
 *
 * @author Stefan Segrt
 */
public class Korisnik extends AbstractDomainObject {

    /**
     * id odredjenog korisnika
     */
    @Expose
    private int idKorisnika;

    /**
     * Ime odredjenog korisnika
     */
    @Expose
    private String ime;

    /**
     * Prezime odredjenog korisnika
     */
    @Expose
    private String prezime;

    /**
     * Datum rodjenja odredjenog korisnika
     *
     */
    @Expose
    private Date datumRodjenja;
     /**
     *
     *
     *
     * Adresa odredjenog korisnika
     */
    @Expose
    private String adresa;

    

    /**
     * Kontakt odredjenog korisnika
     */
    @Expose
    private String kontakt;


    /**
     * Grad u kojem korisnik zivi
     */
    @Expose
    private Grad grad;


    /**
     * Lista tipova treninga koje korisnik pohađa
     */
    @Expose
    private List<TipTreninga> tipovi = new ArrayList<>();

    private static final long serialVersionUID = 123456789;

    /**
     * Podrazumevani konstruktor.
     */
    public Korisnik() {
    }

    /**
     * Konstruktor sa parametrima.
     *
     * @param idKorisnika ID korisnika.
     * @param ime Ime korisnika.
     * @param prezime Prezime korisnika.
     * @param datumRodjenja Datum rodjenja korisnika.
     * @param adresa Adresa korisnika.
     * @param kontakt Kontakt korisnika.
     * @param grad Grad u kojem korisnik zivi.
     * @param tipovi Lista tipova treninga koje korisnik pohađa.
     */
    public Korisnik(int idKorisnika, String ime, String prezime, Date datumRodjenja, String adresa, String kontakt, Grad grad, List<TipTreninga> tipovi) {
        this.idKorisnika = idKorisnika;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.adresa = adresa;
        this.kontakt = kontakt;
        this.grad = grad;
        this.tipovi = tipovi;
    }

    /**
     * Vraća ID korisnika.
     *
     * @return ID korisnika.
     */
    public int getIdKorisnika() {
        return idKorisnika;
    }

    /**
     * Postavlja ID korisnika.
     *
     * @param idKorisnika ID korisnika koji se postavlja.
     */
    public void setIdKorisnika(int idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    /**
     * Vraća ime korisnika.
     *
     * @return Ime korisnika.
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime korisnika.
     *
     * @param ime Ime korisnika koje se postavlja.
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
     * Vraća prezime korisnika.
     *
     * @return Prezime korisnika.
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime korisnika.
     *
     * @param prezime Prezime korisnika koje se postavlja.
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
     * Vraća datum rodjenja korisnika.
     *
     * @return Datum rodjenja korisnika.
     */
    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    /**
     * Postavlja datum rodjenja korisnika.
     *
     * @param datumRodjenja Datum rodjenja korisnika koji se postavlja.
     * @throws NullPointerException ako je datum rodjenja null.
     * @throws IllegalArgumentException ako je datum rodjenja u budućnosti.
     */
    public void setDatumRodjenja(Date datumRodjenja) {
        if (datumRodjenja == null) {
            throw new NullPointerException("Datum ne sme biti null");
        }
        if (datumRodjenja.after(new Date())) {
            throw new IllegalArgumentException("Datum rodjenja se ne sme odnositi na buducnost");
        }
        this.datumRodjenja = datumRodjenja;
    }

    /**
     * Vraća adresu korisnika.
     *
     * @return Adresa korisnika.
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * Postavlja adresu korisnika.
     *
     * @param adresa Adresa korisnika koja se postavlja.
     * @throws NullPointerException ako je adresa null.
     * @throws IllegalArgumentException ako je adresa prazan string.
     */
    public void setAdresa(String adresa) {
        if (adresa == null) {
            throw new NullPointerException("Adresa ne sme biti null");
        }
        if (adresa.isEmpty()) {
            throw new IllegalArgumentException("Adresa ne sme biti prazan string");
        }
        this.adresa = adresa;
    }

    /**
     * Vraća kontakt korisnika.
     *
     * @return Kontakt korisnika.
     */
    public String getKontakt() {
        return kontakt;
    }

    /**
     * Postavlja kontakt korisnika.
     *
     * @param kontakt Kontakt korisnika koji se postavlja.
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
     * Vraća grad u kojem korisnik zivi.
     *
     * @return Grad korisnika.
     */
    public Grad getGrad() {
        return grad;
    }

    /**
     * Postavlja grad korisnika.
     *
     * @param grad Grad koji se postavlja.
     * @throws NullPointerException ako je grad null.
     */
    public void setGrad(Grad grad) {
        if (grad == null) {
            throw new NullPointerException("Ne sme grad biti null");
        }
        this.grad = grad;
    }

    /**
     * Vraća listu tipova treninga koje korisnik pohađa.
     *
     * @return Lista tipova treninga.
     */
    public List<TipTreninga> getTipovi() {
        return tipovi;
    }

    /**
     * Postavlja listu tipova treninga koje korisnik pohađa.
     *
     * @param tipovi Lista tipova treninga.
     * @throws NullPointerException ako je lista tipova treninga null.
     * @throws IllegalArgumentException ako korisnik nema barem jedan tip
     * treninga.
     */
    public void setTipovi(List<TipTreninga> tipovi) {
        if (tipovi == null) {
            throw new NullPointerException("Tipovi ne smeju biti null");
        }
        if (tipovi.isEmpty()) {
            throw new IllegalArgumentException("Korisnik mora imati barem jedan tip treninga");
        }
        this.tipovi = tipovi;
    }

    /**
     * Vraća string reprezentaciju objekta.
     *
     * @return String koji predstavlja ime i prezime korisnika.
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
     * Vraća heš kod za objekat.
     *
     * @return int koji predstavlja heš kod.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    /**
     * Proverava da li su dva objekta jednaka
     *
     * @param obj objekat sa kojim se poredi Korisnik
     * @return true - ako oba objekta imaju istu memorijsku lokaciju, ili ako su
     * iste klase i imaju isto ime, prezime, i kontakt false - u ostalim
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
        final Korisnik other = (Korisnik) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.kontakt, other.kontakt);
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovom entitetu.
     *
     * @return naziv tabele kao String
     */
    @Override
    public String tableName() {
        return " korisnik ";
    }

    /**
     * Vraća alijas koji se koristi za ovu tabelu u SQL upitima.
     *
     * @return alijas kao String
     */
    @Override
    public String alies() {
        return " k ";
    }

    /**
     * Vraća SQL kod za JOIN sa drugim tabelama u SQL upitu.
     *
     * @return SQL kod za JOIN kao String
     */
    @Override
    public String textJoin() {
        return " JOIN kt ON k.idKorisnika=kt.idKorisnika JOIN tip_treninga tt ON kt.idTipa=tt.idTipa "
                + "JOIN grad g ON k.idGrada=g.idGrada";
    }

    /**
     * Vraća nazive kolona za umetanje u bazu podataka.
     *
     * @return String sa nazivima kolona.
     */
    @Override
    public String insertColumns() {
        return " (ime,prezime,datumRodjenja,adresa,kontakt,idGrada) ";
    }

    /**
     * Vraća vrednosti za umetanje u bazu podataka.
     *
     * @return String sa vrednostima.
     */
    @Override
    public String insertValues() {

        return "'" + ime + "', '" + prezime + "', '"
                + datumRodjenja + "', '" + adresa + "', '" + kontakt + "', "
                + grad.getIdGrada();
        //('stefan', 'segrt', '05.02.2001', 'hercegovacka',
    }

    /**
     * Metoda za ažuriranje vrednosti koje se umecu u bazu podataka.
     *
     * @return String sa vrednostima ukoliko ih ima.
     */
    @Override
    public String updateValues() {

        return " adresa= '" + adresa + "', kontakt = '" + kontakt + "'";
    }

    /**
     * Vraća uslov potreban za delete upit nad bazom podataka.
     *
     * @return String sa uslovom.
     */
    @Override
    public String requiredCondition() {

        return " idKorisnika=" + idKorisnika;
    }

    /**
     * Vraća uslov za selektovanje podataka iz baze podataka.
     *
     * @return String sa uslovom za selekciju.
     */
    @Override
    public String conditionForSelect() {

        if (ime == null && prezime == null && kontakt == null && adresa == null && tipovi.isEmpty() && datumRodjenja == null) {
            return "";
        }

        if (!tipovi.isEmpty()) {
            return "WHERE EXISTS (SELECT 1 FROM kt inner_kt JOIN tip_treninga inner_tt ON inner_kt.idTipa = inner_tt.idTipa WHERE inner_kt.idKorisnika = k.idKorisnika AND inner_tt.nazivTipa='" + tipovi.get(0).getNazivTipa() + "')";
        }
        if ((ime == null || ime.isEmpty()) && prezime != null) {
            return " WHERE k.prezime LIKE '" + prezime + "%'";
        }
        if ((prezime == null || prezime.isEmpty()) && ime != null) {
            return " WHERE k.ime LIKE '" + ime + "%'";
        }
        return " WHERE k.ime LIKE '" + ime + "%' and k.prezime LIKE '" + prezime + "%'";
    }

    /**
     * Pravi listu objekata Korisnik na osnovu rezultata ResultSet-a.
     *
     * @param rs ResultSet iz baze podataka.
     * @return Lista objekata.
     * @throws SQLException ako dođe do greške prilikom pristupa ResultSet-u.
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {

        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        Map<Integer, Korisnik> korisnici = new HashMap<>();

        while (rs.next()) {

            int idK = rs.getInt("k.idKorisnika");
            String imeK = rs.getString("k.ime");
            String prezimeK = rs.getString("k.prezime");
            String adresaK = rs.getString("k.adresa");
            java.sql.Date datumSql = rs.getDate("k.datumRodjenja");
            java.util.Date datumRodjenjaK = new java.util.Date(datumSql.getTime());
            String kontaktK = rs.getString("k.kontakt");
            int idGrada = rs.getInt("g.idGrada");
            String nazivGrada = rs.getString("g.nazivGrada");
            Grad g = new Grad(idGrada, nazivGrada);

            Korisnik korisnik = korisnici.get(idK);
            if (korisnik == null) {
                korisnik = new Korisnik();
                korisnik.setIme(imeK);
                korisnik.setPrezime(prezimeK);
                korisnik.setAdresa(adresaK);
                korisnik.setKontakt(kontaktK);
                korisnik.setIdKorisnika(idK);
                korisnik.setDatumRodjenja(datumRodjenjaK);
                korisnik.setGrad(g);
                korisnici.put(idK, korisnik);
            }

            int idTipa = rs.getInt("tt.idTipa");
            String nazivTipa = rs.getString("tt.nazivTipa");
            String opis = rs.getString("tt.opis");
            TipTreninga tip = new TipTreninga(idTipa, nazivTipa, opis);

            korisnik.getTipovi().add(tip);

        }
        lista.addAll(korisnici.values());
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

        return " WHERE kt.idKorisnika= " + idKorisnika;
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

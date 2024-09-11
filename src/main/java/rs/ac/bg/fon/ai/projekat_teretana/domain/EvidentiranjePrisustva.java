/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.domain;

import com.google.gson.annotations.Expose;
import static com.google.protobuf.JavaFeaturesProto.java;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Predstavlja evidenciju prisustva korisnika na treningu.
 *
 * Evidentiranje prisustva uključuje trening, korisnika, potrošene kalorije i
 * otkucaje srca. Nasledjuje apstraktnu klasu AbstractDomainObject za rad sa
 * bazom podataka.
 *
 * @author Stefan Segrt
 */
public class EvidentiranjePrisustva extends AbstractDomainObject {

    /**
     * Trening na kojem je prisustvo evidentirano.
     */
    private Trening trening;

    /**
     * Korisnik čije je prisustvo evidentirano.
     */
    @Expose
    private Korisnik korisnik;

    /**
     * Potrošene kalorije tokom treninga.
     */
    @Expose
    private int potroseneKal;

    /**
     * Otkucaji srca tokom treninga.
     */
    @Expose
    private int otkucajiSrca;

    /**
     * Pomoćna promenljiva za tip treninga.
     */
    @Expose
    private TipTreninga pomocna;

    /**
     * Pravi nov objekat klase EvidentiranjePrisustva. Svi atributi ostaju
     * neinicijalizovani.
     */
    public EvidentiranjePrisustva() {
    }

    /**
     * Pravi nov objekat klase EvidentiranjePrisustva i postavlja sve atribute
     * na unete vrednosti.
     *
     * @param trening trening na kojem je prisustvo evidentirano kao Trening
     * @param korisnik korisnik čije je prisustvo evidentirano kao Korisnik
     * @param potroseneKal potrošene kalorije tokom treninga kao int
     * @param otkucajiSrca otkucaji srca tokom treninga kao int
     */
    public EvidentiranjePrisustva(Trening trening, Korisnik korisnik, int potroseneKal, int otkucajiSrca) {
        this.trening = trening;
        this.korisnik = korisnik;
        this.potroseneKal = potroseneKal;
        this.otkucajiSrca = otkucajiSrca;
    }

    /**
     * Vraća trening na kojem je prisustvo evidentirano.
     *
     * @return trening kao Trening
     */
    public Trening getTrening() {
        return trening;
    }

    /**
     * Postavlja trening na kojem je prisustvo evidentirano.
     *
     * @param trening trening kao Trening
     * @throws NullPointerException ako je uneti trening null
     */
    public void setTrening(Trening trening) {
        if (trening == null) {
            throw new NullPointerException("Trening ne sme biti null");
        }
        this.trening = trening;
    }

    /**
     * Vraća korisnika čije je prisustvo evidentirano.
     *
     * @return korisnik kao Korisnik
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }

    /**
     * Postavlja korisnika čije je prisustvo evidentirano.
     *
     * @param korisnik korisnik kao Korisnik
     * @throws NullPointerException ako je uneti korisnik null
     */
    public void setKorisnik(Korisnik korisnik) {
        if (korisnik == null) {
            throw new NullPointerException("Korisnik ne sme biti null");
        }
        this.korisnik = korisnik;
    }

    /**
     * Vraća potrošene kalorije tokom treninga.
     *
     * @return potrošene kalorije kao int
     */
    public int getPotroseneKal() {
        return potroseneKal;
    }

    /**
     * Postavlja potrošene kalorije tokom treninga.
     *
     * @param potroseneKal potrošene kalorije kao int
     * @throws IllegalArgumentException ako su potrošene kalorije negativan broj
     */
    public void setPotroseneKal(int potroseneKal) {
        if (potroseneKal < 0) {
            throw new IllegalArgumentException("Potrosene kalorije ne mogu biti negativan broj");
        }
        this.potroseneKal = potroseneKal;
    }

    /**
     * Vraća otkucaje srca tokom treninga.
     *
     * @return otkucaji srca kao int
     */
    public int getOtkucajiSrca() {
        return otkucajiSrca;
    }

    /**
     * Postavlja otkucaje srca tokom treninga.
     *
     * @param otkucajiSrca otkucaji srca kao int
     * @throws IllegalArgumentException ako su otkucaji srca manji od nule
     */
    public void setOtkucajiSrca(int otkucajiSrca) {
        if (otkucajiSrca < 0) {
            throw new IllegalArgumentException("Otkucaji srca moraju biti veci od nule");
        }
        this.otkucajiSrca = otkucajiSrca;
    }

    /**
     * Vraca String reprezentaciju objekta EvidentiranjePrisustva
     *
     * @return String sa svim atributima objekta
     */
    @Override
    public String toString() {
        return "EvidentiranjePrisustva{" + "trening=" + trening + ", korisnik=" + korisnik + ", potroseneKal=" + potroseneKal + ", otkucajiSrca=" + otkucajiSrca + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * Proverava da li su dva objekta jednaka
     *
     * @param obj objekat sa kojim se poredi EvidentiranjePrisustva
     * @return true - ako oba objekta imaju istu memorijsku lokaciju, ili ako su
     * iste klase i imaju istu vrednost objekata trening i korisnik false - u
     * ostalim slucajevima
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
        final EvidentiranjePrisustva other = (EvidentiranjePrisustva) obj;
        if (!Objects.equals(this.trening, other.trening)) {
            return false;
        }
        return Objects.equals(this.korisnik, other.korisnik);
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovom entitetu.
     *
     * @return naziv tabele kao String
     */
    @Override
    public String tableName() {
        return "evidentiranje_prisustva";
    }

    /**
     * Vraća alijas koji se koristi za ovu tabelu u SQL upitima.
     *
     * @return alijas kao String
     */
    @Override
    public String alies() {
        return "ep";
    }

    /**
     * Vraća SQL kod za JOIN sa drugim tabelama u SQL upitu.
     *
     * @return SQL kod za JOIN kao String
     */
    @Override
    public String textJoin() {
        return " JOIN korisnik k ON ep.idKorisnika = k.idKorisnika "
                + "JOIN kt ON ep.idKorisnika = kt.idKorisnika "
                + "JOIN tip_treninga t ON kt.idTipa = t.idTipa "
                + "JOIN trening tr ON ep.idTreninga=tr.idTreninga "
                + "JOIN grad g ON k.idGrada=g.idGrada "
                + "JOIN trener trn ON tr.idTrenera=trn.idTrenera";
    }

    /**
     * Vraća nazive kolona za umetanje u bazu podataka.
     *
     * @return String sa nazivima kolona.
     */
    @Override
    public String insertColumns() {
        return " (idTreninga,idKorisnika,potroseneKalorije,otkucajiSrca)";
    }

    /**
     * Vraća vrednosti za umetanje u bazu podataka.
     *
     * @return String sa vrednostima.
     */
    @Override
    public String insertValues() {
        return trening.getIdTreninga() + "," + korisnik.getIdKorisnika()
                + "," + potroseneKal + "," + otkucajiSrca;
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
        return " idTreninga=" + trening.getIdTreninga();
    }

    /**
     * Vraća uslov za selektovanje podataka iz baze podataka.
     *
     * @return String sa uslovom za selekciju.
     */
    @Override
    public String conditionForSelect() {
        pomocna = trening.getTip();
        return " WHERE ep.idTreninga= " + trening.getIdTreninga();
    }

    /**
     * Pravi listu objekata EvidentiranjePrisustva na osnovu rezultata
     * ResultSet-a.
     *
     * @param rs ResultSet iz baze podataka.
     * @return Lista objekata.
     * @throws SQLException ako dođe do greške prilikom pristupa ResultSet-u.
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {

        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        Map<Integer, Korisnik> korisnici = new HashMap<>();
        Map<Integer, Trening> treninzi = new HashMap<>();
        Map<Integer, EvidentiranjePrisustva> prisustva = new HashMap<>();

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

            int idTipa = rs.getInt("t.idTipa");
            String nazivTipa = rs.getString("t.nazivTipa");
            String opis = rs.getString("t.opis");
            TipTreninga tip = new TipTreninga(idTipa, nazivTipa, opis);

            int idTrn = rs.getInt("trn.idTrenera");
            String imeTrenera = rs.getString("trn.ime");
            String prezimeTrenera = rs.getString("trn.prezime");
            String kontaktTrenera = rs.getString("trn.kontakt");
            boolean sertifikat = rs.getBoolean("trn.sertifikat");
            int godinaIskustva = rs.getInt("trn.godinaIskustva");

            Trener t = new Trener(idTrn, imeTrenera, prezimeTrenera, kontaktTrenera,
                    sertifikat, godinaIskustva);

            int idTreninga = rs.getInt("tr.idTreninga");
            int cena = rs.getInt("tr.cena");
            java.sql.Date datumTrnSQL = rs.getDate("tr.datumTreninga");
            java.util.Date datumTreninga = new java.util.Date(datumTrnSQL.getTime());
            int trajanjeTr = rs.getInt("tr.trajanjeUMin");

            Trening tren = treninzi.get(idTreninga);
            if (tren == null) {
                tren = new Trening();
                tren.setCena(cena);
                tren.setDatumTreninga(datumTreninga);
                tren.setIdTreninga(idTreninga);
                tren.setTrener(t);
                tren.setTrajanjeUMin(trajanjeTr);
                tren.setTip(pomocna);
                treninzi.put(idTreninga, tren);
            }

            Korisnik k = korisnici.get(idK);
            if (k == null) {
                k = new Korisnik();
                k.setIme(imeK);
                k.setPrezime(prezimeK);
                k.setAdresa(adresaK);
                k.setKontakt(kontaktK);
                k.setIdKorisnika(idK);
                k.setDatumRodjenja(datumRodjenjaK);
                k.setGrad(g);
                korisnici.put(idK, k);
            }

            int potroseneKalorije = rs.getInt("ep.potroseneKalorije");
            int otkucajiS = rs.getInt("ep.otkucajiSrca");

            EvidentiranjePrisustva ep = prisustva.get(idK);
            if (ep == null) {
                ep = new EvidentiranjePrisustva();
                ep.setKorisnik(k);
                ep.setTrening(tren);
                ep.setOtkucajiSrca(otkucajiS);
                ep.setPotroseneKal(potroseneKalorije);
                prisustva.put(idK, ep);
            }
            ep.getKorisnik().getTipovi().add(tip);
        }
        lista.addAll(prisustva.values());
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

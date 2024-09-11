/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.domain;

import com.google.gson.annotations.Expose;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Predstavlja klasu Trening koja nasleđuje apstraktnu klasu
 * AbstractDomainObject za rad sa bazom podataka. Klasa sadrži atribute kao što
 * su id treninga, cena, datum treninga, trajanje u minutima, tip treninga i
 * trener.
 *
 * Takođe, klasa ima i dodatni atribut konstanta, koji nije deo osnovnog modela
 * treninga.
 *
 * @author Stefan Segrt
 */
public class Trening extends AbstractDomainObject {

    /**
     * id treninga
     */
    @Expose
    private int idTreninga; 
    /**
     * Cena jednog odrzanog treninga
     */
    @Expose
    private int cena; 
    /**
     * Datum kada je neki trening odrzan
     */
    @Expose
    private Date datumTreninga; 
    /**
     * Trajanje treninga u minutima
     */
    @Expose
    private int trajanjeUMin; 
    /**
     * Tip samog treninga ,odnosno naziv tipa i opis tog tipa
     */
    @Expose
    private TipTreninga tip; 
    /**
     * Trener koji je vodio taj trening
     */
    @Expose
    private Trener trener; 

    private int konstanta = 0; 

    /**
     * Podrazumevani konstruktor.
     */
    public Trening() {
    }

    /**
     * Konstruktor sa parametrima.
     *
     * @param idTreninga ID treninga.
     * @param cena Cena treninga.
     * @param datumTreninga Datum treninga.
     * @param trajanjeUMin Trajanje treninga u minutima.
     * @param tip Tip treninga.
     * @param trener Trener koji vodi trening.
     */
    public Trening(int idTreninga, int cena, Date datumTreninga, int trajanjeUMin, TipTreninga tip, Trener trener) {
        this.idTreninga = idTreninga;
        this.cena = cena;
        this.datumTreninga = datumTreninga;
        this.trajanjeUMin = trajanjeUMin;
        this.tip = tip;
        this.trener = trener;
    }

    /**
     * Vraća ID treninga.
     *
     * @return ID treninga.
     */
    public int getIdTreninga() {
        return idTreninga;
    }

    /**
     * Postavlja ID treninga.
     *
     * @param idTreninga ID treninga koji se postavlja.
     */
    public void setIdTreninga(int idTreninga) {
        this.idTreninga = idTreninga;
    }

    /**
     * Vraća cenu treninga.
     *
     * @return Cena treninga.
     */
    public int getCena() {
        return cena;
    }

    /**
     * Postavlja cenu treninga.
     *
     * @param cena Cena treninga koja se postavlja.
     * @throws IllegalArgumentException ako je cena manja ili jednaka nuli.
     */
    public void setCena(int cena) {
        if (cena <= 0) {
            throw new IllegalArgumentException("Cena ne sme biti manja od nule");
        }
        this.cena = cena;
    }

    /**
     * Vraća datum treninga.
     *
     * @return Datum treninga.
     */
    public Date getDatumTreninga() {
        return datumTreninga;
    }

    /**
     * Postavlja datum treninga.
     *
     * @param datumTreninga Datum treninga koji se postavlja.
     * @throws NullPointerException ako je datum treninga null.
     * @throws IllegalArgumentException ako je datum treninga u budućnosti.
     */
    public void setDatumTreninga(Date datumTreninga) {
        if (datumTreninga == null) {
            throw new NullPointerException("Datum ne sme biti null");
        }
        if (datumTreninga.after(new Date())) {
            throw new IllegalArgumentException("Datum se ne sme odnositi na budućnost");
        }
        this.datumTreninga = datumTreninga;
    }

    /**
     * Vraća trajanje treninga u minutima.
     *
     * @return Trajanje treninga u minutima.
     */
    public int getTrajanjeUMin() {
        return trajanjeUMin;
    }

    /**
     * Postavlja trajanje treninga u minutima.
     *
     * @param trajanjeUMin Trajanje treninga u minutima koje se postavlja.
     * @throws IllegalArgumentException ako je trajanje treninga manje ili
     * jednako nuli.
     */
    public void setTrajanjeUMin(int trajanjeUMin) {
        if (trajanjeUMin <= 0) {
            throw new IllegalArgumentException("Trajanje treninga mora biti pozitivan broj");
        }
        this.trajanjeUMin = trajanjeUMin;
    }

    /**
     * Vraća tip treninga.
     *
     * @return Tip treninga.
     */
    public TipTreninga getTip() {
        return tip;
    }

    /**
     * Postavlja tip treninga.
     *
     * @param tip Tip treninga koji se postavlja.
     * @throws NullPointerException ako je tip treninga null.
     */
    public void setTip(TipTreninga tip) {
        if (tip == null) {
            throw new NullPointerException("Tip ne sme biti null");
        }
        this.tip = tip;
    }

    /**
     * Vraća trenera koji vodi trening.
     *
     * @return Trener koji vodi trening.
     */
    public Trener getTrener() {
        return trener;
    }

    /**
     * Postavlja trenera koji vodi trening.
     *
     * @param trener Trener koji vodi trening koji se postavlja.
     * @throws IllegalArgumentException ako je trener null.
     */
    public void setTrener(Trener trener) {
        if (trener == null) {
            throw new IllegalArgumentException("Trener ne sme biti null");
        }
        this.trener = trener;
    }

    /**
     * Postavlja dodatnu konstantu koja sluzi kao pomocna promenjiva.
     *
     * @param konstanta Vrednost konstante koja se postavlja.
     */
    public void setKonstanta(int konstanta) {
        this.konstanta = konstanta;
    }

    /**
     * Vraća vrednost dodatne konstante koja je pomocna promenljiva.
     *
     * @return Vrednost dodatne konstante.
     */
    public int getKonstanta() {
        return konstanta;
    }

    /**
     * Vraća string reprezentaciju treninga u formatu "tip treninga, datum
     * treninga, trener".
     *
     * @return String koji predstavlja trening.
     */
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return tip + ", " + sdf.format(datumTreninga) + ", " + trener;
    }

    /**
     * Vraća heš kod za objekat.
     *
     * @return Heš kod.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * Proverava da li su dva objekta jednaka.
     *
     * @param obj Objekat sa kojim se poredi Trening.
     * @return true - ako oba objekta imaju isti datum treninga; false - u
     * ostalim slučajevima.
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
        final Trening other = (Trening) obj;
        return Objects.equals(this.datumTreninga, other.datumTreninga);
    }

    /**
     * Vraća naziv tabele u bazi podataka koja odgovara ovom entitetu.
     *
     * @return Naziv tabele kao String.
     */
    @Override
    public String tableName() {
        return "trening";
    }

    /**
     * Vraća alijas koji se koristi za ovu tabelu u SQL upitima.
     *
     * @return Alijas kao String.
     */
    @Override
    public String alies() {
        return " t ";
    }

    /**
     * Vraća SQL kod za JOIN sa drugim tabelama u SQL upitu.
     *
     * @return SQL kod za JOIN kao String.
     */
    @Override
    public String textJoin() {
        if (konstanta == 1) {
            return "LEFT JOIN evidentiranje_prisustva ep ON t.idTreninga=ep.idTreninga JOIN trener tr ON t.idTrenera=tr.idTrenera "
                    + "JOIN tip_treninga tt ON t.idTipa=tt.idTipa";
        }

        return " JOIN trener tr on t.idTrenera=tr.idTrenera "
                + "JOIN tip_treninga tt on t.idTipa=tt.idTipa";
    }

    /**
     * Vraća nazive kolona za umetanje u bazu podataka.
     *
     * @return String sa nazivima kolona.
     */
    @Override
    public String insertColumns() {

        return " (cena,datumTreninga,trajanjeUMin,idTrenera,idTipa) ";
    }

    /**
     * Vraća vrednosti za umetanje u bazu podataka.
     *
     * @return String sa vrednostima.
     */
    @Override
    public String insertValues() {

        return "'" + cena + "', '" + datumTreninga + "', "
                + " " + trajanjeUMin + ", " + trener.getIdTrenera() + ", "
                + tip.getIdTipa();
    }

    /**
     * Metoda za ažuriranje vrednosti koje se umetnu u bazu podataka.
     *
     * @return String sa vrednostima ukoliko ih ima.
     */
    @Override
    public String updateValues() {
        return " trajanjeUMin= " + trajanjeUMin + ", idTrenera = " + trener.getIdTrenera();

    }

    /**
     * Vraća uslov potreban za delete upit nad bazom podataka.
     *
     * @return String sa uslovom.
     */
    @Override
    public String requiredCondition() {
        return " idTreninga=" + idTreninga;
    }

    /**
     * Vraća uslov za selektovanje podataka iz baze podataka.
     *
     * @return String sa uslovom za selekciju.
     */
    @Override
    public String conditionForSelect() {

        if (cena == 0 && datumTreninga == null && trajanjeUMin == 0 && tip == null && trener == null && konstanta == 0) {
            return "";
        }

        if (cena == 0 && datumTreninga == null && trajanjeUMin == 0 && tip == null && trener == null && konstanta == 1) {
            return " WHERE ep.idTreninga IS NULL";
        }
        if (cena == 0 && tip != null) {
            return "WHERE t.idTipa= " + tip.getIdTipa();
        }
        if (cena != 0 && tip == null) {
            return " WHERE cena=" + cena;
        }
        return " WHERE t.idTipa= " + tip.getIdTipa() + " and " + "cena= " + cena;
    }

    /**
     * Vraća listu objekata Trening na osnovu ResultSet-a.
     *
     * @param rs ResultSet iz kojeg se čitaju podaci.
     * @return Lista objekata TipTreninga.
     * @throws SQLException ako dođe do greške prilikom čitanja podataka iz
     * ResultSet-a.
     */
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
//        Map<Integer, Trening> treninzi = new HashMap<>();

        while (rs.next()) {

            int idTrenera = rs.getInt("tr.idTrenera");
            String ime = rs.getString("tr.ime");
            String prezime = rs.getString("tr.prezime");
            String kontakt = rs.getString("tr.kontakt");
            boolean sertifikat = rs.getBoolean("tr.sertifikat");
            int godinaIskustva = rs.getInt("tr.godinaIskustva");

            Trener trener = new Trener(idTrenera, ime, prezime, kontakt, sertifikat, godinaIskustva);

            int idTipa = rs.getInt("tt.idTipa");
            String naziv = rs.getString("tt.nazivTipa");
            String opis = rs.getString("tt.opis");

            TipTreninga tip = new TipTreninga(idTipa, naziv, opis);

//            Trening trening = treninzi.get(idTreninga);
//            if (trening == null) {
            int idTreninga = rs.getInt("t.idTreninga");
            int cena = rs.getInt("t.cena");
            int trajanjeUMin = rs.getInt("t.trajanjeUMin");
            java.sql.Date datumSql = rs.getDate("t.datumTreninga");
            java.util.Date datumTreninga = new java.util.Date(datumSql.getTime());
            Trening trening = new Trening(idTreninga, cena, datumTreninga, trajanjeUMin, tip, trener);
//            treninzi.put(idTreninga, trening);
            lista.add(trening);
            // }

        }
//        lista.addAll(treninzi.values());
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
        return " WHERE t.idTreninga= " + idTreninga;
    }

}

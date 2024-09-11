/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.controller;

import rs.ac.bg.fon.ai.projekat_teretana.domain.Administrator;
import rs.ac.bg.fon.ai.projekat_teretana.domain.EvidentiranjePrisustva;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Grad;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Korisnik;
import rs.ac.bg.fon.ai.projekat_teretana.domain.RezultatiKorisnika;
import rs.ac.bg.fon.ai.projekat_teretana.domain.TipTreninga;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trener;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;
import rs.ac.bg.fon.ai.projekat_teretana.so.evidentiranje_prisustva.SOAddPrisustva;
import rs.ac.bg.fon.ai.projekat_teretana.so.evidentiranje_prisustva.SOSearchEP;
import rs.ac.bg.fon.ai.projekat_teretana.so.evidentiranje_prisustva.SOUpdatePrisustva;
import rs.ac.bg.fon.ai.projekat_teretana.so.grad.SOGetListGrad;
import rs.ac.bg.fon.ai.projekat_teretana.so.korisnik.SOAddKorisnik;
import rs.ac.bg.fon.ai.projekat_teretana.so.korisnik.SOGetListKorisnik;
import rs.ac.bg.fon.ai.projekat_teretana.so.korisnik.SOLoadKorisnik;
import rs.ac.bg.fon.ai.projekat_teretana.so.korisnik.SOSearchKorisnik;
import rs.ac.bg.fon.ai.projekat_teretana.so.korisnik.SOUpdateKorisnik;
import rs.ac.bg.fon.ai.projekat_teretana.so.rezultat_korisnika.SOAddRezultat;
import rs.ac.bg.fon.ai.projekat_teretana.so.trener.SOAddTrener;
import rs.ac.bg.fon.ai.projekat_teretana.so.trener.SOGetListTrener;
import rs.ac.bg.fon.ai.projekat_teretana.so.trener.SOLoadTrener;
import rs.ac.bg.fon.ai.projekat_teretana.so.trener.SOSearchTrener;
import rs.ac.bg.fon.ai.projekat_teretana.so.trening.SOAddTrening;
import rs.ac.bg.fon.ai.projekat_teretana.so.trening.SODeleteTrening;
import rs.ac.bg.fon.ai.projekat_teretana.so.trening.SOGetListTrening;
import rs.ac.bg.fon.ai.projekat_teretana.so.trening.SOLoadTrening;
import rs.ac.bg.fon.ai.projekat_teretana.so.trening.SOSearchTrening;
import rs.ac.bg.fon.ai.projekat_teretana.so.trening.SOUpdateTrening;
import rs.ac.bg.fon.ai.projekat_teretana.tip_treninga.SOGetListTip;
import java.util.ArrayList;
import java.util.List;





/**
 *
 * @author Stefan Segrt
 */
public class ServerController {

    private static ServerController instance;
    private List<Administrator> admini = new ArrayList<>();
    private List<Administrator> trenutnoUlogovani = new ArrayList<>();

    private ServerController() {

        Administrator a1 = new Administrator("Stefan", "Stefan1");
        Administrator a2 = new Administrator("Marko", "Marko1");
        Administrator a3 = new Administrator("Petar", "Petar1");
        Administrator a4 = new Administrator("Milos", "Milos1");

        admini.add(a1);
        admini.add(a2);
        admini.add(a3);
        admini.add(a4);

    }

    public List<Administrator> getTrenutnoUlogovani() {
        return trenutnoUlogovani;
    }

    public void setTrenutnoUlogovani(List<Administrator> trenutnoUlogovani) {
        this.trenutnoUlogovani = trenutnoUlogovani;
    }

    public static ServerController getInstance() {

        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {

        for (Administrator a : admini) {

            if (a.equals(administrator)) {
                if (trenutnoUlogovani.contains(a)) {
                    throw new Exception("Administrator je vec ulogovan!!!");
                } else {
                    trenutnoUlogovani.add(administrator);
                }
                return a;
            }
        }
        throw new Exception("Niste uneli dobro korisnicko ime ili lozinku!!!!");
    }

    public void signOutAdministrator(Administrator administrator) {

        trenutnoUlogovani.remove(administrator);
    }

    public int addTrener(Trener trener) throws Exception {

        try {
            SOAddTrener so = new SOAddTrener();
            so.templateExecute(trener);
            int id = so.getId();
            return id;
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da kreira trenera");
        }
    }

    public List<Trener> searchTrener(Trener trener) throws Exception {

        SOSearchTrener so = new SOSearchTrener();
        so.templateExecute(trener);
        List<Trener> treneri = so.getTreneri();
        if (treneri.isEmpty()) {
            throw new Exception("Sistem ne moze da nadje trenere po zadatoj vrednosti");
        }
        return treneri;

    }

    public Trener loadTrener(Trener trener) throws Exception {

        try {

            SOLoadTrener so = new SOLoadTrener();
            so.templateExecute(trener);
            Trener t = so.getTrener();
            return t;
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da ucita trenera");
        }

    }

    public List<TipTreninga> getListTip() throws Exception {

        SOGetListTip so = new SOGetListTip();
        so.templateExecute(new TipTreninga());
        return so.getTipovi();
    }

    public List<Trener> getListTrener() throws Exception {

        SOGetListTrener so = new SOGetListTrener();
        so.templateExecute(new Trener());
        return so.getTreneri();
    }

    public int addTrening(Trening trening) throws Exception {

        try {
            SOAddTrening so = new SOAddTrening();
            so.templateExecute(trening);
            int id = so.getId();
            return id;
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da kreira trening");
        }
    }

    public List<Trening> searchTrening(Trening trening) throws Exception {

        SOSearchTrening so = new SOSearchTrening();
        so.templateExecute(trening);
        List<Trening> treninzi = so.getTreninzi();
        if (treninzi.isEmpty()) {
            throw new Exception("Sistem ne moze da nadje treninge po zadatoj vrednosti");
        }
        return treninzi;
    }

    public Trening loadTrening(Trening trening) throws Exception {

        try {
            SOLoadTrening so = new SOLoadTrening();
            so.templateExecute(trening);
            Trening t = so.getTrening();
            return t;
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da ucita trening");
        }
    }

    public void updateTrening(Trening trening) throws Exception {

        try {
            SOUpdateTrening so = new SOUpdateTrening();
            so.templateExecute(trening);
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da zapamti trening");
        }
    }

    public void deleteTrening(Trening trening) throws Exception {

        try {
            SODeleteTrening so = new SODeleteTrening();
            so.templateExecute(trening);
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da obrise podatke o treningu");
        }
    }

    public int addKorisnik(Korisnik korisnik) throws Exception {

        try {
            SOAddKorisnik so = new SOAddKorisnik();
            so.templateExecute(korisnik);
            int id = so.getIdKorisnik();
            return id;
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da kreira korisnika");
        }
    }

    public List<Grad> getListGrad() throws Exception {

        SOGetListGrad so = new SOGetListGrad();
        so.templateExecute(new Grad());
        List<Grad> gradovi = so.getGradovi();
        if (gradovi.isEmpty()) {
            throw new Exception("Sistem ne moze da ucita sve gradove");
        }
        return gradovi;

    }

    public List<Korisnik> searchKorisnik(Korisnik korisnik) throws Exception {

        SOSearchKorisnik so = new SOSearchKorisnik();
        so.templateExecute(korisnik);
        List<Korisnik> korisnici = so.getKorisnici();
        if (korisnici.isEmpty()) {
            throw new Exception("Sistem ne moze da nadje korisnike po zadatoj vrednosti");
        }
        return korisnici;
    }

    public Korisnik loadKorisnik(Korisnik korisnik) throws Exception {

        try {

            SOLoadKorisnik so = new SOLoadKorisnik();
            so.templateExecute(korisnik);
            Korisnik k = so.getK();
            return k;
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da ucita korisnika");
        }

    }

    public void updateKorisnik(Korisnik korisnik) throws Exception {

        try {
            SOUpdateKorisnik so = new SOUpdateKorisnik();
            so.templateExecute(korisnik);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Sistem ne moze da zapamti korisnika");
        }

    }

    public List<Trening> getListTrening() throws Exception {

        SOGetListTrening so = new SOGetListTrening();

        so.templateExecute(new Trening());
        return so.getTreninzi();

    }

    public void addPrisustva(List<EvidentiranjePrisustva> list) throws Exception {

        try {

            SOAddPrisustva so = new SOAddPrisustva();
            so.templateExecute(list);
            System.out.println(so.getNizId().size());
            for (Integer integer : so.getNizId()) {
                System.out.println(integer);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Sistem ne moze da evidentira korisnike na treningu");
        }
    }

    public List<EvidentiranjePrisustva> searchPrisustva(EvidentiranjePrisustva evidentiranjePrisustva) throws Exception {

        SOSearchEP so = new SOSearchEP();
        so.templateExecute(evidentiranjePrisustva);
        if (so.getPrisustva().isEmpty()) {
            throw new Exception("Sistem ne moze nadje Evidencije za izabrani trening");
        }
        return so.getPrisustva();

    }

    public void updatePrisustva(List<EvidentiranjePrisustva> list) throws Exception {

        try {
            SOUpdatePrisustva so = new SOUpdatePrisustva();
            so.templateExecute(list);
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da zapamti evidencije");
        }

    }

    public List<Korisnik> getListKorisnik() throws Exception {

        SOGetListKorisnik so = new SOGetListKorisnik();
        so.templateExecute(new Korisnik());
        return so.getKorisnici();
    }

    public int addRezultat(RezultatiKorisnika rezultatiKorisnika) throws Exception {

        try {
            SOAddRezultat so = new SOAddRezultat();
            so.templateExecute(rezultatiKorisnika);
            return so.getIdRez();
        } catch (Exception e) {
            throw new Exception("Sistem ne moze da kreira rezultat korisnika");
        }
    }

}

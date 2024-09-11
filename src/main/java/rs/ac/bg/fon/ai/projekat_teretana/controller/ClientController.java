/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.controller;

import java.io.IOException;
import java.net.Socket;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Administrator;
import rs.ac.bg.fon.ai.projekat_teretana.domain.EvidentiranjePrisustva;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Grad;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Korisnik;
import rs.ac.bg.fon.ai.projekat_teretana.domain.RezultatiKorisnika;
import rs.ac.bg.fon.ai.projekat_teretana.domain.TipTreninga;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trener;
import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;
import rs.ac.bg.fon.ai.projekat_teretana.operation.Operation;
import rs.ac.bg.fon.ai.projekat_teretana.transfer.ClientRequest;
import rs.ac.bg.fon.ai.projekat_teretana.transfer.Receiver;
import rs.ac.bg.fon.ai.projekat_teretana.transfer.Sender;
import rs.ac.bg.fon.ai.projekat_teretana.transfer.ServerResponse;
import java.util.List;



/**
 *
 * @author Stefan Segrt
 */
public class ClientController {

    private static ClientController instance;
    private Socket s;
    private Receiver receiver;
    private Sender sender;
    private static Exception e;

    private ClientController() {

        try {

            s = new Socket("localhost", 9005);

        } catch (IOException ex) {

        }

        receiver = new Receiver(s);
        sender = new Sender(s);
    }

//    public void newConnection() {
//        try {
//
//            s = new Socket("localhost", 9005);
//
//        } catch (IOException ex) {
//
//        }
//
//        receiver = new Receiver(s);
//        sender = new Sender(s);
//    }

    public static ClientController getInstance() {

        if (instance == null) {
            instance = new ClientController();

        }

        return instance;
    }

    public Socket getS() {
        return s;
    }

    public void login(Administrator a) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.LOGIN, a);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() != null) {
            throw sr.getException();

        }
    }

    public int maxMinusCurrentlyLogged() throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.MAX_MINUS_CURRENTY_LOGGED, null);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (int) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

    public void signOut(Administrator a) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.SIGN_OUT, a);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() != null) {
            throw sr.getException();

        }
    }

    public int addTrener(Trener trener) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.ADD_TRENER, trener);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (int) sr.getOdgovor();
        } else {
            throw sr.getException();
        }

    }

    public List<Trener> searchTrener(Trener trener) throws IOException, ClassNotFoundException, Exception {
        ClientRequest cr = new ClientRequest(Operation.SEARCH_TRENER, trener);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (List<Trener>) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

    public Trener loadTrener(Trener t) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.LOAD_TRENER, t);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (Trener) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

    public List<TipTreninga> getListTip() throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.GET_ALL_TIP, null);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (List<TipTreninga>) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

    public List<Trener> getListTrener() throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.GET_ALL_TRENER, null);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (List<Trener>) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

    public int addTrening(Trening trening) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.ADD_TRENING, trening);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (int) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

    public List<Trening> searchTrening(Trening t) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.SEARCH_TRENING, t);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (List<Trening>) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

    public Trening loadTrening(Trening t) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.LOAD_TRENING, t);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (Trening) sr.getOdgovor();
        } else {
            throw sr.getException();
        }

    }

    public void updateTrening(Trening trening) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.UPDATE_TRENING, trening);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() != null) {
            throw sr.getException();
        }
    }

    public void deleteTrening(Trening trening) throws IOException, ClassNotFoundException, Exception {
        ClientRequest cr = new ClientRequest(Operation.DELETE_TRENING, trening);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() != null) {
            throw sr.getException();
        }
    }

    public int addKorisnik(Korisnik korisnik) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.ADD_KORISNIK, korisnik);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (int) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

    public List<Grad> getListGrad() throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.GET_ALL_GRAD, null);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (List<Grad>) sr.getOdgovor();
        } else {
            throw sr.getException();
        }

    }

    public List<Korisnik> searchKorisnik(Korisnik korisnik) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.SEARCH_KORISNIK, korisnik);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (List<Korisnik>) sr.getOdgovor();
        } else {
            throw sr.getException();
        }

    }

    public Korisnik loadKorisnik(Korisnik k) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.LOAD_KORISNIK, k);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (Korisnik) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

    public void updateKorisnik(Korisnik k) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.UPDATE_KORISNIK, k);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() != null) {
            throw sr.getException();
        }

    }

    public List<Trening> getListTrening() throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.GET_ALL_TRENING, null);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (List<Trening>) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

    public void addPrisustva(List<EvidentiranjePrisustva> prisustva) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.ADD_PRISUSTVA, prisustva);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() != null) {
            throw sr.getException();

        }
    }

    public List<EvidentiranjePrisustva> searchPrisustva(EvidentiranjePrisustva ep) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.SEARCH_EP, ep);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (List<EvidentiranjePrisustva>) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

    public void updatePrisustva(List<EvidentiranjePrisustva> prisustva) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.UPDATE_EP, prisustva);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() != null) {
            throw sr.getException();
        }
    }

    public List<Korisnik> getListKorisnik() throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.GET_ALL_KORISNIK, null);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (List<Korisnik>) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

    public int addRezultat(RezultatiKorisnika rk) throws IOException, ClassNotFoundException, Exception {

        ClientRequest cr = new ClientRequest(Operation.ADD_REZULTAT, rk);
        sender.sendMessage(cr);
        ServerResponse sr = (ServerResponse) receiver.receiveMessage();
        if (sr.getException() == null) {
            return (int) sr.getOdgovor();
        } else {
            throw sr.getException();
        }
    }

}

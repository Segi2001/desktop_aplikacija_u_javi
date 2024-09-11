/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.thread;

import java.net.Socket;
import java.net.SocketException;
import rs.ac.bg.fon.ai.projekat_teretana.controller.ServerController;
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
public class ClientThread extends Thread {

    private Socket s;
    private Receiver receiver;
    private Sender sender;
    int maxBrojKlijenata;
    ServerThread serverskiSoket;

    public ClientThread(Socket socket,int maxBrojKlijenata,ServerThread serverskiSoket) {

        s = socket;
        receiver = new Receiver(s);
        sender = new Sender(s);
        this.maxBrojKlijenata=maxBrojKlijenata;
        this.serverskiSoket=serverskiSoket;
    }

    @Override
    public void run() {

        try {

            while (!s.isClosed()) {
                ServerResponse sr = new ServerResponse();
                ClientRequest cr = (ClientRequest) receiver.receiveMessage();
                try {

                    switch (cr.getOperation()) {

                        case Operation.LOGIN:
                            Administrator a=ServerController.getInstance().login((Administrator)cr.getParametar());
                            sr.setOdgovor(a);
                            break;
                        case Operation.MAX_MINUS_CURRENTY_LOGGED:
                            int brojTrenutnoUlog=ServerController.getInstance().getTrenutnoUlogovani().size();
                            sr.setOdgovor(maxBrojKlijenata-brojTrenutnoUlog);
                            break;
                        case Operation.SIGN_OUT:
                            ServerController.getInstance().signOutAdministrator((Administrator)cr.getParametar());
                            break;
                        
                        case Operation.ADD_TRENER:
                            int id = ServerController.getInstance().addTrener((Trener) cr.getParametar());
                            //System.out.println(id);
                            sr.setOdgovor(id);
                            break;
                        case Operation.SEARCH_TRENER:
                            List<Trener> treneri = ServerController.getInstance().searchTrener((Trener) cr.getParametar());
                            sr.setOdgovor(treneri);
                            break;
                        case Operation.LOAD_TRENER:
                            Trener t = ServerController.getInstance().loadTrener((Trener) cr.getParametar());
                            sr.setOdgovor(t);
                            break;
                        case Operation.GET_ALL_TIP:
                            List<TipTreninga> tipovi = ServerController.getInstance().getListTip();
                            sr.setOdgovor(tipovi);
                            break;
                        case Operation.GET_ALL_TRENER:
                            List<Trener> listaTrenera = ServerController.getInstance().getListTrener();
                            sr.setOdgovor(listaTrenera);
                            break;
                        case Operation.ADD_TRENING:
                            int idTrening = ServerController.getInstance().addTrening((Trening) cr.getParametar());
                            sr.setOdgovor(idTrening);
                            break;
                        case Operation.SEARCH_TRENING:
                            List<Trening> treninzi = ServerController.getInstance().searchTrening((Trening) cr.getParametar());
                            sr.setOdgovor(treninzi);
                            break;
                        case Operation.LOAD_TRENING:
                            Trening trening = ServerController.getInstance().loadTrening((Trening) cr.getParametar());
                            sr.setOdgovor(trening);
                            break;
                        case Operation.UPDATE_TRENING:
                            ServerController.getInstance().updateTrening((Trening) cr.getParametar());
                            break;
                        case Operation.DELETE_TRENING:
                            ServerController.getInstance().deleteTrening((Trening) cr.getParametar());
                            break;
                        case Operation.ADD_KORISNIK:
                            int idKorisnik = ServerController.getInstance().addKorisnik((Korisnik) cr.getParametar());
                            sr.setOdgovor(idKorisnik);
                            break;
                        case Operation.GET_ALL_GRAD:
                            List<Grad> gradovi = ServerController.getInstance().getListGrad();
                            sr.setOdgovor(gradovi);
                            break;
                        case Operation.SEARCH_KORISNIK:
                            List<Korisnik> korisnici = ServerController.getInstance().searchKorisnik((Korisnik) cr.getParametar());
                            sr.setOdgovor(korisnici);
                            break;
                        case Operation.LOAD_KORISNIK:
                            Korisnik k = ServerController.getInstance().loadKorisnik((Korisnik) cr.getParametar());
                            sr.setOdgovor(k);
                            break;
                        case Operation.UPDATE_KORISNIK:
                            ServerController.getInstance().updateKorisnik((Korisnik) cr.getParametar());
                            break;
                        case Operation.GET_ALL_TRENING:
                            List<Trening> listaTreninga = ServerController.getInstance().getListTrening();
                            sr.setOdgovor(listaTreninga);
                            break;
                        case Operation.ADD_PRISUSTVA:
                            ServerController.getInstance().addPrisustva((List<EvidentiranjePrisustva>) cr.getParametar());
                            break;
                        case Operation.SEARCH_EP:
                            List<EvidentiranjePrisustva> prisustva
                                    = ServerController.getInstance().searchPrisustva((EvidentiranjePrisustva) cr.getParametar());
                            sr.setOdgovor(prisustva);
                            break;
                        case Operation.UPDATE_EP:
                            ServerController.getInstance().updatePrisustva((List<EvidentiranjePrisustva>) cr.getParametar());
                            break;
                        case Operation.GET_ALL_KORISNIK:
                            List<Korisnik> korisniciList=ServerController.getInstance().getListKorisnik();
                            sr.setOdgovor(korisniciList);
                            break;
                        case Operation.ADD_REZULTAT:
                            int idRez=ServerController.getInstance().addRezultat((RezultatiKorisnika)cr.getParametar());
                            sr.setOdgovor(idRez);
                            break;
                    }

                } catch (Exception ex) {
                    sr.setException(ex);
                }
                sender.sendMessage(sr);
            }

        }catch(SocketException se){
            System.out.println(serverskiSoket.getClientThreads().size());
            serverskiSoket.getClientThreads().remove(this);
            System.out.println(serverskiSoket.getClientThreads().size());
        }
        
        
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Socket getS() {
        return s;
    }
    
    
    

}

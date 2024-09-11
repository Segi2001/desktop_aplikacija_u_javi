/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan Segrt
 */
public class ServerThread extends Thread {

    private ServerSocket serverskiSoket;
    private int maxBrojKlijenata;
    List<ClientThread> clientThreads = new ArrayList<>();

    public ServerThread(int maxBrojKlijenata) {

        try {
            serverskiSoket = new ServerSocket(9005);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.maxBrojKlijenata = maxBrojKlijenata;
    }

    @Override
    public void run() {

        try {

            while (true) {

//                BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
//                String text=bf.readLine();
//                if(text.equals("da"))
//                    break;
                Socket klijentskiSoket = serverskiSoket.accept();
                
                System.out.println("Klijent se povezao");

                ClientThread nit = new ClientThread(klijentskiSoket, maxBrojKlijenata, this);
                nit.start();

                clientThreads.add(nit);
                for (ClientThread clientThread : clientThreads) {
                    System.out.println(clientThread.getS().isConnected());
                }

            }

        } catch (SocketException se) {

        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            for (ClientThread clientThread : clientThreads) {
                try {
                    clientThread.getS().close();
//                    System.out.println("asas");
                } catch (IOException ex) {
                    Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }

    public List<ClientThread> getClientThreads() {
        return clientThreads;
    }

    public ServerSocket getServerskiSoket() {
        return serverskiSoket;
    }

    public void setServerskiSoket(ServerSocket serverskiSoket) {
        this.serverskiSoket = serverskiSoket;
    }

}

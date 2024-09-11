/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.transfer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author Stefan Segrt
 */
public class Sender implements Serializable {
    
    
    private Socket s;

    public Sender(Socket s) {
        this.s = s;
    }
    
    
    public void sendMessage(Object object) throws IOException{
        
        
        ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
        oos.writeObject(object);
        oos.flush();
        
    }
}

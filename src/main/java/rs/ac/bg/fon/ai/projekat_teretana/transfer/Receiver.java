/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.transfer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author Stefan Segrt
 */
public class Receiver implements Serializable {
    
    private Socket s;

    public Receiver(Socket s) {
        this.s = s;
    }
    
    public Object receiveMessage() throws IOException, ClassNotFoundException{
        
        ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
        return ois.readObject();
        
    }
    
    
}

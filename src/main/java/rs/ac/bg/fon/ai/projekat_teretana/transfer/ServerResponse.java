/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.transfer;

import java.io.Serializable;

/**
 *
 * @author Stefan Segrt
 */
public class ServerResponse implements Serializable {
    
    private Object odgovor;
    private Exception exception;

    public ServerResponse() {
    }

    public ServerResponse(Object odgovor, Exception exception) {
        this.odgovor = odgovor;
        this.exception = exception;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
    
    
    
    
    
}

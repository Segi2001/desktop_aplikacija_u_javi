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
public class ClientRequest implements Serializable {
    
    private int operation;
    private Object parametar;

    public ClientRequest() {
    }

    public ClientRequest(int operation, Object parametar) {
        this.operation = operation;
        this.parametar = parametar;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }
    
    
    
    
    
}

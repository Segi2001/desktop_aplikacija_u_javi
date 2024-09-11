/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.models;

import rs.ac.bg.fon.ai.projekat_teretana.domain.Korisnik;
import java.util.List;
import javax.swing.table.AbstractTableModel;



/**
 *
 * @author Stefan Segrt
 */
public class ModelTabeleKorisnik extends AbstractTableModel{
    
    
    private List<Korisnik> korisnici;
    private String[] kolone={"Ime","Prezime","Kontakt"};

    public ModelTabeleKorisnik(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    @Override
    public int getRowCount() {
        return korisnici.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Korisnik k=korisnici.get(rowIndex);
        switch(columnIndex){
            
            case 0:
                return k.getIme();
            case 1:
                return k.getPrezime();
            case 2:
                return k.getKontakt();
            default:
                return "N/A";      
        }
    }

    @Override
    public String getColumnName(int column) {
        
        return kolone[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
        if(columnIndex==3){
            return true;
        }
        return false;
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }
    
    
    
    
}

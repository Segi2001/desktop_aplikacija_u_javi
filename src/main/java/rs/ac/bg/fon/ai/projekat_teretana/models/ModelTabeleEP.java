/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.models;

import rs.ac.bg.fon.ai.projekat_teretana.domain.EvidentiranjePrisustva;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author Stefan Segrt
 */
public class ModelTabeleEP extends AbstractTableModel{
    
    
    List<EvidentiranjePrisustva> prisustva;
    private String[] kolone={"Naziv treninga","Datum","Trener","Korisnik","Potrosene Kalorije","Otkucaji srca"};

    public ModelTabeleEP(List<EvidentiranjePrisustva> prisustva) {
        this.prisustva = prisustva;
    }

    @Override
    public int getRowCount() {
        return prisustva.size();
    }

    @Override
    public int getColumnCount() {
        
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        EvidentiranjePrisustva ep=prisustva.get(rowIndex);
        switch(columnIndex){
            
            case 0:
                return ep.getTrening().getTip();
            case 1:
                return sdf.format(ep.getTrening().getDatumTreninga());
            case 2:
                return ep.getTrening().getTrener().getIme()+" "+ep.getTrening().getTrener().getPrezime();
            case 3:
                return ep.getKorisnik().getIme()+" "+ep.getKorisnik().getPrezime();
            case 4:
                return ep.getPotroseneKal();
            case 5:
                return ep.getOtkucajiSrca();
            default:
                return "N/A";
            
        }
    }

    public List<EvidentiranjePrisustva> getPrisustva() {
        return prisustva;
    }
    
    

    @Override
    public String getColumnName(int column) {
        
        return kolone[column];
    }

    public void setPrisustva(List<EvidentiranjePrisustva> prisustva) {
        this.prisustva = prisustva;
    }
    
    
    

    public void addEP(EvidentiranjePrisustva ep) throws Exception {
        
        for (EvidentiranjePrisustva evidentiranjePrisustva : prisustva) {
            if(evidentiranjePrisustva.getKorisnik().equals(ep.getKorisnik()))
                throw new Exception("Nije moguce evidentirati istog korisnika");
        }
        prisustva.add(ep);
        fireTableDataChanged();
    }

    public void deleteEP(EvidentiranjePrisustva ep) {
        
        prisustva.remove(ep);
        fireTableDataChanged();
    }

    public void refreshTable() {
        
        fireTableDataChanged();
    }
    
    
    
    
    
}

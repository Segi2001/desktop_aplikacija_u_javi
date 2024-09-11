/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.models;

import rs.ac.bg.fon.ai.projekat_teretana.domain.Trening;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;



/**
 *
 * @author Stefan Segrt
 */
public class ModelTabeleTrening extends AbstractTableModel{
    
    
    private List<Trening> treninzi;
    private String[] kolone={"Tip treninga","Opis tipa","Cena","Ime Trenera","Datum"};
    

    public ModelTabeleTrening(List<Trening> treninzi) {
        this.treninzi = treninzi;
    }

    @Override
    public int getRowCount() {
        return treninzi.size();
    }

    @Override
    public int getColumnCount() {
        
        return kolone.length;
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Trening t=treninzi.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch(columnIndex){
            
            case 0:
                return t.getTip().getNazivTipa();
            case 1:
                return t.getTip().getOpis().substring(0, 20)+"...";
            case 2:
                return t.getCena();
            case 3:
                return t.getTrener().getIme();
            case 4:
                return sdf.format(t.getDatumTreninga());
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        
        return kolone[column];
    }

    public List<Trening> getTreninzi() {
        return treninzi;
    }

    public String[] getKolone() {
        return kolone;
    }

    
    
    
    
    
    
}

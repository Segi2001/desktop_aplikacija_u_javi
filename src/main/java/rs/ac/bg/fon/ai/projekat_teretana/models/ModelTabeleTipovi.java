/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.models;

import rs.ac.bg.fon.ai.projekat_teretana.domain.TipTreninga;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;



/**
 *
 * @author Stefan Segrt
 */
public class ModelTabeleTipovi extends AbstractTableModel{
    
    private List<TipTreninga> tipovi;
    private String[] kolone={"Naziv tipa","Opis"};

    public ModelTabeleTipovi(List<TipTreninga> tipovi) {
        this.tipovi = tipovi;
    }

    @Override
    public int getRowCount() {
        return tipovi.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        TipTreninga tip=tipovi.get(rowIndex);
        switch(columnIndex){
            
            case 0:
                return tip.getNazivTipa();
            case 1:
                return tip.getOpis().substring(0, 20);
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<TipTreninga> getTipovi() {
        return tipovi;
    }

    public void addTip(TipTreninga tip) {
        for(int i=0;i<tipovi.size();i++){
            if(tipovi.get(i).getNazivTipa().equals(tip.getNazivTipa())){
               return;
            }
        }
        tipovi.add(tip);
        fireTableDataChanged();
    }

    public String[] getKolone() {
        return kolone;
    }

    public void deleteTip(TipTreninga tip) {
        
        tipovi.remove(tip);
        fireTableDataChanged();
    }

    public List<TipTreninga> isprazniTabelu() {
        List<TipTreninga> noviTipovi=new ArrayList<>();
        return noviTipovi;
    }
    
    
    
    
    
}

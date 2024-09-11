/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.models;

import rs.ac.bg.fon.ai.projekat_teretana.domain.Trener;
import java.util.List;
import javax.swing.table.AbstractTableModel;



/**
 *
 * @author Stefan Segrt
 */
public class ModelTabeleTrener extends AbstractTableModel {

    private List<Trener> treneri;
    private String[] kolone = {"Ime", "Prezime"};

    public ModelTabeleTrener(List<Trener> treneri) {
        this.treneri = treneri;
    }

    @Override
    public int getRowCount() {
        return treneri.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Trener t = treneri.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return t.getIme();
            case 1:
                return t.getPrezime();

            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {

        return kolone[column];
    }

    public List<Trener> getTreneri() {
        return treneri;
    }

    public void setTreneri(List<Trener> treneri) {
        this.treneri = treneri;
    }

}

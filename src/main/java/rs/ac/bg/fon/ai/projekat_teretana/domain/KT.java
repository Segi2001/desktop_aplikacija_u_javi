/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Stefan Segrt
 */
public class KT extends AbstractDomainObject{

    private Korisnik korisnik;
    private TipTreninga tip;

    public KT() {
    }

    public KT(Korisnik korisnik, TipTreninga tip) {
        this.korisnik = korisnik;
        this.tip = tip;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public TipTreninga getTip() {
        return tip;
    }

    public void setTip(TipTreninga tip) {
        this.tip = tip;
    }

    
    
    
    @Override
    public String tableName() {
        return " kt ";
    }

    @Override
    public String alies() {
        
        return " kortip ";
    }

    @Override
    public String textJoin() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String insertColumns() {
        return " (idTipa,idKorisnika) ";
    }

    @Override
    public String insertValues() {
       return tip.getIdTipa()+","+korisnik.getIdKorisnika();
    }

    @Override
    public String updateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String requiredCondition() {
        return "idKorisnika="+korisnik.getIdKorisnika();
    }

    @Override
    public String conditionForSelect() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getIdCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

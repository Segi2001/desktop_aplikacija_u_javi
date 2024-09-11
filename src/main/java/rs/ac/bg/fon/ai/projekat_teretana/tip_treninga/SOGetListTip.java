/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.tip_treninga;

import rs.ac.bg.fon.ai.projekat_teretana.db.DBBroker;
import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import rs.ac.bg.fon.ai.projekat_teretana.domain.TipTreninga;
import rs.ac.bg.fon.ai.projekat_teretana.so.AbstractSO;
import java.util.ArrayList;
import java.util.List;



/**
 * Klasa SOGetListTip je odgovorna za dobijanje liste tipova treninga 
 * iz baze podataka(lista svih tipova). Ova klasa prosiruje klasu AbstractSO i implementira 
 * potrebne metode za validaciju i izvrsavanje.
 * 
 * Ova klasa osigurava da je prosledjeni objekat instanca klase 
 * TipTreninga, a zatim dobija listu tipova treninga iz baze podataka.
 * 
 * @author Stefan Segrt
 */
public class SOGetListTip extends AbstractSO {

    /**
     * Lista svih tipova treninga dobijenih iz baze podataka.
     */
    private List<TipTreninga> tipovi;

    /**
     * Validira prosledjeni objekat.
     * 
     * @param obj Objekat koji treba da se validira.
     * @throws Exception ako prosledjeni objekat nije instanca klase 
     *                   TipTreninga.
     */
    @Override
    protected void validate(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        if (!(ado instanceof TipTreninga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase TipTreninga!");
        }
    }

    /**
     * Izvrsava dobijanje liste tipova treninga iz baze podataka.
     * 
     * @param obj Objekat koji sadzi kriterijume za pretragu tipova treninga.
     * @throws Exception ako dodje do greske prilikom preuzimanja iz baze podataka.
     */
    @Override
    protected void execute(Object obj) throws Exception {
        AbstractDomainObject ado = (AbstractDomainObject) obj;
        List<AbstractDomainObject> lista = DBBroker.getInstance().selectList(ado);
        tipovi = (ArrayList<TipTreninga>) (ArrayList<?>) lista;
    }

    /**
     * Vraca listu tipova treninga.
     * 
     * @return Lista tipova treninga.
     */
    public List<TipTreninga> getTipovi() {
        return tipovi;
    }

    
}

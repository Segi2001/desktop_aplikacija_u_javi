/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 *
 * @author Stefan Segrt
 */ 
public abstract class AbstractDomainObject implements Serializable {
    
    
    public abstract String tableName();
    public abstract String alies();
    public abstract String textJoin();
    public abstract String insertColumns();
    public abstract String insertValues();
    public abstract String updateValues();
    public abstract String requiredCondition();
    public abstract String conditionForSelect();
    public abstract String getIdCondition();
    public abstract ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException;
    
}

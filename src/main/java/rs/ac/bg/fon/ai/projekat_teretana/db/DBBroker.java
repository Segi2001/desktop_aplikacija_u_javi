/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.db;

import rs.ac.bg.fon.ai.projekat_teretana.domain.AbstractDomainObject;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;



/**
 *
 * @author Stefan Segrt
 */
public class DBBroker {

    private static DBBroker instance;

    private DBBroker() {
    }

    public static DBBroker getInstance() {

        if (instance == null) {
            instance = new DBBroker();

        }

        return instance;
    }


    public int insert(AbstractDomainObject ado) throws Exception {

        int id = -1;

        String upit = "INSERT INTO " + ado.tableName() + " "
                + ado.insertColumns() + " VALUES(" + ado.insertValues() + ")";

        System.out.println(upit);
        Statement s = DBConnection.getInstance().getConnection().createStatement();
        s.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = s.getGeneratedKeys();

        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        s.close();

        return id;
    }

    public List<AbstractDomainObject> selectList(AbstractDomainObject ado) throws Exception {

        String upit = "SELECT * FROM " + ado.tableName() + " " + ado.alies()
                + " " + ado.textJoin() + " " + ado.conditionForSelect();
        System.out.println(upit);
        Statement s = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.getList(rs);

    }

    public AbstractDomainObject selectObject(AbstractDomainObject ado) throws Exception {

        String upit = " SELECT * FROM " + ado.tableName() + " " + ado.alies() + " "
                + ado.textJoin() + " " + " " + ado.getIdCondition();
        System.out.println(upit);
        Statement s = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rs = s.executeQuery(upit);
        if (rs.next()) {
            return ado;
        }
        return null;
    }


    public int update(AbstractDomainObject ado) throws Exception {

        String upit = "UPDATE " + ado.tableName() + " SET "
                + ado.updateValues() + " WHERE " + ado.requiredCondition();
        System.out.println(upit);
        Statement s = DBConnection.getInstance().getConnection().createStatement();
        int affectedRows = s.executeUpdate(upit);
        return affectedRows;

    }

    public int delete(AbstractDomainObject ado) throws Exception {

        String upit = "DELETE FROM " + ado.tableName() + " WHERE " + ado.requiredCondition();
        System.out.println(upit);
        Statement s = DBConnection.getInstance().getConnection().createStatement();
        int affectedRows = s.executeUpdate(upit);
        return affectedRows;

    }

   


  

}

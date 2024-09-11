/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.projekat_teretana.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ai.projekat_teretana.forms.BaseConfiguration;

/**
 *
 * @author Stefan Segrt
 */
public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private DBConnection() {
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            Properties properties = new Properties();
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("DBBrokerConfig.properties")) {
                if (input == null) {
                    System.out.println("Sorry, unable to find DBBrokerConfig.properties");

                }
                properties.load(input);

                String url = properties.getProperty("url");
                String username = properties.getProperty("username");
                String password = properties.getProperty("password");
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
            } catch (IOException ex) {
                Logger.getLogger(BaseConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }
        return connection;
        
    }
}

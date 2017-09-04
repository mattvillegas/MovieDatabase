/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputOutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MatthewVillegas
 */
public class PostgreSQLConnect {
    private Connection connect;
    private Logger log = Logger.getLogger(PostgreSQLConnect.class.getName());
    
    /**
     *
     * @param connectionData saves the data for connecting to the database server
     */
    // attempts to connect to server using connection data member variable 
    // info
    public PostgreSQLConnect(ConnectionData connectionData)
    {
         try
        {
        Class.forName(connectionData.getType());
        connect = DriverManager.getConnection(connectionData.toString(), connectionData.getLogin(), connectionData.getPassword());
        }
         catch(Exception e)
         {
             log.log(Level.SEVERE, "Connection to Database failed", e);
             e.printStackTrace();
         }
         
         log.log(Level.INFO, "Connection to Database successful!");
    }
    
    /**
     *
     * @return returns the connect member variable
     */
    public Connection getConnection()
    {
        return connect;
    }
   
}

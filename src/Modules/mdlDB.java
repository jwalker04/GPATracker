/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

/**
 *
 * @author Brad
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mdlDB {
	
    //declare a variable for the database connection
    static Connection aConnection = null;

    /*
     * Method Name:		ConnectToDb
     * Purpose:			To connect to a database
     * Parameter:		None
     * Return:			aConnection
     * Change Log:		Brad Walker 2/29/16
     */
    public static Connection ConnectToDb() {


        //use a relative path for portability
        String dbDirectory = "./";
        //forward slashes within java for some reason
        System.setProperty("derby.system.home", dbDirectory);
        //create and return the connection
        String strURL = "jdbc:derby:GPA";

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            aConnection = DriverManager.getConnection(strURL, "", "");
            return aConnection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(mdlDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}

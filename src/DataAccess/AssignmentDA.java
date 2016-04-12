/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Entities.Assignment;
import Modules.mdlDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author bradj
 */
public class AssignmentDA {
    
    static Connection aConnection = null;
    
    /* Method Name:		getRecords
    * Purpose:			To get all the records from a table in the database
    * Parameters:		None
    * Return:			A vector of all objects made by each record - Vector
    * Change Log:		Brad Walker 12/26/2015
    */
    public static ArrayList getAssignment(int atID)
    {
        // error handling
        try
        {
            //connect to DB
            aConnection = mdlDB.ConnectToDb();
            //check the connection status
            if (aConnection == null)
            {
                System.out.println("Database connection failed!");
                return null;
            }

            //create a statement
            Statement aStatement = aConnection.createStatement();
            //create a SQL string to get data from database
            String strSQL = "SELECT * FROM tblAssignment WHERE atID = " + atID;

            // run the query
            ResultSet rs = aStatement.executeQuery(strSQL);

            // declare a vector to hold return records
            ArrayList<Assignment> aList = new ArrayList<>();
            Assignment assignment = null;

            // need a boolean variable to indicate whether there is more data
            boolean boolGotRecord = rs.next();

            //use a loop to build our vector with objects
            while(boolGotRecord)
            {
                assignment = new Assignment(rs.getInt("aID"), rs.getString("aName"), rs.getDouble("aGrade"), rs.getString("aDate"), rs.getInt("atID"));
                          
                //put onto the vector
                aList.add(assignment);

                //go to the next record if there is one
                boolGotRecord = rs.next();
            }//end of while

            //a vector has been built
            return aList;

        }
        catch (Exception e) {
            System.err.println("Error occured in Class:AssignmentDA:getAssignment()" + e.getMessage());
            return null;
        }


    }
    
    
     
    /* Method Name:		addRecord
    * Purpose:			To add records to a table in the database
    * Parameters:		None
    * Return:			A vector of all objects made by each record - Vector
    * Change Log:		Brad Walker 12/26/2015
    */
    public static int addAssignment(Assignment assignment)
    {
        // error handling
        try
        {
            //connect to DB
            aConnection = mdlDB.ConnectToDb();
            
            //create a statement
            Statement aStatement = aConnection.createStatement();
            
            //check the connection status
            if (aConnection == null)
            {
                System.out.println("Database connection failed!");
                return -9;
            }
            
            //create a SQL string to get data from database
            String strSQL = "INSERT INTO tblAssignmentType (aName, aGrade, aDate, atID ) VALUES ('" 
                    + assignment.getAName() + "',"
                    + assignment.getAGrade() + ",'"
                    + assignment.getADate()
                    + assignment.getATID() + ")";

            // run the query
            return aStatement.executeUpdate(strSQL);

        }
        catch (Exception e) {
            System.err.println("Error occured in Class:AssignmentDA:addAssignment()" + e.getMessage());
            return -9;
        }
    }
    
    /* Method Name:		deleteRecord
    * Purpose:			To add records to a table in the database
    * Parameters:		None
    * Return:			A vector of all objects made by each record - Vector
    * Change Log:		Brad Walker 12/26/2015
    */
    public static int deleteAssignment(int recordID)
    {
        // error handling
        try
        {
            //connect to DB
            aConnection = mdlDB.ConnectToDb();
            
            //create a statement
            Statement aStatement = aConnection.createStatement();
            
            //check the connection status
            if (aConnection == null)
            {
                System.out.println("Database connection failed!");
                return -9;
            }
            
            //create a SQL string to get data from database
            String strSQL = "DELETE FROM tblAssignment WHERE aID = " + recordID;

            // run the query
            return aStatement.executeUpdate(strSQL);

        }
        catch (Exception e) {
            System.err.println("Error occured in Class:AssignmentDA:deleteAssignment()" + e.getMessage());
            return -9;
        }
    }
    
    
    
}

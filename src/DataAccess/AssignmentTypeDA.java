/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Entities.AssignmentType;
import Modules.mdlDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author bradj
 */
public class AssignmentTypeDA {
    
    static Connection aConnection = null;
    
    /* Method Name:		getRecords
    * Purpose:			To get all the records from a table in the database
    * Parameters:		None
    * Return:			A vector of all objects made by each record - Vector
    * Change Log:		Brad Walker 12/26/2015
    */
    public static ArrayList getAssignmentType(int courseID)
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
            String strSQL = "SELECT * FROM tblAssignmentType WHERE cID = " + courseID;

            // run the query
            ResultSet rs = aStatement.executeQuery(strSQL);

            // declare a vector to hold return records
            ArrayList<AssignmentType> aList = new ArrayList<>();
            AssignmentType assignmentType = null;

            // need a boolean variable to indicate whether there is more data
            boolean boolGotRecord = rs.next();

            //use a loop to build our vector with objects
            while(boolGotRecord)
            {
                assignmentType = new AssignmentType(rs.getInt("atID"), rs.getString("atName"), rs.getDouble("atPercentage"), rs.getDouble("atOverall"), rs.getInt("cID"));
                          
                //put onto the vector
                aList.add(assignmentType);

                //go to the next record if there is one
                boolGotRecord = rs.next();
            }//end of while

            //a vector has been built
            return aList;

        }
        catch (Exception e) {
            System.err.println("Error occured in Class:AssignmentTypeDA:getAssignmentType()" + e.getMessage());
            return null;
        }


    }
    
    
     
    /* Method Name:		addRecord
    * Purpose:			To add records to a table in the database
    * Parameters:		None
    * Return:			A vector of all objects made by each record - Vector
    * Change Log:		Brad Walker 12/26/2015
    */
    public static int addAssignmentType(AssignmentType assignmentType)
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
            String strSQL = "INSERT INTO tblAssignmentType (atName, atPercentage, atOverall, cID ) VALUES ('" 
                    + assignmentType.getATName() + "',"
                    + assignmentType.getATPercentage() + ","
                    + assignmentType.getATOverall() + "," 
                    + assignmentType.getcID() + ")";

            // run the query
            return aStatement.executeUpdate(strSQL);

        }
        catch (Exception e) {
            System.err.println("Error occured in Class:AssignmentTypeDA:addAssignmentType()" + e.getMessage());
            return -9;
        }
    }
    
    /* Method Name:		deleteRecord
    * Purpose:			To add records to a table in the database
    * Parameters:		None
    * Return:			A vector of all objects made by each record - Vector
    * Change Log:		Brad Walker 12/26/2015
    */
    public static int deleteAssignmentType(int recordID)
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
            String strSQL = "DELETE FROM tblAssignmentType WHERE atID = " + recordID;

            // run the query
            return aStatement.executeUpdate(strSQL);

        }
        catch (Exception e) {
            System.err.println("Error occured in Class:AssignmentTypeDA:deleteAssignmentType()" + e.getMessage());
            return -9;
        }
    }
    
}

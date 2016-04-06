/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import Modules.mdlDB;
import Entities.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Brad
 */
public class CourseDA {
    
    static Connection aConnection = null;
    
    /* Method Name:		getRecords
    * Purpose:			To get all the records from a table in the database
    * Parameters:		None
    * Return:			A vector of all objects made by each record - Vector
    * Change Log:		Brad Walker 12/26/2015
    */
    public static ArrayList getCourse()
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
            String strSQL = "SELECT * FROM tblCourse";

            // run the query
            ResultSet rs = aStatement.executeQuery(strSQL);

            // declare a vector to hold return records
            ArrayList<Course> aList = new ArrayList<>();
            Course course = null;

            // need a boolean variable to indicate whether there is more data
            boolean boolGotRecord = rs.next();

            //use a loop to build our vector with objects
            while(boolGotRecord)
            {
                course = new Course(rs.getInt("cID"), rs.getString("cName"),
                    rs.getDouble("cOverall"));

                
                
                
                /*
                        Test Module:
                        
                        To Display what is in the database table tblCourse
                */
                System.out.println("Course Name: " + course.getCourseName() + " Course ID: " + course.getCourseID());
                
                
                
                
                
                //put onto the vector
                aList.add(course);

                //go to the next record if there is one
                boolGotRecord = rs.next();
            }//end of while

            //a vector has been built
            return aList;

        }
        catch (Exception e) {
            System.err.println("Error occured in Class:CourseDA:getRecords()" + e.getMessage());
            return null;
        }


    }
    
    
     
    /* Method Name:		addRecord
    * Purpose:			To add records to a table in the database
    * Parameters:		None
    * Return:			A vector of all objects made by each record - Vector
    * Change Log:		Brad Walker 12/26/2015
    */
    public static int addRecord(Course course)
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
            String strSQL = "INSERT INTO tblCourse (cName, cOverall) VALUES ('" 
                    + course.getCourseName() + "',"
                    + course.getCourseOverall() + ")";

            // run the query
            return aStatement.executeUpdate(strSQL);

        }
        catch (Exception e) {
            System.err.println("Error occured in Class:CourseDA:addRecord()" + e.getMessage());
            return -9;
        }
    }
    
    /* Method Name:		deleteRecord
    * Purpose:			To add records to a table in the database
    * Parameters:		None
    * Return:			A vector of all objects made by each record - Vector
    * Change Log:		Brad Walker 12/26/2015
    */
    public static int deleteRecord(int recordID)
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
            String strSQL = "DELETE FROM tblCourse WHERE cID = " + recordID;

            // run the query
            return aStatement.executeUpdate(strSQL);

        }
        catch (Exception e) {
            System.err.println("Error occured in Class:CourseDA:deleteRecords()" + e.getMessage());
            return -9;
        }
    }
    
}

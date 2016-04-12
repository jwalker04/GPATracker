/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import DataAccess.CourseDA;
import java.util.ArrayList;

/**
 *
 * @author Brad
 */
public class Course {
    
    //Attributes
    private int CourseID;
    private String CourseName;
    private double CourseOverall;


    
    //Getters & Setters
    public int getCourseID() {
        return CourseID;
    }
    public void setCourseID(int ID) {
        CourseID = ID;
    }
    public String getCourseName() {
        return CourseName;
    }
    public void setCourseName(String Name) {
        CourseName = Name;
    }
    public double getCourseOverall() {
        return CourseOverall;
    }
    public void setCourseOverall(double Overall) {
        CourseOverall = Overall;
    }


        
    //Constructors 
    public Course(int ID, String Name, double Overall) {
        CourseID = ID;
        CourseName = Name;
        CourseOverall = Overall;
    }
    public Course(String Name, double Overall) {
        CourseName = Name;
        CourseOverall = Overall;
    }
    
    /* Method Name:     getRecords
    * Purpose:          To get all the records from a table in the database
    * Parameters:	None
    * Return:		A vector of all objects made by each record - Vector
    * Change Log:	Brad Walker 12/23/2015
    */
    public static ArrayList getCourse() {
        try {

            return CourseDA.getCourse();

        } catch (Exception ex) {
            System.err.println("Error occured in Class:Course:getRecord();  " + ex.getMessage());
            return null;
        }
    }
    
    
    
    /* Method Name:     getRecords
    * Purpose:          To get all the records from a table in the database
    * Parameters:	None
    * Return:		A vector of all objects made by each record - Vector
    * Change Log:	Brad Walker 12/23/2015
    */
    public static Course getACourse(int courseID) {
        try {

            return CourseDA.getACourse(courseID);

        } catch (Exception ex) {
            System.err.println("Error occured in Class:Course:getRecord();  " + ex.getMessage());
            return null;
        }
    }
    
    
    
    
     /* Method Name:     addRecord
    * Purpose:          To get all the records from a table in the database
    * Parameters:	None
    * Return:		A vector of all objects made by each record - Vector
    * Change Log:       Brad Walker 12/23/2015
    */
    public static int addRecord(Course a) {
        try {

            return CourseDA.addRecord(a);

        } catch (Exception ex) {
            System.err.println("Error occured in Class:Course:deleteRecord();  " + ex.getMessage());
            return -9;
        }
    }
    
    /* Method Name:     deleteRecords
    * Purpose:          To get all the records from a table in the database
    * Parameters:	None
    * Return:		A vector of all objects made by each record - Vector
    * Change Log:       Brad Walker 12/23/2015
    */
    public static int deleteRecord(int recordID) {
        try {

            return CourseDA.deleteRecord(recordID);

        } catch (Exception ex) {
            System.err.println("Error occured in Class:Course:deleteRecord();  " + ex.getMessage());
            return -9;
        }
    }
    
}

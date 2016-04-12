/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import DataAccess.AssignmentDA;
import java.util.ArrayList;

/**
 *
 * @author bradj
 */
public class Assignment {
    
    /*
        Attributes
    */
    private int ID;
    private String Name;
    private double Grade;
    private String Date;

    
    private int tID;

    
    /*
        Getters & Setters
    */
    public int getAID() {
        return ID;
    }
    public String getAName() {
        return Name;
    }
    public double getAGrade() {
        return Grade;
    }
    public String getADate() {
        return Date;
    }
    public int getATID() {
        return tID;
    }

    public void setAID(int ID) {
        this.ID = ID;
    }

    public void setAName(String Name) {
        this.Name = Name;
    }

    public void setAGrade(double grade) {
        this.Grade = grade;
    }

    public void setATID(int tID) {
        this.tID = tID;
    }
    public void setaDate(String Date) {
        this.Date = Date;
    }

    
    
    /*
        Constructor
    */
    public Assignment(int id, String name, double grade, String date, int tID) {
        this.ID = id;
        this.Name = name;
        this.Grade = grade;
        this.Date = date;
        this.tID = tID;
    }
    public Assignment(String name, double grade, String date, int tID) {
        this.Name = name;
        this.Grade = grade;
        this.Date = date;
        this.tID = tID;
    }
    
    
    
    
    
    /* Method Name:     getRecords
    * Purpose:          To get all the records from a table in the database
    * Parameters:	None
    * Return:		A vector of all objects made by each record - Vector
    * Change Log:	Brad Walker 12/23/2015
    */
    public static ArrayList getAssignment(int atID) {
        try {

            return AssignmentDA.getAssignment(atID);

        } catch (Exception ex) {
            System.err.println("Error occured in Class:Course:getAssignment();  " + ex.getMessage());
            return null;
        }
    }
    
     /* Method Name:     addRecord
    * Purpose:          To get all the records from a table in the database
    * Parameters:	None
    * Return:		A vector of all objects made by each record - Vector
    * Change Log:       Brad Walker 12/23/2015
    */
    public static int addAssignment(Assignment assignment) {
        try {

            return AssignmentDA.addAssignment(assignment);

        } catch (Exception ex) {
            System.err.println("Error occured in Class:Course:deleteAssignment();  " + ex.getMessage());
            return -9;
        }
    }
    
    /* Method Name:     deleteRecords
    * Purpose:          To get all the records from a table in the database
    * Parameters:	None
    * Return:		A vector of all objects made by each record - Vector
    * Change Log:       Brad Walker 12/23/2015
    */
    public static int deleteAssignment(int recordID) {
        try {

            return AssignmentDA.deleteAssignment(recordID);
        } catch (Exception ex) {
            System.err.println("Error occured in Class:Course:deleteAssignment();  " + ex.getMessage());
            return -9;
        }
    }
}

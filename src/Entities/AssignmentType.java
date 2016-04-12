/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import DataAccess.AssignmentTypeDA;
import java.util.ArrayList;

/**
 *
 * @author bradj
 */
public class AssignmentType {
    
    /*
        Properties
    */
    private int ID;
    private String Name;
    private double Percentage;
    private double Overall;
    private int cID;

    
    /*
        Getters
    */
    public int getATID() {
        return ID;
    }

    public String getATName() {
        return Name;
    }

    public double getATPercentage() {
        return Percentage;
    }

    public double getATOverall() {
        return Overall;
    }
    public int getcID() {
        return cID;
    }
    

    
    /*
        Setters
    */
    public void setATID(int ID) {
        this.ID = ID;
    }

    public void setATName(String Name) {
        this.Name = Name;
    }

    public void setATPercentage(double Percentage) {
        this.Percentage = Percentage;
    }

    public void setATOverall(double Overall) {
        this.Overall = Overall;
    }

    
    
    /*
        Constructor
    */
    public AssignmentType(int id, String name, double percentage, double overall, int cid) {
        ID = id;
        Name = name;
        Percentage = percentage;
        Overall = overall;
        cID = cid;
    }
    public AssignmentType(String name, double percentage, double overall, int cid) {
        Name = name;
        Percentage = percentage;
        Overall = overall;
        cID = cid;
    }
    
    
    
    /* Method Name:     getRecords
    * Purpose:          To get all the records from a table in the database
    * Parameters:	None
    * Return:		A vector of all objects made by each record - Vector
    * Change Log:	Brad Walker 12/23/2015
    */
    public static ArrayList getAssignmentType(int courseID) {
        try {

            return AssignmentTypeDA.getAssignmentType(courseID);

        } catch (Exception ex) {
            System.err.println("Error occured in Class:Course:getAssignmentType();  " + ex.getMessage());
            return null;
        }
    }
    
     /* Method Name:     addRecord
    * Purpose:          To get all the records from a table in the database
    * Parameters:	None
    * Return:		A vector of all objects made by each record - Vector
    * Change Log:       Brad Walker 12/23/2015
    */
    public static int addAssignmentType(AssignmentType assignmentType) {
        try {

            return AssignmentTypeDA.addAssignmentType(assignmentType);

        } catch (Exception ex) {
            System.err.println("Error occured in Class:Course:deleteAssignmentType();  " + ex.getMessage());
            return -9;
        }
    }
    
    /* Method Name:     deleteRecords
    * Purpose:          To get all the records from a table in the database
    * Parameters:	None
    * Return:		A vector of all objects made by each record - Vector
    * Change Log:       Brad Walker 12/23/2015
    */
    public static int deleteAssignmentType(int recordID) {
        try {

            return AssignmentTypeDA.deleteAssignmentType(recordID);
        } catch (Exception ex) {
            System.err.println("Error occured in Class:Course:deleteAssignmentType();  " + ex.getMessage());
            return -9;
        }
    }
    
    
}

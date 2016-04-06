/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

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
    private double grade;
    private int tID;

    
    /*
        Getters & Setters
    */
    public int getID() {
        return ID;
    }
    public String getName() {
        return Name;
    }
    public double getGrade() {
        return grade;
    }
    public int gettID() {
        return tID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void settID(int tID) {
        this.tID = tID;
    }

    
    
    /*
        Constructor
    */
    public Assignment(int ID, String Name, double grade, int tID) {
        this.ID = ID;
        this.Name = Name;
        this.grade = grade;
        this.tID = tID;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.control.Button;

/**
 *
 * @author Brad
 */
public class CustomButton extends Button {
    
    //Attributes
    private int Id;
    
    //Get Method
    public int getID() {
        return Id;
    }
    
    //New Constructer
    public CustomButton(String text, int id) {
        super(text);
        Id = id;
    }
}

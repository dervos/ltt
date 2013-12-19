/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Component;

/**
 *
 * @author Tomas Slaman
 */
public class CustomException extends Exception {

    private Component cmp = null;

    public CustomException() {
        
    }
    
    public CustomException(String exMessage) {
        super(exMessage);
    }

    public CustomException(String exMessage, Component comp) {
        super(exMessage);
        this.cmp = comp;
    }

    public Component getComponent() {
        return this.cmp;
    }
}

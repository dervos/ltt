/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

/**
 *
 * @author Tomas Slaman
 */
public enum Status {
    Missing,
    Found,
    Returned,
    Destroyed,
    Unknown;
    
    private Status()
    {
    }
}

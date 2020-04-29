/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

/**
 *
 * @author ursul
 */
public class NullNameException extends Exception {
    
    
     public NullNameException(String message){
        super(message);
        
    } 
   
    public NullNameException(String message, Throwable inner) {
        super(message, inner); 
        
    }
    
}

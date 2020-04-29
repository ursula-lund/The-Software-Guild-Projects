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
public class NullMoneyException extends Exception {
    
     public NullMoneyException(String message){
        super(message);
        
    } 
    
    public NullMoneyException(String message, Throwable inner, int noStock) {
        super(message, inner); 
        
    }
    
}

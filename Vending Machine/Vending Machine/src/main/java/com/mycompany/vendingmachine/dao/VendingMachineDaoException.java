/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;


/**
 *
 * @author ursul
 */
public class VendingMachineDaoException extends Exception {
    
    public VendingMachineDaoException(String message) {
        super(message);
    
    }

    VendingMachineDaoException(String message, Throwable inner) {
       super( message, inner );
    }
    
  
}

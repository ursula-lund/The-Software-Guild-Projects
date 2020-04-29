/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.service;

/**
 *
 * @author ursul
 */
public class InvalidNameException extends Exception {
     public InvalidNameException(String message) {
        super(message);
    }

    public InvalidNameException(String message, Throwable inner) {
        super(message, inner);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullscows.dao;

/**
 *
 * @author ursul
 */
public class GameDaoException extends Exception {
    
     public GameDaoException(String message) {
        super(message);
    }

    public GameDaoException(String message, Throwable inner) {
        super(message, inner);
    }
    
}

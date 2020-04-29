/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;

/**
 *
 * @author ursul
 */
public class ProductsDaoException extends Exception {

    public ProductsDaoException(String message) {
        super(message);
    }
    public ProductsDaoException(String message, Throwable inner) {
        super(message, inner);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.ui;

/**
 *
 * @author ursul
 */
public interface UserIO {
    
    void print(String msg);
    
    int readInt(String prompt);
    
    int readInt(String prompt, int min, int max);
    
    String readString(String prompt);
  
    String editString(String prompt, String oldString); 

    int editInt(String prompt, int oldValue);
    
    int editInt(String prompt, int oldValue, int min, int max);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author ursul
 */
public class Change {


    
   private int quarters;
   private int dimes;
   private int nickels;
   private int pennies;

  
   

    /**
     * @return the quarters
     */
    public int getQuarters() {
        return quarters;
    }

    /**
     * @param quarters the quarters to set
     */
    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    /**
     * @return the dimes
     */
    public int getDimes() {
        return dimes;
    }

    /**
     * @param dimes the dimes to set
     */
    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    /**
     * @return the nickels
     */
    public int getNickels() {
        return nickels;
    }

    /**
     * @param nickels the nickels to set
     */
    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    /**
     * @return the pennies
     */
    public int getPennies() {
        return pennies;
    }

    /**
     * @param pennies the pennies to set
     */
    public void setPennies(int pennies) {
        this.pennies = pennies;
    }



   

   

    
}

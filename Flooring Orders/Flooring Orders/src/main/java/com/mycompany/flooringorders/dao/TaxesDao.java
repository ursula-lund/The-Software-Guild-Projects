/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;


import com.mycompany.flooringorders.dto.Tax;
import com.mycompany.flooringorders.service.InvalidStateException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ursul
 */
public interface TaxesDao  {
    
    
     public List<Tax> readTaxesFile() throws TaxesDaoException;

     public Tax getTaxRateByState(String state) throws InvalidStateException, TaxesDaoException; 
   

       

     
     
}

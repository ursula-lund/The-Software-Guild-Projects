/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;

import com.mycompany.flooringorders.dao.TaxesDao;
import com.mycompany.flooringorders.dao.TaxesDaoException;
import com.mycompany.flooringorders.dto.Order;
import com.mycompany.flooringorders.dto.Tax;
import com.mycompany.flooringorders.service.InvalidStateException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ursul
 */
public class TaxesDaoInMem implements TaxesDao {

    
    
    List<Tax> allTaxes = new ArrayList<>();

    public TaxesDaoInMem() {
        
        Tax toAdd = new Tax();
        toAdd.setStateAbbreviation("MN");
        toAdd.setStateName("Minnesota");
        toAdd.setTaxRate(new BigDecimal("4.25"));
        allTaxes.add(toAdd);
        
        Tax addAnother = new Tax();
        addAnother.setStateAbbreviation("WI");
        addAnother.setStateName("Wisconsin");
        addAnother.setTaxRate(new BigDecimal("5.00"));
        allTaxes.add(addAnother);
     
    }


    @Override
    public List<Tax> readTaxesFile() throws TaxesDaoException {
        return allTaxes;
    }

    @Override
    public Tax getTaxRateByState(String state) throws InvalidStateException, TaxesDaoException {
         return readTaxesFile()
                .stream()
                .filter(toCheck -> toCheck.getStateAbbreviation().equalsIgnoreCase(state))
                .findAny()
                .orElse(null);
    }
}

    

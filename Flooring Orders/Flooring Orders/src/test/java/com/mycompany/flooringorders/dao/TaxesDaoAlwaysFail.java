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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ursul
 */
public class TaxesDaoAlwaysFail implements TaxesDao {
    


    @Override
    public List<Tax> readTaxesFile() throws TaxesDaoException {
    throw new TaxesDaoException("Always fail dao.");
    }

    @Override
    public Tax getTaxRateByState(String state) throws InvalidStateException, TaxesDaoException {
    throw new TaxesDaoException("Always fail dao.");
    }
   
    
      
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;

import com.mycompany.flooringorders.dto.Products;
import java.io.FileNotFoundException;
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
public class ProductsDaoAlwaysFail implements ProductsDao {

    @Override
    public List<Products> getProductsAndPrices() throws ProductsDaoException {
         throw new ProductsDaoException("Always fail dao.");
    }

    @Override
    public Products getCostSqFt(String product) throws ProductsDaoException {
    throw new ProductsDaoException("Always fail dao.");
    }

 
   
    
}

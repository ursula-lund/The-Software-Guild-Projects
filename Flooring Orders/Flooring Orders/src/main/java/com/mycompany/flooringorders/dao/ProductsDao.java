/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;

import com.mycompany.flooringorders.dto.Products;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author ursul
 */
public interface ProductsDao {

    public List<Products> getProductsAndPrices() throws ProductsDaoException;

    public Products getCostSqFt(String product) throws ProductsDaoException;


    
    
}

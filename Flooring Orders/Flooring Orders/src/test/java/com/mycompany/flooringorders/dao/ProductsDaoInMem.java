/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;

import com.mycompany.flooringorders.dao.ProductsDao;
import com.mycompany.flooringorders.dao.ProductsDaoException;
import com.mycompany.flooringorders.dto.Order;
import com.mycompany.flooringorders.dto.Products;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ursul
 */
public class ProductsDaoInMem implements ProductsDao {
    
    
    List<Products> allProducts = new ArrayList<>();
    
    public ProductsDaoInMem() {
        Products toAdd = new Products();
        toAdd.setProductType("Tile");
        toAdd.setLaborCostPerSquareFoot(new BigDecimal("4.00"));
        toAdd.setCostPerSqaureFoot(new BigDecimal("3.75"));
        allProducts.add(toAdd);
        
        Products second = new Products();
        second.setProductType("Wood");
        second.setLaborCostPerSquareFoot(new BigDecimal("4.25"));
        second.setCostPerSqaureFoot(new BigDecimal("2.25"));
    }

    @Override
    public List<Products> getProductsAndPrices() throws  ProductsDaoException{
        return allProducts;
    }

    @Override
    public Products getCostSqFt(String product) throws ProductsDaoException {
         return getProductsAndPrices()
                    .stream()
                    .filter(toCheck -> toCheck.getProductType().equalsIgnoreCase(product))
                    .findAny()
                    .orElse(null);  
    }

   

    
    
}

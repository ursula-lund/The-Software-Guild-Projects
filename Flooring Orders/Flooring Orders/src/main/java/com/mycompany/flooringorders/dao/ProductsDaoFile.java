/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;

import com.mycompany.flooringorders.dto.Order;
import com.mycompany.flooringorders.dto.Products;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ursul
 */
public class ProductsDaoFile implements ProductsDao {

    String path = "Data/Products.txt";



    public ProductsDaoFile(String path) {
        this.path = path;
    }

    @Override
    public List<Products> getProductsAndPrices() throws ProductsDaoException { 
 
        List<Products> userOrder = new ArrayList<>();
        
        try {  
            
            Scanner reader = new Scanner(new BufferedReader(new FileReader(path)));
            reader.nextLine();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.isBlank()) {
                    Products toAdd = parseProducts(line);
                    userOrder.add(toAdd);
                }
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            throw new ProductsDaoException("Could not open file: " + path, ex);
        }
        return userOrder;
    }

    private Products parseProducts(String line) {
        Products parsed = new Products();
        String[] cells = line.split(",");
        parsed.setProductType(cells[0]);
        parsed.setCostPerSqaureFoot(new BigDecimal(cells[1]));
        parsed.setLaborCostPerSquareFoot(new BigDecimal(cells[2]));
        return parsed;
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



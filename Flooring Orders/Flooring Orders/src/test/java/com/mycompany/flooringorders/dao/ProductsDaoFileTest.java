/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;

import com.mycompany.flooringorders.dto.Order;
import com.mycompany.flooringorders.dto.Products;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ProductsDaoFileTest {

    ProductsDaoFile toTest = new ProductsDaoFile("Test/Products.txt");

    public ProductsDaoFileTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException {

        File testDir = new File("Test");
        File seedDir = new File("Seed");
        File[] testFiles = testDir.listFiles();
        for (File testFile : testFiles) {
            testFile.delete();
            File[] seedFiles = seedDir.listFiles();
            for (File seedFile : seedFiles) {
                Files.copy(seedFile.toPath(), Paths.get("Test", seedFile.getName()), StandardCopyOption.REPLACE_EXISTING);
            }
        }

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void getProductsAndPricesGoldenPath() {

        try {
            List<Products> toCheck = toTest.getProductsAndPrices();
            Products toGet = toCheck.get(0); //checks object at list index 0
            assertEquals("Carpet", toGet.getProductType());
            assertEquals(new BigDecimal("2.25"), toGet.getCostPerSqaureFoot());
            assertEquals(new BigDecimal("2.10"), toGet.getLaborCostPerSquareFoot());
        } catch (ProductsDaoException ex) {
            fail("Threw ProductsDaoException on golden path test.");       
        }
    }

    @Test
    public void getCostSqFtGoldenPath() {
        try {
            toTest.getCostSqFt("Laminate");
            Products toCheck = toTest.getCostSqFt("Laminate");
            assertEquals("Laminate", toCheck.getProductType());
            assertEquals(new BigDecimal("1.75"), toCheck.getCostPerSqaureFoot());
            assertEquals(new BigDecimal("2.10"), toCheck.getLaborCostPerSquareFoot());
        } catch (ProductsDaoException ex) {
            fail("Threw ProductsDaoException on GoldenPathTest");
        }
    }

    @Test
    public void getCostStFtNullProduct() {
        try {
            toTest.getCostSqFt(null);
            
        } catch (ProductsDaoException ex) {
            fail("Did not catch ProductsDaoException on null Product test");
        }
        
    }
}

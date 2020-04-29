/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;

import com.mycompany.flooringorders.dto.Tax;
import com.mycompany.flooringorders.service.InvalidStateException;
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
public class TaxesDaoFileTest {
    
    TaxesDaoFile toTest = new TaxesDaoFile("Test/Taxes.txt");
    
    public TaxesDaoFileTest() {
        
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
    public void readTaxesFilesGoldenPathTest() {
        
        try {
           List <Tax> allTaxes = toTest.readTaxesFile();
           Tax toCheck = allTaxes.get(2);
           assertEquals("KY", toCheck.getStateAbbreviation());
           assertEquals("Kentucky", toCheck.getStateName());
           assertEquals(new BigDecimal("6.00"),toCheck.getTaxRate());
        } catch (TaxesDaoException ex) {
            fail( "hit TaxesDaoException on Golden Path test");
        }
    }
    
    @Test
    public void checkGetTaxRateByStateGoldenPath() {
        try {
            Tax toCheck = toTest.getTaxRateByState("KY");
            assertEquals(new BigDecimal("6.00"), toCheck.getTaxRate());
        }  catch (InvalidStateException ex) {
            fail("Threw invalid state exception on golden path test");
        } catch (TaxesDaoException ex) {
            fail("hit TaxesDaoException on checkGetTaxRateByStateGoldenPath");
        }
        
    }
    
    @Test
    public void checkTaxRateByStateNoSuchState() {
        try {
            toTest.getTaxRateByState("CT");
            
        } catch (InvalidStateException ex) {
            fail("Did not catch InvalidState exception");
        } catch (TaxesDaoException ex) {
            fail( "hit TaxesDaoException on checkTaxRateByStateNoSuchState");
        }
        
    }
    
    @Test
    public void checkTaxRateByStateNull() {
        try {
            toTest.getTaxRateByState(null);
             
        } catch (InvalidStateException ex) {
           fail("Did not catch InvalidState exception");
        } catch (TaxesDaoException ex) {
            fail("hit TaxesDaoException on checkTaxRateByStateNull");
        }
    }
        
}

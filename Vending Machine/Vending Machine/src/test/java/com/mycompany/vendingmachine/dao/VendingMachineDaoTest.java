// /*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.vendingmachine.dao;
//
//import com.mycompany.vendingmachine.dto.Snacks;
//import com.mycompany.vendingmachine.service.InsufficientFundsException;
//import com.mycompany.vendingmachine.service.OutofStockException;
//import com.mycompany.vendingmachine.service.VendingMachineService;
//import com.mycompany.vendingmachine.service.VendingMachineServiceTest;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.StandardCopyOption;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// *
// * @author ursul
// */
//public class VendingMachineDaoTest {
//
//    private VendingMachineDao toTest = new VendingMachineDaoFile("Test.txt");
//
//    public VendingMachineDaoTest() {
//    
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//    }
//
//    @AfterAll
//    public static void tearDownClass() {
//    }
//
//    @BeforeEach
//    public void setUp() throws IOException {
//        Path testPath = Path.of("Test.txt");
//        Path seedPath = Path.of("Seed.txt");
//        Files.deleteIfExists(testPath);
//        Files.copy(seedPath, testPath, 
//                StandardCopyOption.REPLACE_EXISTING);
//                
//
//    }
//
//    @AfterEach
//    public void tearDown() {
//    }
//
//    @Test
//    public void testGetSnackByNameGoldenPath() {
//
//        try {
//          
//
//            toTest.getSnackByName("Potatoes");
//
//            Snacks toCheck = toTest.getSnackByName("Potatoes");
//           
//            assertEquals("Potatoes", toCheck.getName());
//            assertEquals(new BigDecimal("3.45"), toCheck.getPrice());
//            assertEquals(1, toCheck.getQty());
//
//        } catch (VendingMachineDaoException ex) {
//            fail("VendingMachineDaoException happened on golden path test.");
//
//        }
//    }
//
//    @Test
//    public void testGetSnacksByIdInvalidName() {
//
//        try {
//            Snacks retrieved = toTest.getSnackByName("Cheese Squares");
//
//            assertNull(retrieved);
//
//        } catch (VendingMachineDaoException ex) {
//            fail("Did not catch exception on VendingMachineDaoException.");
//        }
//    }
//
//    @Test
//    public void testGoldenPathDecreaseQty() throws VendingMachineDaoException {
//
//        try {
//            List<Snacks> allSnacks = toTest.readSnacks();
//            
//            Snacks toCheck = allSnacks.get(5);
//            int qtyNow = toCheck.getQty();
//            
//            toTest.decreaseQty(toCheck);
//            
//            
//            toCheck = allSnacks.get(5);
//            
//            int qtyAfter = toCheck.getQty();
//
//
//            assertEquals("Apples", toCheck.getName());
//            assertEquals(new BigDecimal("3.45"), toCheck.getPrice());
//            assertEquals(1, toCheck.getQty());
//            
//            toTest.decreaseQty(toCheck);
//            assertEquals("Apples", toCheck.getName());
//            assertEquals(new BigDecimal("3.45"), toCheck.getPrice());
//            assertEquals(0, toCheck.getQty());
//            
//
//        } catch (OutofStockException ex) {
//            fail("OutofStockException thrown on golden path test.");
//
//        }
//    }
//
//    @Test
//    public void testEditAllSnacksNullName() {
//
//        try {
//
//            Snacks toEdit = toTest.getSnackByName("null");
//            toTest.decreaseQty(toEdit);
//            fail("Did not throw exception for testEditAllSnacksNull");
//        } catch (VendingMachineDaoException ex) {
//        } catch (OutofStockException ex) {
//            fail("Threw of OutOfStockException on null name test");
//
//        }
//
//    }
//
//    @Test
//    public void testEditAllSnacksOutOfStock() throws VendingMachineDaoException {
//
//        try {
//
//            Snacks toCheck = toTest.getSnackByName("Mint Gum");
//
//            toTest.decreaseQty(toCheck);
//
//            fail("Did not throw OutofStockException on out of stock test");
//        } catch (OutofStockException ex) {
//
//        }
//    }
//}

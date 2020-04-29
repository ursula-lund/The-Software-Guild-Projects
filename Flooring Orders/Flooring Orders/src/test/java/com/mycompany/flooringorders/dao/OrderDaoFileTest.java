/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;

import com.mycompany.flooringorders.dto.Order;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
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
public class OrderDaoFileTest {

    OrderDaoFile toTest = new OrderDaoFile("Test");

    public OrderDaoFileTest() {
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
    public void retrieveOrdersByDateGoldenPath() {

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate testDate = LocalDate.parse("06/02/2013", formatter);

            List<Order> toCheck = toTest.retrieveOrdersByDate(testDate);

            Order retrievedDate = toCheck.get(0);

            assertEquals(2, retrievedDate.getOrderNumber());
            assertEquals("Doctor Who", retrievedDate.getCustomerName());
            assertEquals("WA", retrievedDate.getState());
            assertEquals(new BigDecimal("9.25"), retrievedDate.getTaxRate());

            assertEquals("Wood", retrievedDate.getProductType());
            assertEquals(new BigDecimal("243.00"), retrievedDate.getArea());
            assertEquals(new BigDecimal("5.15"), retrievedDate.getCostPerSquareFoot());
            assertEquals(new BigDecimal("4.75"), retrievedDate.getLaborCostPerSquareFoot());
            assertEquals(new BigDecimal("1251.45"), retrievedDate.calcMatieralCost());
            assertEquals(new BigDecimal("1154.25"), retrievedDate.calcLaborCost());
            assertEquals(new BigDecimal("222.53"), retrievedDate.calcTax());
            assertEquals(new BigDecimal("2628.23"), retrievedDate.calcTotal());

        } catch (OrderDaoException ex) {
            fail("Threw OrderDaoException on GoldenPathTest");
        }
    }

    @Test
    public void retrieveOrdersByDateNullDate() {

        try {
            toTest.retrieveOrdersByDate(null);
            fail("Did not throw not OrderDaoException on Null Date");
        } catch (OrderDaoException ex) {

        }
    }

    @Test
    public void placeOrderGoldenpath() {
        Order toPlace = new Order();
        try {
            toPlace.setOrderNumber(1);
            toPlace.setCustomerName("Tim Tim");
            toPlace.setState("MN");
            toPlace.setTaxRate(new BigDecimal("3.25"));
            toPlace.setProductType("Tile");
            toPlace.setArea(new BigDecimal("200"));
            toPlace.setCostPerSquareFoot(new BigDecimal("3.25"));
            toPlace.setLaborCostPerSquareFoot(new BigDecimal("5.25"));
            toPlace.setOrderDate(LocalDate.of(2012, 12, 20));

            toTest.addOrder(toPlace);

            Order toCheck = toTest.getOrder(1, LocalDate.of(2012, 12, 20));

            assertEquals(1, toCheck.getOrderNumber());
            assertEquals("Tim Tim", toCheck.getCustomerName());
            assertEquals("MN", toCheck.getState());
            assertEquals(new BigDecimal("3.25"), toCheck.getTaxRate());
            assertEquals("Tile", toCheck.getProductType());
            assertEquals(new BigDecimal("200"), toCheck.getArea());
            assertEquals(new BigDecimal("3.25"), toCheck.getCostPerSquareFoot());
            assertEquals(new BigDecimal("5.25"), toCheck.getLaborCostPerSquareFoot());
            assertEquals(LocalDate.of(2012, 12, 20), toCheck.getOrderDate());
        } catch (OrderDaoException ex) {
            fail("Threw OrderDaoException on GoldenPathTest");
        }
    }

    @Test
    public void placeOrderNullOrder() {
        try {
            toTest.addOrder(null);
            fail("Did not catch null order on null order test");
        } catch (OrderDaoException ex) {

        }
    }

    @Test
    public void getOrderGoldenPath() {
        try {
            
            Order toCheck = toTest.getOrder(2, LocalDate.of(2013, 06, 02));

            assertEquals(2, toCheck.getOrderNumber());
            assertEquals("Doctor Who", toCheck.getCustomerName());
            assertEquals("WA", toCheck.getState());
            assertEquals(new BigDecimal("9.25"), toCheck.getTaxRate());
            assertEquals("Wood", toCheck.getProductType());
            assertEquals(new BigDecimal("243.00"), toCheck.getArea());
            assertEquals(new BigDecimal("5.15"), toCheck.getCostPerSquareFoot());
            assertEquals(new BigDecimal("4.75"), toCheck.getLaborCostPerSquareFoot());
            assertEquals(new BigDecimal("1251.45"), toCheck.calcMatieralCost());
            assertEquals(new BigDecimal("1154.25"), toCheck.calcLaborCost());
            assertEquals(new BigDecimal("222.53"), toCheck.calcTax());
            assertEquals(new BigDecimal("2628.23"), toCheck.calcTotal());

        } catch (OrderDaoException ex) {
            fail("Threw OrderDaoException on getOrderGoldenPath");
        }

    }

    @Test
    public void getOrderNullDate() {

        try {
            Order toCheck = toTest.getOrder(1, null);
            fail("Did not throw exception on null date test.");
        } catch (OrderDaoException ex) {

        }
    }

    @Test
    public void getOrderNoSuchOrderNum() {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate testDate = LocalDate.parse("06/02/2013", formatter);
            Order toCheck = toTest.getOrder(45, testDate);

        } catch (OrderDaoException ex) {
            fail("Did not throw exception on no OrderNumberTest.");

        }
    }

    @Test
    public void getOrderNegativeNum() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate testDate = LocalDate.parse("06/02/2013", formatter);
        try {
            Order toCheck = toTest.getOrder(-1, testDate);
            fail("Did not throw exception on negative number test.");
        } catch (OrderDaoException ex) {

        }

    }

    @Test
    public void finalizeEditGoldenPath() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate testDate = LocalDate.parse("06/02/2013", formatter);
            List<Order> toCheck = toTest.retrieveOrdersByDate(testDate);

            Order retrievedDate = toCheck.get(0);
            List<Order> toEdit = toTest.retrieveOrdersByDate(testDate);
            Order check = toEdit.get(0);

            check.setCustomerName("Nurse Who");
            check.setState("TX");
            check.setProductType("Laminate");
            check.setArea(new BigDecimal("300"));
            toTest.editOrder(check);

            assertEquals(2, check.getOrderNumber());
            assertEquals("Nurse Who", check.getCustomerName());
            assertEquals("TX", check.getState());
            assertEquals(new BigDecimal("9.25"), check.getTaxRate());
            assertEquals("Laminate", check.getProductType());
            assertEquals(new BigDecimal("300"), check.getArea());
            assertEquals(new BigDecimal("5.15"), check.getCostPerSquareFoot());
            assertEquals(new BigDecimal("4.75"), check.getLaborCostPerSquareFoot());

        } catch (OrderDaoException ex) {
            fail("Threw OrderDaoException on GoldenPathTest");
        }

    }

    @Test
    public void finalizeDeleteGoldenPath() {
        try {       
            Order beGone = toTest.getOrder(1, LocalDate.of(2013, 06, 01));
            toTest.deleteOrder(beGone);
          
            int deletedOrderNum = beGone.getOrderNumber();
            assertEquals(deletedOrderNum, beGone.getOrderNumber());
           
        } catch (OrderDaoException ex) {
            fail("Threw OrderDaoException on GoldenPathTest");
        }
    }

    @Test
    public void finalizeDeleteNullOrder() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate testDate = LocalDate.parse("06/02/2013", formatter);
            List<Order> delete = toTest.retrieveOrdersByDate(testDate);
            Order deleted = delete.get(1);
            toTest.deleteOrder(null);
            fail("Threw OrderDaoException on NullOrderTest");
        } catch (OrderDaoException ex) {

        }

    }

}

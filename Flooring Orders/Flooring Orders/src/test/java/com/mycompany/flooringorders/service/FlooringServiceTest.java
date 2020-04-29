/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.service;

import com.mycompany.flooringorders.dao.OrderDao;
import com.mycompany.flooringorders.dao.OrderDaoAlwaysFail;
import com.mycompany.flooringorders.dao.OrderDaoException;
import com.mycompany.flooringorders.dao.OrderDaoInMem;
import com.mycompany.flooringorders.dao.ProductsDao;
import com.mycompany.flooringorders.dao.ProductsDaoAlwaysFail;
import com.mycompany.flooringorders.dao.ProductsDaoException;
import com.mycompany.flooringorders.dao.ProductsDaoInMem;
import com.mycompany.flooringorders.dao.TaxesDao;
import com.mycompany.flooringorders.dao.TaxesDaoAlwaysFail;
import com.mycompany.flooringorders.dao.TaxesDaoException;
import com.mycompany.flooringorders.dao.TaxesDaoInMem;
import com.mycompany.flooringorders.dto.Order;
import com.mycompany.flooringorders.dto.Products;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
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
public class FlooringServiceTest {

    FlooringService service;
    FlooringService alwaysFail;

    public FlooringServiceTest() {

    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        OrderDao oTestDao = new OrderDaoInMem();
        ProductsDao pTestDao = new ProductsDaoInMem();
        TaxesDao tTestDao = new TaxesDaoInMem();

        service = new FlooringService(oTestDao, pTestDao, tTestDao);
        alwaysFail = new FlooringService(new OrderDaoAlwaysFail(), new ProductsDaoAlwaysFail(), new TaxesDaoAlwaysFail());

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetOrderFromDateGoldenPath() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyy");
        LocalDate testDate = LocalDate.parse("06/02/2013", formatter);

        try {
            service.getOrderFromDate(testDate);
            List<Order> toGet = service.getOrderFromDate(testDate);
            Order toRetrieve = toGet.get(0);

            assertEquals(1, toRetrieve.getOrderNumber());
            assertEquals("Bill Perkins", toRetrieve.getCustomerName());
            assertEquals("MN", toRetrieve.getState());
            assertEquals(new BigDecimal("8.35"), toRetrieve.getTaxRate());
            assertEquals("Wood", toRetrieve.getProductType());
            assertEquals(new BigDecimal("230"), toRetrieve.getArea());
            assertEquals(new BigDecimal("4.25"), toRetrieve.getCostPerSquareFoot());
            assertEquals(new BigDecimal("3.25"), toRetrieve.getLaborCostPerSquareFoot());

        } catch (OrderDaoException ex) {
            fail("Threw OrderDaoException on GoldenPathTest");

        }
    }

    @Test
    public void GetOrderFromDateAlwaysFail() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyy");
            LocalDate testDate = LocalDate.parse("06/02/2013", formatter);
            alwaysFail.getOrderFromDate(testDate);
            fail("Did not fail on AlwaysFail test");
        } catch (OrderDaoException ex) {
        }
    }

    @Test
    public void testGetProductsListGoldenPath() {
        try {
            List<Products> toGet = service.getProductsList();
            Products toRetrieve = toGet.get(0); //pulls list item out of index WRONG--Double Check      
            assertEquals("Tile", toRetrieve.getProductType());
            assertEquals(new BigDecimal("4.00"), toRetrieve.getLaborCostPerSquareFoot());
            assertEquals(new BigDecimal("3.75"), toRetrieve.getCostPerSqaureFoot());

        } catch (ProductsDaoException ex) {
            fail("Threw ProductsDaoException on golden path test.");
        }
    }

    @Test
    public void testGetProductsListAlwaysFail() {
        try {

            alwaysFail.getProductsList();
            fail("Did not Fail on alwaysFail test.");

        } catch (ProductsDaoException ex) {

        }
    }

    @Test
    public void addOrderGoldenPath() throws InvalidDateException {
        try {
          Order toComplete = new Order();
          toComplete.setOrderDate(LocalDate.of(2999, 6, 2));
            toComplete.setCustomerName("bill b");
            toComplete.setState("WI");
            toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("500"));
            

            service.addOrder(toComplete);
            toComplete = service.getDateOrderToEdit(1, LocalDate.of(2999, 6, 2));

            assertEquals("bill b", toComplete.getCustomerName());
            assertEquals("WI", toComplete.getState());
            assertEquals("Tile", toComplete.getProductType());
            assertEquals(new BigDecimal("5.00"), toComplete.getTaxRate());
            assertEquals(new BigDecimal("500"), toComplete.getArea());
            assertEquals(new BigDecimal("3.75"), toComplete.getCostPerSquareFoot());
            assertEquals(new BigDecimal("4.00"), toComplete.getLaborCostPerSquareFoot());
            

        } catch (InvalidProductException ex) {
            fail("Threw InvalidProductException on GoldenPathTest");
        } 
        catch (InvalidDateException ex) {
            fail("Threw InvalidDateException on GoldenPathTest");
        } 
        catch (InvalidNameException ex) {
            fail("Threw invalidNameException on GoldenPathTest");
        } catch (InvalidAreaException ex) {
            fail("Threw invalidAreaException on GoldenPathTest");
        } catch (InvalidStateException ex) {
            fail("Threw InvalidStateException on GoldenPathTest");
        } catch (TaxesDaoException ex) {
            fail("Threw TaxesDaoException on GoldenPathTest");
        } catch (OrderDaoException ex) {
            fail("Threw OrderDaoException  GoldenPathTest");
        } catch (ProductsDaoException ex) {
            fail("Threw OrderDaoException on GoldenPathTest");
        } catch (InvalidOrderNumException ex) {
            fail("Threw InvalidOrderNumException on GoldenPathTest");
        }

    }

    @Test
    public void addOrderAlwaysFail() {

        try {

            Order toComplete = new Order();
            toComplete.setCustomerName("bill b");
            toComplete.setState("TX");
            toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("500"));
            toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
            toComplete.setTaxRate(new BigDecimal("4.45"));
            toComplete.setCostPerSquareFoot(new BigDecimal("3.50"));
            toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
            alwaysFail.addOrder(toComplete);
            fail("Did not throw exception on alwaysFail");
        } catch (InvalidProductException ex) {
            fail("Threw InvalidProductException on alwaysFailTest");
        } catch (InvalidDateException ex) {
            fail("Threw InvalidDateException on alwaysFail");
        } catch (InvalidNameException ex) {
            fail("Threw InvalidNameException on alwaysFail");
        } catch (InvalidAreaException ex) {
            fail("Threw InvalidAreaException on alwaysFail");
        } catch (InvalidStateException ex) {
            fail("Threw InvalidStateException on alwaysFail");
        } catch (TaxesDaoException ex) {

        } catch (OrderDaoException ex) {
            fail("Threw OrderDaoException on alwaysFail");
        } catch (ProductsDaoException ex) {
            fail("Threw ProductsDaoException on alwaysFail");
        }
    }

    @Test
    public void addOrderPastDate() {
        try {
            Order toComplete = new Order();
            toComplete.setOrderDate(LocalDate.of(2000, 01, 01));
            service.addOrder(toComplete);
            fail("Did not throw exception on Invalid Date");
        } catch (InvalidProductException ex) {
            fail("Threw InvalidProductException on PastOrderDate Test");
        } catch (InvalidDateException ex) {

        } catch (InvalidNameException ex) {
            fail("Threw InvalidNameException on PastOrderDate Test");
        } catch (InvalidAreaException ex) {
            fail("Threw InvalidAreaException on PastOrderDate Test");
        } catch (InvalidStateException ex) {
            fail("Threw InvalidStateException on PastOrderDate Test");
        } catch (TaxesDaoException ex) {
            fail("Threw TaxesDaoException on PastOrderDate Test");
        } catch (OrderDaoException ex) {
            fail("Threw OrderDaoException on PastOrderDate Test");
        } catch (ProductsDaoException ex) {
            fail("threw ProductsDaoException on PastOrderDate Test");
        }
    }

    @Test
    public void addOrderInvalidDate() {

        try {
            Order invalidDate = new Order();
            invalidDate.setOrderDate(null);
            service.addOrder(invalidDate);
            fail("Did not throw InvalidDateException on InvalidDate Test");
        } catch (InvalidProductException ex) {
            fail("threw InvalidProductException on InvalidDate Test");
        } catch (InvalidDateException ex) {

        } catch (InvalidNameException ex) {
            fail("threw InvalidNameException on InvalidDate Test");
        } catch (InvalidAreaException ex) {
            fail("threw InvalidAreaException on InvalidDate Test");
        } catch (InvalidStateException ex) {
            fail("threw InvalidStateException on InvalidDate Test");
        } catch (TaxesDaoException ex) {
            fail("threw TaxesDaoException on InvalidDate Test");
        } catch (OrderDaoException ex) {
            fail("threw OrderDaoException on InvalidDate Test");
        } catch (ProductsDaoException ex) {
            fail("threw ProductsDaoException on InvalidDate Test");
        }
    }

    @Test
    public void addOrderInvalidName() {
        try {
            Order invalidName = new Order();
            invalidName.setCustomerName(null);
            service.addOrder(invalidName);
            fail("Did not throw InvalidDateException on invalidNameTest");
        } catch (InvalidProductException ex) {
            fail("threw InvalidDateException on InvalidName Test");
        } catch (InvalidDateException ex) {

        } catch (InvalidNameException ex) {
            fail("threw InvalidNameException on InvalidName Test");
        } catch (InvalidAreaException ex) {
            fail("threw InvalidAreaException on InvalidName Test");
        } catch (InvalidStateException ex) {
            fail("threw InvalidStateException on InvalidName Test");
        } catch (TaxesDaoException ex) {
            fail("threw TaxesDaoException on InvalidName Test");
        } catch (OrderDaoException ex) {
            fail("threw OrderDaoException on InvalidName Test");
        } catch (ProductsDaoException ex) {
            fail("threw ProductsDaoException on InvalidName Test");

        }

    }

    @Test
    public void addOrderNegativeArea() {
        try {
            Order toComplete = new Order();
            toComplete.setCustomerName("bill b");
            toComplete.setState("TX");
            toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("-500.0"));
            toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
            toComplete.setTaxRate(new BigDecimal("4.45"));
            toComplete.setCostPerSquareFoot(new BigDecimal("2.0"));
            toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));;
            service.addOrder(toComplete);
            fail("Did not catch invalidArea on NegativeArea Test");
        } catch (InvalidProductException ex) {
            fail("threw InvalidProductException on NegativeArea Test");
        } catch (InvalidDateException ex) {
            fail("threw InvalidDateException on NegativeArea Test");
        } catch (InvalidNameException ex) {
            fail("threw InvalidNameException on NegativeArea Test");
        } catch (InvalidAreaException ex) {

        } catch (InvalidStateException ex) {
            fail("threw InvalidStateException on InvalidName Test");
        } catch (TaxesDaoException ex) {
            fail("threw TaxesDaoException on InvalidName Test");
        } catch (OrderDaoException ex) {
            fail("threw OrderDaoException on InvalidName Test");
        } catch (ProductsDaoException ex) {
            fail("threw ProductsDaoException on InvalidName Test");

        }
    }

    @Test
    public void addOrderNullArea() {

        try {

            Order toComplete = new Order();
            toComplete.setCustomerName("bill b");
            toComplete.setState("TX");
            toComplete.setProductType("Tile");
            //toComplete.setArea(new BigDecimal("500"));
            toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
            toComplete.setTaxRate(new BigDecimal("4.45"));
            toComplete.setCostPerSquareFoot(new BigDecimal("3.50"));
            toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));

            service.addOrder(toComplete);
            fail("Did not catch null area on null area test.");
        } catch (InvalidProductException ex) {
            fail("threw InvalidProductException on NullArea Test");
        } catch (InvalidDateException ex) {
            fail("threw InvalidDateException on NullArea Test");
        } catch (InvalidNameException ex) {
            fail("threw InvalidNameException on NullArea Test");
        } catch (InvalidAreaException ex) {

        } catch (InvalidStateException ex) {
            fail("threw InvalidStateException on NullArea Test");
        } catch (TaxesDaoException ex) {
            fail("threw TaxesDaoException on NullArea Test");
        } catch (OrderDaoException ex) {
            fail("threw OrderDaoException on NullArea Test");
        } catch (ProductsDaoException ex) {
            fail("threw ProductsDaoException on NullArea Test");

        }
    }

    @Test
    public void addOrderNullState() {

        try {

            Order toComplete = new Order();
            toComplete.setCustomerName("bill b");
            //toComplete.setState("TX");
            toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("500"));
            toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
            toComplete.setTaxRate(new BigDecimal("4.45"));
            toComplete.setCostPerSquareFoot(new BigDecimal("3.50"));
            toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));

            service.addOrder(toComplete);
            fail("did not catch NullState on NullStateTest");
        } catch (InvalidProductException ex) {
            fail("threw InvalidProductException on NullStateTest");
        } catch (InvalidDateException ex) {
            fail("threw InvalidDateException on NullStateTest");
        } catch (InvalidNameException ex) {
            fail("threw InvalidNameException on NullStateTest");
        } catch (InvalidAreaException ex) {
            fail("threw InvalidAreaException on NullStateTest");
        } catch (InvalidStateException ex) {

        } catch (TaxesDaoException ex) {
            fail("threw TaxesDaoException on NullStateTest");
        } catch (OrderDaoException ex) {
            fail("threw OrderDaoException on NullStateTest");
        } catch (ProductsDaoException ex) {
            fail("threw ProductsDaoException on NullStateTest");

        }
    }

    @Test
    public void addOrderInvalidState() {
        try {
            Order toComplete = new Order();
            toComplete.setCustomerName("bill b");
            //toComplete.setState("TX");
            toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("500"));
            toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
            toComplete.setTaxRate(new BigDecimal("4.45"));
            toComplete.setCostPerSquareFoot(new BigDecimal("3.50"));
            toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
            service.addOrder(toComplete);

            fail("Did not catch exception on InvalidStateTest");

        } catch (InvalidProductException ex) {
            fail("threw InvalidProductException on InvalidStateTest");
        } catch (InvalidDateException ex) {
            fail("threw InvalidDateException on InvalidStateTest");
        } catch (InvalidNameException ex) {
            fail("threw InvalidNameException on InvalidStateTest");
        } catch (InvalidAreaException ex) {
            fail("threw InvalidAreaException on InvalidStateTest");
        } catch (InvalidStateException ex) {

        } catch (TaxesDaoException ex) {
            fail("threw TaxesDaoException on InvalidStateTest");
        } catch (OrderDaoException ex) {
            fail("threw OrderDaoException on InvalidStateTest");
        } catch (ProductsDaoException ex) {
            fail("threw ProductsDaoException on InvalidStateTest");

        }

    }

    @Test
    public void addOrderNullProduct() {

        Order toComplete = new Order();
        toComplete.setCustomerName("bill b");
        toComplete.setState("TX");
        toComplete.setArea(new BigDecimal("500"));
        toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
        toComplete.setTaxRate(new BigDecimal("4.45"));
        toComplete.setCostPerSquareFoot(new BigDecimal("3.50"));
        toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));

        try {
            service.addOrder(toComplete);

            fail("didn't throw exception on addOrderNullProduct");

        } catch (InvalidProductException ex) {

        } catch (InvalidDateException ex) {
            fail("Threw InvalidDateException on addOrderNullProduct");
        } catch (InvalidNameException ex) {
            fail("Threw invalidNameException on addOrderNullProduct");
        } catch (InvalidAreaException ex) {
            fail("Threw invalidAreaException on addOrderNullProduct");
        } catch (InvalidStateException ex) {
            fail("Threw InvalidStateException on addOrderNullProduct");
        } catch (TaxesDaoException ex) {
            fail("Threw TaxesDaoException on addOrderNullProduct");
        } catch (OrderDaoException ex) {
            fail("Threw OrderDaoException  addOrderNullProduct");
        } catch (ProductsDaoException ex) {
            fail("Threw OrderDaoException on addOrderNullProduct");

        }

    }

    @Test
    public void addOrderInvalidProduct() {
        try {

            Order toComplete = new Order();
            toComplete.setCustomerName("bill b");
            toComplete.setState("TX");
            // toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("500"));
            toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
            toComplete.setTaxRate(new BigDecimal("4.45"));
            toComplete.setCostPerSquareFoot(new BigDecimal("3.50"));
            toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
            service.addOrder(toComplete);
            fail("Did not catch invalid product");
        } catch (InvalidProductException ex) {

        } catch (InvalidDateException ex) {
            fail("threw InvalidDateException on InvalidProduct");
        } catch (InvalidNameException ex) {
            fail("threw InvalidNameException on InvalidProduct");
        } catch (InvalidAreaException ex) {
            fail("threw InvalidAreaException on InvalidProduct");
        } catch (InvalidStateException ex) {
            fail("threw InvalidStateException on InvalidProduct");
        } catch (TaxesDaoException ex) {
            fail("threw TaxesDaoException on InvalidProduct");
        } catch (OrderDaoException ex) {
            fail("threw OrderDaoException on InvalidProduct");
        } catch (ProductsDaoException ex) {
            fail("threw ProductsDaoException on InvalidProduct");

        }
    }

    @Test
    public void getDateOrderToEditGoldenPath() {

        try {
            Order toComplete = new Order();
            toComplete.setOrderNumber(1);
            toComplete.setCustomerName("bill b");
            toComplete.setState("TX");
            toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("500"));
            toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
            toComplete.setTaxRate(new BigDecimal("4.45"));
            toComplete.setCostPerSquareFoot(new BigDecimal("3.50"));
            toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));

            service.getDateOrderToEdit(1, toComplete.getOrderDate());

            assertEquals(1, toComplete.getOrderNumber());
            assertEquals("bill b", toComplete.getCustomerName());
            assertEquals("TX", toComplete.getState());
            assertEquals(new BigDecimal("4.45"), toComplete.getTaxRate());
            assertEquals("Tile", toComplete.getProductType());
            assertEquals(new BigDecimal("500"), toComplete.getArea());
            assertEquals(new BigDecimal("3.50"), toComplete.getCostPerSquareFoot());
            assertEquals(new BigDecimal("4.15"), toComplete.getLaborCostPerSquareFoot());

        } catch (OrderDaoException ex) {
            fail("threw OrderDaoException on goldenpathtest");

        } catch (InvalidOrderNumException ex) {
            fail("threw InvalidOrderNumException on goldenpathtest");
        } catch (InvalidDateException ex) {
            fail("threw InvalidDateException on goldenpathtest");
        }
    }

    @Test
    public void editDateOrderAlwaysFail() {
        try {

            Order toComplete = new Order();
            toComplete.setCustomerName("bill b");
            toComplete.setState("TX");
            toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("500"));
            toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
            toComplete.setTaxRate(new BigDecimal("4.45"));
            toComplete.setCostPerSquareFoot(new BigDecimal("3.50"));
            toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));

            Order toRead = alwaysFail.getDateOrderToEdit(1, toComplete.getOrderDate());
            fail("Did not fail alwaysFail");
        } catch (OrderDaoException ex) {

        } catch (InvalidOrderNumException ex) {
            fail("Threw InvalidOrderNumException on AlwaysFailTest");
        } catch (InvalidDateException ex) {
            fail("Threw InvalidDateException on AlwaysFailTest");
        }
    }

    @Test
    public void updateOrderOrderGoldenPath() {

        try {

            Order toComplete = service.getDateOrderToEdit(1, LocalDate.of(2013, 6, 2));
            toComplete.setCustomerName("bill b");
            toComplete.setState("WI");
            toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("500"));
            //toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
            //toComplete.setTaxRate(new BigDecimal("4.45"));
            //toComplete.setCostPerSquareFoot(new BigDecimal("3.50"));
            //toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));

            service.updateOrder(toComplete);

            toComplete = service.getDateOrderToEdit(1, LocalDate.of(2013, 6, 2));

            assertEquals(1, toComplete.getOrderNumber());
            assertEquals("bill b", toComplete.getCustomerName());
            assertEquals("WI", toComplete.getState());
            assertEquals(new BigDecimal("5.00"), toComplete.getTaxRate());
            assertEquals("Tile", toComplete.getProductType());
            assertEquals(new BigDecimal("500"), toComplete.getArea());
            assertEquals(new BigDecimal("3.75"), toComplete.getCostPerSquareFoot());
            assertEquals(new BigDecimal("4.00"), toComplete.getLaborCostPerSquareFoot());

        } catch (InvalidNameException ex) {
            fail("threw InvalidNameException on goldenpathtest");
        } catch (InvalidAreaException ex) {
            fail("threw InvalidAreaException on goldenpathtest");
        } catch (InvalidStateException ex) {
            fail("threw OrderDInvalidStateExceptionaoException on goldenpathtest");
        } catch (InvalidProductException ex) {
            fail("threw InvalidProductException on goldenpathtest");
        } catch (TaxesDaoException ex) {
            fail("threw TaxesDaoException on goldenpathtest");
        } catch (ProductsDaoException ex) {
            fail("threw ProductsDaoException on goldenpathtest");
        } catch (OrderDaoException ex) {
            fail("threw OrderDaoException on goldenpathtest");
        } catch (InvalidOrderNumException ex) {
            fail("threw InvalidOrderNumException on goldenpathtest");
        } catch (InvalidDateException ex) {
            fail("threw InvalidDateException on goldenpathtest");
        }

    }

    @Test
    public void updateOrderAlwaysFail() {

        try {
            Order toComplete = new Order();
            toComplete.setCustomerName("bill b");
            toComplete.setState("TX");
            toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("500"));
            toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
            toComplete.setTaxRate(new BigDecimal("4.45"));
            toComplete.setCostPerSquareFoot(new BigDecimal("3.50"));
            toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
            alwaysFail.updateOrder(toComplete);

            fail("Did not Fail alwaysFailTest");
        } catch (InvalidNameException ex) {
            fail("Threw InvalidNameException on AlwaysFailTest");
        } catch (InvalidAreaException ex) {
            fail("Threw InvalidAreaException on AlwaysFailTest");
        } catch (InvalidStateException ex) {
            fail("Threw InvalidStateException on AlwaysFailTest");
        } catch (InvalidProductException ex) {
            fail("Threw InvalidProductException on AlwaysFailTest");

        } catch (TaxesDaoException ex) {

        } catch (ProductsDaoException ex) {

        } catch (OrderDaoException ex) {

        }
    }

    @Test
    public void updateOrderNullName() {

        try {

            Order toComplete = service.getDateOrderToEdit(1, LocalDate.of(2013, 6, 2));
            toComplete.setCustomerName(null);
            toComplete.setState("WI");
            toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("500"));
            //toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
            //toComplete.setTaxRate(new BigDecimal("4.45"));
            //toComplete.setCostPerSquareFoot(new BigDecimal("3.50"));
            //toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));

            service.updateOrder(toComplete);
            fail("did not throw exception on updateOrderNullName");

        } catch (InvalidNameException ex) {

        } catch (InvalidAreaException ex) {
            fail("threw InvalidAreaException on goldenpathtest");
        } catch (InvalidStateException ex) {
            fail("threw OrderDInvalidStateExceptionaoException on goldenpathtest");
        } catch (InvalidProductException ex) {
            fail("threw InvalidProductException on goldenpathtest");
        } catch (TaxesDaoException ex) {
            fail("threw TaxesDaoException on goldenpathtest");
        } catch (ProductsDaoException ex) {
            fail("threw ProductsDaoException on goldenpathtest");
        } catch (OrderDaoException ex) {
            fail("threw OrderDaoException on goldenpathtest");
        } catch (InvalidOrderNumException ex) {
            fail("threw InvalidOrderNumException on goldenpathtest");
        } catch (InvalidDateException ex) {
            fail("threw InvalidDateException on goldenpathtest");
        }

    }

    @Test
    public void updateOrderNullArea() {

        try {
            Order toComplete = service.getDateOrderToEdit(1, LocalDate.of(2013, 6, 2));
            toComplete.setCustomerName("bill b");
            toComplete.setState("WI");
            toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("500"));

            service.updateOrder(toComplete);

            toComplete = service.getDateOrderToEdit(2, LocalDate.of(2020, 07, 07));

        } catch (InvalidNameException ex) {
            fail("threw InvalidNameException on goldenpathtest");
        } catch (InvalidAreaException ex) {
            fail("threw InvalidAreaException on goldenpathtest");
        } catch (InvalidStateException ex) {
            fail("threw OrderDInvalidStateExceptionaoException on goldenpathtest");
        } catch (InvalidProductException ex) {
            fail("threw InvalidProductException on goldenpathtest");
        } catch (TaxesDaoException ex) {
            fail("threw TaxesDaoException on goldenpathtest");
        } catch (ProductsDaoException ex) {
            fail("threw ProductsDaoException on goldenpathtest");
        } catch (OrderDaoException ex) {
            fail("threw OrderDaoException on goldenpathtest");
        } catch (InvalidOrderNumException ex) {
            fail("threw InvalidOrderNumException on goldenpathtest");
        } catch (InvalidDateException ex) {
            fail("threw InvalidDateException on goldenpathtest");
        }

    }

    @Test
    public void updateOrderNullProduct() {

        try {
            Order toComplete = new Order();
            toComplete.setCustomerName("bill b");
            toComplete.setState("TX");
            //toComple            //toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("500"));
            toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
            toComplete.setTaxRate(new BigDecimal("4.45"));
            toComplete.setCostPerSquareFoot(new BigDecimal("3.50"));
            toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));

            toComplete = service.getDateOrderToEdit(2, LocalDate.of(2020, 07, 07));

        } catch (OrderDaoException ex) {
            fail("Threw OrderDaoException on nullProducttest");
        } catch (InvalidOrderNumException ex) {
            fail("Threw InvalidOrderNumException on nullProducttest");
        } catch (InvalidDateException ex) {
            fail("Threw InvalidDate on nullproductTest");
        }
    }

    @Test
    public void updateOrderNullState() {
        try {
            Order toComplete = service.getDateOrderToEdit(1, LocalDate.of(2013, 6, 2));
            toComplete.setCustomerName("bill b");
            toComplete.setState(null);
            toComplete.setProductType("Tile");
            toComplete.setArea(new BigDecimal("500"));
            //toComplete.setOrderDate(LocalDate.of(2020, 07, 07));
            //toComplete.setTaxRate(new BigDecimal("4.45"));
            //toComplete.setCostPerSquareFoot(new BigDecimal("3.50"));
            //toComplete.setLaborCostPerSquareFoot(new BigDecimal("4.15"));

            toComplete = service.getDateOrderToEdit(2, LocalDate.of(2020, 07, 07));

        } catch (OrderDaoException ex) {
            fail("threw OrderDaoException on NullState");
        } catch (InvalidOrderNumException ex) {
            fail("threw InvalidOrderNumException on NullStatet");
        } catch (InvalidDateException ex) {
            fail("threw InvalidDateException on NullState");
        }

    }

    @Test
    public void finalizeEditGoldenPath() {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate testDate = LocalDate.parse("06/02/2013", formatter);

            Order check = service.getDateOrderToEdit(1, testDate);

            check.setCustomerName("Nurse Who");
            check.setState("TX");
            check.setProductType("Laminate");
            check.setArea(new BigDecimal("300"));

            check = service.getDateOrderToEdit(1, testDate);

            assertEquals(1, check.getOrderNumber());
            assertEquals("Nurse Who", check.getCustomerName());
            assertEquals("TX", check.getState());
            assertEquals(new BigDecimal("8.35"), check.getTaxRate());
            assertEquals("Laminate", check.getProductType());
            assertEquals(new BigDecimal("300"), check.getArea());
            assertEquals(new BigDecimal("4.25"), check.getCostPerSquareFoot());
            assertEquals(new BigDecimal("3.25"), check.getLaborCostPerSquareFoot());
        } catch (OrderDaoException ex) {
            fail("Threw OrderDaoException on GoldenPathTest");
        } catch (InvalidOrderNumException ex) {
            fail("Threw InvalidOrderNumException on GoldenPathTest");
        } catch (InvalidDateException ex) {
            fail("Threw InvalidDateException on GoldenPathTest");
        }

    }

    @Test
    public void finalizeEditAlwaysFail() {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate testDate = LocalDate.parse("06/02/2013", formatter);
            List<Order> toCheck = alwaysFail.getOrderFromDate(testDate);
            fail("Did not fail alwaysFailTest");
        } catch (OrderDaoException ex) {

        }
    }

    @Test
    public void finalizeDeleteGoldenPath() {
        try {

            Order toComplete = service.getDateOrderToEdit(1, LocalDate.of(2013, 6, 2));

            service.finalizeDelete(toComplete);
            
             toComplete = service.getDateOrderToEdit(1, LocalDate.of(2013, 6, 2));

        } catch (OrderDaoException ex) {
            fail("Threw OrderDaoException on GoldenPathTest");
        } catch (InvalidOrderNumException ex) {
            fail("Threw invalidOrderNumException on GoldenPathTest");
        } catch (InvalidDateException ex) {
        }
    }

    @Test
    public void finalizeDeleteAlwaysFail() {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate testDate = LocalDate.parse("06/02/2013", formatter);

            List<Order> toDelete = service.getOrderFromDate(testDate);
            Order beGone = toDelete.get(0);
            alwaysFail.finalizeDelete(beGone);
            fail("Did not fail alwaysFail test");
        } catch (OrderDaoException ex) {

        }
    }

}

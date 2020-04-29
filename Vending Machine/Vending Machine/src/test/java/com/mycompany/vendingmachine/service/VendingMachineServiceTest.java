///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.vendingmachine.service;
//
//import com.mycompany.vendingmachine.dao.VendingMachineAlwaysFailDao;
//import com.mycompany.vendingmachine.dao.VendingMachineDao;
//import com.mycompany.vendingmachine.dao.VendingMachineDaoException;
//import com.mycompany.vendingmachine.dao.VendingMachineDaoFile;
//import com.mycompany.vendingmachine.dao.VendingMachineInMemDao;
//import com.mycompany.vendingmachine.dto.Change;
//import com.mycompany.vendingmachine.dto.Snacks;
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// *
// * @author ursul
// */
//public class VendingMachineServiceTest {
//
//    VendingMachineService toTest;
//    VendingMachineService alwaysFailServ;
//
//    private VendingMachineService service;
//
//    public VendingMachineServiceTest() {
//
//        ApplicationContext ctx
//                = new ClassPathXmlApplicationContext("applicationContext.xml");
//        service
//                = ctx.getBean("serviceLayer", VendingMachineService.class);
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
//    public void setUp() throws VendingMachineDaoException, InsufficientFundsException, OutofStockException {
//
//        VendingMachineDao testDao = new VendingMachineInMemDao();
//
//        Snacks toAdd = new Snacks();
//        toAdd.setName("Yams");
//        toAdd.setPrice(new BigDecimal("4.00"));
//        toAdd.setQty(3);
//
//        testDao.getSnackByName("Yams");
//
//        toTest = new VendingMachineService(testDao);
//        alwaysFailServ = new VendingMachineService(new VendingMachineAlwaysFailDao());
//
//    }
//
//    @AfterEach
//    public void tearDown() {
//    }
//
//    @Test
//    public void VendItemChangeGoldenPath() {
//
//        try {
//            service.addMoney(new BigDecimal("4.00"));
//            service.vendItemChange("Cheese Balls");
//
//            Snacks toVend = service.dao.getSnackByName("Cheese Balls");
//
//            assertEquals("Cheese Balls", toVend.getName());
//            assertEquals(new BigDecimal("2.40"), toVend.getPrice());
//            assertEquals(1, toVend.getQty());
//
//        } catch (OutofStockException ex) {
//            fail("OutofStockException thrown on golden path test.");
//        } catch (VendingMachineDaoException ex) {
//            fail("VendingMachineDaoException thrown on golden path test.");
//        } catch (InsufficientFundsException ex) {
//            fail("InsufficientFundsException thrown on golden path test.");
//        } catch (NullMoneyException ex) {
//            fail("NullMoneyException thrown on golden path test.");
//        } catch (NullNameException ex) {
//            fail("NullNameException on golden path test.");
//        }
//
//    }
//
//    @Test
//    public void testVendItemChangeAlwaysFail() {
//        try {
//
//            alwaysFailServ.vendItemChange("Cheese Balls");
//
//            fail("Did not throw exception on always fail test");
//
//        } catch (OutofStockException ex) {
//            fail("Threw OutofStockException on always fail test");
//        } catch (InsufficientFundsException ex) {
//            fail("Threw InsufficientFundsException on always fail test");
//        } catch (VendingMachineDaoException ex) {
//
//        } catch (NullNameException ex) {
//            fail("NullNameException on golden path test.");
//        }
//    }
//
//    @Test
//    public void testVendItemChangeNullName() {
//        try {
//            service.vendItemChange(null);
//
//            fail("Did not throw NullNameException");
//
//        } catch (NullNameException ex) {
//
//        } catch (OutofStockException ex) {
//            fail("Threw out of stock exception on null name test.");
//        } catch (InsufficientFundsException ex) {
//            fail("Threw InsufficentFunds exception on null name test.");
//        } catch (VendingMachineDaoException ex) {
//            fail("Threw VendingMachineDaoException  on null name test.");
//        }
//
//    }
//
//    @Test
//    public void testVendItemChangeOutofStockException() {
//
//        try {
//            service.addMoney(new BigDecimal("5.00"));
//            service.vendItemChange("Apples");
//            fail("Did not throw OutofStockException on out of stock item");
//        } catch (OutofStockException ex) {
//
//        } catch (VendingMachineDaoException ex) {
//            fail("Threw VendingMachineDaoException on OutofStockTest");
//        } catch (InsufficientFundsException ex) {
//            fail("Threw InsufficientFunds ex on OutOfStock Test");
//        } catch (NullMoneyException ex) {
//            fail("Threw NullMoneyEx on Out of Stock test");
//        } catch (NullNameException ex) {
//            fail("NullNameException on golden path test.");
//        }
//    }
//
//    @Test
//    public void testVendItemChangeInsufficientFunds() {
//
//        try {
//            BigDecimal userTotal = new BigDecimal("0.10");
//            service.vendItemChange("Cheese Balls");
//
//            fail("Did not throw InsufficientFundsException on insufficentFundsTest.");
//        } catch (InsufficientFundsException ex) {
//
//        } catch (OutofStockException ex) {
//            fail("Threw OutofStockException on InsufficientFundsException test");
//
//        } catch (VendingMachineDaoException ex) {
//            fail("Threw VendingMachineDaoException on OutofStockxception test");
//
//        } catch (NullNameException ex) {
//            fail("Threw NullNameException on OutofStockxception test");
//        }
//
//    }
//
//    @Test
//    public void returnChangeGoldenPath() {
//
//        try {
//
//            toTest.addMoney(new BigDecimal("1.42"));
//
//            Change toChange = toTest.returnChange();
//            assertEquals(5, toChange.getQuarters());
//            assertEquals(1, toChange.getDimes());
//            assertEquals(1, toChange.getNickels());
//            assertEquals(2, toChange.getPennies());
//
//        } catch (Exception ex) {
//            fail("Exception thrown on Golden path test");
//        }
//
//    }
//
//    @Test
//    public void returnChangeAlwaysFail() {
//        try {
//            alwaysFailServ.returnChange();
//        } catch (Exception ex) {
//            fail("Did not throw exception on always fail test");
//
//        }
//
//    }
//
//    @Test
//    public void testAddMoneyGoldenPath() {
//        try {
//
//            BigDecimal toCheck = new BigDecimal("3.00");
//
//            assertEquals((new BigDecimal("3.00")), toTest.addMoney(toCheck));
//
//        } catch (NullMoneyException ex) {
//            fail("Threw nullMoney on GoldenpathTest");
//        }
//    }
//
//    @Test
//    public void testAddMoneyAlwaysFail() {
//
//        BigDecimal toCheck = new BigDecimal("3.00");
//        try {
//            alwaysFailServ.addMoney(toCheck);
//
//        } catch (NullMoneyException ex) {
//            fail("Threw null Money Exception on always fail test");
//        }
//    }
//
//    @Test
//    public void testAddMoneyNull() {
//
//        try {
//
//            BigDecimal Added = toTest.addMoney(null);
//
//            fail("Did not throw NullMoneyException.");
//        } catch (NullMoneyException ex) {
//        }
//    }
//
//    @Test
//    public void addNegativeMoney() {
//
//        try {
//
//            BigDecimal toCheck = new BigDecimal("-1.20");
//            toTest.addMoney(toCheck);
//
//        } catch (NullMoneyException ex) {
//            fail("Did not throw exception on negative money entered");
//        }
//    }
//
//    @Test
//    public void AddMoreMoney() {
//
//        BigDecimal toAdd = new BigDecimal("1.25");
//        BigDecimal toCheck = new BigDecimal("3.00");
//
//        try {
//
//            toAdd.add(toCheck);
//
//            assertEquals((new BigDecimal("3.00")), toTest.addMoney(toCheck));
//
//            BigDecimal addMore = new BigDecimal("1.00");
//
//            toTest.addMoney(addMore);
//
//            assertEquals((new BigDecimal("5.25")), toTest.addMoney(toAdd));
//
//        } catch (NullMoneyException ex) {
//            fail("NullMoneyEx thrown on add more money test");
//        }
//    }
//
//    @Test
//    public void testGetSnackListGoldenPath() {
//
//        try {
//
//            List<Snacks> toCheck = service.getSnackList();
//
//            Snacks toAdd = toCheck.get(0);
//            Snacks addSecond = toCheck.get(1);
//
//            assertEquals("Cheese Balls", toAdd.getName());
//            assertEquals(new BigDecimal("2.40"), toAdd.getPrice());
//            assertEquals(2, toAdd.getQty());
//
//            assertEquals("Apples", addSecond.getName());
//            assertEquals(new BigDecimal("3.00"), addSecond.getPrice());
//            assertEquals(0, addSecond.getQty());
//        } catch (VendingMachineDaoException ex) {
//
//            fail("Threw VendingMachineDaoException on GoldenPath");
//        }
//
//    }
//
//    @Test
//    public void getSnackListAlwaysFail() {
//
//        try {
//            alwaysFailServ.getSnackList();
//            fail("Threw VendingMachineDaoException on AlwaysFailTest");
//        } catch (VendingMachineDaoException ex) {
//
//        }
//    }
//
//}

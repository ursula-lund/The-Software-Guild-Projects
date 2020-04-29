/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine;

import com.mycompany.vendingmachine.Controller.VendingMachineController;
import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachineDaoException;
import com.mycompany.vendingmachine.dao.VendingMachineDaoFile;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.OutofStockException;
import com.mycompany.vendingmachine.service.VendingMachineService;
import com.mycompany.vendingmachine.ui.ConsoleIO;
import com.mycompany.vendingmachine.ui.UserIO;
import com.mycompany.vendingmachine.ui.VendingMachineView;
import java.io.FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ursul
 */
@SpringBootApplication
public class App implements CommandLineRunner {

//    public static void main(String[] args) throws VendingMachineDaoException, OutofStockException, InsufficientFundsException, FileNotFoundException  {
//
//        ApplicationContext ctx = 
//           new ClassPathXmlApplicationContext("applicationContext.xml");
//        VendingMachineController controller = 
//           ctx.getBean("controller", VendingMachineController.class);
//        controller.run();
//
//    }
    
    
    @Autowired
    VendingMachineController controller;

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        controller.run();

    }
}

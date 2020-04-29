/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.controller;

import com.mycompany.flooringorders.dao.OrderDaoException;
import com.mycompany.flooringorders.dao.ProductsDaoException;
import com.mycompany.flooringorders.dao.TaxesDaoException;
import com.mycompany.flooringorders.dto.Order;
import com.mycompany.flooringorders.service.FlooringService;
import com.mycompany.flooringorders.service.InvalidAreaException;
import com.mycompany.flooringorders.service.InvalidDateException;
import com.mycompany.flooringorders.service.InvalidNameException;
import com.mycompany.flooringorders.service.InvalidOrderNumException;
import com.mycompany.flooringorders.service.InvalidProductException;
import com.mycompany.flooringorders.service.InvalidStateException;
import com.mycompany.flooringorders.ui.FlooringView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author ursul
 */
public class FlooringController {

    FlooringService service;
    FlooringView view;

    public FlooringController(FlooringService service, FlooringView view) {
        this.view = view;
        this.service = service;
    }

    public void run() throws OrderDaoException, IOException {
        boolean running = true;
        while (running) {
            int menuSelect = view.printMainMenuSelect();
            try {
                switch (menuSelect) {
                    case 1:
                        viewOrderDate();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        exportAllData();
                        break;
                    case 6:
                        running = false;
                        break;
                }

            } catch (OrderDaoException | ProductsDaoException | TaxesDaoException | InvalidDateException
                    | InvalidNameException | InvalidAreaException | InvalidStateException | InvalidProductException 
                    | InvalidOrderNumException ex) {
                view.errorMessage(ex.getMessage());
            }

        }
        exitMessage();
    }

    private void viewOrderDate() throws OrderDaoException, FileNotFoundException {
        LocalDate enteredDate = view.enterDate();
        view.showOrderFields();
        view.showOrderFromDate(service.getOrderFromDate(enteredDate));

    }

    private void addOrder() throws  ProductsDaoException, TaxesDaoException, InvalidProductException,
    InvalidDateException, InvalidNameException, InvalidAreaException, InvalidStateException, OrderDaoException, 
    FileNotFoundException, IOException {
        view.showProductsFields();
        view.showProductsPricesList(service.getProductsList());
        Order toView = service.addOrder(view.getUserOrder());
        //service.placeOrder(toView);
        view.orderSum(toView);
        
    }

    private void editOrder() throws InvalidOrderNumException, OrderDaoException, InvalidDateException, 
    InvalidNameException, InvalidAreaException, InvalidStateException, InvalidProductException, TaxesDaoException,
    ProductsDaoException, FileNotFoundException {
        int order = view.getOrderNumber();
        LocalDate date = view.getDate();
        Order toEditView = service.getDateOrderToEdit(order, date);
        view.orderSum(toEditView);
        Order toEdit = view.getUserEdits(toEditView);
        Order toCheck = service.updateOrder(toEdit);
        //Order toPlace = view.placeOrder(toCheck);
                view.orderSum(toCheck);

        //service.finalizeEdit(toPlace);
    }
    

    private void removeOrder() throws OrderDaoException, InvalidOrderNumException, InvalidDateException, FileNotFoundException {
        int orderNum = view.getOrderNumDelete();
        LocalDate date = view.getDeleteDate();
        Order toEditView = service.getDateOrderToEdit(orderNum, date);
        view.orderSum(toEditView);
        Order toDelete = view.finalizeEdit(toEditView);
        service.finalizeDelete(toDelete);
    }

    private void exportAllData() throws OrderDaoException {
        service.exportAllData();
        view.displayExportAllSuccess();
    }

    private void exitMessage() {
        view.exitMessage();
    }

}

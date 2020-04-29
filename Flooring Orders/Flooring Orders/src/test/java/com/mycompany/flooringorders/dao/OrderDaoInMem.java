/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;

import com.mycompany.flooringorders.dao.OrderDao;
import com.mycompany.flooringorders.dao.OrderDaoException;
import com.mycompany.flooringorders.dto.Order;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ursul
 */
public class OrderDaoInMem implements OrderDao {

    List<Order> allOrder = new ArrayList<>();

    public OrderDaoInMem() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate testDate = LocalDate.parse("06/02/2013", formatter);

        Order toRead = new Order();
        toRead.setOrderDate(testDate);
        toRead.setOrderNumber(1);
        toRead.setCustomerName("Bill Perkins");
        toRead.setState("MN");
        toRead.setTaxRate(new BigDecimal("8.35"));
        toRead.setProductType("Wood");
        toRead.setArea(new BigDecimal("230"));
        toRead.setCostPerSquareFoot(new BigDecimal("4.25"));
        toRead.setLaborCostPerSquareFoot(new BigDecimal("3.25"));
        toRead.calcLaborCost();
        toRead.calcMatieralCost();
        toRead.calcTax();
        toRead.calcTotal();
       
        allOrder.add(toRead);

    }

 @Override
    public List<Order> retrieveOrdersByDate(LocalDate toRead) throws OrderDaoException {

        return allOrder
                .stream()
                .filter(toCheck -> toCheck.getOrderDate().equals(toRead)
                && toCheck.getOrderDate().equals(toRead)).collect(Collectors.toList());
                
    }
    

    @Override
    public Order addOrder(Order placeOrder) throws OrderDaoException {
        if (placeOrder == null) {
            throw new OrderDaoException("addOrder() recieved a null Order");
        }
        
      
        placeOrder.setOrderNumber(generateOrderNumber(placeOrder.getOrderDate())); //setting new OrderNumber
        allOrder.add(placeOrder);
        return placeOrder;
    }

    @Override
    public Order getOrder(int order, LocalDate date) throws OrderDaoException {
        if (date == null) {
            throw new OrderDaoException("Null date entered");
        }
        if (order < 0) {
            throw new OrderDaoException("Invalid order number entered");
        }
        return retrieveOrdersByDate(date)
                .stream().filter(toGet -> toGet.getOrderNumber()==order).findAny().orElse(null);
                
    }

    @Override
    public void editOrder(Order toPlace) throws OrderDaoException {
        
        int index = -1;
        for (int i = 0; i < allOrder.size(); i++) {
            Order edited = allOrder.get(i);
            if (edited.getOrderNumber() == toPlace.getOrderNumber() && edited.getOrderDate().equals(toPlace.getOrderDate())) {
                index = i;
                break;
            }
        }
        allOrder.set(index, toPlace);
        
  
    
    }

    @Override
    public void deleteOrder(Order toDelete) throws OrderDaoException {
        if (toDelete == null) {
            throw new OrderDaoException("Null order entered");
        }
    
        int index = -1;
        for (int i = 0; i < allOrder.size(); i++) {
            Order deleted = allOrder.get(i);
            if (deleted.getOrderNumber() == toDelete.getOrderNumber() && deleted.getOrderDate().equals(toDelete.getOrderDate())) {
                index = i;
                break;
            }
        }
        allOrder.remove(index);
        
    }

    @Override
    public void exportAllData() throws OrderDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    private int generateOrderNumber(LocalDate dateToRead) throws OrderDaoException {
        if (dateToRead == null) {
            throw new OrderDaoException("Null date entered");
        }
        List<Order> getNum = retrieveOrdersByDate(dateToRead);
        int index = -1;
        int max = 0;
        for (int i = 0; i < getNum.size(); i++) {
            Order toCheck = getNum.get(i);
            if (toCheck.getOrderNumber() > max) {
                max = toCheck.getOrderNumber();
            }
        }
        max++;
        return max;
    }


  
}

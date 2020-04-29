/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;

import com.mycompany.flooringorders.dto.Order;
import com.mycompany.flooringorders.service.InvalidStateException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ursul
 */
public interface OrderDao {


    public List<Order> retrieveOrdersByDate(LocalDate toRead) throws OrderDaoException;

    public Order addOrder(Order placeOrder) throws OrderDaoException;

    public Order getOrder(int order, LocalDate date) throws OrderDaoException;

    public void editOrder(Order toPlace) throws OrderDaoException;

    public void deleteOrder(Order toDelete) throws OrderDaoException;

    public void exportAllData() throws OrderDaoException;

    

   
    

    

    
  

   
    
   
    
}

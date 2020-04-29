/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;

import com.mycompany.flooringorders.dto.Order;
import java.io.FileNotFoundException;
import java.time.LocalDate;
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
public class OrderDaoAlwaysFail implements OrderDao {

    @Override
    public List<Order> retrieveOrdersByDate(LocalDate toRead) throws OrderDaoException {
        throw new OrderDaoException("Always fail dao.");
    }


    @Override
    public Order addOrder(Order placeOrder) throws OrderDaoException {
        throw new OrderDaoException("Always fail dao.");
    }

    @Override
    public Order getOrder(int order, LocalDate date) throws OrderDaoException {
        throw new OrderDaoException("Always fail dao.");
    }

    @Override
    public void editOrder(Order toPlace) throws OrderDaoException {
        throw new OrderDaoException("Always fail dao.");
    }

    @Override
    public void deleteOrder(Order toDelete) throws OrderDaoException {
        throw new OrderDaoException("Always fail dao.");
    }

    @Override
    public void exportAllData() throws OrderDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

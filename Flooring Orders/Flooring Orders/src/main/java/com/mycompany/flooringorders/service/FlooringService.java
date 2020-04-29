/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.service;

import com.mycompany.flooringorders.dao.OrderDaoException;
import java.util.List;
import com.mycompany.flooringorders.dao.OrderDao;
import com.mycompany.flooringorders.dao.ProductsDao;
import com.mycompany.flooringorders.dao.ProductsDaoException;
import com.mycompany.flooringorders.dao.TaxesDao;
import com.mycompany.flooringorders.dao.TaxesDaoException;
import com.mycompany.flooringorders.dto.Order;
import com.mycompany.flooringorders.dto.Products;
import com.mycompany.flooringorders.dto.Tax;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ursul
 */
public class FlooringService {

    OrderDao oDao;
    ProductsDao pDao;
    TaxesDao tDao;

    public FlooringService(OrderDao oDao, ProductsDao pDao, TaxesDao tDao) {
        this.oDao = oDao;
        this.pDao = pDao;
        this.tDao = tDao;
    }

    public List<Order> getOrderFromDate(LocalDate viewDate) throws OrderDaoException {
        List<Order> toReturn = oDao.retrieveOrdersByDate(viewDate);
        return toReturn;
    }

    public List<Products> getProductsList() throws ProductsDaoException {
        List<Products> productsView = pDao.getProductsAndPrices();
        return productsView;

    }

    public Order addOrder( Order toAdd ) throws InvalidProductException, InvalidDateException, InvalidNameException, InvalidAreaException, InvalidStateException, TaxesDaoException, OrderDaoException, ProductsDaoException, OrderDaoException{
    
        toAdd = newUserOrder(toAdd);
        return placeOrder( toAdd );
    
    }
    
    private Order newUserOrder(Order toComplete) throws InvalidProductException, InvalidDateException, InvalidNameException, InvalidAreaException, InvalidStateException, TaxesDaoException, OrderDaoException, ProductsDaoException {
        if (toComplete.getOrderDate() == null) {
           throw new InvalidDateException("Null Date entered");
        }
        LocalDate today = LocalDate.now();
        if (toComplete.getOrderDate().isBefore(today)) {
            throw new InvalidDateException("Order Date must be scheduled for future");
        }
        if (toComplete.getCustomerName() == null) {
            throw new InvalidNameException("Null Name entered");
        }
      
        if (toComplete.getArea() == null) {
            throw new InvalidAreaException("Null entered for area: Please enter Area minimum 100 sq ft");
        }
        if (toComplete.getArea().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAreaException("Negative number entered for Area");
        }

        if (toComplete.getArea().compareTo(new BigDecimal("100")) < 0) {
            throw new InvalidAreaException("Area must be minimum 100 sq ft.");
        }
        if (toComplete.getState() == null) {
            throw new InvalidStateException("Null State entered.");
        }
        if (toComplete.getState().length() > 2) {
            throw new InvalidStateException("Please enter 2-Letter State Abbreviation.");
        }
        if (toComplete.getProductType() == null) {
            throw new InvalidProductException("Null product entered");
        }
        Tax getTax = tDao.getTaxRateByState(toComplete.getState());
        BigDecimal tax = getTax.getTaxRate();
        toComplete.setTaxRate(tax);
        toComplete.setOrderDate(toComplete.getOrderDate());
        Products getCosts = pDao.getCostSqFt(toComplete.getProductType());
        BigDecimal cost = getCosts.getCostPerSqaureFoot();
        toComplete.setCostPerSquareFoot(cost);
        BigDecimal laborCost = getCosts.getLaborCostPerSquareFoot();
        toComplete.setLaborCostPerSquareFoot(laborCost);
        toComplete.setCustomerName(toComplete.getCustomerName());
        toComplete.setProductType(toComplete.getProductType());
        toComplete.setArea(toComplete.getArea());
        return toComplete;

    }

    private Order placeOrder(Order placeOrder) throws OrderDaoException {

        oDao.addOrder(placeOrder);
        return placeOrder;
    }

    public Order getDateOrderToEdit(int orderNum, LocalDate date) throws OrderDaoException,  InvalidOrderNumException, InvalidDateException {
        
        if (orderNum <= 0) {
            throw new InvalidOrderNumException("Order Number cannot be zero or negative");
        }
        if (date == null) {
            throw new InvalidDateException("No Order for date");
        }

        Order toEditView = oDao.getOrder(orderNum, date);
        return toEditView;

    }

    public Order updateOrder(Order toEdit) throws InvalidNameException, InvalidAreaException, InvalidStateException,
            InvalidProductException, TaxesDaoException, ProductsDaoException, OrderDaoException{
        Order edited = calculateOrderToEdit( toEdit );
        finalizeEdit( toEdit );
        
        return edited;
    }
    
    private Order calculateOrderToEdit(Order toEdit) throws InvalidNameException, InvalidAreaException, InvalidStateException,
            InvalidProductException, TaxesDaoException, ProductsDaoException {
        
         
        
        if (toEdit.getCustomerName() == null) {
            throw new InvalidNameException("Null Name entered");
        }
        if (toEdit.getCustomerName().length() > 10) {
            throw new InvalidNameException("Name must be lower than 10 characters");
        }
        if (toEdit.getArea() == null) {
            throw new InvalidAreaException("Null entered for area: Please enter Area minimum 100 sq ft");
        }
        if (toEdit.getArea().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAreaException("Negative number entered for Area");
        }

        if (toEdit.getArea().compareTo(new BigDecimal("100")) < 0) {
            throw new InvalidAreaException("Area must be minimum 100 sq ft.");
        }
        if (toEdit.getState() == null) {
            throw new InvalidStateException("Null State entered.");
        }
        if (toEdit.getState().length() > 2) {
            throw new InvalidStateException("Please enter 2-Letter State Abbreviation.");
        }
        if (toEdit.getProductType() == null) {
            throw new InvalidProductException("Null product entered");
        }
        Tax getTax = tDao.getTaxRateByState(toEdit.getState());
        BigDecimal tax = getTax.getTaxRate();
        toEdit.setTaxRate(tax);
        toEdit.setOrderDate(toEdit.getOrderDate());
        Products getCosts = pDao.getCostSqFt(toEdit.getProductType());
        BigDecimal cost = getCosts.getCostPerSqaureFoot();
        toEdit.setCostPerSquareFoot(cost);
        BigDecimal laborCost = getCosts.getLaborCostPerSquareFoot();
        toEdit.setLaborCostPerSquareFoot(laborCost);
        toEdit.setCustomerName(toEdit.getCustomerName());
        toEdit.setProductType(toEdit.getProductType());
        toEdit.setArea(toEdit.getArea());
        return toEdit;
        
    }

    private void finalizeEdit(Order toPlace) throws OrderDaoException {
       oDao.editOrder(toPlace);

    }

    public void finalizeDelete(Order toDelete) throws OrderDaoException {
        oDao.deleteOrder(toDelete);
    }

    public void exportAllData() throws OrderDaoException {
        oDao.exportAllData();
    }

   

}

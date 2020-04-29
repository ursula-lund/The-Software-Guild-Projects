/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dao;

import com.mycompany.flooringorders.dto.Order;
import com.mycompany.flooringorders.dto.Tax;
import com.mycompany.flooringorders.service.InvalidStateException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ursul
 */
public class OrderDaoFile implements OrderDao {

    String directory;

    public OrderDaoFile(String dir) {
        this.directory = dir;
    }

    private String dateToPath(LocalDate toReadDate) {
        LocalDate toPath = toReadDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String toRead = directory + "/Orders_" + toPath.format(formatter) + ".txt";
        return toRead;
    }

    @Override
    public List<Order> retrieveOrdersByDate(LocalDate dateToRead) throws OrderDaoException {
        if (dateToRead == null) {
            throw new OrderDaoException("Null date entered");
        }
        String toReadFile = dateToPath(dateToRead);
        File orderFile = new File(toReadFile);
        List<Order> userOrder = new ArrayList<>();
        try (Scanner reader = new Scanner(new BufferedReader(new FileReader(toReadFile)))) {
            reader.nextLine();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.isBlank()) {
                    Order toAdd = parseOrders(line);
                    toAdd.setOrderDate(dateToRead);

                    userOrder.add(toAdd);
                }
            }
        } catch (FileNotFoundException ex) {
        }
        return userOrder;
    }

    private Order parseOrders(String line) {

        Order parsed = new Order();
        String[] cells = line.split(",");
        parsed.setOrderNumber(Integer.parseInt(cells[0]));
        parsed.setCustomerName(cells[1]);
        parsed.setState(cells[2]);
        parsed.setTaxRate(new BigDecimal(cells[3]));
        parsed.setProductType(cells[4]);
        parsed.setArea(new BigDecimal(cells[5]));
        parsed.setCostPerSquareFoot(new BigDecimal(cells[6]));
        parsed.setLaborCostPerSquareFoot(new BigDecimal(cells[7]));
//        parsed.setMatieralCost(new BigDecimal(cells[7]));
//        parsed.setLaborCost(new BigDecimal(cells[8]));
//        parsed.setTax(new BigDecimal(cells[9]));
//        parsed.setTotal(new BigDecimal(cells[10]));

        return parsed;
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

    private void writeFile(List<Order> allOrders, LocalDate date) throws OrderDaoException {
        PrintWriter writer = null;
        String path = dateToPath(date);
        try {
            writer = new PrintWriter(new FileWriter(path));
            writer.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,"
                    + "CostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
            for (Order toWrite : allOrders) {
                String line = convertToLine(toWrite);
                writer.println(line);
            }
            writer.close();
        } catch (IOException ex) {
            throw new OrderDaoException("Could not open file for writing: " + path);
        }
    }

    private String convertToLine(Order toWrite) {
        String line
                = toWrite.getOrderNumber() + ","
                + toWrite.getCustomerName() + ","
                + toWrite.getState() + ","
                + toWrite.getTaxRate() + ","
                + toWrite.getProductType() + ","
                + toWrite.getArea() + ","
                + toWrite.getCostPerSquareFoot() + ","
                + toWrite.getLaborCostPerSquareFoot() + ","
                + toWrite.calcMatieralCost()+ ","
                + toWrite.calcLaborCost()+ ","
                + toWrite.calcTax() + ","
                + toWrite.calcTotal();
        return line;
    }

    @Override
    public Order addOrder(Order placeOrder) throws OrderDaoException {
        if (placeOrder == null) {
            throw new OrderDaoException("addOrder() recieved a null Order");
        }
        File newOrder = new File(dateToPath(placeOrder.getOrderDate()));
        List<Order> allOrders = retrieveOrdersByDate(placeOrder.getOrderDate());
        placeOrder.setOrderNumber(generateOrderNumber(placeOrder.getOrderDate())); //se;tting new OrderNumber
        
        allOrders.add(placeOrder);
        writeFile(allOrders, placeOrder.getOrderDate());
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
                .stream()
                .filter(toCheck -> toCheck.getOrderNumber() == (order)
                && toCheck.getOrderDate() == (date))
                .findAny()
                .orElse(null);
    }

    @Override
    public void editOrder(Order toPlace) throws OrderDaoException {
        List<Order> toEdit = retrieveOrdersByDate(toPlace.getOrderDate());
        int index = -1;
        for (int i = 0; i < toEdit.size(); i++) {
            Order edited = toEdit.get(i);
            if (edited.getOrderNumber() == (toPlace.getOrderNumber())) {
                index = i;
                break;
            }
        }
        toEdit.set(index, toPlace);
        writeFile(toEdit, toPlace.getOrderDate());
        
    }

    @Override
    public void deleteOrder(Order toDelete) throws OrderDaoException {
        if (toDelete == null) {
            throw new OrderDaoException("Null order entered");
        }
        List<Order> deleteOrder = retrieveOrdersByDate(toDelete.getOrderDate());
        int index = -1;
        for (int i = 0; i < deleteOrder.size(); i++) {
            Order deleted = deleteOrder.get(i);
            if (deleted.getOrderNumber() == (toDelete.getOrderNumber())) {
                index = i;
                break;
            }
        }
        deleteOrder.remove(index);
        writeFile(deleteOrder, toDelete.getOrderDate());
        
    }

    @Override
    public void exportAllData() throws OrderDaoException {
        //1. Build a Master list of all orders.
        //1a. get list of all files in order directory
        //1b. Loop through list of all files, get a list of orders from each file.
        //1c. add the list of files to the master list.
        //2. Loop through list and write out each one.

        List<Order> allOrdersMaster = new ArrayList<>();
        File[] allFiles = getAllOrderFiles();
        for (int i = 0; i < allFiles.length; i++) {
            File orderFile = allFiles[i];
            LocalDate orderDate = convertFileNametoDate(orderFile.getName());
            List<Order> allOrderbyDate = retrieveOrdersByDate(orderDate);
            allOrdersMaster.addAll(allOrderbyDate);
        }
        writeExportFile(allOrdersMaster);
    }

    private File[] getAllOrderFiles() {
        File dir = new File(directory);
        return dir.listFiles();
    }

    private LocalDate convertFileNametoDate(String name) {
        String dateString = name.substring(7, 15);
        //"Orders_MMddyyyy.txt"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        return LocalDate.parse(dateString, formatter);
    }

    private void writeExportFile(List<Order> allOrders) throws OrderDaoException {
        String path = Paths.get("Folders", "Backup", "DataExport.txt").toString();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(path));
            writer.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,"
                    + "CostPerSquareFoot,MaterialCost,LaborCost,Tax,Total,Date");
            for (Order toWrite : allOrders) {
                String line = convertToLine(toWrite) + "," + toWrite.getOrderDate();
                writer.println(line);
            }
            writer.close();
        } catch (IOException ex) {
            throw new OrderDaoException("Could not open file for writing: " + path);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.ui;

import com.mycompany.flooringorders.dto.Order;
import com.mycompany.flooringorders.dto.Products;
import com.mycompany.flooringorders.ui.ConsoleIo;
import com.mycompany.flooringorders.ui.UserIo;
import com.mycompany.flooringorders.ui.UserIo;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ofPattern;
import java.util.List;

/**
 *
 * @author ursul
 */
public class FlooringView {

    UserIo io = new ConsoleIo();

    public FlooringView(UserIo io) {
        this.io = io;
    }

    public int printMainMenuSelect() {

        io.print("1. View Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");

        return io.readInt("Please pick menu selection: ", 1, 6);

    }

    public LocalDate enterDate() {
        LocalDate enteredDate = io.readLocalDate("Please enter date to view order(MM/DD/YYYY): ");
        return enteredDate;
    }

    public void showOrderFromDate(List<Order> orderFromDate) {
        orderFromDate.stream()
                .forEach(order -> System.out.println(order.getOrderNumber() + " : " + order.getCustomerName()
                + " : " + order.getState() + " : " + order.getTaxRate() + " : " + order.getProductType()
                + " : " + order.getArea() + " : " + order.getCostPerSquareFoot() + " : " + order.calcLaborCost()
                + " : " + order.calcMatieralCost() + " : " + order.calcLaborCost() + " : " + order.calcTax()
                + " : " + order.calcTotal()));
    }

    public void showProductsPricesList(List<Products> toView) {

        toView.stream()
                .forEach(product -> System.out.println(product.getProductType() + " : " + product.getCostPerSqaureFoot()
                + " ; " + product.getLaborCostPerSquareFoot()));
    }

    public Order getUserOrder() {
        Order userOrder = new Order();
        userOrder.setOrderDate(io.readLocalDate("Please enter new date for order(MM/dd/yyyy):  "));
        userOrder.setCustomerName(io.readString("Please enter new Customer Name: "));
        userOrder.setProductType(io.readString("Please select new product: "));
        userOrder.setState(io.readString("Please enter New State"));
        userOrder.setArea(io.readBigDecimal("Please enter new area"));
        return userOrder;
    }

    public void errorMessage(String message) {
        io.print("ERROR: " + message);
    }

    public void exitMessage() {
        io.print("Good bye!");
    }

    public void showOrderFields() {
        io.print("Order Number : Customer Name : State : TaxRate : Product Type : Area : CostPerSquareFoot :"
                + " LaborCostPerSquareFoot : MaterialCost : LaborCost : Tax : Total ");
    }

    public void showProductsFields() {
        io.print("ProductType : Cost perSqFt : LaborCost per SqFt");
    }

    public void orderSum(Order toView) {
        io.print("Your Order:  Customer Name: " + toView.getCustomerName() + " State: " + toView.getState()
                + " TaxRate: " + toView.getTaxRate() + " ProductType: " + toView.getProductType() + " Area: "
                + toView.getArea() + " CostPerSquareFoot: " + toView.getCostPerSquareFoot() + " LaborCostPerSquareFoot: "
                + toView.getLaborCostPerSquareFoot() + " MatieralCost: " + toView.calcMatieralCost() + " LaborCost: " + toView.calcLaborCost()
                + " Tax: " + toView.calcTax() + " Total: " + toView.calcTotal());

    }


    

//    public Order placeOrder(Order toView) {
//
//        String yesNo = io.readString("Place new Order? (y/n):");
//        if (yesNo.equalsIgnoreCase("n")) {
//            io.print("Order not placed");
//        } else if (yesNo.equalsIgnoreCase("y")) {
//            io.print("Order placed.");
//            return toView;
//        }
//        return null;
//    }

    public int getOrderNumber() {
        int orderNum = io.readInt("Please enter Order Number to Edit");
        return orderNum;
    }
    
    public int getOrderNumDelete(){
        int orderNum = io.readInt("Please ener Order Number to Delete.");
        return orderNum;
    }

    public LocalDate getDate() {
        LocalDate date = io.readLocalDate("Please enter Order Date to Edit");
        return date;
    }
     public LocalDate getDeleteDate() {
        LocalDate date = io.readLocalDate("Please enter Order Date to Delete");
        return date;
     }


    public Order getUserEdits(Order toEdit) {
        String customerName = io.editString("Edit Customer name (or press enter to skip): ", toEdit.getCustomerName());
        String state = io.editString("Please edit state (or enter to skip): ", toEdit.getState());
        String productType = io.editString("Please edit Product type (or enter to skip): ", toEdit.getProductType());
        BigDecimal area = io.editBigDecimal("Please edit area (or enter to skip): ", toEdit.getArea());

        toEdit.setCustomerName(customerName);
        toEdit.setState(state);
        toEdit.setProductType(productType);
        toEdit.setArea(area);
        

        
        return toEdit;

    }

    public Order finalizeEdit(Order toDelete) {
        String yesNo = io.readString("Are you sure you want to Delete this Order? (Y/N): ");
        if (yesNo.equalsIgnoreCase("n")) {
            io.print("Order not Deleted");
        } else if (yesNo.equalsIgnoreCase("y")) {
            io.print("Order Deleted.");
            return toDelete;
        }
        return null;
    }

    public void displayExportAllSuccess() {
        io.print("Data export success");
    }

   
}

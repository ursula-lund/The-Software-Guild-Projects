/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author ursul
 */
public class Order {
    
    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;
    private LocalDate orderDate;

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }
    

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    public BigDecimal calcMatieralCost() {
        
        return costPerSquareFoot.multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

//    public void setMatieralCost(BigDecimal matieralCost) {
//        this.matieralCost = matieralCost;
//    }

    public BigDecimal calcLaborCost() {
        return laborCostPerSquareFoot.multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

//    public void setLaborCost(BigDecimal laborCost) {
//        this.laborCost = laborCost;
//    }

    public BigDecimal calcTax() {
        return calcMatieralCost().add(calcLaborCost()).multiply(taxRate)
                .divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
    }

//    public void setTax(BigDecimal tax) {
//        this.tax = tax;
//    }

    public BigDecimal calcTotal() {
        return calcTax().add(calcLaborCost().add(calcMatieralCost())).setScale(2, RoundingMode.HALF_UP);
    }

//    public void setTotal(BigDecimal total) {
//        this.total = total;
//    }

  
    
}

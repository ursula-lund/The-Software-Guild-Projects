/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.dto;

import java.math.BigDecimal;

/**
 *
 * @author ursul
 */
//INSTRUCTORS NOTE: may want singularize this class name
public class Products {
    
    private String productType;
    private BigDecimal costPerSqaureFoot;
    private BigDecimal laborCostPerSquareFoot;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSqaureFoot() {
        return costPerSqaureFoot;
    }

    public void setCostPerSqaureFoot(BigDecimal costPerSqaureFoot) {
        this.costPerSqaureFoot = costPerSqaureFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        this.laborCostPerSquareFoot = laborCostPerSquareFoot;
    }
    
    
}

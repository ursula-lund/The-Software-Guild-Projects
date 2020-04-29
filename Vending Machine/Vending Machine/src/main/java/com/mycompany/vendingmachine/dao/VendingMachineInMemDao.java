/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Snacks;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.OutofStockException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ursul
 */
public class VendingMachineInMemDao implements VendingMachineDao {

    List<Snacks> allSnacks = new ArrayList<>();

    public VendingMachineInMemDao() {
        
        Snacks toAdd = new Snacks();

        toAdd.setName("Cheese Balls");
        toAdd.setPrice(new BigDecimal("2.40"));
        toAdd.setQty(2);
       
        
        allSnacks.add(toAdd);
        
        Snacks noStock = new Snacks();
        
        noStock.setName("Apples");
        noStock.setPrice(new BigDecimal("3.00"));
        noStock.setQty(0);
        
        allSnacks.add(noStock);
        
        
        

    }

    @Override
    public List<Snacks> readSnacks() throws VendingMachineDaoException {

        return allSnacks;
    }

    @Override
    public List<Snacks> decreaseQty(Snacks editSnacks) throws VendingMachineDaoException, OutofStockException {

        if (editSnacks == null) {

            throw new VendingMachineDaoException("Got null Snack for editAllSnacks()");

        }

        int index = -1;

        for (int i = 0; i < allSnacks.size(); i++) {
            Snacks toEdit = allSnacks.get(i);

            if (toEdit.getQty() == editSnacks.getQty()) {
                index = i;
                break;
            }
        }
        
        
         if (editSnacks.getQty() == 0) {
            throw new OutofStockException("Snack out of Stock");
        }

        
        int newQty = editSnacks.getQty() - 1;
        editSnacks.setQty(newQty);


        allSnacks.set(index, editSnacks);
        return allSnacks;
    }

    @Override
    public Snacks getSnackByName(String itemName) throws VendingMachineDaoException {

        return readSnacks()
                .stream()
                .filter(findName -> findName.getName().equals(itemName))
                .findAny()
                .orElse(null);
        
    }

 

}

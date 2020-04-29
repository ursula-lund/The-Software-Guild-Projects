/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import java.util.Scanner;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Snacks;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.OutofStockException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author ursul
 */
public interface VendingMachineDao {    
    
    public List<Snacks> decreaseQty(Snacks editSnacks) throws VendingMachineDaoException, OutofStockException;
    
    public Snacks getSnackByName(String itemName) throws VendingMachineDaoException;

    public List<Snacks> readSnacks() throws VendingMachineDaoException;
    

 



}

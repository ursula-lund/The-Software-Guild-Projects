/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.service;

import com.mycompany.vendingmachine.dao.VendingMachineDao;
import com.mycompany.vendingmachine.dao.VendingMachineDaoException;
import com.mycompany.vendingmachine.ui.VendingMachineView;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Snacks;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ursul
 */
@Service
public class VendingMachineService {
    
    @Autowired
    VendingMachineDao dao;

    BigDecimal userTotal = BigDecimal.ZERO;
    

    public Change vendItemChange(String itemName) throws OutofStockException, InsufficientFundsException, VendingMachineDaoException, NullNameException {

        
        if (itemName == null) {
            throw new NullNameException("No such snack)");
        }
        
        Snacks toVend = dao.getSnackByName(itemName);
        
       
        if ( userTotal.compareTo(toVend.getPrice()) <0 ) {
            throw new InsufficientFundsException("Insufficient Funds");
        }
        
         if (toVend.getQty() == 0 ) {
             throw new OutofStockException("Snack out of Stock");    
         }
         
         
         
         
        BigDecimal toChange = userTotal.subtract(toVend.getPrice());
        Change toReturn = computeChange(toChange);
        returnChange();
        
        decreaseQty(itemName);

        return toReturn;

    }

    private Snacks decreaseQty(String itemName) throws OutofStockException, VendingMachineDaoException {
        Snacks toDecrease = dao.getSnackByName(itemName);
        dao.decreaseQty(toDecrease);
        return toDecrease;
    }

    private Change computeChange(BigDecimal intoChange) {

        Change leftOver = new Change();

        int pennies = intoChange.multiply(new BigDecimal("100")).intValue();

        int quarters = pennies / 25;
        pennies -= 25 * quarters;
        int dimes = pennies / 10;
        pennies -= 10 * dimes;
        int nickels = pennies / 5;
        pennies -= 5 * nickels;

        leftOver.setQuarters(quarters);
        leftOver.setDimes(dimes);
        leftOver.setNickels(nickels);
        leftOver.setPennies(pennies);

        return leftOver;

    }

    public Change returnChange() {

        Change toChange = computeChange(userTotal);
        userTotal = BigDecimal.ZERO;
        return toChange;

    }

    public List<Snacks> getSnackList() throws VendingMachineDaoException {
        List<Snacks> toView = dao.readSnacks();
        return toView;
    }

    public BigDecimal addMoney(BigDecimal toAdd) throws NullMoneyException {
        if (toAdd == null) {
            throw new NullMoneyException("Null money added");
        }
        userTotal = toAdd.add(userTotal);
        return userTotal;
      

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.Controller;

import com.mycompany.vendingmachine.dao.VendingMachineDaoException;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.service.InsufficientFundsException;
import com.mycompany.vendingmachine.service.NullMoneyException;
import com.mycompany.vendingmachine.service.NullNameException;
import com.mycompany.vendingmachine.service.OutofStockException;
import com.mycompany.vendingmachine.service.VendingMachineService;
import com.mycompany.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author ursul
 */
@Controller
public class VendingMachineController {

    @Autowired 
    VendingMachineService service;
    
    @Autowired
    VendingMachineView view;


    public void run() throws VendingMachineDaoException, OutofStockException, InsufficientFundsException {
        boolean vending = true;

        view.showItemstoVend(service.getSnackList());
        view.welcomeEnterMoneyMessage();

        while (vending) {
            int menuSelect = view.printMainMenuSelect();
            try {
                switch (menuSelect) {
                    case 1:
                        enterFunds();
                        break;
                    case 2:
                        vendItem();
                        break;
                    case 3:
                        returnFunds();
                        break;
                    case 4:
                        vending = false;
                        break;
                }
            } catch (VendingMachineDaoException | NullMoneyException |
                 InsufficientFundsException | OutofStockException | NullNameException ex ) {
                view.displayErrorMessage(ex.getMessage());
            } 

        }
        exitMessage();
    }

    private BigDecimal enterFunds() throws VendingMachineDaoException, NullMoneyException {
        BigDecimal userFunds = new BigDecimal(view.getUserMoney());
        BigDecimal totalFunds = service.addMoney(userFunds);
        view.showCurrentFunds(totalFunds);
        return userFunds;
    }

    private void vendItem() throws VendingMachineDaoException, OutofStockException, InsufficientFundsException, NullNameException {
        String itemVended = view.getItemVended();
       Change toPrint = service.vendItemChange(itemVended);
        view.printChange(toPrint);
    }

    private void returnFunds() throws VendingMachineDaoException {
        Change toPrint = service.returnChange();
        view.printChange(toPrint);
    }

    private void exitMessage() {
        view.goodBye();
    }
}

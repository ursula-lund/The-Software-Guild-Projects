/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.ui;


import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.Snacks;
import com.mycompany.vendingmachine.ui.UserIO;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *
 * @author ursul
 */
@Component
public class VendingMachineView {
    @Autowired
    UserIO io;
        
     public void welcomeEnterMoneyMessage() {
        
        io.print("==== Greetings. Please enter funds. ====");
    }
    
    public String getUserMoney() {
 
        String userMoney = io.readString("Please enter Funds$$$ :  ");
        return userMoney;
        
    }
    
    public String showCurrentTotal() {
        String showMoney = getUserMoney();
        return showMoney;
    }
    
    public void showCurrentFunds(BigDecimal userFunds) {
        io.print("");
        io.print("Current total is: " + userFunds + "$");
    }
 

    public int printMainMenuSelect() {
        io.print("");
        io.print("1. Enter Funds");
        io.print("2. Select Snack");
        io.print("3. $$$ Return");
        io.print("4. Exit");
      
        return io.readInt("Please enter choice: ", 1, 4);
    }

     public String getItemVended() {
        io.print("");
        io.print("Please pick item to Vend: ");
        Scanner sc = new Scanner (System.in);
        String itemVended = sc.nextLine();
        return itemVended;
    }
     
    public void printChange(Change toPrint) {
        io.print("Your change is: " + toPrint.getQuarters() + " Quarters " + toPrint.getDimes()
                + " Dimes " + toPrint.getNickels() + " Nickels " + toPrint.getPennies() + " Pennies");
    }

  
    public void showItemstoVend(List<Snacks> allSnacks) {
       
       allSnacks.stream()
              .forEach( s -> System.out.println( s.getName() + " : " + s.getPrice() + " : " + s.getQty()));

    }
    

    public void goodBye() {
        io.print("=== Good Bye!! ===");
    }

    public void displayErrorMessage(String message) {
        io.print( "ERROR: " + message );
    }

  

   
        
                
    }
   

    
    
   
  

    
    


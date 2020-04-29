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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ursul
 */
public class VendingMachineDaoFile implements VendingMachineDao {
    String path = "vendingmachine.txt";
    
    public VendingMachineDaoFile() {       
   }
   
    public VendingMachineDaoFile(String path) {
        this.path = path;
    }

    @Override
    public List<Snacks> readSnacks() throws VendingMachineDaoException {
        List<Snacks> allSnacks = new ArrayList<>();         
        try {
           Scanner reader = new Scanner(new BufferedReader(new FileReader(path)));
            reader.nextLine();
            while (reader.hasNextLine()) {
               String line = reader.nextLine();          
                if (!line.isBlank()) {               
                Snacks parsed = parseSnack(line);                            
               allSnacks.add(parsed);             
                }
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            throw new VendingMachineDaoException("Could not open file: " + path, ex);
        }
        return allSnacks;
    }

    public Snacks parseSnack(String line) {      
        String[] cells = line.split(",");
        Snacks parsed = new Snacks();
        parsed.setName(cells[0]);
        parsed.setPrice(new BigDecimal(cells[1]));
        parsed.setQty(Integer.parseInt(cells[2]));
        return parsed;
    }

    @Override
    public List<Snacks> decreaseQty(Snacks editSnacks) throws VendingMachineDaoException, OutofStockException {

        if (editSnacks == null) {
            throw new VendingMachineDaoException("Got null Snack for editAllSnacks()");
        }
        List<Snacks> allSnacks = readSnacks();
        int index = -1;
        for (int i = 0; i < allSnacks.size(); i++) {
            Snacks toEdit = allSnacks.get(i);
            if (toEdit.getName().equals(editSnacks.getName())) {
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
        writeFile(allSnacks);
          return allSnacks;

    }
   


    @Override
    public Snacks getSnackByName(String itemName) throws VendingMachineDaoException {
     return readSnacks()
                .stream()
                .filter(findName -> (findName.getName() == null ? itemName == null : findName.getName().equals(itemName)))
                .findAny()
                .orElse(null);
    }

    private void writeFile(List<Snacks> allSnacks) throws VendingMachineDaoException {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(path));
            writer.println("name,price,qty");
            for (Snacks toWrite : allSnacks) {
                String line = convertToLine(toWrite);
                writer.println(line);
            }
            writer.close();
        } catch (IOException ex) {
            throw new VendingMachineDaoException("Could not open file for writing: " + path);
        }
    }

    private String convertToLine(Snacks toWrite) {

        String line
                = toWrite.getName() + ","
                + toWrite.getPrice() + ","
                + toWrite.getQty();

        return line;
    }

  

    

}

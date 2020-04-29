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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ursul
 */
public class TaxesDaoFile implements TaxesDao {

    String path = "Data/Taxes.txt";

    public TaxesDaoFile(String path) {
        this.path = path;
    }

    @Override
    public List<Tax> readTaxesFile() throws TaxesDaoException  {
        try {
            List<Tax> taxesFiles = new ArrayList<>();
            
            Scanner reader;
            reader = new Scanner(new BufferedReader(new FileReader(path)));
            reader.nextLine();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.isBlank()) {
                    Tax parsed = parseTaxes(line);
                    taxesFiles.add(parsed);
                }
                
            }
            reader.close();
            return taxesFiles;
        } catch (FileNotFoundException ex) {
            throw new TaxesDaoException("Could not load file: " + path);
        }
    }

    private Tax parseTaxes(String line) {

        Tax parsed = new Tax();
        String[] cells = line.split(",");
        parsed.setStateAbbreviation(cells[0]);
        parsed.setStateName(cells[1]);
        parsed.setTaxRate(new BigDecimal(cells[2]));
        return parsed;
    }

    @Override

    public Tax getTaxRateByState(String stateAbbreviation) throws InvalidStateException, TaxesDaoException {
        return readTaxesFile()
                .stream()
                .filter(toCheck -> toCheck.getStateAbbreviation().equalsIgnoreCase(stateAbbreviation))
                .findAny()
                .orElse(null);

    }
}

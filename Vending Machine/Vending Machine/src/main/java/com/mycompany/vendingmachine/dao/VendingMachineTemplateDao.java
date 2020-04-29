/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.Snacks;
import com.mycompany.vendingmachine.service.OutofStockException;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ursul
 */
@Repository
public class VendingMachineTemplateDao implements VendingMachineDao {

    @Autowired
    JdbcTemplate template;


    @Override
    public List<Snacks> decreaseQty(Snacks name) throws VendingMachineDaoException, OutofStockException {
            int rowsAffected = template.update("Update Snacks SET Qty = ?"
                    name.getQty();
                    

        if (rowsAffected == 0) {
            throw new VenindgMachineDaoException("Could not find name: " + name.getId());
        } else if (rowsAffected > 1) {
            throw new NounDaoException("The sky is falling.  All is lost.");
        }
    
        
    }

    @Override
    public Snacks getSnackByName(String name) throws VendingMachineDaoException {
        Snacks toReturn = template.queryForObject("SELECT * FROM Snacks WHERE Name = " + name, new SnacksMapper());
        return toReturn;
    }

    @Override
    public List<Snacks> readSnacks() throws VendingMachineDaoException {

        List<Snacks> allSnacks = template.query("SELECT * FROM Snacks", new SnacksMapper());

        return allSnacks;
    }

    private static final class SnacksMapper implements RowMapper<Snacks> {
        @Override
        public Snacks mapRow(ResultSet row, int rowNum) throws SQLException {

            Snacks converted = new Snacks();

            converted.setName(row.getString("Name"));
            converted.setPrice(row.getBigDecimal("Price"));
            converted.setQty(row.getInt("Qty"));

            return converted;

        }

    }

}

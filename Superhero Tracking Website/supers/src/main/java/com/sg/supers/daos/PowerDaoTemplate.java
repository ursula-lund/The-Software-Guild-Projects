/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.daos;

import com.sg.supers.DTOs.Power;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ursul
 */
@Repository
public class PowerDaoTemplate implements PowerDao {

    @Autowired
    JdbcTemplate template;

    @Override
    @Transactional
    public Power addPower(Power toAdd) {
        KeyHolder kh = new GeneratedKeyHolder();
        int rowsAffected = template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Powers(PowerDesc, PowerName, isDeleted) VALUES (?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setString(1, toAdd.getPowerDesc());
                    ps.setString(2, toAdd.getPowerName());
                    ps.setBoolean(3, toAdd.getIsDeleted());

                    return ps;
                },
                kh);
        int generatedId = kh.getKey().intValue();

        toAdd.setPowerId(generatedId);
        return toAdd;
    }

    @Override
    @Transactional
    public void updatePower(Power updated) {
        template.update("UPDATE Powers SET PowerName = ?, PowerDesc = ?, isDeleted = ? WHERE PowerId = ?",
                    updated.getPowerName(), updated.getPowerDesc(), updated.getIsDeleted(), updated.getPowerId());
    }
    

    @Override
    public void deletePower(int powerId) {
        template.update("UPDATE CurrentPowers SET isDeleted = 1 WHERE PowerId = ?", powerId);
    }

    @Override
    public List<Power> getAllPowers() {
        try {
            List<Power> allPowers = template.query("SELECT * FROM CurrentPowers;", new PowerMapper());
            return allPowers;
        } catch (DataAccessException ex) {
            return null;
        }

    }
    
   @Override
    public Power getPowerById(int powerId) {
       try {
           Power toReturn = template.queryForObject("SELECT * FROM CurrentPowers WHERE PowerId = ?", new PowerMapper(), powerId);
           return toReturn;
           } catch (DataAccessException ex) {
            return null;
        }
    }

    private static class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet row, int rowNum) throws SQLException {

            Power converted = new Power();

            converted.setPowerId(row.getInt("PowerId"));
            converted.setPowerDesc(row.getString("PowerDesc"));
            converted.setPowerName(row.getString("PowerName"));
            converted.setIsDeleted(row.getBoolean("isDeleted"));

            return converted;
        }
    }
}

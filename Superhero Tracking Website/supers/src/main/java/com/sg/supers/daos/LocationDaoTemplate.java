/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.daos;

import com.sg.supers.DTOs.Location;
import com.sg.supers.DTOs.Sighting;
import com.sg.supers.DTOs.Super;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
public class LocationDaoTemplate implements LocationDao {

    @Autowired
    JdbcTemplate template;

    @Override
    @Transactional
    public Location addLocation(Location toAdd) {
        KeyHolder kh = new GeneratedKeyHolder();
        int rowsAffected = template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Locations(Address, Latitude, Longitude, isDeleted) VALUES (?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setString(1, toAdd.getAddress());
                    ps.setBigDecimal(2, toAdd.getLatitude());
                    ps.setBigDecimal(3, toAdd.getLongitude());
                    ps.setBoolean(4, toAdd.getIsDeleted());

                    return ps;
                },
                kh);
        int generatedId = kh.getKey().intValue();

        toAdd.setLocationId(generatedId);
        return toAdd;
    }

    @Override
    @Transactional
    public void updateLocation(Location updated) {
        template.update("UPDATE Locations SET Address = ?, Latitude = ?, Longitude = ?, isDeleted = ? WHERE LocationId = ?",
                    updated.getAddress(), updated.getLatitude(), updated.getLongitude(), updated.getIsDeleted(), updated.getLocationId());
    }

    @Override
    public void deleteLocation(int locationId) {
        template.update("UPDATE CurrentLocations SET isDeleted = 1 WHERE LocationId = ?", locationId);
    }


    @Override
    public List<Location> getAllLocations() {
        try {
            List<Location> allLocations = template.query("SELECT * FROM CurrentLocations;", new LocationMapper());
            return allLocations;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Location getLocationById(int locationId) {
       try {
           Location toReturn = template.queryForObject("SELECT * FROM CurrentLocations WHERE LocationId = ?", new LocationMapper(), locationId);
           return toReturn;
           } catch (DataAccessException ex) {
            return null;
        }
    }
    



    private static class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet row, int rowNum) throws SQLException {

            Location converted = new Location();

            converted.setLocationId(row.getInt("LocationId"));
            converted.setAddress(row.getString("Address"));
            converted.setLatitude(row.getBigDecimal("Latitude"));
            converted.setLongitude(row.getBigDecimal("Longitude"));
            converted.setIsDeleted(row.getBoolean("isDeleted"));

            return converted;
        }
    }
}

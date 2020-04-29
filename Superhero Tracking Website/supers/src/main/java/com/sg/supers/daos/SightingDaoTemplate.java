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
import java.time.LocalDate;
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
public class SightingDaoTemplate implements SightingDao {

    @Autowired
    JdbcTemplate template;

    //add SuperSighting will take in SuperId, so update bridge table SuperSightings
    @Override
    @Transactional
    public Sighting addSuperSighting(Sighting toAdd) {



        KeyHolder kh = new GeneratedKeyHolder();
          try {
        int rowsAffected = template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Sightings(LocationId, SightDate, isDeleted, SuperId) VALUES (?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setInt(1, toAdd.getLoc().getLocationId());
                    ps.setString(2, toAdd.getSightDate().toString());
                    ps.setBoolean(3, toAdd.getIsDeleted());
                    ps.setInt(4, toAdd.getSup().getSuperId());

                    return ps;
                },
                kh);
        int generatedId = kh.getKey().intValue();

        toAdd.setSightingId(generatedId);

          } catch (DataAccessException ex) {
            return null;
        }
    
        

        return toAdd;

    }

    @Override
    public List<Sighting> allSightingsByDate(LocalDate sightDate) {
        try {
            List<Sighting> byDate = template.query("SELECT * FROM CurrentSightings cs JOIN CurrentSupers supers" 
                    + " ON cs.SuperId = supers.SuperId JOIN CurrentLocations cl ON cs.LocationId = cl.LocationId"    
                    + " WHERE sightDate = ?;", new SightingMapper(), sightDate);
            return byDate;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        try {
            List<Sighting> allSightings = template.query("SELECT * FROM CurrentSightings cs JOIN CurrentSupers supers"
                    + " ON cs.SuperId = supers.SuperId JOIN CurrentLocations cl ON cs.LocationId = cl.LocationId", new SightingMapper());
            return allSightings;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public void updateSighting(Sighting toUpdate) {
        

        template.update("UPDATE Sightings SET SuperId =?, LocationId = ?, SightDate = ?, isDeleted = ? WHERE SightingId =?;", 
                toUpdate.getSup().getSuperId(),
                toUpdate.getLoc().getLocationId(), toUpdate.getSightDate(), toUpdate.getIsDeleted(), toUpdate.getSightingId());
                
              

    }


    @Override
    public void deleteSighting(Integer sightingId) {
        template.update("UPDATE Sightings SET isDeleted = 1 WHERE SightingId = ?", sightingId);
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        Sighting toGet = template.queryForObject("SELECT * FROM CurrentSightings cs JOIN CurrentSupers supers"
                + " ON cs.SuperId = supers.SuperId JOIN CurrentLocations cl ON cs.LocationId = cl.LocationId"
                + " WHERE SightingId = ?;", new SightingMapper(), sightingId);
        return toGet;
    }

    @Override
    public List<Sighting> getAllSightingsBySuperId(int superId) {

        try {
            List<Sighting> bySuper = template.query("Select * FROM CurrentSupers cs "
                    + " JOIN CurrentSightings s ON cs.SuperId = s.SuperId JOIN CurrentLocations cl ON cl.LocationId"
                    + " = cs.LocationId WHERE cs.SuperId = ? ORDER BY s.SightDate DESC;", new SightingMapper(), superId);
           
            return bySuper;
        } catch (DataAccessException ex) {
            return null;
        }
    }



    private static class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet row, int rowNum) throws SQLException {

            Sighting converted = new Sighting();
            Location locationSighting = new Location();
     
             locationSighting.setLocationId(row.getInt("LocationId"));
             locationSighting.setAddress(row.getString("Address"));
             locationSighting.setIsDeleted(row.getBoolean("isDeleted"));
             locationSighting.setLongitude(row.getBigDecimal("Longitude"));
             locationSighting.setLatitude(row.getBigDecimal("Latitude"));
              
            converted.setLoc(locationSighting);
            
             Super sightedSuper = new Super();
             sightedSuper.setSuperId(row.getInt("SuperId"));
             sightedSuper.setIsDeleted(row.getBoolean("isDeleted"));
             sightedSuper.setPowerId(row.getInt("PowerId"));
             sightedSuper.setSuperName(row.getString("SuperName"));
             sightedSuper.setSuperDesc(row.getString("SuperDesc"));
            
             converted.setSup(sightedSuper);

            converted.setSightDate((row.getDate("SightDate").toLocalDate()));
            converted.setSightingId(row.getInt("SightingId"));
            converted.setIsDeleted(row.getBoolean("isDeleted"));
            

            return converted;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.daos;

import com.sg.supers.DTOs.Power;
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
public class SuperDaoTemplate implements SuperDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Super> getAllSupersByOrganization(int organizationId) {
        try {
            List<Super> byOrganization = template.query("Select * from CurrentOrganizations co "
                    + "JOIN SuperOrgs so ON co.OrganizationId = so.OrganizationId "
                    + "JOIN CurrentSupers cs ON so.SuperId = cs.SuperId "
                    + "WHERE co.OrganizationId = ?;",
                    new SuperMapper(), organizationId);
            return byOrganization;

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Super> getAllSupersByLocation(int locationId) {
        try {
            List<Super> byLocation = template.query("SELECT * FROM CurrentLocations cl JOIN CurrentSightings cs "
                    + " ON cl.LocationId = cs.LocationId JOIN "
                    + " CurrentSupers cSupers  ON cs.SuperId = cSupers.SuperId WHERE cSupers.SuperId = ?;", new SuperMapper(), locationId);
            return byLocation;
        } catch (DataAccessException ex) {
            return null;
        }

    }

    @Override
    public List<Super> getAllSupers() {
        try {
            List<Super> allSupers = template.query("SELECT * FROM CurrentSupers;", new SuperMapper());
            return allSupers;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    //add Super will take in their OrganizationIds as well, so update bridge table
    @Override
    @Transactional
    public Super addSuper(Super toAdd, String[] organizationIds) {

        List<Integer> organizations = organizationIds == null ? new ArrayList<>()
                : Arrays.stream(organizationIds).map(Integer::valueOf).collect(Collectors.toList());

        KeyHolder kh = new GeneratedKeyHolder();
        int rowsAffected = template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Supers (PowerId, SuperName, SuperDesc, isDeleted) VALUES (?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setInt(1, toAdd.getPowerId());
                    ps.setString(2, toAdd.getSuperName());
                    ps.setString(3, toAdd.getSuperDesc());
                    ps.setBoolean(4, toAdd.getIsDeleted());

                    return ps;
                },
                kh);
        int generatedId = kh.getKey().intValue();

        toAdd.setSuperId(generatedId);

       
        return toAdd;
    }

    @Override
    @Transactional
    public void updateSuper(Super toUpdate) {

        template.update("Update Supers SET SuperName = ?, SuperDesc = ?, PowerId = ?, isDeleted = ? WHERE SuperId = ?;",
                toUpdate.getSuperName(), toUpdate.getSuperDesc(), toUpdate.getSuperPower().getPowerId(), toUpdate.getIsDeleted(),
                toUpdate.getSuperId());

       

        
    }

    @Override
    public void deleteSuper(int superId) {
        
        template.update(
                "UPDATE CurrentSupers SET isDeleted = 1 WHERE SuperId = ?", superId);
    }

    @Override
    public Super getSuperById(int id) {
        Super toReturn = template.queryForObject("SELECT * FROM CurrentSupers WHERE SuperId =?;", new SuperMapper(), id);
        return toReturn;
    }





 

    private static class SuperMapper implements RowMapper<Super> {

        @Override
        public Super mapRow(ResultSet row, int rowNum) throws SQLException {

            Super converted = new Super();
           

            converted.setPowerId(row.getInt("PowerId"));
            converted.setSuperId(row.getInt("SuperId"));
            converted.setSuperName(row.getString("SuperName"));
            converted.setSuperDesc(row.getString("SuperDesc"));
            converted.setIsDeleted(row.getBoolean("isDeleted"));

            return converted;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.daos;

import com.sg.supers.DTOs.Organization;
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
import org.springframework.dao.DataIntegrityViolationException;
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
public class OrganizationDaoTemplate implements OrganizationDao {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Organization> getAllOrganizationsBySuperId(int superId) {
        try {
            List<Organization> bySuper = template.query("SELECT * from CurrentSupers cs JOIN SuperOrgs so ON cs.SuperId = so.SuperId "
                    + "JOIN CurrentOrganizations co ON so.OrganizationId = co.OrganizationId"
                    + " WHERE so.SuperId = ? ORDER BY OrgName DESC;",
                    new OrganizationMapper(), superId);
            return bySuper;

        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {

        try {
            List<Organization> allOrganizations = template.query("SELECT * from CurrentOrganizations;", new OrganizationMapper());
            return allOrganizations;

        } catch (DataAccessException ex) {
            return null;
        }
    }

    //AddSuper takes care of BridgeTable inserts
    @Override
    @Transactional
    public Organization addOrganization(Organization toAdd, String[] superIds) {

        List<Integer> supers = superIds == null ? new ArrayList<>()
                : Arrays.stream(superIds).map(Integer::valueOf).collect(Collectors.toList());

        KeyHolder kh = new GeneratedKeyHolder();
        int rowsAffected = template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO Organizations(OrgName, Description, Address, Phone, isDeleted) VALUES (?, ?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setString(1, toAdd.getOrgName());
                    ps.setString(2, toAdd.getDescription());
                    ps.setString(3, toAdd.getAddress());
                    ps.setString(4, toAdd.getPhone());
                    ps.setBoolean(5, toAdd.getIsDeleted());

                    return ps;
                },
                kh);
        int generatedId = kh.getKey().intValue();

        toAdd.setOrganizationId(generatedId);

        for (int superId : supers) {

            template.update("INSERT INTO SuperOrgs(SuperId, OrganizationId) VALUES (?, ?);", superId, generatedId);

        }

        return toAdd;
    }

    @Override
    @Transactional
    public void updateOrganization(Organization toUpdate, String[] superIds)  {
         List<Integer> idNums = superIds == null ? new ArrayList<>()
                : Arrays.stream(superIds).map(Integer::valueOf).collect(Collectors.toList());

        
        
                template.update("DELETE FROM SuperOrgs WHERE OrganizationId = ?;", 
                        toUpdate.getOrganizationId());
            
       
            template.update("UPDATE Organizations SET OrgName = ?, Description = ?, Address = ?, Phone =?,"
                    + " isDeleted = ? WHERE OrganizationId = ?",
                    toUpdate.getOrgName(), toUpdate.getDescription(), toUpdate.getAddress(), toUpdate.getPhone(),
                    toUpdate.getIsDeleted(), toUpdate.getOrganizationId());
            

            for (int idNum : idNums) {
                template.update("INSERT INTO SuperOrgs (SuperId, OrganizationId) VALUES (?,?)", 
                        idNum, toUpdate.getOrganizationId());
            }

    }

    @Override
    public void deleteOrganization(int organizationId) {
        template.update("UPDATE CurrentOrganizations SET isDeleted = 1 WHERE OrganizationId = ?", organizationId);

    }

    @Override
    public Organization getOrganizationById(int id) {
        Organization toReturn = template.queryForObject("SELECT * FROM CurrentOrganizations WHERE OrganizationId = ?;", new OrganizationMapper(), id);
        return toReturn;
    }

    private static class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet row, int rowNum) throws SQLException {

            Organization converted = new Organization();

            converted.setOrgName(row.getString("OrgName"));
            converted.setOrganizationId(row.getInt("OrganizationId"));
            converted.setDescription(row.getString("Description"));
            converted.setAddress(row.getString("Address"));
            converted.setPhone(row.getString("Phone"));
            converted.setIsDeleted(row.getBoolean("isDeleted"));

            return converted;
        }
    }

}

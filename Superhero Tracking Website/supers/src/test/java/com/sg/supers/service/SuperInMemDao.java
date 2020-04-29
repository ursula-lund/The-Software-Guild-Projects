/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.service;

import com.sg.supers.DTOs.Location;
import com.sg.supers.DTOs.Organization;
import com.sg.supers.DTOs.Power;
import com.sg.supers.DTOs.Sighting;
import com.sg.supers.DTOs.Super;
import com.sg.supers.daos.SuperDao;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ursul
 */
public class SuperInMemDao implements SuperDao {

    List<Super> allSupers = new ArrayList<>();

    public SuperInMemDao() {

        Location loc = new Location();
        Location loc2 = new Location();

        loc.setLocationId(1);
        loc.setAddress("Test");
        loc.setLatitude(new BigDecimal("1234"));
        loc.setLongitude(new BigDecimal("5678"));
        loc.setIsDeleted(false);

        loc2.setLocationId(2);
        loc2.setAddress("Test Street");
        loc2.setLatitude(new BigDecimal("7000.00"));
        loc2.setLongitude(new BigDecimal("6000.00"));
        loc2.setIsDeleted(false);

        Sighting first = new Sighting();
        first.setSightingId(1);
        first.setIsDeleted(false);
        first.setSightDate(LocalDate.of(2019, Month.MARCH, 19));
        first.setLoc(loc);

        Sighting second = new Sighting();
        second.setSightingId(2);
        second.setIsDeleted(false);
        second.setSightDate(LocalDate.of(2019, Month.MARCH, 20));
        second.setLoc(loc2);

        List<Sighting> sightList = new ArrayList<>();
        sightList.add(first);
        sightList.add(second);

        Power pow = new Power();
        pow.setPowerId(1);
        pow.setIsDeleted(false);
        pow.setPowerDesc("Test");
        pow.setPowerName("Test");

        Power power = new Power();
        power.setPowerId(2);
        power.setIsDeleted(false);
        power.setPowerDesc("Test2");
        power.setPowerName("Test2");

        List<Organization> orgs = new ArrayList<>();

        Super toAdd = new Super();

        toAdd.setSuperId(1);
        toAdd.setSuperName("Test");
        toAdd.setSuperDesc("Test");
        toAdd.setSuperPower(pow);
        toAdd.setPowerId(1);
        toAdd.setOrganizationsList(orgs);
        toAdd.setIsDeleted(false);

        Super superAdd = new Super();
        superAdd.setOrganizationsList(orgs);
        superAdd.setPowerId(2);
        superAdd.setSuperDesc("Test 2");
        superAdd.setSuperName("Test2");
        superAdd.setIsDeleted(false);
        superAdd.setSuperId(2);
        superAdd.setSuperPower(power);

        List<Super> orgSupers = new ArrayList<>();
        orgSupers.add(toAdd);

        Organization org = new Organization();
        org.setOrganizationId(1);
        org.setIsDeleted(false);
        org.setDescription("Test");
        org.setPhone("111-111-1111");
        org.setSupersList(orgSupers);

        allSupers.add(toAdd);
        allSupers.add(superAdd);

    }

    @Override
    public List<Super> getAllSupersByOrganization(int organizationId) {
       
        List<Super> toFind = allSupers;
        
        for (Super s : toFind) {
            List<Organization> toIterate = s.getOrganizationsList();
            
            for (Organization o : toIterate) {
            toIterate.stream()
                .filter(org -> org.getOrganizationId() == organizationId)
                 .findAny()
                .orElse(null);  
       
        }
        }
        return toFind;
    }

    @Override
    public List<Super> getAllSupersByLocation(int locationId) {
        return allSupers;
    }

    @Override
    public Super getSuperById(int id) {
       return allSupers

                .stream()
                .filter(toCheck -> toCheck.getSuperId() == id)
               .findAny()
                .orElse(null);            
    }

    @Override
    public List<Super> getAllSupers() {
        return allSupers;
    }

    @Override
    public void updateSuper(Super toUpdate) {
        
        allSupers.remove(toUpdate);
        allSupers.add(toUpdate);
    }

    @Override
    public void deleteSuper(int superId) {
        
      Super toDelete = allSupers
                .stream()
                .filter(toCheck -> toCheck.getSuperId() == superId)
                 .findAny()
                .orElse(null);  
        allSupers.remove(toDelete);
    }

    @Override
    public Super addSuper(Super toAdd, String[] organizations) { 
       
        allSupers.add(toAdd);
        return toAdd;
    }

}

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
import com.sg.supers.daos.LocationDao;
import com.sg.supers.daos.OrganizationDao;
import com.sg.supers.daos.PowerDao;
import com.sg.supers.daos.SightingDao;
import com.sg.supers.daos.SuperDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author ursul
 */
@Component
public class SuperService {

    LocationDao lDao;
    OrganizationDao oDao;
    PowerDao pDao;
    SightingDao sightDao;
    SuperDao superDao;

    @Autowired
    public SuperService(LocationDao lDao, OrganizationDao oDao, PowerDao pDao, SightingDao sightDao, SuperDao superDao) {
        this.lDao = lDao;
        this.oDao = oDao;
        this.pDao = pDao;
        this.sightDao = sightDao;
        this.superDao = superDao;
    }

 
    

    public List<Super> getAllSupers() {
        List<Super> toReturn = superDao.getAllSupers();
        for (Super s : toReturn) {
            s.setOrganizationsList(oDao.getAllOrganizationsBySuperId(s.getSuperId()));
            
        }
        return toReturn;
    }

    public List<Organization> getAllOrganizations() {
        List<Organization> toReturn = oDao.getAllOrganizations();
        for (Organization o : toReturn) {
            o.setSupersList(superDao.getAllSupersByOrganization(o.getOrganizationId()));
        }
        return toReturn;
    }

    public void updateOrganization(Organization toEdit, String[] superIds) {
        oDao.updateOrganization(toEdit, superIds);
    }

    public void addOrganization(Organization toAdd, String[] superIds) {
        oDao.addOrganization(toAdd, superIds);
    }

    public List<Location> getAllLocations() {
       return lDao.getAllLocations();
        
    }

    public Location addLocation(Location toAdd) {
        return lDao.addLocation(toAdd);
    }

    public void updateLocation(Location toUpdate) {
        lDao.updateLocation(toUpdate);
    }

    public void deleteLocation(int locationId) {
        lDao.deleteLocation(locationId);
    }

    public List<Power> getallPowers() {
        return pDao.getAllPowers();
    }

    public Power addPower(Power toAdd) {
        return pDao.addPower(toAdd);
    }

    public void updatePower(Power toEdit) {
        pDao.updatePower(toEdit);
    }

    public void deletePower(int powerId) {
        pDao.deletePower(powerId);
    }

    public Super addSuper(Super toAdd, String[] organizations) {
        return superDao.addSuper(toAdd, organizations);
    }

    public List<Sighting> getAllSightings() {
        return sightDao.getAllSightings();
    }

    public void addSighting(Sighting toAdd) {
        sightDao.addSuperSighting(toAdd);
    }

    public void updateSighting(Sighting toEdit) {
        sightDao.updateSighting(toEdit);
    }

    public void deleteSighting(int sightingId) {
        sightDao.deleteSighting(sightingId);
    }

    public Sighting getSightingById(int sightingId) {
        return sightDao.getSightingById(sightingId);

    }

    public Location getLocationById(int locationId) {
       return lDao.getLocationById(locationId);
   

    }

    public Organization getOrganizationById(int id) {
        Organization toReturn = oDao.getOrganizationById(id);
        List<Super> toView = superDao.getAllSupersByOrganization(id);
        toReturn.setSupersList(toView);
        return toReturn;
    }

    public Power getPowerById(int powerId) {
        return pDao.getPowerById(powerId);
    }

    public Super getSuperByid(int superId) {
        Super toReturn = superDao.getSuperById(superId);
        List<Organization> toView = oDao.getAllOrganizationsBySuperId(superId);
        toReturn.setOrganizationsList(toView);
        return toReturn;
    }

    public void deleteSuper(int toDelete) {
        superDao.deleteSuper(toDelete);
    }

    public void deleteOrganization(int toDelete) {
        oDao.deleteOrganization(toDelete);
    }



 
}

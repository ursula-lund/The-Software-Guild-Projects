/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.daos;

import com.sg.supers.DTOs.Organization;
import com.sg.supers.DTOs.Super;
import java.util.List;

/**
 *
 * @author ursul
 */
public interface OrganizationDao {

    public List<Organization> getAllOrganizationsBySuperId(int superId);

    public List<Organization> getAllOrganizations();
    
    public Organization getOrganizationById(int id);
    
    public Organization addOrganization(Organization toAdd, String[] superIds);
    
    public void updateOrganization(Organization toUpdate, String[] superIds);
    
    public void deleteOrganization(int organizationId);
    
    
   
    
}

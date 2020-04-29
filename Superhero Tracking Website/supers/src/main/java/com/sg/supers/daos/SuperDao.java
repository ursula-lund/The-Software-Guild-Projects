/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.daos;

import com.sg.supers.DTOs.Super;
import java.util.List;

/**
 *
 * @author ursul
 */
public interface SuperDao {

    public List<Super> getAllSupersByOrganization(int organizationId);
  
     public List<Super> getAllSupersByLocation(int locationId );
    public Super getSuperById(int id);
    public List<Super> getAllSupers();
    public void updateSuper(Super toUpdate);
    public void deleteSuper(int superId);
    public Super addSuper(Super toAdd, String[] organizations);
}

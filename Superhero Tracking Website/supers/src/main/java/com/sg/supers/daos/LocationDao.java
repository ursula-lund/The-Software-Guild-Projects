/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.daos;

import com.sg.supers.DTOs.Location;
import java.util.List;

/**
 *
 * @author ursul
 */
public interface LocationDao {

   
    public List<Location> getAllLocations();

    public Location addLocation(Location toAdd);

    public void updateLocation(Location updated);

    public void deleteLocation(int LocationId);

    public Location getLocationById(int locationId);
    
    

}

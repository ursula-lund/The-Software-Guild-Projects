/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.daos;

import com.sg.supers.DTOs.Sighting;
import com.sg.supers.DTOs.Super;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ursul
 */
public interface SightingDao {
    
    public Sighting addSuperSighting(Sighting toAdd);
    public List<Sighting> getAllSightings();
    public List<Sighting> getAllSightingsBySuperId(int superId);
    public List<Sighting> allSightingsByDate(LocalDate sightDate);
    public void updateSighting(Sighting toUpdate);
    public void deleteSighting (Integer sightingId);
    public Sighting getSightingById(int sightingId);
    
}

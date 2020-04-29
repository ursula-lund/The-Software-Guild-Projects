/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.daos;

import com.sg.supers.DTOs.Power;
import java.util.List;

/**
 *
 * @author ursul
 */
public interface PowerDao {
    
    public Power addPower(Power toAdd);
    public void updatePower(Power updated);
    public void deletePower(int powerId);
    public List<Power> getAllPowers();
    Power getPowerById(int powerId);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.controller;

import com.sg.supers.DTOs.Location;
import com.sg.supers.DTOs.Sighting;
import com.sg.supers.DTOs.Super;
import com.sg.supers.service.SuperService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ursul
 */
@Controller
public class LocationController {
    
    @Autowired
    SuperService service;
    
    @GetMapping("location")
    public String displayLocationPage(Model m) {
        
        List<Location> locations = service.getAllLocations();
        m.addAttribute("locations", locations);
        return "location";
    }
    
    @GetMapping("viewLocation")
    public String viewLocation(HttpServletRequest request, Model m) {
        int locationId = Integer.parseInt(request.getParameter("id"));
        Location location = service.getLocationById(locationId);
       
        
        m.addAttribute("location", location);
       
        
        return "viewLocation";
    }    
    
    @GetMapping("addLocation")
    public String addLocation(Model m) {
        return "addLocation";
    }
    
    @PostMapping("addLocation")
    public String addLocation(Location toAdd) {
        
        toAdd.setIsDeleted(false);
        service.addLocation(toAdd);
        return "redirect:/location";
    }
    
    @GetMapping("updateLocation")
    public String updateLocation(HttpServletRequest request, Model m) {
        
        int locationId = Integer.parseInt(request.getParameter("id"));
        
        Location location = service.getLocationById(locationId);
        m.addAttribute("location", location);
        return "updateLocation";
        
    }
    
    @PostMapping("updateLocation")
    public String editLocation(Location toEdit, HttpServletRequest request) {
        int locationId = Integer.parseInt(request.getParameter("id"));
        
        toEdit.setLocationId(locationId);
        toEdit.setIsDeleted(false);        
        service.updateLocation(toEdit);
        return "redirect:/location";
        
    }
    
    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {
        int locationId = Integer.parseInt(request.getParameter("id"));
        service.deleteLocation(locationId);
        return "redirect:/location";
    }    
    
}

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author ursul
 */
@Controller
public class SightingController {

    @Autowired
    SuperService service;

    @GetMapping("sighting")
    public String displaySightingsPage(Model m) {
        List<Sighting> sightings = service.getAllSightings();
        List<Location> locations = service.getAllLocations();
        List<Super> supers = service.getAllSupers();
        m.addAttribute("sightings", sightings);
        m.addAttribute("locations", locations);
        m.addAttribute("supers", supers);
        return "sighting";
    }

    @GetMapping("addSighting")
    public String addSighting(Model m) {

        List<Sighting> sightings = service.getAllSightings();
        List<Location> locations = service.getAllLocations();
        List<Super> supers = service.getAllSupers();
        m.addAttribute("sightings", sightings);
        m.addAttribute("locations", locations);
        m.addAttribute("supers", supers);

        return "addSighting";
    }

    @PostMapping("addSighting")
    public String addSighting(Sighting toAdd, HttpServletRequest request) {

         String dateString = request.getParameter("sDate");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate sDate = LocalDate.parse(dateString, formatter);
        toAdd.setSightDate(sDate);
        toAdd.setIsDeleted(false);
        service.addSighting(toAdd);
        return "redirect:/sighting";
    }

    @GetMapping("updateSighting")
    public String updateSighting(HttpServletRequest request, Model m) {

        int sightingId = Integer.parseInt(request.getParameter("id"));

        Sighting sighting = service.getSightingById(sightingId);
        List<Location> locations = service.getAllLocations();
        List<Super> supers = service.getAllSupers();

        m.addAttribute("sighting", sighting);
        m.addAttribute("locations", locations);
        m.addAttribute("supers", supers);
        return "updateSighting";

    }

    @PostMapping("updateSighting")
    public String updateSighting(Sighting toEdit, HttpServletRequest request) {
        
        String dateString = request.getParameter("sDate");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate sDate = LocalDate.parse(dateString, formatter);
        toEdit.setSightDate(sDate);
        toEdit.setIsDeleted(false);
        service.updateSighting(toEdit);
        return "redirect:/sighting";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request) {
        int sightingId = Integer.parseInt(request.getParameter("id"));
        service.deleteSighting(sightingId);
        return "redirect:/sighting";
    }
}

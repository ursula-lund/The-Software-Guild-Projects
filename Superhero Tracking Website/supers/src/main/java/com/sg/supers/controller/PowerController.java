/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.controller;

import com.sg.supers.DTOs.Power;
import com.sg.supers.service.SuperService;
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
public class PowerController {

    @Autowired
    SuperService service;

    @GetMapping("power")
    public String displayPowerPage(Model m) {
        List<Power> powers = service.getallPowers();
        m.addAttribute("powers", powers);
        return "power";
    }
    
     @GetMapping("addPower")
    public String addPower() {
        return "addPower";
    }

    @PostMapping("addPower")
    public String addPower(Power toAdd) {

        toAdd.setIsDeleted(false);
        service.addPower(toAdd);
        return "redirect:/power";
    }

    @GetMapping("updatePower")
    public String updatePower(HttpServletRequest request, Model m) {

        int powerId = Integer.parseInt(request.getParameter("id"));

        Power power = service.getPowerById(powerId);
        m.addAttribute("power", power);
        return "updatePower";

    }

    @PostMapping("updatePower")
    public String editPower(Power toEdit, HttpServletRequest request) {
        int powerId = Integer.parseInt(request.getParameter("id"));

        toEdit.setPowerId(powerId);
        toEdit.setIsDeleted(false);
        service.updatePower(toEdit);
        return "redirect:/power";
    }

    @GetMapping("deletePower")
    public String deleteLocation(HttpServletRequest request) {
        int powerId = Integer.parseInt(request.getParameter("id"));
        service.deletePower(powerId);
        return "redirect:/power";
    }
}

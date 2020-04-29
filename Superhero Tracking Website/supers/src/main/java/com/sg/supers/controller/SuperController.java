/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.controller;

import com.sg.supers.DTOs.Location;
import com.sg.supers.DTOs.Organization;
import com.sg.supers.DTOs.Power;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ursul
 */
@Controller
public class SuperController {

    @Autowired
    SuperService service;

    @GetMapping("super")
    public String supersPage(Model m) {
        List<Super> supers = service.getAllSupers();
        List<Organization> organizations = service.getAllOrganizations();
        List<Power> powers = service.getallPowers();

        m.addAttribute("supers", supers);
        m.addAttribute("organizations", organizations);
        m.addAttribute("powers", powers);
        return "super";
    }

    @GetMapping("viewSuper")
    public String viewSuper(Model m, HttpServletRequest request) {
        int superId = Integer.parseInt(request.getParameter("id"));
        Super toView = service.getSuperByid(superId);
        List<Organization> organizations = toView.getOrganizationsList();
        Power toShow = service.getPowerById(toView.getPowerId());
        

        m.addAttribute("super", toView);
        m.addAttribute("organizations", organizations);
        m.addAttribute("power", toShow);
      

        return "viewSuper";
    }

    @GetMapping("addSuper")
    public String addSuper(Model m) {
        List<Super> supers = service.getAllSupers();
        List<Organization> organizations = service.getAllOrganizations();
        List<Power> powers = service.getallPowers();
        m.addAttribute("supers", supers);
        m.addAttribute("organizations", organizations);
        m.addAttribute("powers", powers);

        return "addSuper";
    }

    @PostMapping("addSuper")
    public String addSuper(Super toAdd, HttpServletRequest request, Model m) {

        m.addAttribute("organizations", service.getAllOrganizations());
        m.addAttribute("powers", service.getallPowers());

        String[] organizationIds = request.getParameterValues("organizationIds");

        int powerId = Integer.parseInt(request.getParameter("powerId"));

        toAdd.setPowerId(powerId);
        toAdd.setIsDeleted(false);

        service.addSuper(toAdd, organizationIds);

        return "redirect:/super";
    }

    @GetMapping("updateSuper")
    public String updateSuper(Model m) {
        List<Super> supers = service.getAllSupers();
        List<Organization> organizations = service.getAllOrganizations();
        List<Power> powers = service.getallPowers();
        m.addAttribute("supers", supers);
        m.addAttribute("organizations", organizations);
        m.addAttribute("powers", powers);

        return "updateSuper";
    }

    @PostMapping("updateSuper")
    public String updateSuper(Super toAdd, HttpServletRequest request, Model m) {

        String[] organizationIds = request.getParameterValues("organizationIds");

        int powerId = Integer.parseInt(request.getParameter("powerId"));

        toAdd.setPowerId(powerId);
        toAdd.setIsDeleted(false);

        service.addSuper(toAdd, organizationIds);

        return "redirect:/super";
    }

    @GetMapping("deleteSuper")
    public String deleteSuper(HttpServletRequest request) {
        int toDelete = Integer.parseInt(request.getParameter("id"));
        service.deleteSuper(toDelete);
        return "redirect:/super";
    }

}

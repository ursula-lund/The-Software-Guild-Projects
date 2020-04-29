/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.controller;

import com.sg.supers.DTOs.Organization;
import com.sg.supers.DTOs.Super;
import com.sg.supers.service.SuperService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ursul
 */
@Controller
public class OrganizationController {

    @Autowired
    SuperService service;

    @GetMapping("organization")
    public String organizationsPage(Model m) {
        List<Organization> organizations = service.getAllOrganizations();
        m.addAttribute("organizations", organizations);
        return "organization";
    }

    @GetMapping("addOrganization")
    public String addOrganizationPage(Model m) {
        List<Organization> organizations = service.getAllOrganizations();
        List<Super> supers = service.getAllSupers();
        m.addAttribute("organization", organizations);
        m.addAttribute("supers", supers);
        return "addOrganization";
    }

    @PostMapping("addOrganization")
    public String addOrganization(Organization toAdd, HttpServletRequest request) {

        String[] superIds = request.getParameterValues("superIds");

        toAdd.setIsDeleted(false);
        service.addOrganization(toAdd, superIds);
        return "redirect:/organization";
    }

    @GetMapping("updateOrganization")
    public String updateOrganization(HttpServletRequest request, Model m) {

        int id = Integer.parseInt(request.getParameter("id"));
        Organization organization = service.getOrganizationById(id);
        List<Super> supers = service.getAllSupers();

        m.addAttribute("organization", organization);
        m.addAttribute("supers", supers);

        return "updateOrganization";

    }

    @PostMapping("updateOrganization")
    public String updateOrganization(Organization toEdit, HttpServletRequest request) {

        String[] superIds = request.getParameterValues("superIds");

        
        toEdit.setIsDeleted(false);
        service.updateOrganization(toEdit, superIds);

        return "redirect:/viewOrganization?id=" + toEdit.getOrganizationId();


    }

    @GetMapping("/viewOrganization")
    public String viewOrganization(HttpServletRequest request, Model m) {

        int organizationId = Integer.parseInt(request.getParameter("id"));
        Organization toView = service.getOrganizationById(organizationId);

        List<Super> members = toView.getSupersList();

        m.addAttribute("organization", toView);
        m.addAttribute("supers", members);

        return "viewOrganization";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(HttpServletRequest request) {
        int toDelete = Integer.parseInt(request.getParameter("id"));
        service.deleteOrganization(toDelete);
        return "redirect:/organization";
    }

}

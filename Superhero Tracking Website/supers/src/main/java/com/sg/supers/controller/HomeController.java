/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.controller;

import com.sg.supers.DTOs.Organization;
import com.sg.supers.service.SuperService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author ursul
 */
@Controller
public class HomeController {
    
    @Autowired
    SuperService service;
    
    @GetMapping({"/", "/home"})
    public String displayHomePage(Model m) {
        
        return "home";
    }
    

    
}

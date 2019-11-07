package com.goruslan.demo.controller;

import com.goruslan.demo.domain.Schedule;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class MainController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/list")
    public String list() { return "list"; }



}

package com.goruslan.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {

    @GetMapping("/login")
    public String home() {
        return "index";
    }

}

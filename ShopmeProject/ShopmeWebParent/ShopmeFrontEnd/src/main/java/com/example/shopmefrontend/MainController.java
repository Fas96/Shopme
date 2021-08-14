package com.example.shopmefrontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String viewHomePage(){
        System.out.println("---------page accesssed---------");
        return "index";
    }
}

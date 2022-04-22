package com.example.demo.Mvc;

import com.example.demo.payload.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageController {
    @GetMapping
    public String getHomePage(Model model ){
        model.addAttribute("userDto",new UserDto());
        return "registration";
    }
    @PostMapping
    public String addAll(Model model){
        return "registration";
    }
}

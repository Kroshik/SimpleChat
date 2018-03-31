package ru.ifmo.cse.phms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "auth/login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "home";
    }
}

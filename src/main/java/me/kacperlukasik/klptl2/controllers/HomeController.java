package me.kacperlukasik.klptl2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index(Principal principal)
    {
        return "home/index";
    }

}

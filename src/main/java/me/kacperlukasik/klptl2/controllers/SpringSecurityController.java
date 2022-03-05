package me.kacperlukasik.klptl2.controllers;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Log4j2
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityController {

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView( "login");
    }


    @GetMapping("/error403")
    public ModelAndView error(){
        return new ModelAndView( "error403");
    }
}

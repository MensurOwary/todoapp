package com.owary.faora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("index");
        model.addObject("msg", "Faora");
        return model;
    }

}

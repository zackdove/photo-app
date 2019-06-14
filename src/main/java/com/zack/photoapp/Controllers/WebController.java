package com.zack.photoapp.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

public class WebController {

    private static final Logger LOG = LoggerFactory.getLogger(WebController.class);



    @GetMapping("/")
    public String addUser(Model model){
        LOG.info("Get request for index");
        return "index";
    }

}

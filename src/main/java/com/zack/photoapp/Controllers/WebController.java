package com.zack.photoapp.Controllers;

import com.zack.photoapp.Services.AmazonS3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Controller
public class WebController {

    private static final Logger LOG = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private AmazonS3Service amazonS3Service;


    @GetMapping("/")
    public String addUser(Model model){
        LOG.info("Get request for index");
        amazonS3Service.getPictures();
        return "index";
    }

}

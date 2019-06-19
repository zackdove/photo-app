package com.zack.photoapp.Controllers;

import com.zack.photoapp.Services.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

    private static final Logger LOG = LoggerFactory.getLogger(WebController.class);

    @Autowired
    private PhotoService photoService;

    @GetMapping("/")
    public String getIndex(){
        LOG.info("Get request for /");
        return "index";
    }

    @GetMapping("/photographs")
    public String getPhotographs(Model model){
        LOG.info("Get request for /photographs");
        return "redirect:/photographs/0";
    }


    @GetMapping("/photographs/{i}")
    public String getPhotographsI(Model model, @PathVariable Integer i){
        model.addAttribute("page", i);
        model.addAttribute("photos", photoService.getIthSetOf10(i));
        return "photographs";
    }

    @GetMapping("/about")
    public String getAbout(){
        LOG.info("Get request for /about");
        return "about";
    }
}

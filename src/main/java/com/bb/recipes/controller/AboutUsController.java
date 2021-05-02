package com.bb.recipes.controller;

import java.util.List;

import com.bb.recipes.entities.AboutUs;
import com.bb.recipes.service.AboutUsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about-us")
public class AboutUsController {

    private static final String ATTRIBUTE_ABOUT_US = "aboutUs";

    @Autowired
    private AboutUsService aboutUsService;

    @GetMapping("/")
    public String list(Model model) {
        List<AboutUs> aboutUs = aboutUsService.findAll();
        if (aboutUs.size() == 0) {
            model.addAttribute(ATTRIBUTE_ABOUT_US, new AboutUs());
        } else {
            model.addAttribute(ATTRIBUTE_ABOUT_US, aboutUs.get(0));
        }

        return "about-us/about-us";
    }

    @GetMapping("/add")
    public String add(Model model) {
        AboutUs aboutUs = new AboutUs();

        model.addAttribute(ATTRIBUTE_ABOUT_US, aboutUs);
        return "about-us/update-about-us";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("aboutUs") AboutUs aboutUs) {
        aboutUsService.deleteAll();
        aboutUsService.save(aboutUs);

        return "redirect:/about-us/";

    }

    @GetMapping("/update")
    public String update(Model model) {
        if (aboutUsService.findAll().size() == 0) {
            model.addAttribute(ATTRIBUTE_ABOUT_US, new AboutUs());
        } else {
            model.addAttribute(ATTRIBUTE_ABOUT_US, aboutUsService.findAll().get(0));
        }

        return "about-us/update-about-us";

    }

    @GetMapping("/delete")
    public String delete() {
        aboutUsService.deleteAll();
        return "redirect:/about-us/";
    }
}
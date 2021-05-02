package com.bb.recipes.controller;

import java.util.List;

import com.bb.recipes.entities.Recipe;
import com.bb.recipes.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainContoller {
@Autowired
    private RecipeService recipeService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
    List<Recipe> recentRecipes = recipeService.findAll();
        recentRecipes.get(0).getImage();
        model.addAttribute("recentRecipes", recentRecipes);
        return "index";
    }

}
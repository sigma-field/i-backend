package com.bb.recipes.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.bb.recipes.entities.Category;
import com.bb.recipes.entities.Recipe;
import com.bb.recipes.service.CategoryService;
import com.bb.recipes.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private static final String ATTRIBUTE_RECIPE = "recipe";
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/list", produces = { org.springframework.http.MediaType.IMAGE_JPEG_VALUE,
            org.springframework.http.MediaType.IMAGE_PNG_VALUE })
    public String list(Model model) {
        List<Recipe> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);

        return "recipes/list-recipes";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Recipe recipe = new Recipe();

        List<Category> categories = categoryService.findAll();
        model.addAttribute(ATTRIBUTE_RECIPE, recipe);
        model.addAttribute("categories", categories);

        return "recipes/add-recipe-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("recipe") Recipe recipe,
            @RequestParam("recipeImage") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        recipe.setImage(fileName);

        Recipe savedRecipe = recipeService.save(recipe);

        String uploadDirectory = "./images/recipe/" + savedRecipe.getId();
        Path uploadPath = Paths.get(uploadDirectory);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        InputStream inputStream = multipartFile.getInputStream();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        recipeService.save(recipe);

        return "redirect:/recipes/list";

    }

    @GetMapping("/update")
    public String update(@RequestParam("recipeId") Long id, Model model) {

        Recipe recipe = recipeService.findById(id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute(ATTRIBUTE_RECIPE, recipe);
        model.addAttribute("categories", categories);

        return "recipes/add-recipe-form";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("recipeId") Long id, Model model) {
        recipeService.deleteById(id);
        return "redirect:/recipes/list";
    }

    @GetMapping("/get")
    public String get(@RequestParam("recipeId") Long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute(ATTRIBUTE_RECIPE, recipe);

        return "recipes/view-recipe";

    }
}
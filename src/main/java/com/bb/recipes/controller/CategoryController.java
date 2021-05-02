package com.bb.recipes.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.bb.recipes.entities.Category;
import com.bb.recipes.service.CategoryService;

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
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/list", produces = { org.springframework.http.MediaType.IMAGE_JPEG_VALUE,
            org.springframework.http.MediaType.IMAGE_PNG_VALUE })
    public String list(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        return "categories/list-categories";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Category category = new Category();

        model.addAttribute("category", category);
        return "categories/category-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("category") Category category,
            @RequestParam("categoryImage") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        category.setImage(fileName);
        Category savedCategory = categoryService.save(category);

        String uploadDirectory = "./images/category/" + savedCategory.getId();
        Path uploadPath = Paths.get(uploadDirectory);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        InputStream inputStream = multipartFile.getInputStream();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        return "redirect:/categories/list";

    }

    @GetMapping("/update")
    public String update(@RequestParam("categoryId") Integer id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "categories/category-form";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("categoryId") Integer id, Model model) {
        categoryService.deleteById(id);
        return "redirect:/categories/list";
    }
}
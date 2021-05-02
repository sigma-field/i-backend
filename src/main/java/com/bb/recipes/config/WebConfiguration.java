package com.bb.recipes.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path categoryIploadDirectory = Paths.get("images/category");
        Path recipeIploadDirectory = Paths.get("images/recipe");
        String recipeUploadPath = recipeIploadDirectory.toFile().getAbsolutePath();
        String categoryUploadPath = categoryIploadDirectory.toFile().getAbsolutePath();
        registry.addResourceHandler("/images/category/**").addResourceLocations("file:/" + categoryUploadPath + "/");
        registry.addResourceHandler("/images/recipe/**").addResourceLocations("file:/" + recipeUploadPath + "/");
    }

}
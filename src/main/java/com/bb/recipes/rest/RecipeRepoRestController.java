package com.bb.recipes.rest;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bb.recipes.entities.Category;
import com.bb.recipes.entities.Comment;
import com.bb.recipes.entities.Recipe;
import com.bb.recipes.repo.CategoryRepository;
import com.bb.recipes.repo.RecipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController

public class RecipeRepoRestController {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public RecipeRepoRestController(RecipeRepository recipeRepository,
            CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/recipes/{recipeId}/comments")
    public @ResponseBody ResponseEntity<?> findVisibleComments(@PathVariable("recipeId") Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).get();
        Collection<Comment> comments = recipe.getComments();
        List<Comment> visibleComments = new ArrayList<>();
        comments.forEach(element -> {
            if (element.getIsVisible())
                visibleComments.add(element);
        });
        CollectionModel<Comment> resources = new CollectionModel<>(visibleComments);

        resources.add(linkTo(methodOn(RecipeRepoRestController.class).findVisibleComments(recipeId)).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/categories/{categoryId}/recipes")
    public @ResponseBody ResponseEntity<?> getAllRecipes(HttpServletRequest request, @PathVariable Integer categoryId) {

        Collection<Recipe> recipes = recipeRepository.findRecipesByCategoryId(categoryId);

        recipes.forEach(element -> element.setImage(
                request.getRequestURL().toString().replace(request.getRequestURI(), "") + element.getImagePath()));

        CollectionModel<Recipe> resources = new CollectionModel<>(recipes);

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/categories")
    public @ResponseBody ResponseEntity<?> getAllCategories(HttpServletRequest request) {

        Collection<Category> categories = categoryRepository.findAll();

        categories.forEach(element -> element.setImage(
                request.getRequestURL().toString().replace(request.getRequestURI(), "") + element.getImagePath()));

        CollectionModel<Category> resources = new CollectionModel<>(categories);

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/recipes")
    public @ResponseBody ResponseEntity<?> getAllRecipes(HttpServletRequest request) {

        Collection<Recipe> recipes = recipeRepository.findAll();

        recipes.forEach(element -> element.setImage(
                request.getRequestURL().toString().replace(request.getRequestURI(), "") + element.getImagePath()));

        CollectionModel<Recipe> resources = new CollectionModel<>(recipes);

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/recipes/{id}")
    public @ResponseBody ResponseEntity<?> getAllRecipes(HttpServletRequest request, @PathVariable Long id) {

        Recipe recipe = recipeRepository.findById(id).get();

        recipe.setImage(
                request.getRequestURL().toString().replace(request.getRequestURI(), "") + recipe.getImagePath());

        EntityModel<Recipe> resource = new EntityModel<>(recipe);

        return ResponseEntity.ok(resource);
    }

}
package com.bb.recipes.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.bb.recipes.entities.Comment;
import com.bb.recipes.repo.CommentRepository;
import com.bb.recipes.repo.RecipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
public class CommentRepoRestController {
    private final CommentRepository commentRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public CommentRepoRestController(CommentRepository commentRepository, RecipeRepository recipeRepository) {
        this.commentRepository = commentRepository;
        this.recipeRepository = recipeRepository;
    }

    @PostMapping(value = "/comments/{recipeId}")
    @ResponseBody
    public ResponseEntity<Comment> saveComment(@PathVariable(name = "recipeId") long recipeId,
            @RequestBody Comment comment) {
        comment.setIsVisible(false);
        comment.setPostDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")));
        comment.setRecipe(recipeRepository.findById(recipeId).get());
        commentRepository.save(comment);
        EntityModel<Comment> resource = EntityModel.of(comment);
        resource.add(linkTo(methodOn(CommentRepoRestController.class).saveComment(recipeId, comment)).withSelfRel());

        return ResponseEntity.ok(comment);
    }

}
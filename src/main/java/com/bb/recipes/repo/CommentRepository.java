package com.bb.recipes.repo;

import java.util.List;

import javax.transaction.Transactional;

import com.bb.recipes.entities.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
@Transactional
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByIsVisibleTrue();

    public List<Comment> findByRecipeIdAndIsVisibleTrue(Long recipeId);
}
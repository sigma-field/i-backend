package com.bb.recipes.service;

import java.util.List;

import com.bb.recipes.entities.Comment;

public interface CommentService {
    public List<Comment> findAll();

    public Comment findById(Long id);

    public void save(Comment customer);

    public void deleteById(Long id);
}
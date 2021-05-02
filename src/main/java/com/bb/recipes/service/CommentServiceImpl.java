package com.bb.recipes.service;

import java.util.List;

import com.bb.recipes.entities.Comment;
import com.bb.recipes.repo.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);

    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {

        return commentRepository.findById(id).get();
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

}
package com.bb.recipes.controller;

import java.io.IOException;
import java.util.List;

import com.bb.recipes.entities.Comment;
import com.bb.recipes.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/list")
    public String list(Model model) {
        List<Comment> comments = commentService.findAll();
        model.addAttribute("comments", comments);

        return "comments/list-comments";
    }

    @PostMapping("/save")
    public String save(@RequestParam("commentId") Long commentId) throws IOException {
        Comment comment = commentService.findById(commentId);
        comment.setIsVisible(true);
        commentService.save(comment);

        return "redirect:/comments/list";

    }

    @GetMapping("/update")
    public String update(@RequestParam("commentId") Long id, Model model) {
        Comment comment = commentService.findById(id);
        model.addAttribute("comment", comment);
        return "comments/comment-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("commentId") Long id, Model model) {
        commentService.deleteById(id);
        return "redirect:/comments/list";
    }
}
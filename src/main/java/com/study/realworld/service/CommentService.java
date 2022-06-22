package com.study.realworld.service;

import com.study.realworld.domain.Comment;
import com.study.realworld.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment register(Comment comment){
        Comment savedComment = commentRepository.save(comment);
        return savedComment;
    }

    public void delete(Long commentId){
        commentRepository.delete(commentId);
    }

    public Comment findById (Long commentId){
        Comment byId = commentRepository.findById(commentId);
        return byId;
    }

    public List<Comment> findByArticleId(Long articleId){
        List<Comment> byArticleId = commentRepository.findByArticleId(articleId);
        return byArticleId;
    }

    public void updateFavorites(Long commentId){
        commentRepository.updateFavorites(commentId);
    }
}

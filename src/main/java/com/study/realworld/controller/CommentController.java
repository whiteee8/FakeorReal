package com.study.realworld.controller;

import com.study.realworld.domain.article.Article;
import com.study.realworld.domain.Comment;
import com.study.realworld.domain.User;
import com.study.realworld.service.ArticleServiceSub;
import com.study.realworld.service.CommentService;
import com.study.realworld.service.UserService;
import com.study.realworld.service.article.ArticleService;
import com.study.realworld.web.dto.article.ArticleRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final ArticleService articleService;

    private static User currentUser;
    private static ArticleRequestDto currentArticle;

    public CommentController(CommentService commentService, UserService userService, ArticleService articleService) {
        this.commentService = commentService;
        this.userService = userService;
        this.articleService = articleService;
    }


    @GetMapping("/commentForm/{id}")//view_qna
    public String commentForm(Model model, Model model2, Model model3, Model model4, Model model5, @PathVariable Long id){
        currentUser = userService.findByEmail("yang@naver.com");
        currentArticle = articleService.findById(id);
        List<Comment> comments = commentService.findByArticleId(currentArticle.getId());

        model.addAttribute("currentUser", currentUser );
        model2.addAttribute("currentArticle", currentArticle );
        model3.addAttribute("comments", comments);
        model4.addAttribute("userService", userService);
        model5.addAttribute("articleService", articleService);
        return "commentForm";
    }

    @PostMapping("/comment")//댓글 작성
    public String register(@RequestParam String body, Model model){
        Comment comment = new Comment();
        comment.setUser_id(currentUser.getId());
        comment.setArticle_id(currentArticle.getId());
        comment.setBody(body);
        comment.setCreated_at(new Date());
        Comment registerComment = commentService.register(comment);

        List<Comment> comments = commentService.findByArticleId(currentArticle.getId());
        model.addAttribute("comments", comments);
        return "redirect:commentForm/" + currentArticle.getId();
    }

    @GetMapping("/delete/{commentId}")//댓글 삭제
    public String delete(@PathVariable Long commentId, Model model){
        Comment byId = commentService.findById(commentId);
        if(byId.getUser_id() == currentUser.getId()) {
            commentService.delete(commentId);
            return "redirect:/commentForm/" + currentArticle.getId();
        }
        else{
            return "commentDeleteNotAllowed";
        }
    }

    @GetMapping("/favorite/{commentId}/{userId}")//추천
    public String favorite(@PathVariable Long commentId, @PathVariable Long userId){

        User byId = userService.findById(userId);
        if(byId.getCommentFavoriteCount() >0){
            commentService.updateFavorites(commentId);
            userService.updateFavoriteCnt(userId);

            return "redirect:/commentForm/" + currentArticle.getId();
        }

        else return "favoriteLimited";

    }
}

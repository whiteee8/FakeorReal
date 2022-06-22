package com.study.realworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/board_info")
    public String board_info(){
        return "board_info";
    }
    @RequestMapping("/board_qna")
    public String board_qna(){
        return "board_qna";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/myRanking")
    public String myRanking(){
        return "myRanking";
    }
    @RequestMapping("/view_info")
    public String view_info(){
        return "view_info";
    }
    @RequestMapping("/view_qna")
    public String view_qna(){
        return "view_qna";
    }
    @RequestMapping("/write_qna")
    public String write_qna(){
        return "write_qna";
    }
    @RequestMapping("/write_info")
    public String write_info(){
        return "write_info";
    }
    @RequestMapping("/commentForm")
    public String commentForm10(){
        return "view_qna";
    }
    @RequestMapping("/commentform")
    public String commentForm11(){
        return "view_info";
    }
}

package com.study.realworld.controller;

import com.study.realworld.dao.RankingUser;
import com.study.realworld.service.UserRankingService;
import com.study.realworld.service.UserRankingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ShowUserRankingController {

    @Autowired
    UserRankingService userRankingService;

    @RequestMapping("/")
    public String showUserRanking(Model model) throws Exception {
        List<RankingUser> userList = userRankingService.showUserRanking();
        model.addAttribute("userList", userList);
        return "hello";
    }
}

package com.study.realworld.service;

import com.study.realworld.dao.RankingUser;

import java.util.List;

public interface UserRankingService {
    List<RankingUser> showUserRanking() throws Exception;
}

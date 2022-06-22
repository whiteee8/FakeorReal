package com.study.realworld.service;

import com.study.realworld.dao.RankingUser;
import com.study.realworld.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class UserRankingServiceImpl implements UserRankingService {

    @Autowired
    private TestDao testDao;

    @Override
    public List<RankingUser> showUserRanking() throws Exception {
        List<RankingUser> memberList = new ArrayList();
        for (int i = 1; i < 6; i++) {
            Map<String, Object> map = testDao.getRank(i);
            RankingUser user = new RankingUser(i, (String) map.get("username"), (Long) map.get("point"), (String) map.get("rank"));
            memberList.add(user);
        }

        return memberList;
    }

}

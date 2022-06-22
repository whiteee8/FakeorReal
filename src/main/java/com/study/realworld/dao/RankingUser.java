package com.study.realworld.dao;

public class RankingUser {
    int userId;
    String userName;
    Long userPoint;
    String rank;

    public RankingUser(int userId, String userName, Long userPoint, String rank) {
        this.userId = userId;
        this.userName = userName;
        this.userPoint = userPoint;
        this.rank = rank;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public Long getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Long point) {
        this.userPoint = point;
    }

    public String getRank() {
        return rank;
    }

    public void setUserRank(String rank) {
        this.rank = rank;
    }

}

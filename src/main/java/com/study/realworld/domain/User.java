package com.study.realworld.domain;

import java.util.Date;

public class User {

    private Long id;
    private String username;
    private String email;
    private String password;
    private String bio;
    private String image;
    private Long commentFavoriteCount;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    public void useCommentFavorite(){
        this. commentFavoriteCount--;
    }

    public Long getCommentFavoriteCount() {
        return commentFavoriteCount;
    }

    public void setCommentFavoriteCount(Long commentFavoriteCount) {
        this.commentFavoriteCount = commentFavoriteCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }
}

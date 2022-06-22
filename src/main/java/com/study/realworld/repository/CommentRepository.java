package com.study.realworld.repository;

import com.study.realworld.domain.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentRepository {

    private final JdbcTemplate jdbcTemplate;

    public CommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Comment save(Comment comment){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("comment").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", comment.getUser_id());
        parameters.put("article_id", comment.getArticle_id());
        parameters.put("body", comment.getBody());
        parameters.put("created_at", comment.getCreated_at());
        parameters.put("updated_at", comment.getUpdated_at());
        parameters.put("deleted_at", comment.getDeleted_at());
        parameters.put("favorite", comment.getFavorite());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        comment.setId(key.longValue());
        return comment;
    }

    public void delete(Long id){
        String SQL = "delete from comment where id = ?";
        jdbcTemplate.update(SQL, id);
    }


    public Comment findById(Long commentId){

        List<Comment> results = jdbcTemplate.query("select * from comment where id = ?", commentRowMapper(), commentId);
        return results.isEmpty() ? null : results.get(0);
    }

    public List<Comment> findByArticleId(Long articleId){
        List<Comment> results = jdbcTemplate.query("select * from comment where article_id = ?", commentRowMapper(), articleId);
        return results;
    }

    public void updateFavorites(Long commentId){
        Comment byId = findById(commentId);
        Long favorite = byId.getFavorite();
        favorite++;
        jdbcTemplate.update("update comment set favorite=? where id=?", favorite , commentId);
    }

    private RowMapper<Comment> commentRowMapper(){
        return new RowMapper<Comment>() {
            @Override
            public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {

                Comment comment = new Comment();
                comment.setArticle_id(rs.getLong("article_id"));
                comment.setUser_id(rs.getLong("user_id"));
                comment.setBody(rs.getString("body"));
                comment.setCreated_at(rs.getDate("created_at"));
                comment.setId(rs.getLong("id"));
                comment.setFavorite(rs.getLong("favorite"));
                return comment;
            }
        };
    }

}
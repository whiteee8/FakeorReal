package com.study.realworld.repository;

import com.study.realworld.domain.article.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepositorySub {

    private final JdbcTemplate jdbcTemplate;

    public ArticleRepositorySub(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Article save(Article article){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("article").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", article.getUser_id());
        parameters.put("slug", article.getSlug());
        parameters.put("title", article.getTitle());
        parameters.put("description", article.getDescription());
        parameters.put("body", article.getBody());
        parameters.put("created_at", article.getCreated_at());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        article.setId(key.longValue());
        return article;
    }


    public Article findBySlug(String slug){
        List<Article> results = jdbcTemplate.query("select * from article where slug = ?", articleRowMapper(), slug);
        return results.isEmpty() ? null : results.get(0);
    }

    private RowMapper<Article> articleRowMapper(){
        return new RowMapper<Article>() {
            @Override
            public Article mapRow(ResultSet rs, int rowNum) throws SQLException {

                Article article = new Article();
                article.setId(rs.getLong("id"));
                article.setUser_id(rs.getLong("user_id"));
                article.setSlug(rs.getString("slug"));//unique
                article.setTitle(rs.getString("title"));
                article.setDescription(rs.getString("description"));
                article.setBody(rs.getString("body"));
                article.setCreated_at(rs.getDate("created_at"));
                return article;
            }
        };
    }
}


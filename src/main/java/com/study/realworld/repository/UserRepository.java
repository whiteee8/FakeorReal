package com.study.realworld.repository;

import com.study.realworld.domain.User;
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
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User save(User user){

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", user.getEmail());
        parameters.put("username", user.getUsername());
        parameters.put("password", user.getPassword());
        parameters.put("bio", user.getBio());
        parameters.put("image", user.getImage());
        parameters.put("created_at", user.getCreated_at());
        parameters.put("favorite_cnt", user.getCommentFavoriteCount());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        user.setId(key.longValue());
        return user;
    }

    public User findByEmail(String email){
        //return this.jdbcTemplate.queryForObject("SELECT id from user where email = ?", Long.class, email);

        List<User> results = jdbcTemplate.query("select * from user where email = ?", new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(rs.getLong("id"));
                        user.setEmail(rs.getString("email"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setBio(rs.getString("bio"));
                        user.setImage(rs.getString("image"));
                        user.setCreated_at(rs.getDate("created_at"));
                        user.setCommentFavoriteCount(rs.getLong("favorite_cnt"));
                        return user;
                    }
                }, email);
        return results.isEmpty() ? null : results.get(0);
    }

    public User findById(Long id){
        //return this.jdbcTemplate.queryForObject("SELECT id from user where email = ?", Long.class, email);

        List<User> results = jdbcTemplate.query("select * from user where id = ?", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setBio(rs.getString("bio"));
                user.setImage(rs.getString("image"));
                user.setCreated_at(rs.getDate("created_at"));
                user.setCommentFavoriteCount(rs.getLong("favorite_cnt"));
                return user;
            }
        }, id);
        return results.isEmpty() ? null : results.get(0);
    }

    public void updateFavoriteCnt(Long userId){
        User byId = findById(userId);
        Long cnt = byId.getCommentFavoriteCount();
        cnt--;
        jdbcTemplate.update("update user set favorite_cnt=? where id=?", cnt , userId);
    }
}

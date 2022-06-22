package com.study.realworld.domain.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByArticleId(Long articleId);

    List<Vote> findTop10ByOrderByFakeTrueDesc();
}

package com.study.realworld.domain.vote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteHistoryRepository extends JpaRepository<VoteHistory, Long> {
    VoteHistory findByArticleIdAndUserId(Long articleId, Long userId);
}

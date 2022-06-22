package com.study.realworld.domain.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByOrderId(Long orderId);

    Image findByOrderId(Long id);
}

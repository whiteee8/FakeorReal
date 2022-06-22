package com.study.realworld.domain.article;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "article")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    private short kind;

    private String slug;

    private String title;

    private String description;

    private String body;

    @Column(columnDefinition = "TEXT")
    private String img;

    private Date created_at;

    private Date updated_at;

    private Date deleted_at;

    public void update(Optional<String> slug, Optional<String> title, Optional<String> description, Optional<String> body) {
        slug.ifPresent((e) -> this.slug = e);
        title.ifPresent((e) -> this.title = e);
        description.ifPresent((e) -> this.description = e);
        body.ifPresent((e) -> this.body = e);
    }
}

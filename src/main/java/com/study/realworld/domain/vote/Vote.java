package com.study.realworld.domain.vote;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "vote")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long articleId;

    private int fakeTrue;

    private int fakeFalse;

    public void update(boolean fake) {
        if (fake)
            this.fakeTrue++;
        else
            this.fakeFalse++;
    }
}

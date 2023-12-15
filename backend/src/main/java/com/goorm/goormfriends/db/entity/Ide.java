package com.goorm.goormfriends.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Ide {
    @Id @GeneratedValue
    @Column(name = "ide_id")
    private Long id;
    private boolean solved;
    private String usercode;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    //==연관관계 메서드==//

    public void setProblem(Problem problem) {
        this.problem = problem;
        problem.getIdes().add(this);
    }
}

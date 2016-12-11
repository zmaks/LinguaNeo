package com.zheltoukhov.linguaneo.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Maksim on 07.12.2016.
 */
public abstract class CommonEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

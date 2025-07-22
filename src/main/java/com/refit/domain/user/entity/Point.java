package com.refit.domain.user.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Point {
    private int point;

    public Point() {
    }

    public Point(int point) {
        this.point = point;
    }
}

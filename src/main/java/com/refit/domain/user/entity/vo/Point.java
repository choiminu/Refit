package com.refit.domain.user.entity.vo;

import com.refit.global.exception.CustomException;
import com.refit.global.exception.ErrorCode;
import jakarta.persistence.Embeddable;

@Embeddable
public class Point {
    private int point;

    public Point() {
    }

    public Point(int point) {
        validatePoint(point);
        this.point = point;
    }


    private void validatePoint(int point) {
        validatePointRange(point);
    }

    private void validatePointRange(int point) {
        if (point < 0 || point > 1_000_000_000) {
            throw new CustomException(ErrorCode.FILE_SAVE_FAIL, "올바른 포인트가 아닙니다.");
        }
    }
}

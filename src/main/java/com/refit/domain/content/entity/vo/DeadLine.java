package com.refit.domain.content.entity.vo;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class DeadLine {

    private LocalDate deadLine;

    public DeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    private void validateDeadLine(LocalDate deadLine) {
        //TODO
    }
}

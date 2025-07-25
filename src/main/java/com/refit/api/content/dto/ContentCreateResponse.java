package com.refit.api.content.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentCreateResponse {
    private String title;
    private LocalDateTime createAt;
}

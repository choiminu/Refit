package com.refit.api.content.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentResponse {

    private Long id;
    private String body;
    private LocalDateTime createdAt;
    private String author;
}
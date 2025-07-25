package com.refit.api.content.dto;

import com.refit.domain.content.entity.enums.ContentCategory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ContentCreateRequest {

    private String title;

    private ContentCategory category;

    private Integer rewardPoint;

    private String body;

    private LocalDate deadLine;

    private List<MultipartFile> files;

}

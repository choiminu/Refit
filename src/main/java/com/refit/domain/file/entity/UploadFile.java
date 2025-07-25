package com.refit.domain.file.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UploadFile {
    private String originalFileName;
    private String storeFileName;
    private String filePath;
}

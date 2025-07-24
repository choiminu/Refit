package com.refit.domain.file.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadFile {
    private String originalFileName;
    private String storeFileName;
}

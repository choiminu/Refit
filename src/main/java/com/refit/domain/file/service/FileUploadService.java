package com.refit.domain.file.service;

import com.refit.domain.file.entity.UploadFile;
import com.refit.domain.file.exception.FileUploadException;
import com.refit.global.exception.ErrorCode;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileUploadService {

    @Value("${file.dir}")
    private String uploadDir;

    public String getPullPath(String fileName) {
        return uploadDir + fileName;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> files) {
        List<UploadFile> fileList = new LinkedList<>();

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                fileList.add(storeFile(file));
            }
        }

        return fileList;
    }

    public UploadFile storeFile(MultipartFile file) {

        if (file == null) {
            return null;
        }

        String originalFilename = file.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);

        try {
            file.transferTo(new File(getPullPath(storeFileName)));
            log.info("📁 [파일 저장 완료] fileName = {} size = {}", originalFilename, file.getSize());
        } catch (IOException e) {
            throw new FileUploadException(ErrorCode.FILE_SAVE_FAIL);
        }

        return new UploadFile(originalFilename, storeFileName);
    }

    private String createStoreFileName(String originalFileName) {
        return UUID.randomUUID() + "." + extractExt(originalFileName);
    }

    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }

}

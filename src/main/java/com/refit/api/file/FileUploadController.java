package com.refit.api.file;

import com.refit.api.file.dto.FileUploadResponse;
import com.refit.domain.file.entity.UploadFile;
import com.refit.domain.file.service.FileUploadService;
import java.net.MalformedURLException;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<FileUploadResponse> uploadFile(MultipartFile file) {
        UploadFile uploadFile = fileUploadService.storeFile(file);
        return ResponseEntity.ok(new FileUploadResponse(uploadFile.getStoreFileName()));
    }

    @GetMapping("/{filename}")
    public Resource getFile(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileUploadService.getPullPath(filename));
    }


}

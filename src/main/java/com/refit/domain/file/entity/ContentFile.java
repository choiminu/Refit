package com.refit.domain.file.entity;

import com.refit.domain.content.entity.Content;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ContentFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UploadFile uploadFile;

    private String fileUrl;

    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    public ContentFile(Content content, UploadFile uploadFile) {
        this.content = content;
        this.uploadFile = uploadFile;
        this.fileUrl = uploadFile.getFilePath();
    }

}

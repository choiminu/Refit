package com.refit.domain.content.entity;

import com.refit.domain.content.entity.enums.ContentCategory;
import com.refit.domain.content.entity.enums.ContentStatus;
import com.refit.domain.content.entity.vo.DeadLine;
import com.refit.domain.file.entity.ContentFile;
import com.refit.domain.file.entity.UploadFile;
import com.refit.domain.user.entity.User;
import com.refit.domain.user.entity.vo.Point;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Embedded
    @Column(name = "reward_point")
    private Point rewardPoint;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String body;

    @Enumerated(EnumType.STRING)
    private ContentCategory category;

    @Enumerated(EnumType.STRING)
    private ContentStatus status;

    @Builder.Default
    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContentFile> contentFiles = new ArrayList<>();

    @Embedded
    private DeadLine deadLine;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public void fileUpload(List<UploadFile> fileList) {
        if (!fileList.isEmpty()) {
            for (UploadFile uploadFile : fileList) {
                ContentFile contentFile = new ContentFile(this, uploadFile);
                this.contentFiles.add(contentFile);
            }
        }
    }


}

package com.refit.domain.content.service;

import com.refit.api.content.dto.ContentCreateRequest;
import com.refit.api.content.dto.ContentCreateResponse;
import com.refit.domain.content.entity.Content;
import com.refit.domain.content.entity.enums.ContentCategory;
import com.refit.domain.content.entity.enums.ContentStatus;
import com.refit.domain.content.entity.vo.DeadLine;
import com.refit.domain.content.repository.ContentRepository;
import com.refit.domain.file.entity.UploadFile;
import com.refit.domain.file.service.FileUploadService;
import com.refit.domain.user.entity.User;
import com.refit.domain.user.entity.vo.Point;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final FileUploadService fileUploadService;

    public ContentCreateResponse createContent(User user, ContentCreateRequest request) {

        Content content = Content.builder()
                .title(request.getTitle())
                .category(request.getCategory())
                .body(request.getBody())
                .rewardPoint(new Point(request.getRewardPoint()))
                .user(user)
                .createAt(LocalDateTime.now())
                .status(ContentStatus.BEFORE_REQUEST)
                .deadLine(new DeadLine(request.getDeadLine()))
                .build();

        content.fileUpload(fileUploadService.storeFiles(request.getFiles()));

        contentRepository.save(content);

        ContentCreateResponse response = new ContentCreateResponse();
        response.setTitle(content.getTitle());
        response.setCreateAt(content.getCreateAt());

        return response;
    }

    public Content findById(Long id) {
        return contentRepository.findById(id);
    }


}

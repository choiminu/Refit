package com.refit.api.content;

import com.refit.api.content.dto.ContentCreateRequest;
import com.refit.api.content.dto.ContentCreateResponse;
import com.refit.api.content.dto.ContentResponse;
import com.refit.domain.content.entity.Content;
import com.refit.domain.content.service.ContentService;
import com.refit.domain.user.entity.User;
import com.refit.global.response.ApiResponse;
import com.refit.global.utils.LoginCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ApiResponse<ContentCreateResponse> uploadContent(
            @LoginCheck User user,
            @ModelAttribute ContentCreateRequest request
    ) {
        ContentCreateResponse response = contentService.createContent(user, request);
        return ApiResponse.success(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponse> getContent(@PathVariable Long id) {
        Content content = contentService.findById(id);
        ContentResponse response = new ContentResponse(
                content.getId(),
                content.getBody(),
                null,
                null
        );
        return ResponseEntity.ok(response);
    }


}

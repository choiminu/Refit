package com.refit.user.application.service;

import com.refit.user.application.dto.UserResponse;
import com.refit.user.application.dto.UserSignupRequest;
import com.refit.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User signupRequestToEntity(UserSignupRequest req);
    UserResponse entityToResponse(User user);
}

package com.rehome.chat.service;

import com.rehome.chat.dto.UserProfileRequest;
import com.rehome.chat.dto.UserProfileResponse;
import com.rehome.chat.entity.UserProfile;

public interface UserProfileService {
    UserProfileResponse doesUsernameExists(String username);
    UserProfileResponse createProfile(UserProfileRequest userProfileRequest);
    UserProfileResponse updateProfile(UserProfileRequest userProfileRequest);
    UserProfileResponse viewProfile(String username);
	UserProfile getUserById(Long userId);
}

package com.rehome.chat.service;

import com.rehome.chat.dto.UserProfileRequest;
import com.rehome.chat.dto.UserProfileResponse;
import com.rehome.chat.entity.UserProfile;
import com.rehome.chat.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    /**
     *
     * @param username
     * @return sets userProfileAlreadyExists to true if username exists in User Profiles, else, false.
     */
    @Override
    public UserProfileResponse doesUsernameExists(String username) {
        return UserProfileResponse.builder()
            .userProfileAlreadyExists(
                StringUtils.isBlank(username) || doesUserProfileExistsByUsername(username)
            )
            .build();
    }

    /**
     *
     * @param userProfileRequest
     * @return
     * - sets userId to null if username exists in User Profiles, else, the generated User ID.
     * - sets userProfileAlreadyExists to true if username exists in User Profiles, else, false.
     */
    public UserProfileResponse createProfile(UserProfileRequest userProfileRequest) {
        if (doesUserProfileExistsByUsername(userProfileRequest.getUsername())) {
            return UserProfileResponse.builder().userId(null).userProfileAlreadyExists(true).build();
        }

        UserProfile newUserProfile = UserProfile
            .builder()
            .username(userProfileRequest.getUsername())
            .contactNumber(userProfileRequest.getContactNumber())
            .address(userProfileRequest.getAddress())
            .build();

        UserProfile createdUserProfile = userProfileRepository.save(newUserProfile);
        log.info("Created new user profile: " + createdUserProfile.getId());

        return UserProfileResponse.builder()
            .userId(createdUserProfile.getId())
            .userProfileAlreadyExists(false)
            .build();
    }

    /**
    *
    * @param userProfileRequest
    * @return
    * - updated user profile
    */
    public UserProfileResponse updateProfile(UserProfileRequest userProfileRequest) {
        String username = userProfileRequest.getUsername();

        if (StringUtils.isBlank(username) || !doesUserProfileExistsByUsername(username)) {
            return UserProfileResponse.builder().userId(null).userProfileAlreadyExists(false).build();
        }

        UserProfile existingUserProfile = userProfileRepository.findByUsername(username).orElse(null);

        existingUserProfile.setContactNumber(userProfileRequest.getContactNumber());
        existingUserProfile.setAddress(userProfileRequest.getAddress());

        UserProfile updatedUserProfile = userProfileRepository.save(existingUserProfile);

        log.info("Updated user profile: " + updatedUserProfile.getId());

        return UserProfileResponse.builder()
            .userId(updatedUserProfile.getId())
            .userProfileAlreadyExists(true)
            .build();
    }

    public UserProfileResponse viewProfile(String username) {
        if (StringUtils.isBlank(username)) {
            return UserProfileResponse.builder().userId(null).userProfileAlreadyExists(false).build();
        }

        UserProfile userProfile = userProfileRepository.findByUsername(username).orElse(null);

        if (userProfile == null) {
            return UserProfileResponse.builder().userId(null).userProfileAlreadyExists(false).build();
        }

        return UserProfileResponse.builder()
            .userId(userProfile.getId())
            .username(userProfile.getUsername())
            .contactNumber(userProfile.getContactNumber())
            .address(userProfile.getAddress())
            .userProfileAlreadyExists(true)
            .build();
    }

    /**
     *
     * @param username
     * @return if Username exists in User Profiles
     */
    private boolean doesUserProfileExistsByUsername(String username) {
        return userProfileRepository.existsByUsername(username);
    }

    @Override
    public UserProfile getUserById(Long userId) {
        return userProfileRepository.findById(userId).orElse(null);
    }
}

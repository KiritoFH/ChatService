package com.rehome.chat.service;

import com.rehome.chat.entity.UserInfo;
import com.rehome.chat.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserInfo getUserById(Long userId) {
        return userProfileRepository.findById(userId).orElse(null);
    }
}

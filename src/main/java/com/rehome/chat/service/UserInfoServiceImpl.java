package com.rehome.chat.service;

import com.rehome.chat.entity.UserInfo;
import com.rehome.chat.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

  @Autowired
  UserProfileRepository userProfileRepository;

  public UserInfoServiceImpl(UserProfileRepository userProfileRepository) {
    this.userProfileRepository = userProfileRepository;
  }
  public UserInfo getUserById(Long userId) {
      return userProfileRepository.findById(userId).orElse(null);
  }
}

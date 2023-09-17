package com.rehome.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserProfileRequest {
    private String username;
    private String password;
    private String contactNumber;
    private String address;
}

package com.rehome.chat.dto;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class UserProfileResponse {
    private Long userId;
    private String username;
    private String contactNumber;
    private String address;

    private boolean userProfileAlreadyExists;

    public Long getUserId() { return userId; }

    public String getUsername() {
		return username;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public boolean userProfileAlreadyExists() { return userProfileAlreadyExists; }
}

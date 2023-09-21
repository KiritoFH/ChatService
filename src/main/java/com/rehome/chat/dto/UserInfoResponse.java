package com.rehome.chat.dto;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class UserInfoResponse {
    private Long userId;
    private String username;

    public Long getUserId() { return userId; }

    public String getUsername() {
		return username;
	}

}

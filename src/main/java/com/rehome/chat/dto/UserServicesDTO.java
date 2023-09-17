package com.rehome.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class UserServicesDTO {
	private Long userId;
    private List<Long> serviceIds;
    //private Float suggestedHourlyRate;

    private boolean userServiceAssignmentAlreadyExists;

    public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<Long> getServiceIds() {
		return serviceIds;
	}
	/*public Float getSuggestedHourlyRate() {
		return suggestedHourlyRate;
	}
	public void setSuggestedHourlyRate(Float suggestedHourlyRate) {
		this.suggestedHourlyRate = suggestedHourlyRate;
	}*/
	public boolean userServiceAssignmentAlreadyExists() { return userServiceAssignmentAlreadyExists; }

}

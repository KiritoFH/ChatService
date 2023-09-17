package com.rehome.chat.service;

import com.rehome.chat.dto.UserServicesDTO;
import com.rehome.chat.entity.Services;

import java.util.List;

public interface UserServicesService {
	UserServicesDTO assignServiceToUser(UserServicesDTO request);

	List<Services> listServicesForHandyman(Long handymanId);

	void deleteServiceAssignment(Long assignmentId);
}

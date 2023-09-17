package com.rehome.chat.service;

import com.rehome.chat.dto.UserServicesDTO;
import com.rehome.chat.entity.Services;
import com.rehome.chat.entity.UserProfile;
import com.rehome.chat.entity.UserServices;
import com.rehome.chat.repository.UserServicesRepository;
import com.rehome.chat.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServicesServiceImpl implements UserServicesService{

	@Autowired
    private WebClient webClient;

	@Autowired
    private UserServicesRepository userServicesRepository;

	@Autowired
    private UserProfileService userProfileService;

    @Autowired
    private ServicesService serviceService;

    @Override
    public UserServicesDTO assignServiceToUser(UserServicesDTO request) {

        Long userId = request.getUserId();
        List<Long> serviceIds = request.getServiceIds();
        //Float suggestedHourlyRate = request.getSuggestedHourlyRate();

        UserProfile user = userProfileService.getUserById(userId);

        if (user == null) {
            throw new EntityNotFoundException("User not found.");
        }

        List<UserServices> assignedServices = new ArrayList<>();

        for (Long serviceId : serviceIds) {
        	Services service = serviceService.getServiceById(serviceId);

        	if (service == null) {
                throw new EntityNotFoundException("Service not found.");
            }

        	if (userServicesRepository.existsByUserAndService(user, service)) {
                throw new EntityExistsException("Assignment already exists.");
            }

        	UserServices userServices = new UserServices();
            userServices.setUser(user);
            userServices.setService(service);
            //userServices.setSuggestedHourlyRate(suggestedHourlyRate);

            assignedServices.add(userServices);
        }

        userServicesRepository.saveAll(assignedServices);

        return UserServicesDTO.builder()
        		.userId(userId)
                .serviceIds(serviceIds)
                .userServiceAssignmentAlreadyExists(false)
                .build();
    }

    @Override
    public List<Services> listServicesForHandyman(Long handymanId) {

        UserProfile handyman = userProfileService.getUserById(handymanId);
        List<UserServices> userServices = userServicesRepository.findByUser(handyman);
        List<Services> services = userServices.stream()
                                              .map(UserServices::getService)
                                              .collect(Collectors.toList());

        return services;
    }

    @Override
    public void deleteServiceAssignment(Long assignmentId) {
        Optional<UserServices> assignmentOptional = userServicesRepository.findById(assignmentId);

        if (assignmentOptional.isEmpty()) {
            throw new EntityNotFoundException("Service assignment not found.");
        }

        UserServices assignment = assignmentOptional.get();

        if (SecurityUtils.doesCurrentUserHaveAuthority("ROLE_HANDYMAN")) {
            userServicesRepository.delete(assignment);
        }
    }
}





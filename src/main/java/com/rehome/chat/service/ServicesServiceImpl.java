package com.rehome.chat.service;

import com.rehome.chat.entity.Services;
import com.rehome.chat.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServicesServiceImpl implements ServicesService {

	@Autowired
    private ServiceRepository serviceRepository;

	@Override
    public Services getServiceById(Long serviceId) {
        return serviceRepository.findById(serviceId).orElse(null);
    }

}

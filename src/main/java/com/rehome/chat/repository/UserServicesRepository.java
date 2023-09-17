package com.rehome.chat.repository;

import com.rehome.chat.entity.Services;
import com.rehome.chat.entity.UserProfile;
import com.rehome.chat.entity.UserServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserServicesRepository extends JpaRepository<UserServices, Long> {
	boolean existsByUserAndService(UserProfile user, Services service);
	List<UserServices> findByUser(UserProfile user);
}

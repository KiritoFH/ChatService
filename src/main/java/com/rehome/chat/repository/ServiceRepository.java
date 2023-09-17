package com.rehome.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rehome.chat.entity.Services;

public interface ServiceRepository extends JpaRepository<Services, Long> {

}

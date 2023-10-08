package com.rehome.chat.service;

import com.rehome.chat.entity.Chat;
import com.rehome.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
  @Autowired
  ChatRepository chatRepository;

  public List<Chat> getAllChatForAppointmentId(Long appointmentId) {
      return chatRepository.findByAppointmentId(appointmentId);
  }

  @Override
  public void closeChat(Long appointmentId) {

  }

}

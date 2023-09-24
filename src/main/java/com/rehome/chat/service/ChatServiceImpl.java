package com.rehome.chat.service;

import com.rehome.chat.entity.Chat;
import com.rehome.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ChatServiceImpl implements ChatService {

  private final Logger logger = Logger.getLogger(ChatServiceImpl.class.getName());

  @Autowired
  ChatRepository chatRepository;


  public List<Chat> getAllChatForAppointmentId(Long appointmentId) {
      return chatRepository.findByAppointmentId(appointmentId);
  }

  @Override
  public void closeChat(Long appointmentId) {

  }

}

package com.rehome.chat.service;

import com.rehome.chat.entity.Chat;
import com.rehome.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
  @Autowired
  ChatRepository chatRepository;

  public List<Chat> getAllChatForAppointmentId(Long appointmentId) {
      return chatRepository.findByAppointmentId(appointmentId);
  }

  public Chat createNewChat(Long appointmentId, Long from, Long to, String content) {
    Chat newChat = null;
    newChat.setSeen("FALSE");
    newChat.setAppointmentId(appointmentId);
    newChat.setMessage(content);
    newChat.setSenderId(from);
    newChat.setReceiverId(to);
    newChat.setDateCreated(LocalDateTime.now());
    return chatRepository.save(newChat);
  }

  @Override
  public void closeChat(Long appointmentId) {

  }

}

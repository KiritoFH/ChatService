package com.rehome.chat.service;

import com.rehome.chat.entity.Chat;
import com.rehome.chat.exception.ChatBadRequestException;
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

  public Chat createNewChat(Chat chat) {
    if (null == chat.getReceiverId() ||null == chat.getSenderId() ) {
      throw new ChatBadRequestException("Chat cannot be created as mandatory fields missing");
    }
    return chatRepository.save(chat);
  }

  @Override
  public void closeChat(Long appointmentId) {

  }

}

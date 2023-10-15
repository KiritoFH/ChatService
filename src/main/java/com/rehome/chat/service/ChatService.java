package com.rehome.chat.service;

import com.rehome.chat.entity.Chat;

import java.util.List;


public interface ChatService {

    public List<Chat> getAllChatForAppointmentId(Long receiverId);

   public Chat createNewChat(Long appointmentId, Long from, Long to, String content);

    public void closeChat(Long appointmentId);

}

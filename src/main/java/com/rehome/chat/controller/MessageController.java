package com.rehome.chat.controller;

import com.rehome.chat.entity.Chat;
import com.rehome.chat.model.MessageModel;
import com.rehome.chat.repository.ChatRepository;
import com.rehome.chat.service.ChatService;
import com.rehome.chat.storage.UserStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/chat")
public class MessageController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    ChatService chatService;

    @Autowired
    ChatRepository chatRepository;

  @GetMapping("/ping")
  public String ping() {
    String response = "/chat/ping pinged";
    log.info(response);
    return response;
  }

  @GetMapping("/chat/{appointmentId}")
  public ResponseEntity<List<Chat>> getAllChatForAppointmentID(@PathVariable("appointmentId") Long appointmentId) {
    if (appointmentId == null || appointmentId < 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Appointment ID");
    }

    List<Chat> handyChats = chatService.getAllChatForAppointmentId(appointmentId);
    return ResponseEntity.ok().body(handyChats);
  }


  @MessageMapping("/chat/{to}")
  public void sendMessage(@DestinationVariable String to, MessageModel message) {
      System.out.println("handling send message: " + message + " to: " + to);
      boolean isExists = UserStorage.getInstance().getUsers().contains(to);
      if (isExists) {
          simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
      }
  }
}

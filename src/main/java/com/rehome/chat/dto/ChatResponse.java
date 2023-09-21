package com.rehome.chat.dto;

import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@ToString
public class ChatResponse {
    private Long chatId;

    private Long senderId;

    private Long receiverId;

    private String message;

    private Long appointmentId;

    private String seen;

    private LocalDateTime dateCreated;

    public Long getChatId() {
      return chatId;
    }

    public void setChatId(Long chatId) {
      this.chatId = chatId;
    }

    public Long getSenderId() {
      return senderId;
    }

    public void setSenderId(Long senderId) {
      this.senderId = senderId;
    }

    public Long getReceiverId() {
      return receiverId;
    }

    public void setReceiverId(Long receiverId) {
      this.receiverId = receiverId;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public Long getAppointmentId() {
      return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
      this.appointmentId = appointmentId;
    }

    public String getSeen() {
      return seen;
    }

    public void setSeen(String seen) {
      this.seen = seen;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
      this.dateCreated = dateCreated;
    }
}

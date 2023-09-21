package com.rehome.chat.dto;

import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@ToString
public class ServiceFeeResponse {
    private Long chatId;

    private Long senderId;

    private Long receiverId;

    private int amount;

    private Long appointmentId;

    private LocalDateTime dateLastMod;

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

    public int getAmount() {
      return amount;
    }

    public void setAmount(int amount) {
      this.amount = amount;
    }

    public Long getAppointmentId() {
      return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
      this.appointmentId = appointmentId;
    }

    public LocalDateTime getDateLastMod() {
      return dateLastMod;
    }

    public void setDateLastMod(LocalDateTime dateLastMod) {
      this.dateLastMod = dateLastMod;
    }
}

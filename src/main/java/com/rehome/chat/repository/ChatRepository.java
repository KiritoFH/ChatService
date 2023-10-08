package com.rehome.chat.repository;

import com.rehome.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
  // List ALL receiver chats
  List<Chat> findByReceiverId(Long receiverId);

  // List ALL sender chats
  List<Chat> findBySenderId(Long senderId);

  // List chat when match with appointmentid
  @Query(value = "select * from chat where appointment_id = :appointmentId", nativeQuery = true)
  List<Chat> findByAppointmentId(@Param("appointmentId") Long appointmentId);

  @Query(value = "select * from chat a where a.receiver_id = :receiverId", nativeQuery = true)
  List<Chat> findAllChatsForReceiver(@Param("receiverId") Long receiverId);

  @Query(value = "select * from chat a where a.sender_id = :senderId", nativeQuery = true)
  List<Chat> findAllChatsForSender(@Param("senderId") Long senderId);
}

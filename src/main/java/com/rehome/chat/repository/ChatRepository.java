package com.rehome.chat.repository;

import com.rehome.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findByChatId(Long chatId);
}

package com.rehome.chat.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long chatId;

    @NotBlank
    @Column(name = "sender_id")
    private Long senderId;

    @NotBlank
    @Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "message")
    private String message;

    @NotBlank
    @Column(name = "appointment_id")
    private Long appointmentId;

    @Column(name = "seen")
    private String seen;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;
}

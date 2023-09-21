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

public class ServiceFee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "sender_id")
    private Long senderId;

    @NotBlank
    @Column(name = "receiver_id")
    private Long receiverId;

    @Column(name = "amount")
    private int amount;

    @NotBlank
    @Column(name = "appointment_id")
    private String appointmentId;

    @NotBlank
    @Column(name = "date_last_mod")
    private LocalDateTime dateLastMod;
}

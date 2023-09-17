package com.rehome.chat.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "user_profile",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
    }
)
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 36)
    @Column(name = "username")
    private String username;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "address")
    private String address;
}

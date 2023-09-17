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
    name = "services",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
    }
)
public class Services {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 36)
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_service_id")
    private Services parentService;
}

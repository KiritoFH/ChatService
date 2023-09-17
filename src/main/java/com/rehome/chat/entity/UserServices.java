package com.rehome.chat.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(
	 name = "user_services",
	 uniqueConstraints = {
			 @UniqueConstraint(columnNames = {"user_id", "service_id"})
	 }
)
public class UserServices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserProfile user;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Services service;

	@Column(name = "suggested_hourly_rate")
	private Float suggestedHourlyRate;

	public Long getId() {
		return id;
	}

	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

	public Services getService() {
		return service;
	}

	public void setService(Services service) {
		this.service = service;
	}

	public Float getSuggestedHourlyRate() {
		return suggestedHourlyRate;
	}

	public void setSuggestedHourlyRate(Float suggestedHourlyRate) {
		this.suggestedHourlyRate = suggestedHourlyRate;
	}
}

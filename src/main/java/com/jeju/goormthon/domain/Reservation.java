package com.jeju.goormthon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "hospital_id", nullable = false)
	private Long hospitalId;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(name = "symptoms", columnDefinition = "TEXT")
	private String symptoms;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "status")
	private String status; // PENDING, CANCELLED

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		if (status == null) {
			status = "PENDING";
		}
	}
}
package com.jeju.goormthon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("hospital_id")
	private Long hospitalId;

	@JsonProperty("hospital_name")
	private String hospitalName;

	@JsonProperty("phone_number")
	private String phoneNumber;

	@JsonProperty("symptoms")
	private String symptoms;

	@JsonProperty("status")
	private String status;

	@JsonProperty("created_at")
	private LocalDateTime createdAt;
}
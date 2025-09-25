package com.jeju.goormthon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "예약 신청 요청")
public class ReservationRequest {

	@NotNull(message = "병원 ID는 필수입니다.")
	@JsonProperty("hospital_id")
	@Schema(description = "병원 ID", example = "1")
	private Long hospitalId;

	@NotBlank(message = "휴대폰 번호는 필수입니다.")
	@Pattern(regexp = "^[0-9-]+$", message = "휴대폰 번호는 숫자와 하이픈만 입력 가능합니다.")
	@JsonProperty("phone_number")
	@Schema(description = "휴대폰 번호", example = "010-1234-5678")
	private String phoneNumber;

	@NotBlank(message = "증상 내용은 필수입니다.")
	@JsonProperty("symptoms")
	@Schema(description = "증상 내용", example = "머리가 아프고 열이 납니다.")
	private String symptoms;
}
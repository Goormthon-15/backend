package com.jeju.goormthon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

	@JsonProperty("code")
	private Integer code;

	@JsonProperty("message")
	private String message;

	@JsonProperty("data")
	private T data;

	public static <T> ApiResponse<T> success(Integer code, String message, T data) {
		return ApiResponse.<T>builder()
			.code(code)
			.message(message)
			.data(data)
			.build();
	}

	public static <T> ApiResponse<T> error(Integer code, String message) {
		return ApiResponse.<T>builder()
			.code(code)
			.message(message)
			.build();
	}
}
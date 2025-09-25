package com.jeju.goormthon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GptRequestDto {

	private String model;
	private java.util.List<GptMessage> messages;
	private double temperature;
	private int max_tokens;
}
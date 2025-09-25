package com.jeju.goormthon.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GptResponseDto {

	private java.util.List<Choice> choices;

	@Getter
	@NoArgsConstructor
	public static class Choice {

		private GptMessage message;
	}
}
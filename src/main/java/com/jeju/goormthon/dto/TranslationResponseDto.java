package com.jeju.goormthon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranslationResponseDto {

	private String originalMessage;
	private String detectedLanguage;
	private String translatedMessage;
	private Long translationId;
}
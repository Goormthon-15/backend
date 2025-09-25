package com.jeju.goormthon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 번역 요청 DTO
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TranslationRequestDto {

	private String message;
}
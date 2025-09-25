package com.jeju.goormthon.controller;

import com.jeju.goormthon.dto.ApiResponse;
import com.jeju.goormthon.dto.TranslationRequestDto;
import com.jeju.goormthon.dto.TranslationResponseDto;
import com.jeju.goormthon.service.TranslationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/translation")
@RequiredArgsConstructor
@Tag(name = "번역 API", description = "메시지 번역을 처리하는 API")
public class TranslationController {

	private final TranslationService translationService;

	@PostMapping
	public ApiResponse<TranslationResponseDto> translate(
		@RequestBody TranslationRequestDto request) {
		try {
			if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
				return ApiResponse.error(400, "번역할 메시지가 필요합니다.");
			}

			TranslationResponseDto result = translationService.translateMessage(
				request.getMessage());
			return ApiResponse.success(200, "번역이 완료되었습니다.", result);

		} catch (Exception e) {
			return ApiResponse.error(500, "번역 처리 중 오류가 발생했습니다: " + e.getMessage());
		}
	}
}
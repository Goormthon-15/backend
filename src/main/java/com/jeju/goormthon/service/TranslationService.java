package com.jeju.goormthon.service;

import com.jeju.goormthon.domain.TranslationHistory;
import com.jeju.goormthon.dto.GptMessage;
import com.jeju.goormthon.dto.GptRequestDto;
import com.jeju.goormthon.dto.GptResponseDto;
import com.jeju.goormthon.dto.TranslationResponseDto;
import com.jeju.goormthon.repository.TranslationHistoryRepository;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class TranslationService {

	private final TranslationHistoryRepository translationHistoryRepository;
	private final WebClient webClient;

	@Value("${openai.api.key}")
	private String openaiApiKey;

	@Value("${openai.api.url}")
	private String openaiApiUrl;

	public TranslationResponseDto translateMessage(String message) {
		try {
			// GPT에게 언어 감지 및 번역 요청
			String gptResponse = callGptApi(message);

			// GPT 응답 파싱
			TranslationResult result = parseGptResponse(gptResponse);

			// DB에 저장
			TranslationHistory history = TranslationHistory.builder()
				.originalMessage(message)
				.detectedLanguage(result.getDetectedLanguage())
				.translatedMessage(result.getTranslatedMessage())
				.build();

			TranslationHistory saved = translationHistoryRepository.save(history);

			return TranslationResponseDto.builder()
				.originalMessage(message)
				.detectedLanguage(result.getDetectedLanguage())
				.translatedMessage(result.getTranslatedMessage())
				.translationId(saved.getId())
				.build();

		} catch (Exception e) {
			log.error("번역 처리 중 오류 발생: ", e);
			throw new RuntimeException("번역 처리 중 오류가 발생했습니다.");
		}
	}

	private String callGptApi(String message) {
		String prompt = String.format("""
            다음 메시지의 언어를 감지하고 한국어로 번역해주세요.
            
            응답 형식:
            언어: [감지된 언어명]
            번역: [한국어 번역 결과]
            
            메시지: %s
            """, message);

		List<GptMessage> messages = Arrays.asList(
			new GptMessage("system", "당신은 전문 번역가입니다. 언어를 정확히 감지하고 자연스러운 한국어로 번역해주세요."),
			new GptMessage("user", prompt)
		);

		GptRequestDto request = GptRequestDto.builder()
			.model("gpt-4o-mini")
			.messages(messages)
			.temperature(0.3)
			.max_tokens(1000)
			.build();

		Mono<GptResponseDto> response = webClient.post()
			.uri(openaiApiUrl)
			.header("Authorization", "Bearer " + openaiApiKey)
			.header("Content-Type", "application/json")
			.bodyValue(request)
			.retrieve()
			.bodyToMono(GptResponseDto.class);

		GptResponseDto gptResponse = response.block();

		if (gptResponse != null && !gptResponse.getChoices().isEmpty()) {
			return gptResponse.getChoices().get(0).getMessage().getContent();
		}

		throw new RuntimeException("GPT API 응답을 받을 수 없습니다.");
	}

	private TranslationResult parseGptResponse(String response) {
		// GPT 응답에서 언어와 번역 결과 추출
		Pattern languagePattern = Pattern.compile("언어:\\s*(.+)");
		Pattern translationPattern = Pattern.compile("번역:\\s*(.+)");

		Matcher languageMatcher = languagePattern.matcher(response);
		Matcher translationMatcher = translationPattern.matcher(response);

		String detectedLanguage = languageMatcher.find() ? languageMatcher.group(1).trim() : "Unknown";
		String translatedMessage = translationMatcher.find() ? translationMatcher.group(1).trim() : response;

		return new TranslationResult(detectedLanguage, translatedMessage);
	}

	@lombok.AllArgsConstructor
	@lombok.Getter
	private static class TranslationResult {
		private String detectedLanguage;
		private String translatedMessage;
	}
}
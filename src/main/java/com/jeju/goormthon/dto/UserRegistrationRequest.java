package com.jeju.goormthon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeju.goormthon.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationRequest {

	// 이름 - 특수문자 제한 (하이픈, 아포스트로피만 허용)
	@NotBlank(message = "이름을 입력해주세요")
	@Schema(example = "john")
	@Size(min = 1, max = 50, message = "이름은 1자 이상 50자 이하여야 합니다")
	@Pattern(regexp = "^[a-zA-Z가-힣\\s'-]+$", message = "이름에는 문자, 하이픈(-), 아포스트로피(')만 사용할 수 있습니다")
	@JsonProperty("firstName")
	private String firstName;

	// 성 - 특수문자 제한 (하이픈, 아포스트로피만 허용)
	@NotBlank(message = "성을 입력해주세요")
	@Schema(example = "Smith")
	@Size(min = 1, max = 50, message = "성은 1자 이상 50자 이하여야 합니다")
	@Pattern(regexp = "^[a-zA-Z가-힣\\s'-]+$", message = "성에는 문자, 하이픈(-), 아포스트로피(')만 사용할 수 있습니다")
	@JsonProperty("lastName")
	private String lastName;

	// 생년월일 - YYYY.MM.DD 형식
	@NotBlank(message = "생년월일을 입력해주세요")
	@Pattern(regexp = "^(19|20)\\d{2}\\.(0[1-9]|1[0-2])\\.(0[1-9]|[12]\\d|3[01])$",
		message = "생년월일은 YYYY.MM.DD 형식으로 입력해주세요")
	@JsonProperty("birthDate")
	private String birthDate;

	// 성별 - MALE/FEMALE
	@NotNull(message = "성별을 선택해주세요")
	@JsonProperty("gender")
	private User.Gender gender;

	// 사용언어 - English/Chinese
	@NotNull(message = "사용 언어를 선택해주세요")
	@JsonProperty("language")
	private Language language;

	// 언어 옵션 enum
	public enum Language {
		ENGLISH("English us"),
		CHINESE("Chinese cn"),
		KOREA("korean kr");

		private final String displayName;

		Language(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}

		// 기존 시스템과의 호환성을 위한 매핑
		public String toLegacyCode() {
			switch (this) {
				case ENGLISH: return "en";
				case CHINESE: return "zh";
				case KOREA: return "kr";
				default: return "en";
			}
		}
	}
}
package com.jeju.goormthon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jeju.goormthon.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("birthDate")
	private String birthDate;

	@JsonProperty("gender")
	private String gender;

	@JsonProperty("genderKorean")
	private String genderKorean;

	@JsonProperty("language")
	private String language;

	@JsonProperty("role")
	private String role;

	@JsonProperty("roleKorean")
	private String roleKorean;

	public static UserResponse from(User user) {
		return UserResponse.builder()
			.id(user.getId())
			.firstName(user.getFirstName())
			.lastName(user.getLastName())
			.birthDate(user.getBirthDate())
			.gender(user.getGender().name())
			.genderKorean(user.getGender().getKoreanName())
			.language(user.getPreferredLang())
			.role(user.getRole().name())
			.roleKorean(user.getRole().getKoreanName())
			.build();
	}
}

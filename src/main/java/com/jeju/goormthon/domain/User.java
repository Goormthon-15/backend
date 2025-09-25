package com.jeju.goormthon.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String lastName;

	@Column(nullable = false, length = 10)
	private String birthDate; // "YYYY.MM.DD" 형태

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Gender gender;

	@Column(nullable = false, length = 10)
	private String preferredLang; // "en", "zh" 등

	@Enumerated(EnumType.STRING)
	@Builder.Default
	@Column(nullable = false)
	private Role role = Role.PATIENT; // 기본값으로 PATIENT 설정

	@Builder.Default
	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Builder.Default
	@Column(nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();

	public enum Gender {
		MALE("남"),
		FEMALE("여");

		private final String koreanName;

		Gender(String koreanName) {
			this.koreanName = koreanName;
		}

		public String getKoreanName() {
			return koreanName;
		}
	}

	public enum Role {
		PATIENT("환자");

		private final String koreanName;

		Role(String koreanName) {
			this.koreanName = koreanName;
		}

		public String getKoreanName() {
			return koreanName;
		}
	}
}

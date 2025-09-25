package com.jeju.goormthon.service;

import com.jeju.goormthon.domain.User;
import com.jeju.goormthon.dto.UserRegistrationRequest;
import com.jeju.goormthon.dto.UserResponse;
import com.jeju.goormthon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

	private final UserRepository userRepository;

	public UserResponse registerUser(UserRegistrationRequest request) {

		// 생년월일 유효성 추가 검증 (미래 날짜 체크)
		if (isFutureDate(request.getBirthDate())) {
			throw new IllegalArgumentException("생년월일은 미래 날짜일 수 없습니다.");
		}

		// User 엔티티 생성
		User user = User.builder()
			.firstName(request.getFirstName())
			.lastName(request.getLastName())
			.birthDate(request.getBirthDate())
			.gender(request.getGender())
			.preferredLang(request.getLanguage().toLegacyCode())
			.role(User.Role.PATIENT) // 기본값으로 PATIENT 설정
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();

		// DB 저장
		User savedUser = userRepository.save(user);
		log.info("사용자 등록 완료: ID={}", savedUser.getId());

		// Response DTO 반환
		return UserResponse.from(savedUser);
	}

	private boolean isFutureDate(String birthDate) {
		try {
			String[] parts = birthDate.split("\\.");
			int year = Integer.parseInt(parts[0]);
			int month = Integer.parseInt(parts[1]);
			int day = Integer.parseInt(parts[2]);

			LocalDateTime inputDate = LocalDateTime.of(year, month, day, 0, 0);
			return inputDate.isAfter(LocalDateTime.now());
		} catch (Exception e) {
			return false;
		}
	}
}
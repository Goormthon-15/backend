package com.jeju.goormthon.config;

import com.jeju.goormthon.domain.User;
import com.jeju.goormthon.domain.User.Gender;
import com.jeju.goormthon.domain.User.Role;
import com.jeju.goormthon.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UserInitializer {

	private final UserRepository userRepository;

	@PostConstruct
	public void init() {
		if (userRepository.count() == 0) {
			createUsers();
		}
	}

	private void createUsers() {
		User user1 = User.builder()
			.firstName("John")
			.lastName("Doe")
			.birthDate("1990.05.15")
			.gender(Gender.MALE)
			.preferredLang("ko")
			.role(Role.PATIENT)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();

		User user2 = User.builder()
			.firstName("Jane")
			.lastName("Smith")
			.birthDate("1985.10.20")
			.gender(Gender.FEMALE)
			.preferredLang("en")
			.role(Role.PATIENT)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();

		userRepository.save(user1);
		userRepository.save(user2);
	}
}
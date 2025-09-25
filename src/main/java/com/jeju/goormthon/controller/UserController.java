package com.jeju.goormthon.controller;

import com.jeju.goormthon.dto.ApiResponse;
import com.jeju.goormthon.dto.UserRegistrationRequest;
import com.jeju.goormthon.dto.UserResponse;
import com.jeju.goormthon.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<ApiResponse<UserResponse>> registerUser(
		@Valid @RequestBody UserRegistrationRequest request) {

		log.info("POST /api/users - 사용자 등록 요청");

		try {
			UserResponse userResponse = userService.registerUser(request);

			ApiResponse<UserResponse> response = ApiResponse.success(
				201,
				"사용자 등록 완료",
				userResponse
			);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (IllegalArgumentException e) {
			log.error("사용자 등록 실패: {}", e.getMessage());

			ApiResponse<UserResponse> errorResponse = ApiResponse.error(
				400,
				e.getMessage()
			);

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		} catch (Exception e) {
			log.error("사용자 등록 중 서버 오류 발생", e);

			ApiResponse<UserResponse> errorResponse = ApiResponse.error(
				500,
				"서버 오류가 발생했습니다."
			);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
	}
}
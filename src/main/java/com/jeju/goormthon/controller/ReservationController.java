package com.jeju.goormthon.controller;

import com.jeju.goormthon.dto.ApiResponse;
import com.jeju.goormthon.dto.ReservationRequest;
import com.jeju.goormthon.dto.ReservationResponse;
import com.jeju.goormthon.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;

	/**
	 * 예약 신청
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<ReservationResponse>> createReservation(
		@Valid @RequestBody ReservationRequest request) {
		try {
			ReservationResponse response = reservationService.createReservation(request);
			return ResponseEntity.ok(ApiResponse.success(200, "예약 신청이 완료되었습니다.", response));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest()
				.body(ApiResponse.error(400, e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(500)
				.body(ApiResponse.error(500, "예약 신청 중 오류가 발생했습니다."));
		}
	}

	/**
	 * 예약 상세 조회
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ReservationResponse>> getReservation(@PathVariable Long id) {
		try {
			ReservationResponse response = reservationService.getReservationById(id);
			return ResponseEntity.ok(ApiResponse.success(200, "예약 조회 성공", response));
		} catch (RuntimeException e) {
			return ResponseEntity.status(404)
				.body(ApiResponse.error(404, e.getMessage()));
		}
	}

	/**
	 * 병원별 예약 목록 조회
	 */
	@GetMapping("/hospital/{hospitalId}")
	public ResponseEntity<ApiResponse<List<ReservationResponse>>> getReservationsByHospital(
		@PathVariable Long hospitalId) {
		List<ReservationResponse> reservations = reservationService.getReservationsByHospital(hospitalId);
		return ResponseEntity.ok(ApiResponse.success(200, "병원별 예약 목록 조회 성공", reservations));
	}

}
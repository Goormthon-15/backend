package com.jeju.goormthon.controller;

import com.jeju.goormthon.domain.Hospital;
import com.jeju.goormthon.dto.ApiResponse;
import com.jeju.goormthon.service.HospitalService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {

	private final HospitalService hospitalService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Hospital>>> getHospitals() {
		List<Hospital> hospitals = hospitalService.getAllHospitals();
		return ResponseEntity.ok(ApiResponse.success(200, "병원 목록 조회 성공", hospitals));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Hospital>> getHospital(@PathVariable Long id) {
		Hospital hospital = hospitalService.getHospitalById(id);
		return ResponseEntity.ok(ApiResponse.success(200, "병원 상세 조회 성공", hospital));
	}

	@PutMapping("/{id}/favorite")
	public ResponseEntity<ApiResponse<Hospital>> toggleFavorite(@PathVariable Long id) {
		Hospital hospital = hospitalService.toggleFavorite(id);
		return ResponseEntity.ok(ApiResponse.success(200, "즐겨찾기 변경 완료", hospital));
	}
}
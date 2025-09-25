package com.jeju.goormthon.service;

import com.jeju.goormthon.domain.Hospital;
import com.jeju.goormthon.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

	private final HospitalRepository hospitalRepository;

	public List<Hospital> getAllHospitals() {
		return hospitalRepository.findAll();
	}

	public Hospital getHospitalById(Long id) {
		return hospitalRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("병원을 찾을 수 없습니다."));
	}

	public Hospital toggleFavorite(Long id) {
		Hospital hospital = getHospitalById(id);
		hospital.setIsFavorite(!hospital.getIsFavorite());
		return hospitalRepository.save(hospital);
	}
}
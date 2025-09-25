package com.jeju.goormthon.service;

import com.jeju.goormthon.domain.Hospital;
import com.jeju.goormthon.domain.Reservation;
import com.jeju.goormthon.dto.ReservationRequest;
import com.jeju.goormthon.dto.ReservationResponse;
import com.jeju.goormthon.repository.HospitalRepository;
import com.jeju.goormthon.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final HospitalRepository hospitalRepository;

	@Transactional
	public ReservationResponse createReservation(ReservationRequest request) {
		// 병원 존재 여부 확인
		Hospital hospital = hospitalRepository.findById(request.getHospitalId())
			.orElseThrow(() -> new RuntimeException("병원을 찾을 수 없습니다. ID: " + request.getHospitalId()));

		// 예약 생성
		Reservation reservation = Reservation.builder()
			.hospitalId(request.getHospitalId())
			.phoneNumber(request.getPhoneNumber())
			.symptoms(request.getSymptoms())
			.build();

		Reservation savedReservation = reservationRepository.save(reservation);
		log.info("새로운 예약이 생성되었습니다. 예약 ID: {}, 병원: {}", savedReservation.getId(), hospital.getName());

		return convertToResponse(savedReservation, hospital);
	}

	public List<ReservationResponse> getReservationsByHospital(Long hospitalId) {
		List<Reservation> reservations = reservationRepository.findByHospitalId(hospitalId);
		return reservations.stream()
			.map(reservation -> {
				Hospital hospital = hospitalRepository.findById(reservation.getHospitalId()).orElse(null);
				return convertToResponse(reservation, hospital);
			})
			.collect(Collectors.toList());
	}

	public List<ReservationResponse> getReservationsByPhone(String phoneNumber) {
		List<Reservation> reservations = reservationRepository.findByPhoneNumber(phoneNumber);
		return reservations.stream()
			.map(reservation -> {
				Hospital hospital = hospitalRepository.findById(reservation.getHospitalId()).orElse(null);
				return convertToResponse(reservation, hospital);
			})
			.collect(Collectors.toList());
	}

	public ReservationResponse getReservationById(Long id) {
		Reservation reservation = reservationRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("예약을 찾을 수 없습니다. ID: " + id));

		Hospital hospital = hospitalRepository.findById(reservation.getHospitalId()).orElse(null);
		return convertToResponse(reservation, hospital);
	}

	@Transactional
	public ReservationResponse updateReservationStatus(Long id, String status) {
		Reservation reservation = reservationRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("예약을 찾을 수 없습니다. ID: " + id));

		reservation.setStatus(status);
		Reservation updatedReservation = reservationRepository.save(reservation);

		Hospital hospital = hospitalRepository.findById(reservation.getHospitalId()).orElse(null);
		return convertToResponse(updatedReservation, hospital);
	}

	private ReservationResponse convertToResponse(Reservation reservation, Hospital hospital) {
		return ReservationResponse.builder()
			.id(reservation.getId())
			.hospitalId(reservation.getHospitalId())
			.hospitalName(hospital != null ? hospital.getName() : "알 수 없음")
			.phoneNumber(reservation.getPhoneNumber())
			.symptoms(reservation.getSymptoms())
			.status(reservation.getStatus())
			.createdAt(reservation.getCreatedAt())
			.build();
	}
}
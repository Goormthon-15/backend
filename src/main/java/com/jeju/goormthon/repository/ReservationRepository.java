package com.jeju.goormthon.repository;

import com.jeju.goormthon.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	// 병원별 예약 목록
	List<Reservation> findByHospitalId(Long hospitalId);

	// 휴대폰 번호로 예약 목록 조회
	List<Reservation> findByPhoneNumber(String phoneNumber);

	// 상태별 예약 목록
	List<Reservation> findByStatus(String status);

	// 병원별 + 상태별 예약 목록
	List<Reservation> findByHospitalIdAndStatus(Long hospitalId, String status);
}
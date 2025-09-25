package com.jeju.goormthon.domain;

import com.jeju.goormthon.enums.DepartmentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "hospitals")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "address3")
	private String address3;

	@Column(name = "phone")
	private String phone;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private DepartmentType type;

	@Column(name = "office_hours")
	private String officeHours;

	@Column(name = "is_popular")
	private Boolean isPopular;

	@Column(name = "is_currently_open")
	private Boolean isCurrentlyOpen;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "is_favorite")
	private Boolean isFavorite;

	// 필터링을 위한 추가 필드들
	@Column(name = "language")
	private String language;

	@Column(name = "location")
	private String location; // "제주", "서귀포"

	@Column(name = "consultation_available")
	private Boolean consultationAvailable;
}
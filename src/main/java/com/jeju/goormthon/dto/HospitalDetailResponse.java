package com.jeju.goormthon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalDetailResponse {
	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("address1")
	private String address1;

	@JsonProperty("address2")
	private String address2;

	@JsonProperty("address3")
	private String address3;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("type")
	private String type;

	@JsonProperty("office_hours")
	private String officeHours;

	@JsonProperty("is_popular")
	private Boolean isPopular;

	@JsonProperty("is_currently_open")
	private Boolean isCurrentlyOpen;

	@JsonProperty("latitude")
	private Double latitude;

	@JsonProperty("longitude")
	private Double longitude;

	@JsonProperty("is_favorite")
	private Boolean isFavorite;

	@JsonProperty("language")
	private String language;

	@JsonProperty("location")
	private String location;

	@JsonProperty("consultation_available")
	private Boolean consultationAvailable;

	@JsonProperty("description")
	private String description;
}

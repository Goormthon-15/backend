package com.jeju.goormthon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalListResponse {

	@JsonProperty("hospitals")
	private List<HospitalSummary> hospitals;

	@JsonProperty("total_count")
	private Integer totalCount;

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class HospitalSummary {
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
	}
}
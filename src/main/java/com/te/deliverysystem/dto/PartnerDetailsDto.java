package com.te.deliverysystem.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDetailsDto {
	@NotEmpty
	private String name;
	@NotBlank
	private String contactNo;
}

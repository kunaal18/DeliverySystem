package com.te.deliverysystem.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Integer serialNo;
	@NotBlank(message = "enter valid productId")
	private String prodeuctId;
	@NotBlank(message = "user Id cannot be blank")
	private String userId;
	@NotEmpty
	private String userName;
	@Min(10)
	@Max(10)
	private String contactNo;
	@Email(regexp = "@gmail.com")
	private String emailId;
	@NotEmpty
	private String address;
}

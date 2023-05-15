package com.te.deliverysystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Entity
@Data
@Table(name = "User_details")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer serialNo;
	@NonNull
	private String prodeuctId;
	@NonNull
	private String userId;
	@NonNull
	private String userName;
	@NonNull
	private String contactNo;
	@NonNull
	private String emailId;
	@NonNull
	private String address;
	
}

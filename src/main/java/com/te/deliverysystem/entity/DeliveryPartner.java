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
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Delivery_person_details")
public class DeliveryPartner {

	@Id
	private Integer partnerId;
	private String name;
	private String contactNo;
	private String personalMailId;
	
}

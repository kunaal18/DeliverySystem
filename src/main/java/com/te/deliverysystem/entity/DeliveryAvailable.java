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
@Table(name = "Delivery_partner_avalilibility")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAvailable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer slNo;
	private String name;
	private String contactNo;
}

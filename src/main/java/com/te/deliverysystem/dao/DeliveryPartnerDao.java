package com.te.deliverysystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.deliverysystem.entity.DeliveryPartner;

public interface DeliveryPartnerDao extends JpaRepository<DeliveryPartner, String>{

	DeliveryPartner findByPartnerId(String partnerId);
	
	DeliveryPartner findByName(String name);
	
}

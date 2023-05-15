package com.te.deliverysystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.deliverysystem.entity.DeliveryAvailable;

public interface PartnerAvailabilityDao extends JpaRepository<DeliveryAvailable, Integer>{

	DeliveryAvailable findByName(String name);
}

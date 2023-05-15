package com.te.deliverysystem.service;

import java.util.List;

import com.te.deliverysystem.dto.DeliveryPartnerDto;
import com.te.deliverysystem.dto.PartnerDetailsDto;
import com.te.deliverysystem.dto.UserDto;
import com.te.deliverysystem.entity.UserDetails;

public interface DeliveryService {

	UserDto registerUser(UserDto userDto);
	
	List<DeliveryPartnerDto> addAllPartners(List<DeliveryPartnerDto> partnerDtos);
	
	DeliveryPartnerDto getPartner(String name);
	
	List<PartnerDetailsDto> allPartners();
	
	UserDto getUser(String userEmail);
}

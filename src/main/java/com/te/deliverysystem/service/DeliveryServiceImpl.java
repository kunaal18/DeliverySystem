package com.te.deliverysystem.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.deliverysystem.dao.DeliveryPartnerDao;
import com.te.deliverysystem.dao.PartnerAvailabilityDao;
import com.te.deliverysystem.dao.UserDao;
import com.te.deliverysystem.dto.DeliveryPartnerDto;
import com.te.deliverysystem.dto.PartnerDetailsDto;
import com.te.deliverysystem.dto.UserDto;
import com.te.deliverysystem.entity.DeliveryAvailable;
import com.te.deliverysystem.entity.DeliveryPartner;
import com.te.deliverysystem.entity.UserDetails;
import com.te.deliverysystem.exception.PartnerNameNotFoundException;
import com.te.deliverysystem.exception.UserNotValidException;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private PartnerAvailabilityDao availabilityDao;
	@Autowired
	private DeliveryPartnerDao partnerDao;

	ModelMapper mapper = new ModelMapper();

	@Override
	public UserDto registerUser(UserDto userDto) {

		UserDetails details = new UserDetails();
		BeanUtils.copyProperties(userDto, details);
		UserDetails save = userDao.save(details);
		if (save != null) {

			return userDto;
		}
		throw new UserNotValidException("something went wrong! please try again...");
	}

	@Override
	public List<DeliveryPartnerDto> addAllPartners(List<DeliveryPartnerDto> partnerDtos) {
		List<DeliveryPartner> partners = new ArrayList<>();
		partnerDtos.stream().forEach((dto) -> {
			partners.add(mapper.map(dto, DeliveryPartner.class));
		});
		List<DeliveryPartner> saveAll = partnerDao.saveAll(partners);
		if (saveAll.isEmpty()) {
			throw new PartnerNameNotFoundException("Nothing Stored in Partners table");
		}
		return partnerDtos;
	}

	@Override
	public DeliveryPartnerDto getPartner(String name) {
		DeliveryAvailable findByName = availabilityDao.findByName(name);
		if (findByName == null) {
			throw new PartnerNameNotFoundException(name + " is not available! please select other partner");
		}
		// find all details of partner
		DeliveryPartner findByName2 = partnerDao.findByName(name);
		// to store partner in not availbility list
//		DeliveryAvailable available = new DeliveryAvailable();
//		BeanUtils.copyProperties(findByName2, available);
		DeliveryAvailable available = new DeliveryAvailable();
		available.setName(name);
		available.setContactNo(findByName2.getContactNo());
		availabilityDao.save(available);
		// to maintain atleast partners should be available
		if (partnerDao.findAll().size() == availabilityDao.findAll().size()) {
			availabilityDao.deleteAll();
			availabilityDao.save(available);
		}
		// converting into DTO
		DeliveryPartnerDto partnerDto = new DeliveryPartnerDto();
		BeanUtils.copyProperties(findByName2, partnerDto);

		return partnerDto;
	}

	@Override
	public List<PartnerDetailsDto> allPartners() {
		List<DeliveryPartner> findAll = partnerDao.findAll();
		if (findAll.isEmpty()) {
			throw new PartnerNameNotFoundException("partners not availble");
		}
		List<PartnerDetailsDto> detailsDtos = new ArrayList<>();
		PartnerDetailsDto dto = new PartnerDetailsDto();
		findAll.stream().forEach((partner) -> {
			dto.setName(partner.getName());
			dto.setContactNo(partner.getContactNo());
			detailsDtos.add(dto);
		});
		return detailsDtos;
	}

	@Override
	public UserDto getUser(String userEmail) {
		UserDetails findByEmailId = userDao.findByEmailId(userEmail);
		if (findByEmailId == null) {
			throw new UserNotValidException("not a valid user");
		}

		return mapper.map(findByEmailId, UserDto.class);
	}

}

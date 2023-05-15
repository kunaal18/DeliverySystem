package com.te.deliverysystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.deliverysystem.Email;
import com.te.deliverysystem.dto.DeliveryPartnerDto;
import com.te.deliverysystem.dto.PartnerDetailsDto;
import com.te.deliverysystem.dto.ResponseDto;
import com.te.deliverysystem.dto.UserDto;
import com.te.deliverysystem.service.DeliveryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/partner")
@Slf4j
public class PartnerController {

	@Autowired
	private DeliveryService deliveryService;

	@GetMapping("getPartnersInfo")
	public ResponseEntity<?> getAllPartnersInfo() {
		log.info("request for getting all delivery partner details");
		return new ResponseEntity<>(new ResponseDto(false, "list of all partners ", deliveryService.allPartners()),
				HttpStatus.ACCEPTED);
	}

	@PostMapping("/addPartners")
	public ResponseEntity<?> addAllPartners(@RequestBody List<DeliveryPartnerDto> list) {
		log.info("request for adding all partners is taken");
		if (list.size() != 0) {
			log.info("cheking minimum details added or not");
			List<DeliveryPartnerDto> addAllPartners = deliveryService.addAllPartners(list);
			if (addAllPartners != null) {
				log.info("all details of partners saved");
				return new ResponseEntity<>(new ResponseDto(false, "Added all Partners", addAllPartners),
						HttpStatus.ACCEPTED);
			}
			log.info("while saving partners details somethin went wrong");
			return new ResponseEntity<>(new ResponseDto(true, "Partners details not saved into db", list),
					HttpStatus.BAD_REQUEST);

		}
		log.info("entered details are empty");
		return new ResponseEntity<>(new ResponseDto(true, "Please enter partners details first", list),
				HttpStatus.BAD_REQUEST);
	}

	@PostMapping("assignPartner/{userEmail}/{partnerName}")
	public ResponseEntity<?> assignPartner(@PathVariable String userEmail, @PathVariable String partnerName) {
		log.info("request for assigning delivery partner is taken");
		if (userEmail.isEmpty() || partnerName.isEmpty()) {
			log.info("user have missed an credential");
			return new ResponseEntity<>(
					new ResponseDto(true, "entered email" + userEmail, partnerName + "entered partner Name"),
					HttpStatus.CHECKPOINT);
		} else {
			log.info("entered all credentials and checking for availability");
			
			UserDto user = deliveryService.getUser(userEmail);
			if (user!=null) {
				DeliveryPartnerDto partner = deliveryService.getPartner(partnerName);
				if(partner!=null) {
				log.info("sending information to the user and delivery partner ");
				Email.sendEmail("product sent", "you can contact our partner " + partner, userEmail,
						"banduhaladur@gmail.com");
				Email.sendEmail("the delivery details..", "the user and delivery details : " + user,
						partner.getPersonalMailId(), "banduhaladur@gmail.com");
				log.info("info sent and returning data to front end");
				return new ResponseEntity<>(new ResponseDto(false,""+partnerName+" assigned to "+user.getUserName(), user), HttpStatus.ACCEPTED);
			}
			}
			log.info("some issue while assigning partner");
			return new ResponseEntity<>(new ResponseDto(true,"Something went wrong! please try again..",null), HttpStatus.BAD_REQUEST);
		}
	}
}

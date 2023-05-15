package com.te.deliverysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.deliverysystem.Email;
import com.te.deliverysystem.dto.ResponseDto;
import com.te.deliverysystem.dto.UserDto;
import com.te.deliverysystem.entity.UserDetails;
import com.te.deliverysystem.service.DeliveryService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private DeliveryService service;
	@PostMapping("registerUser")
	public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
		if(userDto!=null) {
			UserDto registerUser = service.registerUser(userDto);
			if(registerUser!=null) {
				Email.sendEmail("Registered Successfully","Thanks for registering with ",userDto.getEmailId(),"banduhaladur@gmail.com");
			return new ResponseEntity<>(new ResponseDto(false,"Registered Successfully", registerUser), HttpStatus.ACCEPTED);
			}
			return new ResponseEntity<>(new ResponseDto(true,"Something went wrong ", userDto), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new ResponseDto(true,"Entries cnnot be null", userDto), HttpStatus.BAD_REQUEST);
	}
}

package com.te.deliverysystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PartnerNameNotFoundException extends RuntimeException{
	private String message;
}

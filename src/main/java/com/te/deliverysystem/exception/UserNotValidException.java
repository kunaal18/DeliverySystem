package com.te.deliverysystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserNotValidException extends RuntimeException{
private String messg;
}

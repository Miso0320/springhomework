package com.mycompany.springhomework.exception;

public class Ch10SoldOutException extends RuntimeException {
	public Ch10SoldOutException() {
	}
	
	public Ch10SoldOutException(String message) {
		super(message);
	}
}

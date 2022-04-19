package com.mycompany.webapp.exception;

public class Ch10SoldOutException extends RuntimeException {
	public Ch10SoldOutException() {
		super("품절");
	}
	
	public Ch10SoldOutException(String message) {
		super(message);
	}
}

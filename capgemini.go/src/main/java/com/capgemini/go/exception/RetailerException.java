package com.capgemini.go.exception;

public class RetailerException extends Exception {
	private static final long serialVersionUID = -2339227516000641134L;

	public RetailerException() {}

	public RetailerException(String errorMessage) {
		super(errorMessage);
	}

	public RetailerException(Throwable cause) {
		super(cause);
	}

	public RetailerException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}

	public RetailerException(String errorMessage, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(errorMessage, cause, enableSuppression, writableStackTrace);
	}
}

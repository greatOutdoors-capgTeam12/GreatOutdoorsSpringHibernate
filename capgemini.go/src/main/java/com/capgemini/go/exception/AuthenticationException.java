/**
 * Exception class to Show error in Authentication Function
 */

package com.capgemini.go.exception;

public class AuthenticationException extends Exception {

	public AuthenticationException() {

	}

	public AuthenticationException(String arg0) {
		super(arg0);

	}

	public AuthenticationException(Throwable arg0) {
		super(arg0);

	}

	public AuthenticationException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public AuthenticationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);

	}

}

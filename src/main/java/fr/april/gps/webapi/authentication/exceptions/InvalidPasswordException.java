package fr.april.gps.webapi.authentication.exceptions;

import fr.april.gps.webapi.common.exceptions.AbstractException;
import org.springframework.http.HttpStatus;

/**
 * by osow on 16/11/17.
 * for GPS
 */
public class InvalidPasswordException extends AbstractException {
	public InvalidPasswordException(String s) {
		super(s);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public String getErrorCode() {
		return "INVALID_PASSWORD";
	}
}

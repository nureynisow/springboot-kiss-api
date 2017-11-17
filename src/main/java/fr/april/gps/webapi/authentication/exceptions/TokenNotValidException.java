package fr.april.gps.webapi.authentication.exceptions;

import fr.april.gps.webapi.common.exceptions.AbstractException;
import org.springframework.http.HttpStatus;

/**
 * by osow on 16/11/17.
 * for GPS
 */
public class TokenNotValidException extends AbstractException {

	public TokenNotValidException(String message) {
		super(message);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.FORBIDDEN;
	}

	@Override
	public String getErrorCode() {
		return "TOKEN_INVALID";
	}
}

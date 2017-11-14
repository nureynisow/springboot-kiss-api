package fr.april.gps.common.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Created by mfradin
 */
public class RequiredException extends AbstractException {
	public RequiredException(String message) {
		super(message);
	}

	public RequiredException(String message, String className) {
		super(message, className);
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public String getErrorCode() {
		return "MISSING_REQUIRED_FIELD";
	}


}

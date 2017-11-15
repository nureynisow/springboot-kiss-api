package fr.april.gps.webapi.authentication.models;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * by osow on 15/11/17.
 * for GPS
 */
@Data
public class SignupDataBean {

	@NotNull
	private String login;

	@NotNull
	private String password;

	@NotNull
	private String confirmPassword;

	private String firstName, lastName;
}

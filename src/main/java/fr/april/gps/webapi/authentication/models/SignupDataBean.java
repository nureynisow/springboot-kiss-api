package fr.april.gps.webapi.authentication.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * by osow on 15/11/17.
 * for GPS
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDataBean {

	@NotNull
	private String login;

	@NotNull
	private String password;

	@NotNull
	private String confirmPassword;

	private String firstName, lastName;
}

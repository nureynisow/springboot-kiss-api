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
public class LoginDataBean {

	@NotNull
	private String login;

	@NotNull
	private String password;

}

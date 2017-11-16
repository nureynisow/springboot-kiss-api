package fr.april.gps.webapi.common.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * by osow on 15/11/17.
 * for GPS
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

	private String login;

	@JsonIgnore
	private String password;

	private String firstName, lastName;
}

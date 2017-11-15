package fr.april.gps.webapi.common.configurations.security.models;

import lombok.Builder;
import lombok.Data;

/**
 * by osow on 15/11/17.
 * for GPS
 */
@Data
@Builder
public class TokenWrapper {

	private String profileId;

	private Integer sessionDuration;

	private String expirationDate;


}

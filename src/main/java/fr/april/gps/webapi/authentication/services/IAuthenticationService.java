package fr.april.gps.webapi.authentication.services;

import fr.april.gps.common.models.Profile;
import fr.april.gps.webapi.authentication.models.SignupDataBean;

/**
 * by osow on 15/11/17.
 * for GPS
 */
public interface IAuthenticationService {
	Profile signup(SignupDataBean signupDataBean);
}

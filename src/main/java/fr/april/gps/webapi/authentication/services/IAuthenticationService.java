package fr.april.gps.webapi.authentication.services;

import fr.april.gps.webapi.authentication.models.SignupDataBean;
import fr.april.gps.webapi.common.models.Profile;

/**
 * by osow on 15/11/17.
 * for GPS
 */
public interface IAuthenticationService {
	/**
	 * Register a new user
	 *
	 * @param signupDataBean information wrapper
	 * @return saved {@link Profile}
	 */
	Profile signup(SignupDataBean signupDataBean);
}

package fr.april.gps.webapi.authentication.services;

import fr.april.gps.common.models.Profile;
import fr.april.gps.webapi.authentication.models.SignupDataBean;
import fr.april.gps.webapi.common.configurations.security.services.ICryptoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * by osow on 15/11/17.
 * for GPS
 */
@Slf4j
@Service
public class AuthenticationService implements IAuthenticationService {

	private static final String LOG_HEADER = "[AUTHENTICATION]";
	private final ICryptoService cryptoService;

	@Autowired
	public AuthenticationService(ICryptoService cryptoService) {
		this.cryptoService = cryptoService;
	}


	@Override
	public Profile signup(SignupDataBean signupDataBean) {
		Profile profile = Profile.builder()
						.login(signupDataBean.getLogin())
						.password(cryptoService.encryptPassword(signupDataBean.getPassword()))
						.firstName(signupDataBean.getFirstName())
						.lastName(signupDataBean.getLastName())
						.build();
		return null;
	}
}

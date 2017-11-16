package fr.april.gps.webapi.authentication.services;

import fr.april.gps.webapi.authentication.entities.ProfileEntity;
import fr.april.gps.webapi.authentication.exceptions.InvalidPasswordException;
import fr.april.gps.webapi.authentication.exceptions.UnableToLoginException;
import fr.april.gps.webapi.authentication.models.LoginDataBean;
import fr.april.gps.webapi.authentication.models.SignupDataBean;
import fr.april.gps.webapi.authentication.repositories.ProfileRepository;
import fr.april.gps.webapi.common.configurations.security.services.ICryptoService;
import fr.april.gps.webapi.common.exceptions.InternalServerException;
import fr.april.gps.webapi.common.models.Profile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * by osow on 15/11/17.
 * for GPS
 */
@Slf4j
@Service
public class AuthenticationService implements IAuthenticationService {

	private static final String LOG_HEADER = "[AUTHENTICATION SERVICE]";
	private final ICryptoService cryptoService;
	private final ProfileRepository profileRepository;
	private final DozerBeanMapper mapper;

	@Value("${april.gps.token.header.name}")
	private String tokenHeaderName;

	@Autowired
	public AuthenticationService(ICryptoService cryptoService, ProfileRepository profileRepository, DozerBeanMapper
					mapper) {
		this.cryptoService = cryptoService;
		this.profileRepository = profileRepository;
		this.mapper = mapper;
	}


	@Override
	public Profile signup(SignupDataBean signupDataBean) {
		if (StringUtils.isBlank(signupDataBean.getPassword()) || StringUtils.isBlank(signupDataBean.getConfirmPassword())
						|| !signupDataBean.getPassword().equals(signupDataBean.getConfirmPassword())) {
			log.error("{} Empty password or not same passwords | pwd : {} | confirmPwd {}",
							LOG_HEADER,
							signupDataBean.getPassword(),
							signupDataBean.getConfirmPassword());
			throw new InvalidPasswordException("Empty password or not same passwords");
		}
		Profile profile = Profile.builder()
						.login(signupDataBean.getLogin())
						.password(cryptoService.encryptPassword(signupDataBean.getPassword()))
						.firstName(signupDataBean.getFirstName())
						.lastName(signupDataBean.getLastName())
						.build();
		log.info("{} Adding profile {}", LOG_HEADER, profile);

		ProfileEntity profileEntity = this.mapper.map(profile, ProfileEntity.class);
		profileEntity.setCreationDateTime(LocalDateTime.now());
		profileEntity.setLastModificationDateTime(LocalDateTime.now());

		log.info("{} Saving profile entity {}", LOG_HEADER, profileEntity);

		ProfileEntity saved = null;
		try {
			saved = this.profileRepository.save(profileEntity);
		} catch (DataAccessException dae) {
			log.error("{} Save of profile {} failed with this error {}", LOG_HEADER, profileEntity, dae.getMessage());
			throw new InternalServerException("Save of profile failed with this error : " + dae.getMessage());
		}
		return saved == null ? null : profile;
	}

	@Override
	public Profile login(LoginDataBean loginDataBean) {
		log.info("{} looking for login {}", LOG_HEADER, loginDataBean.getLogin());
		// TODO Login with Active Directory first
		Profile profile = localLogin(loginDataBean.getLogin(), loginDataBean.getPassword());
		if (profile == null) throw new UnableToLoginException("Bad credentials");
		return profile;
	}

	@Override
	public void setJsonWebTokenUsingProfile(Profile profile, HttpServletResponse response) {
		final String jwt = cryptoService.createTokenFromProfile(profile);
		cryptoService.persistTokenInDB(profile.getLogin(), jwt);
		response.setHeader(tokenHeaderName, jwt);
	}

	/**
	 * Login from mongo repository
	 * return {@code null} if bad credentials
	 *
	 * @param login    profile login
	 * @param password profile password
	 * @return profile
	 */
	private Profile localLogin(String login, String password) {
		Profile profile = mapper.map(profileRepository.findByLogin(login), Profile.class);
		return this.cryptoService.checkPassword(password, profile.getPassword()) ? profile : null;
	}

}

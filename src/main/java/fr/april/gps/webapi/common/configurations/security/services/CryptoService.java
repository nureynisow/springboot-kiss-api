package fr.april.gps.webapi.common.configurations.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import fr.april.gps.webapi.authentication.entities.TokenEntity;
import fr.april.gps.webapi.authentication.repositories.TokenRepository;
import fr.april.gps.webapi.common.models.Profile;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

/**
 * by osow on 15/11/17.
 * for GPS
 */
@Slf4j
@Service
public class CryptoService implements ICryptoService {

	private static final String LOG_HEADER = "[CRYPTO SERVICE]";

	private final TokenRepository tokenRepository;

	private Algorithm algoHS;

	@Value("${april.gps.security.salt}")
	private String salt;

	@Value("${april.gps.security.jwt-duration}")
	private String duration;

	@Value("${april.gps.security.jwt-claim-key}")
	private String jwtClaimKey;

	@Value("${april.gps.security.jwt-claim-ttl-key}")
	private String jwtClaimTTLKey;

	@Value("${april.gps.security.jwt-issuer}")
	private String jwtIssuer;

	@Autowired
	public CryptoService(@Value("${april.gps.security.jwt-secret}") String secret, TokenRepository tokenRepository) {
		try {
			this.algoHS = Algorithm.HMAC512(secret);
		} catch (UnsupportedEncodingException e) {
			log.error("{} failed to init HMAC512 algorithm {}", LOG_HEADER, e);
		}
		this.tokenRepository = tokenRepository;
	}


	@Override
	public String encryptPassword(String password) {
		return DigestUtils.md5Hex(password + salt);
	}

	@Override
	public boolean checkPassword(String passwordToTest, String realPassword) {
		return !StringUtils.isBlank(realPassword) && realPassword.equals(DigestUtils.md5Hex(passwordToTest + salt));
	}

	@Override
	public String createTokenFromProfile(Profile profile) {
		String expirationDateTime = LocalDateTime.now().plusSeconds(Long.parseLong(this.duration)).toString();
		if (StringUtils.isBlank(profile.getLogin())) {
			return null;
		}
		String jwt = JWT.create()
						.withClaim(jwtClaimKey, profile.getLogin())
						.withClaim(jwtClaimTTLKey, expirationDateTime)
						.withIssuer(jwtIssuer)
						.sign(algoHS);
		log.trace("{} Generated token for user {} is : {}", LOG_HEADER, profile.getLogin(), jwt);
		return jwt;
	}

	@Override
	public Long persistTokenInDB(String login, String jwt) {
		this.tokenRepository.save(TokenEntity.builder()
						.login(login)
						.jsonWebToken(jwt)
						.build());
		return this.tokenRepository.countByLogin(login);
	}

}

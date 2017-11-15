package fr.april.gps.webapi.common.configurations.security.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * by osow on 15/11/17.
 * for GPS
 */
@Service
public class CryptoService implements ICryptoService {

	@Value("${april.gps.security.salt}")
	private String salt;

	@Override
	public String encryptPassword(String password) {
		return DigestUtils.md5Hex(password + salt);
	}

	@Override
	public boolean checkPassword(String passwordToTest, String realPassword) {
		return !StringUtils.isBlank(realPassword) && realPassword.equals(DigestUtils.md5Hex(passwordToTest + salt));
	}

}

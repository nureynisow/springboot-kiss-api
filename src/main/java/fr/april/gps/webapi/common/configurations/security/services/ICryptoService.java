package fr.april.gps.webapi.common.configurations.security.services;

/**
 * by osow on 15/11/17.
 * for GPS
 */
public interface ICryptoService {
	/**
	 * Crypt a given password
	 *
	 * @param password password to crypt
	 * @return encrypted password
	 */
	String encryptPassword(String password);

	/**
	 * Check if a given password is equals to
	 * an encrypted one
	 *
	 * @param passwordToTest the pwd to check
	 * @param realPassword   the actual password
	 * @return a boolean
	 */
	boolean checkPassword(String passwordToTest, String realPassword);
}

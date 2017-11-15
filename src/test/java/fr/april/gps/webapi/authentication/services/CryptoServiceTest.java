package fr.april.gps.webapi.authentication.services;

import fr.april.gps.webapi.ApplicationTest;
import fr.april.gps.webapi.common.configurations.security.services.ICryptoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * by osow on 15/11/17.
 * for GPS
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class CryptoServiceTest {

	@Autowired
	private ICryptoService cryptoService;

	@Test
	public void testPasswordEncryption() {
		String password = "myPassword";
		String encryptedPassword = cryptoService.encryptPassword(password);
		Assert.assertNotEquals(password, encryptedPassword);
		Assert.assertTrue(cryptoService.checkPassword(password, encryptedPassword));
		Assert.assertFalse(cryptoService.checkPassword(password + "toto", encryptedPassword));
	}

}

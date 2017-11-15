package fr.april.gps.webapi.authentication.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * by osow on 15/11/17.
 * for GPS
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguation
@WebAppConfiguration
@ActiveProfiles("test")
public class CryptoServiceTest {
	//
//	@Autowired
//	private ICryptoService cryptoService;
//
//	@Test
//	public void testPasswordEncryption() {
//		String password = "myPassword";
//		String encryptedPassword = cryptoService.encryptPassword(password);
//		Assert.assertNotEquals(password, encryptedPassword);
//		Assert.assertTrue(cryptoService.checkPassword(password, encryptedPassword));
//		Assert.assertFalse(cryptoService.checkPassword(password + "toto", encryptedPassword));
//	}
	@Test
	public void testOK() {
		Assert.assertTrue(true);
	}
}

package fr.april.gps.webapi.authentication;

import fr.april.gps.webapi.ApplicationTest;
import fr.april.gps.webapi.authentication.exceptions.InvalidPasswordException;
import fr.april.gps.webapi.authentication.exceptions.UnableToLoginException;
import fr.april.gps.webapi.authentication.models.LoginDataBean;
import fr.april.gps.webapi.authentication.models.SignupDataBean;
import fr.april.gps.webapi.authentication.repositories.ProfileRepository;
import fr.april.gps.webapi.authentication.services.AuthenticationService;
import fr.april.gps.webapi.common.exceptions.InternalServerException;
import fr.april.gps.webapi.common.models.Profile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
public class AuthenticationTest {

	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private ProfileRepository profileRepository;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testSignup() {
		SignupDataBean signupDataBean = SignupDataBean.builder()
						.login("neo9")
						.password("Passer33")
						.confirmPassword("Passer33")
						.firstName("Neo9 Lyon")
						.build();
		Profile retrievedProfile = this.authenticationService.signup(signupDataBean);
		Assert.assertNotNull(retrievedProfile);
		Assert.assertEquals(retrievedProfile.getLogin(), "neo9");
		Assert.assertEquals(retrievedProfile.getLogin(), "neo9");
	}

	@Test
	public void testDuplicatedUser() throws InternalServerException {
		SignupDataBean signupDataBean = SignupDataBean.builder()
						.login("neo9")
						.firstName("Neo9 Lyon")
						.password("Passer33")
						.confirmPassword("Passer33")
						.build();
		Profile retrievedProfile = this.authenticationService.signup(signupDataBean);
		Assert.assertNotNull(retrievedProfile);
		thrown.expect(InternalServerException.class);
		retrievedProfile = this.authenticationService.signup(signupDataBean);
		Assert.assertNull(retrievedProfile);
	}

	@Test
	public void testBothPasswords() throws InvalidPasswordException {
		thrown.expect(InvalidPasswordException.class);

		SignupDataBean signupDataBean = SignupDataBean.builder()
						.login("april")
						.firstName("Aprilium")
						.confirmPassword("test")
						.build();
		Profile retrievedProfile = this.authenticationService.signup(signupDataBean);
		Assert.assertNull(retrievedProfile);

		signupDataBean.setPassword("wrong pwd");
		retrievedProfile = this.authenticationService.signup(signupDataBean);
		Assert.assertNull(retrievedProfile);

		signupDataBean.setPassword("test");
		retrievedProfile = this.authenticationService.signup(signupDataBean);
		Assert.assertNotNull(retrievedProfile);
	}

	@Test
	public void testLoginMongo() {
		SignupDataBean signupDataBean = SignupDataBean.builder()
						.login("neo9MongoUser")
						.password("mongo")
						.confirmPassword("mongo")
						.firstName("First Name")
						.build();
		Profile savedProfile = this.authenticationService.signup(signupDataBean);
		Profile loggedInProfile = null;
		UnableToLoginException exceptedException = null;
		try {
			loggedInProfile = this.authenticationService.login(LoginDataBean.builder()
							.login("neo9MongoUser")
							.password("badPassword")
							.build());
		} catch (UnableToLoginException le) {
			exceptedException = le;
		}
		Assert.assertNotNull(exceptedException);
		Assert.assertNull(loggedInProfile);
		exceptedException = null;
		try {
			loggedInProfile = this.authenticationService.login(LoginDataBean.builder()
							.login("neo9MongoUser")
							.password("mongo")
							.build());
		} catch (UnableToLoginException le) {
			exceptedException = le;
		}
		Assert.assertNull(exceptedException);
		Assert.assertNotNull(loggedInProfile);
		Assert.assertEquals(savedProfile.getFirstName(), loggedInProfile.getFirstName());

	}


	@After
	public void flushCollection() {
		this.profileRepository.deleteAll();
	}
}

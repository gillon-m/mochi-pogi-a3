package validator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.mockito.Mockito;

import authentication.Administrator;
import authentication.Database;
import authentication.User;

import org.junit.Test;

public class AuthenticationRoleTest {
	
	public Database mock = Mockito.mock(Database.class);
	
	@Before
	public void initialise() {
		User user = new User();
		Administrator admin = new Administrator();
		
	}
	

	@Test
	public void RoleisAdministratorTest() {
		Administrator admin = new Administrator();
		assertEquals(admin, Administrator.class);
	}
	
	@Test
	public void RoleisUserTest() {
		User user = new User();
		assertEquals(user, Administrator.class);
	}
	
	@Test
	public void InvalidSigninAdminTest() {
		
	}
	
	@Test
	public void InvalidSigninUserTest() {
		
	}
	
	@Test
	public void checkSignUpUserTest() {
		
	}

}

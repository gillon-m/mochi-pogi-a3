package validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import authentication.Administrator;
import authentication.User;
import validator.marketcomprehension.MongoClient;
import validator.marketcomprehension.MongoDatabase;

import org.junit.Test;

/*Two types of roles Users and Administrators can sign up, in and off.*/

public class AuthenticationRoleTest {

	
	
	@Before
	public void initialise() {
		User user = new User();
		Administrator admin = new Administrator();
		
		
	}
	

	//check if user is user and admin is admin
	@Test
	public void RoleisAdministratorTest() {
		Administrator admin = new Administrator();
		assertNotEquals(admin.getClass(), User.class);
		assertEquals(admin.getClass(), Administrator.class);
	}
	
	@Test
	public void RoleisUserTest() {
		User user = new User();
		assertEquals(user.getClass(), User.class);
		assertNotEquals(user.getClass(), Administrator.class);
	}
	
	//check if they can successfully signin as a user or admin and are in the registry
	@Test
	public void UserHasSucessfullySignedInTest() {
		
	}
	
	@Test
	public void AdminHasSucessfullySignedInTest() {
		
	}
	
	@Test
	public void InvalidSigninAdminTest() {
		
	}
	
	@Test
	public void InvalidSigninUserTest() {
		
	}
	
	@Test
	public void userNotInRegistryTest() {
		
	}
	
	@Test
	public void adminNotInRegistryTest() {
		
	}
	
	//check if the user and/or admin can signup
	
	@Test
	public void SignUpUserTest() {
		
	}
	
	@Test
	public void SignUpAdminTest() {
		
	}

	
	//check if the user and admin can sign off
	
	@Test
	public void SignOffUserTest() {
		
	}
	
	@Test
	public void SignOffAdminTest() {
		
	}
}

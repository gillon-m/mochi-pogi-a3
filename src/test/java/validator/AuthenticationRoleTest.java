package validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;


import authentication.Administrator;
import authentication.Registry;
import authentication.User;

import org.junit.Test;

/*Two types of roles Users and Administrators can sign up, in and off.*/

public class AuthenticationRoleTest {

	public Registry databaseRegistry;
	
	//this isnt really used
	@Before
	public void initialise() {
		User user = new User("username", "password");
		Administrator admin = new Administrator("username", "password");
		
		User mockUser1 = Mockito.mock(User.class);
		mockUser1.setUsername("User1");
		mockUser1.setPassword("Pass1");
		User mockUser2 = Mockito.mock(User.class);
		mockUser2.setUsername("User2");
		mockUser2.setPassword("Pass2");
		User mockUser3 = Mockito.mock(User.class);
		mockUser1.setUsername("User3");
		mockUser1.setPassword("Pass3");
		User mockUser4 = Mockito.mock(User.class);
		mockUser1.setUsername("User4");
		mockUser1.setPassword("Pass4");
		User mockUser5 = Mockito.mock(User.class);
		mockUser1.setUsername("User5");
		mockUser1.setPassword("Pass5");
		Administrator mockAdmin1 = Mockito.mock(Administrator.class);
		mockUser1.setUsername("Admin1");
		mockUser1.setPassword("PassA1");
		Administrator mockAdmin2 = Mockito.mock(Administrator.class);
		mockUser1.setUsername("Admin2");
		mockUser1.setPassword("PassA2");
		
		databaseRegistry.addUser(mockUser1);
		databaseRegistry.addUser(mockUser2);
		databaseRegistry.addUser(mockUser3);
		databaseRegistry.addUser(mockUser4);
		databaseRegistry.addUser(mockUser5);
		databaseRegistry.addAdmin(mockAdmin1);
		databaseRegistry.addAdmin(mockAdmin2);
		
	}
	

	//check if user is user and admin is admin
	@Test
	public void RoleisAdministratorTest() {
		Administrator admin = new Administrator("username", "password");
		assertNotEquals(admin.getClass(), User.class);
		assertEquals(admin.getClass(), Administrator.class);
	}
	
	@Test
	public void RoleisUserTest() {
		User user = new User("username", "password");
		assertEquals(user.getClass(), User.class);
		assertNotEquals(user.getClass(), Administrator.class);
	}
	
	//check if they can successfully signin as a user or admin and are in the registry
	@Test
	public void UserHasSucessfullySignedInTest() {
		User user = new User("username", "password");
		assertTrue(user.signIn());
		
	}
	
	@Test
	public void AdminHasSucessfullySignedInTest() {
		Administrator admin = new Administrator("admin", "password");
		assertFalse(admin.signIn());
	}
	
	@Test
	public void InvalidSigninAdminTest() {
		Administrator admin = new Administrator("admin", "password");
		assertFalse(admin.signIn());
	}
	
	@Test
	public void InvalidSigninUserTest() {
		User user = new User("username", "password");
		assertFalse(user.signIn());
	}
	
	@Test
	public void userNotInRegistryTest() {
		User user = new User("username", "password");
		databaseRegistry.checkforUser(user);
	}
	
	@Test
	public void adminNotInRegistryTest() {
		Administrator admin = new Administrator("username", "password");
		databaseRegistry.checkforAdmin(admin);
	}
	
	//check if the user and/or admin can signup
	
	@Test
	public void SignUpUserTest() {
		User user = new User("username", "password");
		databaseRegistry.addUser(user);
		assertEquals(user, databaseRegistry.getUser(user));
	}
	
	@Test
	public void SignUpAdminTest() {
		Administrator admin = new Administrator("username", "password");
		databaseRegistry.addAdmin(admin);
		assertEquals(admin, databaseRegistry.getAdmin(admin));
	}

	
	//check if the user and admin can sign off
	
	@Test
	public void SignOffUserTest() {
		User user = new User("username", "password");
		databaseRegistry.getUser(user);
		assertTrue(user.signOut());

	}
	
	@Test
	public void SignOffAdminTest() {
		Administrator admin = new Administrator("username", "password");
		databaseRegistry.getAdmin(admin);
		assertTrue(admin.signOut());

	}
	
	
	
	/*Administrators need to know how many users have registered.*/
	@Test
	public void adminChecksNumberOfUsersTest() {
		Administrator admin = new Administrator("username", "password");
		databaseRegistry.checkUserCount();
		
	}

}

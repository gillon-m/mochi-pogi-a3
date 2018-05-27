package validator;

import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.Mockito;


import authentication.Administrator;
import authentication.User;

import org.junit.Test;

/*Two types of roles Users and Administrators can sign up, in and off.*/

public class AuthenticationRoleTest {


	

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
		assertTrue(user.signIn("username", "password"));
		
	}
	
	@Test
	public void AdminHasSucessfullySignedInTest() {
		Administrator admin = new Administrator("admin", "password");
		assertTrue(admin.signIn("admin", "password"));
	}
	
	@Test
	public void InvalidSigninAdminTest() {
		Administrator admin = new Administrator("admin", "password");
		assertFalse(admin.signIn("notAdmin", "password"));
		assertFalse(admin.signIn("admin", "notP"));
	}
	
	@Test
	public void InvalidSigninUserTest() {
		User user = new User("username", "password");
		assertFalse(user.signIn("notUser", "password"));
		assertFalse(user.signIn("username", "notP"));
	}
	
	@Test
	public void userNotInRegistryTest() {
		User user = new User("username", "password");
		//databaseRegistry.checkforUser(user);
	}
	
	@Test
	public void adminNotInRegistryTest() {
		Administrator admin = new Administrator("username", "password");
		//databaseRegistry.checkforAdmin(admin);
	}
	
	//check if the user and/or admin can signup
	
	@Test
	public void SignUpUserTest() {
		User user = new User("username", "password");
		//databaseRegistry.addUser(user);
		//assertEquals(user, databaseRegistry.getUser(user));
	}
	
	@Test
	public void SignUpAdminTest() {
		Administrator admin = new Administrator("username", "password");
		//databaseRegistry.addAdmin(admin);
		//assertEquals(admin, databaseRegistry.getAdmin(admin));
	}

	
	//check if the user and admin can sign off
	
	@Test
	public void SignOffUserTest() {
		User user = new User("username", "password");
		//databaseRegistry.getUser(user);
		//assertTrue(user.signOut());

	}
	
	@Test
	public void SignOffAdminTest() {
		Administrator admin = new Administrator("username", "password");
		//databaseRegistry.getAdmin(admin);
		//assertTrue(admin.signOut());

	}
	
	
	
	/*Administrators need to know how many users have registered.*/
	@Test
	public void adminChecksNumberOfUsersTest() {
		Administrator admin = new Administrator("username", "password");
		
		int noUsers = admin.checkRegisteredUsers();

		assertEquals(5, noUsers);
		
	}

	@Test
	public void usersChecksNumberOfUsersTest() {
		User user = new User("username", "password");
		try {
			int noUsers = user.checkRegisteredUsers();
			fail("Should not work");
		} catch () {
			
		}
	}
}

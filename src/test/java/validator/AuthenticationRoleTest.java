package validator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;


import authentication.Administrator;
import authentication.Registry;
import authentication.Role;
import authentication.User;
import validator.exceptions.AuthenticationException;
import validator.marketcomprehension.MongoDatabase;

import org.junit.Test;

/*Two types of roles Users and Administrators can sign up, in and off.*/

public class AuthenticationRoleTest {
	private Registry registry;
	private Throwable exception;

	@Before
	public void initialise() {
		List<Role> roles = new ArrayList<Role>();
		roles.add(new User("u1", "p1"));
		roles.add(new User("u2", "p2"));
		roles.add(new User("u3", "p3"));
		roles.add(new User("u4", "p4"));
		roles.add(new Administrator("a1", "p1"));
		MongoDatabase db = Mockito.mock(MongoDatabase.class);
		Mockito.when(db.getRoles()).thenReturn(roles);
		
		registry = Registry.getInstance();
		registry.setDatabase(db);
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
	public void validSigninAdminTest() {
		try {
			Role admin = registry.signIn("a1", "p1");
			assertTrue(admin instanceof Administrator);
			assertEquals("a1", admin.getUsername());
			assertEquals("p1", admin.getPassword());
		} catch (AuthenticationException e) {
			fail();
		}
	}
	
	@Test
	public void validSigninUserTest() {
		try {
			Role user = registry.signIn("u1", "p1");
			assertTrue(user instanceof User);
			assertEquals("u1", user.getUsername());
			assertEquals("p1", user.getPassword());
		} catch (AuthenticationException e) {
			fail();
		}
	}
	
	@Test
	public void userNotInRegistryTest() {
		try {
			registry.signIn("username", "password");
			fail();
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Role Not Found", exception.getMessage());
		}
	}
	
	@Test
	public void adminNotInRegistryTest() {
		try {
			registry.signIn("username", "password");
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Role Not Found", exception.getMessage());
		}
		
	}
	
	@Test
	public void passwordForUserIsIncorrectInRegistryTest() {
		try {
			registry.signIn("u1", "password");
			fail();
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Password Incorrect", exception.getMessage());
		}
	}
	
	@Test
	public void passwordForAdminIsIncorrectInRegistryTest() {
		try {
			registry.signIn("a1", "password");
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Password Incorrect", exception.getMessage());
		}
		
	}
	
	//check if the user and/or admin can signup
	
	@Test
	public void SignUpUserWhenNewCredentialsAreEnteredTest() {
		try {
			Role user = registry.signUp("username", "password", User.class);
			assertTrue(user instanceof User);
			assertEquals("username", user.getUsername());
			assertEquals("password", user.getPassword());

		} catch (AuthenticationException e) {
			fail();
		}
	}
	
	@Test
	public void SignUpAdminWhenNewCredentialsAreEnteredTest() {
		try {
			Role admin = registry.signUp("admin", "password", Administrator.class);
			assertTrue(admin instanceof Administrator); 
			assertEquals("admin", admin.getUsername());
			assertEquals("password", admin.getPassword());

		} catch (AuthenticationException e) {
			fail();
		}
	}
	
	@Test
	public void SignUpUserAlreadyExistsInRegistryTest() {
		try {
			Role user = registry.signUp("u1", "p1", User.class);
			fail();

		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Username Already Exists", exception.getMessage());
		}
	}
	
	@Test
	public void SignUpAdminAlreadyExistsInRegistryTest() {
		try {
			Role admin = registry.signUp("a1", "p1", Administrator.class);
			fail();

		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Username Already Exists", exception.getMessage());
		}
	}

	
	//check if the user and admin can sign off
	
	@Test
	public void SignOffUserTest() {
		User user = new User("u1", "p1");
		try {
			registry.signOff(user);
			assertEquals("u1", user.getUsername());
			assertEquals("p1", user.getPassword());

		} catch (AuthenticationException e) {
			fail();
		}
	}
	
	@Test
	public void SignOffAdminTest() {
		Administrator admin = new Administrator("a1", "p1");
		try {
			registry.signOff(admin);
			assertEquals("a1", admin.getUsername());
			assertEquals("p1", admin.getPassword());

		} catch (AuthenticationException e) {
			fail();
		}

	}
	
	
	
	/*Administrators need to know how many users have registered.*/
	@Test
	public void adminChecksNumberOfUsersTest() {
		Administrator admin = new Administrator("a1", "p1");
		try {
			int noUsers = admin.checkRegisteredUsers();
			assertEquals(4, noUsers);
		} catch (AuthenticationException e) {
			fail("should not throw exception");
		}
		
	}

	@Test
	public void usersChecksNumberOfUsersTest() {
		User user = new User("username", "password");
		try {
			int noUsers = user.checkRegisteredUsers();
			fail("Should not work");
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("User Cannot Search", exception.getMessage());
		}
	}
	
	
	//check if they are in the current session
	@Test
	public void userCurrentSessionCountTest() {
		try {
			Role user = registry.signIn("u1", "p1");
			int userSessionCount = 0;
			if (user.signStatus()) {
				user.addSearchCount();
				userSessionCount = user.getSessionCount();
			}
			assertEquals(1, userSessionCount);
		} catch (AuthenticationException e) {
			fail();
		}
	}
	
	@Test
	public void userTotalSearchCountOfTwoTest() {
		try {
			Role user = registry.signIn("u1", "p1");
			int userTotalSearchCount = 0;
			int userSessionCount = 0;
			if (user.signStatus()) {
				user.addSearchCount();
				user.addSearchCount();
			}
			registry.signOff(user);
			user = registry.signIn("u1","p1");
			if (user.signStatus()) {
				userTotalSearchCount = user.getTotalSearchCount();
				userSessionCount = user.getSessionCount();
			}
			assertEquals(2, userTotalSearchCount);
			assertEquals(0, userSessionCount);

		} catch (AuthenticationException e) {
			fail();
		}
	}
	
	@Test
	public void AdminThrowsExceptionWhenSearchingCountTest() {
		try {
			Role admin = registry.signIn("a1", "p1");
			if (admin.signStatus()) {
				admin.addSearchCount();
				fail();
			}
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Admin Cannot Search", exception.getMessage());
		}
	}
	
	@Test
	public void AdminThrowsExceptionWhenSearchingForSearchTotalCountTest() {
		try {
			Role admin = registry.signIn("a1", "p1");
			if (admin.signStatus()) {
				admin.getTotalSearchCount();
				fail();
			}
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Admin Cannot Search", exception.getMessage());
		}
	}
	
	@Test
	public void AdminThrowsExceptionWhenSearchingForSearchCurrentCountTest() {
		try {
			Role admin = registry.signIn("a1", "p1");
			if (admin.signStatus()) {
				admin.getSessionCount();
				fail();
			}
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Admin Cannot Search", exception.getMessage());
		}
	}
	
}

package validator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.mockito.Mockito;

import validator.authentication.Administrator;
import validator.authentication.Registry;
import validator.authentication.Role;
import validator.authentication.User;
import validator.database.MongoDatabase;
import validator.exceptions.AuthenticationException;

import org.junit.Test;

/*Two types of roles Users and Administrators can sign up, in and off.*/

public class AuthenticationRegistryTest {
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
	
	//check if they can successfully signin as a user or admin and are in the registry
	@Test
	public void shouldSigninValidAdminWhenGivenCorrectCredentialsTest() {
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
	public void shouldSigninValidUserWhenGivenCorrectCredentialsTest() {
		try {
			Role user = registry.signIn("u2", "p2");
			assertTrue(user instanceof User);
			assertEquals("u2", user.getUsername());
			assertEquals("p2", user.getPassword());
		} catch (AuthenticationException e) {
			fail();
		}
	}
	
	@Test
	public void shouldReturnExceptionWhenUserIsNotInRegistryTest() {
		try {
			registry.signIn("username", "password");
			fail();
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Role Not Found", exception.getMessage());
		}
	}
	
	@Test
	public void shouldReturnExceptionWhenAdminIsNotInRegistryTest() {
		try {
			registry.signIn("username", "password");
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Role Not Found", exception.getMessage());
		}
		
	}
	
	@Test
	public void shouldReturnExceptionWhenPasswordForUserIsIncorrectInRegistryTest() {
		try {
			registry.signIn("u1", "password");
			fail();
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Password Incorrect", exception.getMessage());
		}
	}
	
	@Test
	public void shouldReturnExceptionWhenPasswordForAdminIsIncorrectInRegistryTest() {
		try {
			registry.signIn("a1", "password");
			fail();
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Password Incorrect", exception.getMessage());
		}
		
	}
	
	//check if the user and/or admin can signup
	
	@Test
	public void shouldSuccessfullysSignUpUserWhenNewCredentialsAreEnteredTest() {
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
	public void shouldSuccessfullysSignUpAdminWhenNewCredentialsAreEnteredTest() {
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
	public void shouldReturnExceptionWhenUserAlreadyExistsInRegistryTest() {
		try {
			Role user = registry.signUp("u1", "p1", User.class);
			fail();

		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Username Already Exists", exception.getMessage());
		}
	}
	
	@Test
	public void shouldReturnExceptionWhenAdminAlreadyExistsInRegistryTest() {
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
	public void shouldSuccessfullySignOffUserWhenCredentialsAreCorrectTest() {
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
	public void shouldSuccessfullySignOffAdminWhenCredentialsAreCorrectTest() {
		Administrator admin = new Administrator("a1", "p1");
		try {
			registry.signOff(admin);
			assertEquals("a1", admin.getUsername());
			assertEquals("p1", admin.getPassword());

		} catch (AuthenticationException e) {
			fail();
		}

	}
	
	@Test
	public void shouldReturnExceptionWhenUserSignsOffWithIncorrectCredentialsTest() {
		User user = new User("username", "p1");
		try {
			registry.signOff(user);
			fail();

		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Role Not Found", exception.getMessage());
		}
	}
	
	@Test
	public void shouldReturnExceptionWhenAdminSignsOffWithIncorrectCredentialsTest() {
		Administrator admin = new Administrator("admin", "p1");
		try {
			registry.signOff(admin);
			fail();

		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Role Not Found", exception.getMessage());
		}

	}
	
	
	
	/*Administrators need to know how many users have registered.*/
	@Test
	public void shouldReturnFourWhenAdminChecksNumberOfUsersInRegistryTest() {
		Administrator admin = new Administrator("a1", "p1");
		try {
			int noUsers = admin.checkRegisteredUsers();
			assertEquals(4, noUsers);
		} catch (AuthenticationException e) {
			fail("should not throw exception");
		}
		
	}

	@Test
	public void shouldReturnExceptionWhenUserChecksNumberOfUsersWithoutCorrectPrivilegesTest() {
		User user = new User("username", "password");
		try {
			int noUsers = user.checkRegisteredUsers();
			fail("Should not work");
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("User Cannot Search", exception.getMessage());
		}
	}
	
	
	
}

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

public class AuthenticationTest {
	private Registry registry;
	private MongoDatabase db;
	private Throwable exception;

	@Before
	public void initialise() {
		registry = Registry.getInstance();
		db = Mockito.mock(MongoDatabase.class);
		registry.setDatabase(db);
		List<Role> roles = new ArrayList<Role>();
		Mockito.when(registry.getTotalSearchCount("u1")).thenReturn(1);
		Mockito.when(registry.getTotalSearchCount("u2")).thenReturn(2);
		Mockito.when(registry.getTotalSearchCount("u3")).thenReturn(3);
		Mockito.when(registry.getTotalSearchCount("u4")).thenReturn(4);
		roles.add(Mockito.spy(new User("u1", "p1")));
		roles.add(Mockito.spy(new User("u2", "p2")));
		roles.add(Mockito.spy(new User("u3", "p3")));
		roles.add(Mockito.spy(new User("u4", "p4")));
		roles.add(Mockito.spy(new Administrator("a1", "p1")));
		Mockito.when(db.getRoles()).thenReturn(roles);
		registry.setRoles();
	}
	
	@Test
	public void shouldSigninValidUserWhenGivenCorrectCredentialsTest() {
		try {
			Role role = registry.signIn("u2", "p2");
			assertEquals("u2", role.getUsername());
			assertEquals("p2", role.getPassword());
		} catch (AuthenticationException e) {
			fail();
		}
	}
	
	@Test
	public void shouldReturnExceptionWhenRoleIsNotInRegistryTest() {
		try {
			Role role = registry.signIn("username", "password");
			fail();
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Role Not Found", exception.getMessage());
		}
	}
	
	@Test
	public void shouldReturnExceptionWhenPasswordForUserIsIncorrectInRegistryTest() {
		try {
			Role role = registry.signIn("u1", "password");
			fail();
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("Password Incorrect", exception.getMessage());
		}
	}
	
	
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

	
	@Test
	public void shouldChangeSignStatusWhenSignOutTest() {
		Role role = db.getRoles().get(0);
		try {
			assertTrue(role.isSignedIn());
			registry.signOff(role.getUsername());
			assertFalse(role.isSignedIn());
		} catch (AuthenticationException e) {
			fail();
		}
	}	
}

package validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import validator.authentication.Administrator;
import validator.authentication.Registry;
import validator.authentication.Role;
import validator.authentication.User;
import validator.database.MongoDatabase;
import validator.exceptions.AuthenticationException;

public class AdministratorPrevilegeActionTest {
	private Registry registry;
	private MongoDatabase db;
	private Throwable exception;

	@Before
	public void setUp() {
		List<Role> roles = new ArrayList<Role>();
		db = Mockito.mock(MongoDatabase.class);
		registry = Registry.getInstance();
		registry.setDatabase(db);
		Mockito.when(db.getRoles()).thenReturn(roles);
		registry.setRoles();		
		Mockito.when(registry.getTotalSearchCount("u1")).thenReturn(1);
		Mockito.when(registry.getTotalSearchCount("u2")).thenReturn(2);
		Mockito.when(registry.getTotalSearchCount("u3")).thenReturn(3);
		Mockito.when(registry.getTotalSearchCount("u4")).thenReturn(4);
		roles.add(Mockito.spy(new User("u1", "p1")));
		roles.add(Mockito.spy(new User("u2", "p2")));
		roles.add(Mockito.spy(new User("u3", "p3")));
		roles.add(Mockito.spy(new User("u4", "p4")));
		roles.add(Mockito.spy(new Administrator("a1", "p1")));
	}
	/*Administrators need to know how many users have registered.*/
	@Test
	public void shouldReturnFourWhenAdminChecksNumberOfUsersInRegistryTest() {
		Administrator admin = Mockito.spy(new Administrator("a1", "p1"));
		try {
			int noUsers = admin.checkRegisteredUsers();
			assertEquals(4, noUsers);
		} catch (AuthenticationException e) {
			fail("should not throw exception");
		}
	}

	@Test
	public void shouldReturnExceptionWhenUserChecksNumberOfUsersWithoutCorrectPrivilegesTest() {
		User user = Mockito.spy(new User("username", "password"));
		try {
			int noUsers = user.checkRegisteredUsers();
			fail("Should not work");
		} catch (AuthenticationException e) {
			exception = e;
			assertEquals("User Cannot Search", exception.getMessage());
		}
	}
}

package validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import authentication.Administrator;
import authentication.User;
import validator.exceptions.AuthenticationException;

public class AuthenticationRolesTest {
	SearchHandler sh;
	private AuthenticationException exception;
	@Before
	public void setup(){
		sh = new SearchHandler(Mockito.mock(InputProcessor.class));
	}
	//check if user is user and admin is admin
	@Test
	public void shouldReturnAdminRoleWhenAdminIsCreatedTest() {
		Administrator admin = new Administrator("username", "password");
		assertNotEquals(admin.getClass(), User.class);
		assertEquals(admin.getClass(), Administrator.class);
	}

	@Test
	public void shouldReturnUserRoleWhenUserIsCreatedTest() {
		User user = new User("username", "password");
		assertEquals(user.getClass(), User.class);
		assertNotEquals(user.getClass(), Administrator.class);
	}

	//check if they are in the current session
	@Test
	public void shouldReturnOneWhenUserMakesASearchAndChecksCurrentSessionCountTest() {
			User user = new User("u1", "p1");
			user.setSignStatus();
			int userSessionCount = 0;
			if (user.signStatus()) {
				sh.doSearch("input", user);
				user.addSearchCount();
				userSessionCount = user.getSessionCount();
			}
			assertEquals(1, userSessionCount);
	}

	@Test
	public void shouldReturnTwoWhenUserMakesTwoSearchesAndSignsOffAndInAndChecksTotalSearchCountTest() {
			User user = new User("u1", "p1");
			user.setSignStatus();
			int userTotalSearchCount = 0;
			int userSessionCount = 0;
			if (user.signStatus()) {
				sh.doSearch("input", user);
				user.addSearchCount();
				sh.doSearch("input", user);
				user.addSearchCount();
			}
			user.setSignStatus();
			user.setSignStatus();
			if (user.signStatus()) {
				userTotalSearchCount = user.getTotalSearchCount();
				userSessionCount = user.getSessionCount();
			}
			assertEquals(2, userTotalSearchCount);
			assertEquals(0, userSessionCount);

	}


}

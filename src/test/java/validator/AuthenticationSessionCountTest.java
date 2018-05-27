package validator;

import static org.junit.Assert.*;

import org.junit.Test;

import authentication.User;



/*The count of searches that have been performed by a user so far in the current session and the total count of searches should be recorded.*/
public class AuthenticationSessionCountTest {

	//check if they are in the current session
	@Test
	public void userCurrentSessionCountTest() {
		User user = new User("username","password");
		user.signIn("username","password");
		int userSessionCount = 0;
		if (user.signStatus()) {
			user.addSearchCount();
			userSessionCount = user.getSessionCount();
		}
		assertEquals(1, userSessionCount);
		

	}
	
	@Test
	public void userTotalSearchCountOfTwoTest() {
		User user = new User("username","password");
		int userTotalSearchCount = 0;
		int userSessionCount = 0;
		user.signIn("username","password");
		if (user.signStatus()) {
			user.addSearchCount();
			user.addSearchCount();
		}
		user.signOut();
		user.signIn("username","password");
		if (user.signStatus()) {
			userTotalSearchCount = user.getTotalSearchCount();
			userSessionCount = user.getSessionCount();
		}
		assertEquals(2, userTotalSearchCount);
		assertEquals(0, userSessionCount);
	}

}

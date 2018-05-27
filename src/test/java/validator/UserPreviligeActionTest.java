package validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import validator.authentication.User;
import validator.word.InputProcessor;
import validator.word.SearchHandler;

public class UserPreviligeActionTest {
	SearchHandler sh;

	@Before
	public void setup(){
		sh = new SearchHandler(Mockito.mock(InputProcessor.class));
	}

	//test for total count increasing when doSearch is called
	@Test
	public void shouldIncreaseTotalSearchCountWhenUserMakesASearchTest() {
		User user = Mockito.spy(new User("u1", "p1"));
		int totalBefore = user.getTotalSearchCount();
		sh.doSearch("input", user);
		assertEquals(totalBefore + 1, user.getTotalSearchCount());
	}
	
	//check if they are in the current session
	@Test
	public void shouldIncreaseSessionSearchCountWhenUserMakesASearchTest() {
		User user = Mockito.spy(new User("u1", "p1"));
		assertEquals(0, user.getSessionCount());
		sh.doSearch("input", user);
		assertEquals(1, user.getSessionCount());
	}

	//test for total count should not be cleared after signout
	@Test
	public void shouldNotResetTotalSearchCountWhenUserSignsOutTest() {
		User user = Mockito.spy(new User("u1", "p1"));
		int totalBefore = user.getTotalSearchCount();
		sh.doSearch("input", user);
		sh.doSearch("input", user);
		user.signOut();
		assertEquals(totalBefore + 2, user.getTotalSearchCount());		
	}
	//test for session count cleared after signout
	@Test
	public void shouldResetSessionSearchCountWhenUserSignsOutTest() {
		User user = Mockito.spy(new User("u1", "p1"));
		sh.doSearch("input", user);
		sh.doSearch("input", user);
		user.signOut();
		assertEquals(0, user.getSessionCount());
	}
}
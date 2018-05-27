package validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import validator.authentication.Registry;
import validator.authentication.User;
import validator.database.MongoDatabase;
import validator.word.InputProcessor;
import validator.word.SearchHandler;

public class UserPreviligeActionTest {
	private Registry registry;
	SearchHandler sh;
	private MongoDatabase db;
	private User user;

	@Before
	public void setup(){
		sh = new SearchHandler(Mockito.mock(InputProcessor.class));
		db = Mockito.mock(MongoDatabase.class);
		registry = Registry.getInstance();
		registry.setDatabase(db);
		Mockito.when(db.getTotalSearchCount("u1")).thenReturn(5);
		user = Mockito.spy(new User("u1", "p1"));
	}

	//test for total count increasing when doSearch is called
	@Test
	public void shouldIncreaseTotalSearchCountWhenUserMakesASearchTest() {
		int totalBefore = user.getTotalSearchCount();
		sh.doSearch("input", user);
		assertEquals(totalBefore + 1, user.getTotalSearchCount());
	}
	
	//check if they are in the current session
	@Test
	public void shouldIncreaseSessionSearchCountWhenUserMakesASearchTest() {
		assertEquals(0, user.getSessionCount());
		sh.doSearch("input", user);
		assertEquals(1, user.getSessionCount());
	}

	//test for total count should not be cleared after signout
	@Test
	public void shouldNotResetTotalSearchCountWhenUserSignsOutTest() {
		int totalBefore = user.getTotalSearchCount();
		sh.doSearch("input", user);
		sh.doSearch("input", user);
		user.signOut();
		assertEquals(totalBefore + 2, user.getTotalSearchCount());		
	}
	//test for session count cleared after signout
	@Test
	public void shouldResetSessionSearchCountWhenUserSignsOutTest() {
		sh.doSearch("input", user);
		sh.doSearch("input", user);
		user.signOut();
		assertEquals(0, user.getSessionCount());
	}
}
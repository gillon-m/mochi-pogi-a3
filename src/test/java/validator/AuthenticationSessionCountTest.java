package validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import authentication.Administrator;
import authentication.Registry;
import authentication.User;
import validator.marketcomprehension.MongoClient;
import validator.marketcomprehension.MongoDatabase;


/*The count of searches that have been performed by a user so far in the current session and the total count of searches should be recorded.*/
public class AuthenticationSessionCountTest {


	private Registry databaseRegistry;
	@Before
	public void initialise() {
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
	//check if they are in the current session
	@Test
	public void userCurrentSessionCountTest() {
		
	}
	
	@Test
	public void userTotalCountTest() {
		
	}

}

package validator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import validator.marketcomprehension.MongoClient;
import validator.marketcomprehension.MongoDatabase;


/*The count of searches that have been performed by a user so far in the current session and the total count of searches should be recorded.*/
public class AuthenticationSessionCountTest {

	@Mock MongoClient mongoClient;
	@Mock MongoDatabase mongoDatabase;
	String roleRegistryDBName;
	
	
	@Before
	public void initialise() {

		
		
		MockitoAnnotations.initMocks(this);
		roleRegistryDBName = "RoleRegistry";
		mongoClient = Mockito.mock(MongoClient.class);
		mongoDatabase = new MongoDatabase();
		
	}
	//check if they are in the current session
	@Test
	public void checkRandomUserSearchCountTest() {
		
	}

}

package authentication;

import validator.marketcomprehension.MongoDatabase;

public class Registry {
	private MongoDatabase _db;
	
	public Registry(MongoDatabase db) {
		_db = db;
	}
	
	public Role signIn(String username, String password){
		return null;
		
	}
	
	public void signOff(Role role){
		
	}
	
	public void signUp(){
		
	}

	public Role signUp(String username, String password, Class RoleTypeClass) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

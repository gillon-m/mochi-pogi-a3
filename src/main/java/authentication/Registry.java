package authentication;

import java.util.List;

import validator.exceptions.AuthenticationException;
import validator.marketcomprehension.MongoDatabase;

public class Registry {
	private static Registry instance;
	private MongoDatabase _db;
	private List<Role> rolesList;

	private Registry() {
	}

	public Role signIn(String username, String password){
		for (int i= 0; i< rolesList.size(); i++) {
			if (rolesList.get(i).getUsername() == username) {
				if (rolesList.get(i).getPassword() == password) {
					rolesList.get(i).setSignStatus();
					return rolesList.get(i);
				} else {
					throw new AuthenticationException("Password Incorrect");
				}
			} else {
				throw new AuthenticationException("Role Not Found");
			}
		}
		return null;

	}

	public void signOff(Role role){
		for (int i= 0; i< rolesList.size(); i++) {
			if (rolesList.get(i).getUsername() == role.getUsername()) {
				if (rolesList.get(i).getPassword() == role.getPassword()) {
					rolesList.get(i).setSignStatus();
					//has successfully signed off
				} else {
					throw new AuthenticationException("Role Not Found");
				}
			} else {
				throw new AuthenticationException("Role Not Found");
			}
		}
	}

	public Role signUp(String username, String password, Class RoleTypeClass) {
		for (int i= 0; i< rolesList.size(); i++) {
			if (rolesList.get(i).getUsername() == username) {
				throw new AuthenticationException("Username Already Exists");
			}
		}
		Role role;
		if (RoleTypeClass.equals(User.class)) {
			User user = new User(username, password);
			role = user;
		} else {
			Administrator admin = new Administrator(username, password);
			role = admin;
		}

		return role;
	}

	public static Registry getInstance() {
		if(instance == null) {
			instance = new Registry();
		}
		return instance;
	}
	public void setDatabase(MongoDatabase db){
		_db = db;
		rolesList=db.getRoles();
	}
	
	int getUserSize() {
		int count = 0;
		for (int i= 0; i< rolesList.size(); i++) {
			if (rolesList.get(i) instanceof User) {
				count++;
			}
		}
		return count;
	}

}

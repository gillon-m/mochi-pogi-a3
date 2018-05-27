package authentication;

import java.util.List;

import validator.exceptions.AuthenticationException;
import validator.marketcomprehension.MongoDatabase;

public class Registry {
	private static Registry instance;
	private List<Role> rolesList;

	private Registry() {
	}

	public Role signIn(String username, String password){
		boolean passwordIncorrect = false;
		boolean roleNotFound = false;
		for (int i= 0; i< rolesList.size(); i++) {
			if (rolesList.get(i).getUsername() == username) {
				if (rolesList.get(i).getPassword() == password) {
					rolesList.get(i).setSignStatus();
					return rolesList.get(i);
				} else {
					passwordIncorrect = true;
					break;
				}
			} else {
				roleNotFound = true;
			}
		}
		if (passwordIncorrect) {
			throw new AuthenticationException("Password Incorrect");
		}
		if (roleNotFound) {
			throw new AuthenticationException("Role Not Found");
		}
		return null;

	}

	public void signOff(Role role){
		boolean roleNotFound = false;
		for (int i= 0; i< rolesList.size(); i++) {
			if (rolesList.get(i).getUsername() == role.getUsername()) {
				if (rolesList.get(i).getPassword() == role.getPassword()) {
					rolesList.get(i).setSignStatus();
					//has successfully signed off
					roleNotFound = false;
					break;
				} else {
					roleNotFound = true;
				}
			} else {
				roleNotFound = true;
			}
		}
		if (roleNotFound) {
			throw new AuthenticationException("Role Not Found");
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

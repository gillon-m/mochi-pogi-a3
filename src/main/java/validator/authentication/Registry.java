package validator.authentication;

import java.util.List;

import validator.database.MongoDatabase;
import validator.exceptions.AuthenticationException;

public class Registry {
	private static Registry instance;
	private MongoDatabase db;
	private List<Role> rolesList;

	private Registry() {
	}

	public static Registry getInstance() {
		if(instance == null) {
			instance = new Registry();
		}
		return instance;
	}

	public Role signIn(String username, String password){
		boolean roleNotFound = false;
		for (Role role : rolesList) {
			if (role.getUsername().equals(username)) {
				if (role.getPassword().equals(password)) {
					role.signIn();
					return role;
				} else {
					throw new AuthenticationException("Password Incorrect");
				}
			} else {
				roleNotFound = true;
			}
		}
		if (roleNotFound) {
			throw new AuthenticationException("Role Not Found");
		}
		return null;
	}

	public void signOff(String username){
		Role role = null;
		for (Role r: rolesList) {
			if (r.getUsername().equals(username)) {
				role = r;
			}
		}
		if (role != null) {
			role.signOut();			
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

	public void setDatabase(MongoDatabase db){
		this.db = db;
	}
	
	public void setRoles() {
		rolesList = db.getRoles();
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
	
	public int getTotalSearchCount(String username) {
		return db.getTotalSearchCount(username);
	}

	public void setTotalSearchCount(String username, int updatedCount) {
		db.setTotalSearchCount(username, updatedCount);
	}
}

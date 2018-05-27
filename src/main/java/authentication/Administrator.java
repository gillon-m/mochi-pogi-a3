package authentication;

import validator.exceptions.AuthenticationException;

public class Administrator implements Role{
	public String _username;
	public String _password;
	private boolean _signStatus;

	public Administrator(String username, String password) {
		_username = username;
		_password = password;
	}


	public String getUsername() {
		return _username;
	}

	public String getPassword() {
		return _password;
	}

	public int checkRegisteredUsers() {
		return Registry.getInstance().getUserSize();
		
	}
	
	public boolean signStatus() {
		return _signStatus;
	}

	public void setSignStatus() {
		if (_signStatus) {
			_signStatus = false;
		} else {
			_signStatus = true;
		}
	
		
	}

}

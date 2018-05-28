package validator.authentication;

public class Administrator implements Role{
	public String _username;
	public String _password;
	private boolean _isSignedIn;

	public Administrator(String username, String password) {
		_username = username;
		_password = password;
		_isSignedIn = true;
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
	
	public boolean isSignedIn() {
		return _isSignedIn;
	}

	public void signIn() {
		_isSignedIn = true;
	}
	
	public void signOut() {
		_isSignedIn = false;
	}

}

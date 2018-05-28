package validator.authentication;

import validator.exceptions.AuthenticationException;

public class User implements Role{
	private String _username;
	private String _password;
	private boolean _isSignedIn;
	private int _sessionCount;
	private int _totalSearchCount;
	
	public User(String username, String password) {
		_username = username;
		_password = password;
		_isSignedIn = true;
		_sessionCount = 0;
		_totalSearchCount = Registry.getInstance().getTotalSearchCount(username);
	}

	public String getUsername() {
		return _username;
	}

	public String getPassword() {
		return _password;
	}

	private void resetSessionCount() {
		_sessionCount = 0;
	}

	public boolean isSignedIn() {
		return _isSignedIn;
	}

	public int getSessionCount() {
		return _sessionCount;
		
	}

	public void addSearchCount() {
		_sessionCount++;
		_totalSearchCount++;
		
	}

	public int getTotalSearchCount() {
		return _totalSearchCount;
		
	}


	public int checkRegisteredUsers() {
		throw new AuthenticationException("User Cannot Search");
	}

	public void signIn() {
		_isSignedIn = true;
	}
	
	public void signOut() {
		_isSignedIn = false;
		resetSessionCount();
		Registry.getInstance().setTotalSearchCount(_username, _totalSearchCount);
	}	
}

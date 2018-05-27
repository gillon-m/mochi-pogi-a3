package validator.authentication;

import validator.exceptions.AuthenticationException;

public class User implements Role{

	public String _username;
	public String _password;
	public boolean _signStatus;
	public int _sessionCount;
	public int _totalSearchCount;
	public String _input;
	
	public User(String username, String password) {
		_username = username;
		_password = password;
		_signStatus = false;
		_sessionCount = 0;
		_totalSearchCount = 0;
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

	public boolean signStatus() {
		return _signStatus;
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

	public void setSignStatus() {
		if (_signStatus) {
			resetSessionCount();
			_signStatus = false;
		} else {
			_signStatus = true;
		}
	}


}

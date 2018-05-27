package authentication;

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
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean signStatus() {
		return _signStatus;
	}


	public void addSearchCount() {
		// TODO Auto-generated method stub
		
	}


	public int getSessionCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getTotalSearchCount() {
		// TODO Auto-generated method stub
		return 0;
	}


}

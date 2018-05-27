package authentication;

public interface Role {
	public String getUsername();
	public String getPassword();
	
	public void setUsername(String username);
	public void setPassword(String password);
	
	public boolean signIn(String username, String password);
	public boolean signOut();
	public int checkRegisteredUsers();
	public boolean signStatus();
	
}

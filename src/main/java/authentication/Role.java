package authentication;

public interface Role {
	public String getRoleType();
	public String getUsername();
	public String getPassword();
	
	public void setUsername(String username);
	public void setPassword(String password);
	
	public boolean signIn();
	public boolean signOut();
	
}

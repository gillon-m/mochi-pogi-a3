package validator.authentication;

public interface Role {
	public String getUsername();
	public String getPassword();

	public int checkRegisteredUsers();
	
	public void signIn();
	public void signOut();
	public boolean isSignedIn();
}

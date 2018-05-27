package authentication;

public interface Role {
	public String getUsername();
	public String getPassword();

	public int checkRegisteredUsers();
	
	public void setSignStatus();
	public boolean signStatus();
	
	public void addSearchCount();
	public int getSessionCount();
	public int getTotalSearchCount();
	
}
package validator.marketcomprehension;

import java.util.List;

import authentication.Role;
import validator.marketcomprehension.Document;


public interface MongoDatabase {

	public List<Document> getList();

	public void addDocument(Document document);
	
	public void addAllDocuments(List<Document> documentz);
	
	public void addRole();
	
	public List<Role> getRoles();
	
	
	
	
}

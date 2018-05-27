package validator.marketcomprehension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import authentication.Role;
import validator.Word;
import validator.marketcomprehension.Document;
import validator.marketcomprehension.DocumentBuilder;


public interface MongoDatabase {

	public List<Document> getList();

	public void addDocument(Document document);
	
	public void addAllDocuments(List<Document> documentz);
	
	public void addRole();
	
	public Role getRole(String username);
	
	
	
	
}

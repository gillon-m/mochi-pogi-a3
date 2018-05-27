package validator.database;

import java.util.List;

import validator.authentication.Role;
import validator.cluster.Document;


public interface MongoDatabase {

	public List<Document> getList();

	public void addDocument(Document document);
	
	public void addAllDocuments(List<Document> documentz);
		
	public List<Role> getRoles();	
}

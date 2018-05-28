package validator.database;

import java.util.List;
import java.util.Set;

import validator.cluster.Document;

public class DocumentPersistence {

	private String dbname;
	private MongoClient mongoClient;
	private String collectionName;
	private MongoDatabase mongoDatabase;
	private List<Document> mongoList;

	
	public DocumentPersistence(MongoClient mongoClient,
			String dbname) {
		this.mongoClient = mongoClient;
		this.mongoDatabase = this.mongoClient.getDatabase(dbname);
		this.mongoList = this.mongoDatabase.getList();
	}
	
	public List<Document> getAllDocuments() {
		return mongoList;
	}
}

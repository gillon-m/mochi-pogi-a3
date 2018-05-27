package validator.marketcomprehension;

import java.util.List;
import java.util.Set;

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
	
	public boolean isMongoDBClientNull() {
		return this.mongoClient==null;
	}
	
	public List<Document> getAllDocuments() {
		return mongoList;
	}
}

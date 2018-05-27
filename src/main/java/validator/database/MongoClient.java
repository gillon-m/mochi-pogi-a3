package validator.database;

public interface MongoClient {

	public MongoDatabase getDatabase(String documentRegistryDBName);

}

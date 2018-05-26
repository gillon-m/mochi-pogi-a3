package validator.marketcomprehension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import validator.Category;
import validator.Word;

public class MarketComprehension {	
	/**
	 * Gets documents from the database if the document contains keyword param keyword.
	 * @param keywords the keyword to identify documents
	 * @param mongoClient name of the mongoDB client
	 * @param documentRegistryDBName name of the database
	 * @return
	 */
	public List<Document> getDocumentsFromKeywords(List<String> keywords, MongoClient mongoClient, String documentRegistryDBName) {
		List<Document> searchResultDocuments = new ArrayList<Document>();
		DocumentPersistence documentPersistence = new DocumentPersistence(mongoClient, documentRegistryDBName);
		for (Document d : documentPersistence.getAllDocuments()) {
			if (d.getStringKeyWords().containsAll(keywords)) {
				searchResultDocuments.add(d);
			}
		}
		return searchResultDocuments;
	}
	
	/**
	 * Returns a list of all unique categories for a given list of documents
	 * @param docs documents to analyze
	 * @return
	 */
	public List<Category> getClustersFromDocuments(List<Document> docs) { 
		List<String> allDocumentKeywords = new ArrayList<String>();
		for (Document d : docs) {
			allDocumentKeywords.addAll(d.getStringKeyWords());
		}

		List<Category> categoryCluster = getCategoryClusters(docs);
		return categoryCluster;
	}
	
	/**
	 * For a given category, places the document in that category if it contains that keyword
	 * @param docs List of documents to categorize
	 * @return
	 */
	private List<Category> getCategoryClusters(List<Document> docs) {
		ArrayList<Category> categories = new ArrayList<Category>();
		categories.addAll(getCategoryNames(docs));
		for (Document d : docs) {
			for (Category c : categories) {
				if (d.getStringKeyWords().contains(c.toString())) {
					c.addDocument(d);
				}
			}
		}
		
		return categories;
	}

	/**
	 * Creates a list of categories names dynamically based on document keywords
	 * @param docs documents to generate names for.
	 * @return
	 */
	private Set<Category> getCategoryNames(List<Document> docs) {
		List<String> keywordsUsed = new ArrayList<String>();
		Set<Category> categorySet = new HashSet<Category>();
		for (Document d : docs) {
			for (String keyword : d.getStringKeyWords()) {
				if (!keywordsUsed.contains(keyword)) {
					categorySet.add(new Category(keyword));
					keywordsUsed.add(keyword);
				}
			}
		}
		return categorySet;
	}
}

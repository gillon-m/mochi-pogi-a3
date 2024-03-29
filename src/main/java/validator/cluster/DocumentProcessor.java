package validator.cluster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import validator.database.DocumentPersistence;
import validator.database.MongoClient;
import validator.exceptions.NoDocumentsReturnedException;

public class DocumentProcessor {	
	/**
	 * Gets documents from the database if the document contains keyword param keyword.
	 * @param keywords the keyword to identify documents
	 * @param mongoClient name of the mongoDB client
	 * @param documentRegistryDBName name of the database
	 * @return
	 * @throws Exception 
	 */
	public List<Document> getDocumentsFromKeywords(List<String> keywords, MongoClient mongoClient, String documentRegistryDBName) throws NoDocumentsReturnedException {
		List<Document> searchResultDocuments = new ArrayList<Document>();
		DocumentPersistence documentPersistence = new DocumentPersistence(mongoClient, documentRegistryDBName);
		for (Document d : documentPersistence.getAllDocuments()) {
			for (String s : keywords) {
				if (d.getStringKeyWords().contains(s)) {
					searchResultDocuments.add(d);
				}

			}
		}
		if (searchResultDocuments.size() == 0) {
			throw new NoDocumentsReturnedException("No documents were returned by your search. No documents were returned by your search query, or an error occurred your search.");
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

	public String generateLabel(String category, List<String> keywords, List<Document> documents) {
		List<Document> labelDocs = new ArrayList<Document>();

		for (Document d : documents) {
			for (String s : d.getStringKeyWords()) {
				if (s.equals(category)) {
					labelDocs.add(d);
				}
			}
		}
		List<Category> categoryCluster = getClustersFromDocuments(labelDocs);


		List<String> categoryNames = new ArrayList<String>();
		for (Category c : categoryCluster) {
			categoryNames.add(c.toString());
		}

		Collections.sort(keywords, String.CASE_INSENSITIVE_ORDER);
		Collections.sort(categoryNames, String.CASE_INSENSITIVE_ORDER);

		String label = "Searching for keyword: ";

		for (int i = 0; i < keywords.size(); i++) {
			if (i == keywords.size()-1) {
				label += keywords.get(i);
			} else {
				label += keywords.get(i) + ", ";
			}
		}

		label += " contains " + categoryCluster.size() + " categories. The " + category + " category contains " + category + " documents for ";

		for (int i = 0; i < categoryNames.size(); i++) {
			if (i == categoryNames.size()-1) {
				label += categoryNames.get(i);
			} else {
				label += categoryNames.get(i) + ", ";
			}
		}

		label += ".";
		return label;
	}


}

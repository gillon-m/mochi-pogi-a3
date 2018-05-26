package validator;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import validator.marketcomprehension.Document;
import validator.marketcomprehension.MarketComprehension;
import java.util.HashSet;
import java.util.Set;

/**
 * A category of related documents
 * @author Gillon Manalastas
 *
 */
public class Category {
		
	private String _label;
	private double _relevance;

	private List<Document> _documents;

	private static final double DEFAULT_RELEVANCE = Relevance.RELEVANT.factor();

	/**
	 * Create a category with a given label
	 * @param label	what the category is called
	 */
	public Category(String label){
		_documents = new ArrayList<Document>();
		_label = label;
		_relevance = DEFAULT_RELEVANCE;

	}

	/**
	 * Adds a document to this label
	 * @param document the document to be added to _documents
	 */
	public void addDocument(Document document) {
		_documents.add(document);
	}
	
	/**
	 * Returns the list of all documents for this category.
	 */	
	public List<Document> getDocumentsOfThisCategory() {
		return _documents;
	}

	/**
	 * Return number of documents in this category
	 * @return documents in this category
	 */
	public int size() {
		return _documents.size();
	}


	/**
	 * Set the relevance of this category compared to the user's idea
	 * If set < 0.0 then it is set to 0
	 * If set > 1.0 then it is set to 1.0
	 * @param r
	 */
	public void relevance(double r) {
		r = Math.max(0.0, r);
		r = Math.min(1.0, r);
		_relevance = r;
	}
	/**
	 * Set the relevance of this category compared to the user's idea
	 * @param r
	 */
	public void relevance(Relevance r) {
		relevance(r.factor());
	}
	/**
	 * Get relevance of the category to the user's idea
	 * @return relevance
	 */
	public double relevance() {
		return _relevance;
	}
	@Override
	public String toString(){
		return _label;
	}
	public String generateLabel(String category, List<String> keywords, List<Document> documents) {
		List<Document> labelDocs = new ArrayList<Document>();
		MarketComprehension mc = new MarketComprehension();
		
		for (Document d : documents) {
			for (String s : d.getStringKeyWords()) {
				if (s.equals(category)) {
					labelDocs.add(d);
				}
			}
		}
		List<Category> categoryCluster = mc.getClustersFromDocuments(labelDocs);
		
		
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
	public String getSummary(String categoryToSummarize, List<Document> documents) {
		return null;
	}
}

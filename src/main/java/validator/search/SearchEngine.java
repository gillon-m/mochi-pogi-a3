package validator.search;

import java.util.List;
import java.util.Set;

import validator.cluster.Category;
import validator.cluster.Document;
import validator.word.Word;

public interface SearchEngine {
	public List<Document> getDocumentsFromKeyWords(List<String> keywords);
	public Set<Category> getCategoriesFromDocuments(List<Document> documents);
}

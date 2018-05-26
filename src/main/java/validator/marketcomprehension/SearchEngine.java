package validator.marketcomprehension;

import java.util.List;
import java.util.Set;

import validator.Category;
import validator.Word;

public interface SearchEngine {
	public List<Document> getDocumentsFromKeyWords(List<String> keywords);
	public Set<Category> getCategoriesFromDocuments(List<Document> documents);
}

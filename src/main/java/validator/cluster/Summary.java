package validator.cluster;

import java.util.List;

public interface Summary {

	public String getSummary(String categoryToSummarize, List<Document> documents);
}

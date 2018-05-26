package validator.marketcomprehension;

import java.util.ArrayList;
import java.util.List;

import validator.Category;
import validator.Word;

public class Document {
	private String title;
	private String content;
	private String summary;
	private List<Word> keywords;
	
	public Document() {}
	public Document(String title, String content, String summary, 
			List<Word> keywords) {
		
		this.title = title;
		this.content = content;
		this.summary = summary;
		this.keywords = keywords;
	}
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getSummary() {
		return summary;
	}

	public List<Word> getKeywords() {
		return keywords;
	}
	
	public List<String> getStringKeyWords() {
		List<String> stringKeywords = new ArrayList<String>();
		for (Word w : keywords) {
			stringKeywords.add(w.getName());
		}
		return stringKeywords;
	}
}

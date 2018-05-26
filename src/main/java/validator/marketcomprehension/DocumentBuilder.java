package validator.marketcomprehension;

import java.util.List;

import validator.marketcomprehension.Document;
import validator.Category;
import validator.Word;

public class DocumentBuilder {
	private String title;
	private String content;
	private String summary;
	private List<Word> keywords;

	public DocumentBuilder() {
	}
	
	public DocumentBuilder setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public DocumentBuilder setContent(String content) {
		this.content = content;
		return this;
	}
	
	public DocumentBuilder setSummary(String summary) {
		this.summary = summary;
		return this;
	}
	
	public DocumentBuilder setKeywords(List<Word> keywords) {
		this.keywords = keywords;
		return this;
	}
	
	public Document buildDocument() {
		Document document = new Document(title, content, summary, keywords);			
		return document;
	}
}

package MarketComprehension;

import java.util.List;

import validator.Category;

public class Document {
	private String title;
	private String content;
	private String summary;
	private List<Category.CategoryType> categories;
	private List<String> keywords;
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public List<Category.CategoryType> getCategories() {
		return categories;
	}
	
	public List<String> getKeywords() {
		return keywords;
	}
	
	public static class DocumentBuilder {
		private String title;
		private String content;
		private String summary;
		private List<Category.CategoryType> categories;
		private List<String> keywords;

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
		
		public DocumentBuilder setCategories(List<Category.CategoryType> categories) {
			this.categories = categories;
			return this;
		}
		public DocumentBuilder setKeywords(List<String> keywords) {
			this.keywords = keywords;
			return this;
		}
		
		
		public Document buildDocument() {
			Document document = new Document();
			document.categories = this.categories;
			document.content = this.content;
			document.keywords = this.keywords;
			document.summary = this.summary;
			document.title = this.title;
			
			return document;
		}

	}
}

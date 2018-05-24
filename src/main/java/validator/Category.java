package validator;

import java.util.List;

import MarketComprehension.Document;

public class Category {
	public enum CategoryType {SPORTS, ANIMALS ,WALKING,ARTS,CRAFTS,COLLECTIBLES,
		BEAUTY, FRAGRANCES,BOOKS,MAGAZINES,CLOTHING, COMPUTERS, ACCESSORIES,SHOES,
		EDUCATION, ELECTRONICS, ENTERTAINMENT, FOOD, DRINK};
		
	private List<Document> documents;
	
	public Category(List<Document> documents) {
		this.documents = documents;
	}
	/**
	 * Return number of documents in this category
	 * @return documents in this category
	 */
	public int size() {
		return 0;
	}
	
	
	
	public int size(Category.CategoryType categoryType) {
		int counter = 0;
		for (Document document : documents) {
			if (document.getCategories().contains(categoryType)) {
				counter ++;
			}
		}
		return counter;
	}

}

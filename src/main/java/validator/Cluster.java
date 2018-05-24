package validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import validator.Category;
/**
 * A cluster of categories
 * @author Gillon Manalastas
 *
 */
public class Cluster {
	private  Set<Category> _categories;
	/**
	 * Create Cluster
	 */
	public Cluster(){
		_categories = new HashSet<Category>();
	}
	/**
	 * Create cluster with categories
	 * @param c
	 */
	public Cluster(Set<Category> c){
		this();
		_categories.addAll(c);
	}
	/**
	 * Adds a category of documents to the cluster
	 * @param c
	 */
	public void add(Category c){
		_categories.add(c);
	}
	/**
	 * Adds categories of documents to the cluster
	 * @param c
	 */
	public void add(List<Category> c){
		_categories.addAll(c);
	}
	/**
	 * Get the popularity of a given category
	 * @param category
	 * @return popularity of the selected category
	 */
	public double getPopularity(Category category) {
		return getPopularity(category.toString());
	}
	/**
	 * Get the popularity of a given category
	 * @param category
	 * @return popularity of the selected category
	 */
	public double getPopularity(String category) {
		int totalDocuments = 0;
		for(Category c: _categories){
			totalDocuments+=c.size();
		}
		Category c = get(category);
		if(c==null){
			return 0.0;
		}else{
			int documentsInCategory = c.size();
			if(documentsInCategory==0){
				return 0.0;
			}
			return (double)documentsInCategory/(double)totalDocuments;
		}
	}
	/**
	 * Get the selected category
	 * @param category
	 * @return desired category
	 */
	public Category get(String category){
		for(Category c: _categories){
			if(c.toString().equals(category)){
				return c;
			}
		}
		return null;
	}
	/**
	 * Calculate the maturity of the business idea in the market
	 * @return
	 */
	public double maturity() {
		double maturity=0.0;
		for(Category c: _categories){
			maturity+=c.relevance()*getPopularity(c);
		}
		return maturity;
	}	
}

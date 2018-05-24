package validator;

public class Category {
	private String _label;
	private double _relevance;
	private static final double DEFAULT_RELEVANCE = 0.5;
	
	/**
	 * Create a category with a given label
	 * @param label	what the category is called
	 */
	public Category(String label){
		_label = label;
		_relevance = DEFAULT_RELEVANCE;
	}


	/**
	 * Return number of documents in this category
	 * @return documents in this category
	 */
	public int size() {
		return 0;
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
}

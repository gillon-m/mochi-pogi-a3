package validator;

public enum Relevance {
	NOT_RELEVANT(0.0), WEAK_RELEVANT(0.2), RELEVANT(0.5), VERY_RELEVANT(0.8), THE_SAME(1.0);
	
	private double _relevanceFactor;
	private Relevance(double relevanceFactor){
		_relevanceFactor = relevanceFactor;
	}
	public double factor(){
		return _relevanceFactor;
	}
}

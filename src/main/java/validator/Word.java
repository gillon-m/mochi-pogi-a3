package validator;

public class Word {
	private String _name;
	private int _weight;
	
	public Word(String name) {
		this(name, 0);
	}
	public Word(String name, int weight) {
		_name = name;
		_weight = weight;
	}

	public int getWeight() {
		return _weight;
	}
	
	public void setWeight(int weight) {
		_weight = weight;
	}
	
	public String getName() {
		return _name;
	}
}
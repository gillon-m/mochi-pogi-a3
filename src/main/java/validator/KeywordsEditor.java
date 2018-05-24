package validator;

import java.util.Set;

public class KeywordsEditor {
	private Set<Word> _words;
	public KeywordsEditor(Set<Word> words) {
		_words = words;
	}
	
	public void changeKeywordPriority(Word word, int newWeight) {
		word.setWeight(newWeight);
	}
}
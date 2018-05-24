package validator;

import java.util.Iterator;
import java.util.Set;

import validator.exceptions.KeywordException;

public class KeywordsEditor {
	private Set<Word> _words;
	public KeywordsEditor(Set<Word> words) {
		_words = words;
	}
	
	public void changeKeywordPriority(Word keyword, int weight) {
		if (isValidWeight(weight)) {
			keyword.setWeight(weight);
		}
	}
	
	public void injectWord(Word wordToAdd) {
		_words.add(wordToAdd);
	}

	private boolean isValidWeight(int weight) {
		if (weight > 10) {
			throw new KeywordException("The maximum weight for a keyword is 10");
		} else if (weight < 1) {
			throw new KeywordException("The minimum weight for a keyword is 1");
		}
		Iterator<Word> iterator = _words.iterator();
		while (iterator.hasNext()) {
			Word word = iterator.next();
			if (word.getWeight() == weight) {
				throw new KeywordException("Each keyword must have a different weight");
			}
		}
		return true;
	}
}
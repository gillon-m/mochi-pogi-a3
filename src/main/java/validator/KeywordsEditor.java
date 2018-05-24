package validator;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import validator.exceptions.KeywordException;

public class KeywordsEditor {
	private final int MAX_SIZE = 5;
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
		if (_words.size() == MAX_SIZE) {
			throw new KeywordException("You can only have up to 5 keywords");
		}
		if (isValidName(wordToAdd.getName()) && (isValidWeight(wordToAdd.getWeight()))) {
			_words.add(wordToAdd);			
		}
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

	private boolean isValidName(String name) {
		Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
		Matcher matcher = pattern.matcher(name);
		if (!matcher.matches()) {
			throw new KeywordException("Keyword cannot contain numbers or symbols");
		}
		Iterator<Word> iterator = _words.iterator();
		while (iterator.hasNext()) {
			Word word = iterator.next();
			if (word.getName().equals(name)) {
				throw new KeywordException("This keyword is already in the list");
			}
		}
		return true;
	}
}
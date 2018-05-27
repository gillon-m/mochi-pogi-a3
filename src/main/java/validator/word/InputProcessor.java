package validator.word;

import java.util.Set;

public interface InputProcessor {
	public Set<Word> extractKeywords(String input);
}

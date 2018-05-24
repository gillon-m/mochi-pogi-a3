package validator;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.mockito.Mockito;

public class StepsChangeKeywords {
	private InputProcessor _processor;
	private KeywordsEditor _editor;
	private Set<Word> _words;
	private Word _word;

	@Given("Keywords list")
	public void givenKeywordsList() {
		_processor = Mockito.mock(InputProcessor.class);
		Word word1 = Mockito.spy(new Word("Dog", 10));
		Word word2 = Mockito.spy(new Word("Cat", 9));
		Set<Word> words = new HashSet<Word>();
		words.add(word1);
		words.add(word2);
		
		Mockito.when(_processor.extractKeywords()).thenReturn(words);	
		_words = _processor.extractKeywords();
	}
	@Given("User wants to inject a word to the list")
	public void whenUserWantsToAddToList() {
		_editor = new KeywordsEditor(_words);
	}
	
	@Given("The word is $word with a weight of $weight")
	public void givenWord(String word, int weight) {
		_word = Mockito.spy(new Word(word, weight));
	}

	@When("User injects it")
	public void whenUserInjectsWord() {
		_editor.injectWord(_word);
	}
	
	@Then("The word is added to the list")
	public void thenWordAddedToList() {
		assertTrue(_words.contains(_word));
	}
}

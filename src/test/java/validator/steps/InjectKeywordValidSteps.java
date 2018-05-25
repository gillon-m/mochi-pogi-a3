package validator.steps;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.mockito.Mockito;

import validator.InputProcessor;
import validator.KeywordsEditor;
import validator.Word;
import validator.exceptions.KeywordException;

public class InjectKeywordValidSteps {
	private InputProcessor _processor;
	private KeywordsEditor _editor;
	private Set<Word> _words;
	private Word _word;
	private Throwable _exception;
	
	@Given("A keywords list")
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
	
	@When("User injects a word $word with a weight of $weight")
	public void whenUserInjectsAWordToList(String word, int weight) {
		_editor = new KeywordsEditor(_words);
		_word = Mockito.spy(new Word(word, weight));
		try {
			_editor.injectWord(_word);
		} catch (KeywordException e) {
			_exception = e;
		}
	}
	
	@Then("The word is added to the list")
	public void thenWordAddedToList() {
		assertTrue(_words.contains(_word));
		assertTrue(_exception == null);
	}
}

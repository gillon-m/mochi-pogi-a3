package validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.mockito.Mockito;

import validator.exceptions.KeywordException;

public class StepsChangeKeywords {
	private InputProcessor _processor;
	private KeywordsEditor _editor;
	private Set<Word> _words;
	private Word _word;
	private Throwable _exception;
	
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
	
	@Given("A full keywords list")
	public void givenFullKeywordsList() {
		_processor = Mockito.mock(InputProcessor.class);
		Word word1 = Mockito.spy(new Word("Dog", 10));
		Word word2 = Mockito.spy(new Word("Cat", 9));
		Word word3 = Mockito.spy(new Word("Rabbit", 8));
		Word word4 = Mockito.spy(new Word("Horse", 7));
		Word word5 = Mockito.spy(new Word("Pig", 6));
		Set<Word> words = new HashSet<Word>();
		words.add(word1);
		words.add(word2);
		words.add(word3);
		words.add(word4);
		words.add(word5);
		
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
	
	@Then("Error message $message")
	public void thenUserGetsErrorMessage(String message) {
		assertEquals(message, _exception.getMessage());
	}
}

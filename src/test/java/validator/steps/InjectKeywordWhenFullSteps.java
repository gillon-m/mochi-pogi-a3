package validator.steps;

import static org.junit.Assert.assertEquals;

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

public class InjectKeywordWhenFullSteps {
	private InputProcessor _processor;
	private KeywordsEditor _editor;
	private Set<Word> _words;
	private Word _word;
	private Throwable _exception;
	
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

	@When("User injects a word $word with a weight of $weight")
	public void whenUserInjectsAWordToAFullList(String word, int weight) {
		_editor = new KeywordsEditor(_words);
		_word = Mockito.spy(new Word(word, weight));
		try {
			_editor.injectWord(_word);
		} catch (KeywordException e) {
			_exception = e;
		}
	}
	
	@Then("User gets an error message $message")
	public void thenUserGetsErrorMessage(String message) {
		assertEquals(message, _exception.getMessage());
	}
}

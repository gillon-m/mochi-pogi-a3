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

public class RemoveKeywordWhenEmptySteps {
	private InputProcessor _processor;
	private KeywordsEditor _editor;
	private Set<Word> _words;
	private Word _word;
	private Throwable _exception;

	@Given("An empty keywords list")
	public void givenEmptyKeywordsList() {
		_processor = Mockito.mock(InputProcessor.class);
		Set<Word> words = new HashSet<Word>();
		
		Mockito.when(_processor.extractKeywords()).thenReturn(words);	
		_words = _processor.extractKeywords();
	}

	@When("User selects a word $word to remove from the list")
	public void whenUserRemovesKeyword(String word) {
		_editor = new KeywordsEditor(_words);
		_word = Mockito.spy(new Word(word));
		try {
			_editor.removeWord(_word);			
		} catch (KeywordException e) {
			_exception = e;
		}
	}
	
	@Then("User gets an error message $message")
	public void thenUserGetsErrorMessage(String message) {
		assertEquals(message, _exception.getMessage());
	}
}

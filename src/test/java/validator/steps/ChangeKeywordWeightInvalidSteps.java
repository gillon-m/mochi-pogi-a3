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

public class ChangeKeywordWeightInvalidSteps {
	private InputProcessor _processor;
	private Set<Word> _words;
	private KeywordsEditor _editor;
	private Word _word;
	private Throwable _exception;

	@Given("A keywords list")
	public void givenKeywords() {
		_processor = Mockito.mock(InputProcessor.class);
		Word word1 = Mockito.spy(new Word("Dog", 10));
		Word word2 = Mockito.spy(new Word("Cat", 9));
		Set<Word> words = new HashSet<Word>();
		words.add(word1);
		words.add(word2);
		
		Mockito.when(_processor.extractKeywords()).thenReturn(words);		
		_words = _processor.extractKeywords();
	}
	
	@When("User wants to change the weight of a keyword to $weight")
	public void givenUserWantsToChangeWeight(int weight) {
		_editor = new KeywordsEditor(_words);
		_word = (Word) _words.toArray()[0];
		try {
			_editor.changeKeywordPriority(_word, weight);
		} catch (KeywordException e) {
			_exception = e;
		}
	}
	
	@Then("User gets an error message $message")
	public void thenUserGetsErrorMessage(String message) {
		assertEquals(message, _exception.getMessage());
	}
}
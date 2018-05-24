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

public class StepsChangeWeight {
	private InputProcessor _processor;
	private Set<Word> _words;
	private KeywordsEditor _editor;
	private Word _word;
	private int _newWeight;
	private Throwable _exception;

	@Given("User has keywords")
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
	
	@Given("User wants to change the weight")
	public void givenUserWantsToChangeWeight() {
		_editor = new KeywordsEditor(_words);
	}
	
	@Given("User selects one keyword to change its weight")
	public void givenUserSelectsOneKeyword() {
		_word = (Word) _words.toArray()[0];
	}
	
	@Given("The new weight is $weight")
	public void givenWeight(int weight) {
		_newWeight = weight;
	}
	
	@When("User sets the new weight")
	public void whenUserSetsNewWeight() {
		try {
			_editor.changeKeywordPriority(_word, _newWeight);
		} catch (KeywordException e) {
			_exception = e;
		}
	}

	@Then("The weight of the keyword changes")
	public void thenWeightOfKeywordsChanged() {
		assertEquals(_newWeight, _word.getWeight());
		assertTrue(_exception == null);
	}
	
	@Then("User gets an error message $message")
	public void thenUserGetsErrorMessage(String message) {
		assertEquals(message, _exception.getMessage());
	}
}
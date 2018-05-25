package validator.steps;

import static org.junit.Assert.assertFalse;
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

public class RemoveKeywordValidSteps {
	private InputProcessor _processor;
	private KeywordsEditor _editor;
	private Set<Word> _words;
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
	
	@Then("Keyword list no longer have it")
	public void thenKeywordListNoLongerHaveWord() {
		assertFalse(_words.contains(_word));
		assertTrue(_exception == null);
	}	
}

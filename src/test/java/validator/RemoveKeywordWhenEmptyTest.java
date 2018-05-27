package validator;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Test;
import org.mockito.Mockito;

import validator.exceptions.KeywordException;

public class RemoveKeywordWhenEmptyTest extends JUnitStory {
	private InputProcessor _processor;
	private KeywordsEditor _editor;
	private Set<Word> _words;
	private Word _word;
	private Throwable _exception;

	@Given("An empty keywords list")
	public void givenEmptyKeywordsList() {
		_processor = Mockito.mock(InputProcessor.class);
		Set<Word> words = new HashSet<Word>();
		
		Mockito.when(_processor.extractKeywords("keyword input")).thenReturn(words);	
		_words = _processor.extractKeywords("keyword input");
	}

	@When("User selects a word $word to remove from the list")
	public void whenUserRemovesKeywordFromEmptyList(String word) {
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

	@Override
	public List<CandidateSteps> candidateSteps() {
		return new InstanceStepsFactory(configuration(), this).createCandidateSteps();
	}

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration().useStoryLoader(
				new LoadFromClasspath(getClass().getClassLoader())).useStoryReporterBuilder(
						new StoryReporterBuilder().withFormats(Format.CONSOLE));
	}
	
	@Override
	@Test
	public void run() throws Throwable {
		super.run();
	}
}

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
import validator.word.InputProcessor;
import validator.word.KeywordsEditor;
import validator.word.Word;

public class InjectKeywordWhenFullTest extends JUnitStory {
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
		
		Mockito.when(_processor.extractKeywords("keyword input")).thenReturn(words);	
		_words = _processor.extractKeywords("keyword input");
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

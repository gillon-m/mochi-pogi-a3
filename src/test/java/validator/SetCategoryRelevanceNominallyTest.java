package validator;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Test;

import org.jbehave.core.junit.JUnitStory;

public class SetCategoryRelevanceNominallyTest extends JUnitStory{
	private final static double EPSILON = 0.001;
	Category _category;
	
	@Given("that the user is given a cluster with the category: $category")
	public void givenCategory(String category){
		_category = new Category(category);
	}
	@When("the user sets the relevance of category to $nominalRelevance")
	public void whenSetNominalRelevance(String nominalRelevance){
		Relevance r = null;
		if(nominalRelevance.equals("NOT_RELEVANT")){
			r = Relevance.NOT_RELEVANT;
		}else if(nominalRelevance.equals("WEAK_RELEVANT")){
			r = Relevance.WEAK_RELEVANT;
		}else if(nominalRelevance.equals("RELEVANT")){
			r = Relevance.RELEVANT;
		}else if(nominalRelevance.equals("VERY_RELEVANT")){
			r = Relevance.VERY_RELEVANT;
		}else if(nominalRelevance.equals("THE_SAME")){
			r = Relevance.THE_SAME;
		}
		_category.relevance(r);
	}
	@Then("the relevance of the category changes to $relevance")
	public void thenRevenceIsSetTo(double relevance){
		assertEquals(relevance, _category.relevance(), EPSILON);
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

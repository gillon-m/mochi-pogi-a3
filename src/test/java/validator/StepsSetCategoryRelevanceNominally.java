package validator;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import static org.junit.Assert.*;


public class StepsSetCategoryRelevanceNominally {
	private final static double EPSILON = 0.001;
	Category _category;
	
	@Given("that the user is given a category: $category")
	public void givenCategory(String category){
		_category = new Category(category);
	}
	@When("the user sets the relevance of category to $nominalRelevance")
	public void whenSetNominalRelevance(Relevance nominalRelevance){
		_category.relevance(nominalRelevance);
	}
	@Then("the relevance of category changes to $relevance")
	public void thenRevenceIsSetTo(double relevance){
		assertEquals(relevance, _category.relevance(), EPSILON);
	}
}

package validator;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import static org.junit.Assert.*;


public class StepsSetCategoryRelevanceNominally {
	private final static double EPSILON = 0.001;
	Category _category;
	
	@Given("that the user is given a cluster with the category: $category")
	public void givenCategory(String category){
		_category = new Category(category);
	}
	@When("the user sets the relevance of category to $nominalRelevance")
	public void whenSetNominalRelevance(String nominalRelevance){
		Relevance r = null;
		switch(nominalRelevance){
		case "NOT_RELEVANT":
			r = Relevance.NOT_RELEVANT;
			break;
		case "WEAK_RELEVANT":
			r = Relevance.WEAK_RELEVANT;
			break;
		case "RELEVANT":
			r = Relevance.RELEVANT;
			break;
		case "VERY_RELEVANT":
			r = Relevance.VERY_RELEVANT;
			break;
		case "THE_SAME":
			r = Relevance.THE_SAME;
			break;
		}
		_category.relevance(r);
	}
	@Then("the relevance of the category changes to $relevance")
	public void thenRevenceIsSetTo(double relevance){
		assertEquals(relevance, _category.relevance(), EPSILON);
	}
}

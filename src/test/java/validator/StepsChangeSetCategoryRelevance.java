package validator;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import org.mockito.Mockito;
import static org.junit.Assert.*;

public class StepsChangeSetCategoryRelevance {
	private final static double EPSILON = 0.001;
	Category _c1;
	Cluster _cluster;
	
	@Given("that the user is given a cluster of categories: $c1")
	public void givenCategories(String c1){
		_cluster = new Cluster();
		_c1 = new Category(c1);
		_cluster.add(_c1);
	}
	
	@When("the user sets the relevance of $category to $relevance")
	public void whenRelevanceSet(String category, double relevance){
		Cluster c = _cluster.get(category);
		c.relevance(relevance);
	}
	
	
	@Then("the relevance of $category changes to $setRelevance")
	public void thenRelevanceChanges(String category, double setRelevance){
		assertEquals(setRelevance, _cluster.get(category).relevance, EPSILON);
	}
}

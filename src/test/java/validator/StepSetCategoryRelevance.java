package validator;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import static org.junit.Assert.*;

public class StepSetCategoryRelevance {
	private final static double EPSILON = 0.001;
	Category _c1;
	Category _c2;
	Cluster _cluster;
	
	@Given("that the user is given a cluster of categories: $c1, $c2")
	public void givenCategories(String c1, String c2){
		_cluster = new Cluster();
		_c1 = new Category(c1);
		_c2 = new Category(c2);
		_cluster.add(_c1);
		_cluster.add(_c2);
	}
	
	@When("the user sets the relevance of $c1 to $c1relevance")
	public void whenRelevanceSet(String c1, double c1relevance){
		Category c = _cluster.get(c1);
		c.relevance(c1relevance);
	}
	
	
	@Then("the relevance of $c1 changes to $c1setRelevance")
	public void thenRelevanceChanges(String c1, double c1setRelevance){
		assertEquals(c1setRelevance, _cluster.get(c1).relevance(), EPSILON);
	}
	
	@Then("the relevance of $c2 remains as $defaultRelevance")
	public void thenRelevanceIsDefault(String c2, double defaultRelevance){
		assertEquals(defaultRelevance, _cluster.get(c2).relevance(), EPSILON);
	}
}

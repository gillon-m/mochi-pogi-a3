package validator.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import validator.TestPopularityCalculation;
import validator.TestSetCategoryRelevance;

@RunWith(Suite.class)				
@Suite.SuiteClasses({
	//add test cases here
	TestPopularityCalculation.class,
	TestSetCategoryRelevance.class,
})
public class TestSuite {

}
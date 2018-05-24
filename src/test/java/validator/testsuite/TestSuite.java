package validator.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import validator.TestMarketComprehension;
import validator.TestPopularityCalculation;
import validator.TestSetCategoryRelevance;

@RunWith(Suite.class)				
@Suite.SuiteClasses({
	//add test cases here
	TestPopularityCalculation.class,
	TestSetCategoryRelevance.class,
	TestMarketComprehension.class,
})
public class TestSuite {

}
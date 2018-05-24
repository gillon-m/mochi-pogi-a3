package validator.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import validator.TestChangeWeightInvalid;
import validator.TestChangeWeightValid;
import validator.TestInjectKeywordInvalid;
import validator.TestInjectKeywordValid;
import validator.TestInjectKeywordWhenFull;
import validator.TestPopularityCalculation;
import validator.TestSetCategoryRelevance;

@RunWith(Suite.class)				
@Suite.SuiteClasses({
	//add test cases here
	TestPopularityCalculation.class,
	TestSetCategoryRelevance.class,
	TestInjectKeywordInvalid.class,
	TestInjectKeywordValid.class,
	TestInjectKeywordWhenFull.class,
	TestChangeWeightInvalid.class,
	TestChangeWeightValid.class
})
public class TestSuite {

}
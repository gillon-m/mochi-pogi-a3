package validator.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import validator.ChangeWeightInvalidTest;
import validator.ChangeWeightValidTest;
import validator.InjectKeywordInvalidTest;
import validator.InjectKeywordValidTest;
import validator.InjectKeywordWhenFullTest;
import validator.PopularityCalculationTest;
import validator.RemoveKeywordInvalidTest;
import validator.RemoveKeywordValidTest;
import validator.RemoveKeywordWhenEmptyTest;
import validator.SetCategoryRelevanceTest;
import validator.SetCategoryRelevanceNominallyTest;

@RunWith(Suite.class)				
@Suite.SuiteClasses({
	//add test cases here
	PopularityCalculationTest.class,
	SetCategoryRelevanceTest.class,
	SetCategoryRelevanceNominallyTest.class,
	InjectKeywordInvalidTest.class,
	InjectKeywordValidTest.class,
	InjectKeywordWhenFullTest.class,
	RemoveKeywordValidTest.class,
	RemoveKeywordInvalidTest.class,
	RemoveKeywordWhenEmptyTest.class,
	ChangeWeightInvalidTest.class,
	ChangeWeightValidTest.class
})
public class TestSuite {

}
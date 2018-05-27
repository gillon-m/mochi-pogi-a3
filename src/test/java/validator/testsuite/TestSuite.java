package validator.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import validator.MarketComprehensionTest;
import validator.AuthenticationRoleTest;
import validator.BusinessIdeaMaturityTest;
import validator.ChangeKeywordWeightInvalidTest;
import validator.ChangeKeywordWeightValidTest;
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
	AuthenticationRoleTest.class,
	MarketComprehensionTest.class,
	BusinessIdeaMaturityTest.class,
	PopularityCalculationTest.class,
	SetCategoryRelevanceTest.class,
	SetCategoryRelevanceNominallyTest.class,
	BusinessIdeaMaturityTest.class,
	InjectKeywordInvalidTest.class,
	InjectKeywordValidTest.class,
	InjectKeywordWhenFullTest.class,
	RemoveKeywordValidTest.class,
	RemoveKeywordInvalidTest.class,
	RemoveKeywordWhenEmptyTest.class,
	ChangeKeywordWeightInvalidTest.class,
	ChangeKeywordWeightValidTest.class
})
public class TestSuite {

}
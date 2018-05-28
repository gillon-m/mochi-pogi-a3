package validator.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import validator.MarketComprehensionTest;
import validator.AdministratorPrevilegeActionTest;
import validator.AuthenticationTest;
import validator.UserPrevilegeActionTest;
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
	AdministratorPrevilegeActionTest.class,
	AuthenticationTest.class,
	BusinessIdeaMaturityTest.class,
	ChangeKeywordWeightInvalidTest.class,
	ChangeKeywordWeightValidTest.class,
	InjectKeywordInvalidTest.class,
	InjectKeywordValidTest.class,
	InjectKeywordWhenFullTest.class,
	MarketComprehensionTest.class,
	PopularityCalculationTest.class,
	RemoveKeywordValidTest.class,
	RemoveKeywordInvalidTest.class,
	RemoveKeywordWhenEmptyTest.class,
	SetCategoryRelevanceTest.class,
	SetCategoryRelevanceNominallyTest.class,
	UserPrevilegeActionTest.class
})
public class TestSuite {

}
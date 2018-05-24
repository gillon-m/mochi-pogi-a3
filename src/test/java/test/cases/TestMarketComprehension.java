package test.cases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import MarketComprehension.Document;
import validator.Category;
import validator.Category.CategoryType;

public class TestMarketComprehension {

	@Before
	public void setUp() {
		Document documentBuilder = new Document.DocumentBuilder()
				.setTitle("Dog walking in Roskill")
				.setCategories(new ArrayList<CategoryType>(Arrays.asList(Category.CategoryType.ANIMALS,
						Category.CategoryType.WALKING)))
				.setKeywords(new ArrayList<String>(Arrays.asList("Roskill", "Dog", "Walking")))
				.setSummary("An example of a summary for dog walking in Roskill")
				.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
				.buildDocument();
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

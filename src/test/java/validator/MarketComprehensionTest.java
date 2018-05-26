package validator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;

import validator.marketcomprehension.Document;
import validator.marketcomprehension.DocumentPersistence;
import validator.marketcomprehension.MarketComprehension;
import validator.marketcomprehension.MongoClient;
import validator.marketcomprehension.MongoDatabase;
import validator.marketcomprehension.SearchEngine;
import validator.marketcomprehension.Summary;
import validator.Category;

public class MarketComprehensionTest {
	@Mock SearchEngine se;
	@Mock MongoClient mongoClient;
	@Mock MongoDatabase mongoDatabase;
	@Mock Summary s;
	String documentRegistryDBName;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		documentRegistryDBName = "DocumentRegistry";
		mongoClient = Mockito.mock(MongoClient.class);
		mongoDatabase = new MongoDatabase();
		Mockito.doReturn(mongoDatabase).when(mongoClient).getDatabase(documentRegistryDBName);


	}
	private List<Document> getDocumentsOnKeywords(List<String> words) {
		List<String> keywords = new ArrayList<String>();
		for (String s : words) {
			keywords.add(new Word(s).getName());
		}

		MarketComprehension mc = new MarketComprehension();
		List<Document> searchResultDocuments = mc.getDocumentsFromKeywords(keywords, mongoClient, documentRegistryDBName);

		Mockito.when(se.getDocumentsFromKeyWords(keywords)).thenReturn(searchResultDocuments);
		List<Document> docs = se.getDocumentsFromKeyWords(keywords);
		return docs;
	}	


	@Test
	public void searchWithKeywordsShouldGiveDocuments() {
		List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Training")));
		List<Document> docs = getDocumentsOnKeywords(keywords);
		assert(!docs.isEmpty());
	}

	@SuppressWarnings({ "unchecked" })
	@Test
	public void searchTrainingWithTrainingCategoryShouldReturnThreeDocuments() {
		MarketComprehension mc = new MarketComprehension();
		List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Training")));
		List<Document> documents = getDocumentsOnKeywords(keywords);
		List<Category> categoryCluster = mc.getClustersFromDocuments(documents);

		for (Category category : categoryCluster) {
			if (category.toString().equals("Training")) {
				assertThat(category.getDocumentsOfThisCategory(), containsInAnyOrder(
						hasProperty("title", is("Dog training in west Auckland")),
						hasProperty("title", is("Barista training in west Auckland")),
						hasProperty("title", is("Parrot daycare and training in west Auckland"))));

			}
		}
	}

	@SuppressWarnings({ "deprecation" })
	@Test
	public void searchTrainingWithDogCategoryShouldReturnOneDocument() {
		MarketComprehension mc = new MarketComprehension();
		List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Training")));
		List<Document> documents = getDocumentsOnKeywords(keywords);
		List<Category> categoryCluster = mc.getClustersFromDocuments(documents);

		for (Category category : categoryCluster) {
			if (category.toString().equals("Dog")) {
				assertThat(category.getDocumentsOfThisCategory(), containsInAnyOrder(
						hasProperty("title", is("Dog training in west Auckland"))));

			}
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Test
	public void searchTrainingWithAnimalsCategoryShouldReturnTwoDocuments() {
		MarketComprehension mc = new MarketComprehension();
		List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Training")));
		List<Document> documents = getDocumentsOnKeywords(keywords);
		List<Category> categoryCluster = mc.getClustersFromDocuments(documents);

		for (Category category : categoryCluster) {
			if (category.toString().equals("Animals")) {
				assertThat(category.getDocumentsOfThisCategory(), containsInAnyOrder(
						hasProperty("title", is("Dog training in west Auckland")),
						hasProperty("title", is("Parrot daycare and training in west Auckland"))));

			}
		}
	}

	@SuppressWarnings({ "deprecation" })
	@Test
	public void searchTrainingWithParrotCategoryShouldReturnTwoDocuments() {
		MarketComprehension mc = new MarketComprehension();
		List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Training")));
		List<Document> documents = getDocumentsOnKeywords(keywords);
		List<Category> categoryCluster = mc.getClustersFromDocuments(documents);

		for (Category category : categoryCluster) {
			if (category.toString().equals("Parrot")) {
				assertThat(category.getDocumentsOfThisCategory(), containsInAnyOrder(
						hasProperty("title", is("Parrot daycare and training in west Auckland"))));

			}
		}
	}
	@SuppressWarnings({ "deprecation" })
	@Test
	public void searchTrainingWithDaycareCategoryShouldReturnOneDocument() {
		MarketComprehension mc = new MarketComprehension();
		List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Training")));
		List<Document> documents = getDocumentsOnKeywords(keywords);
		List<Category> categoryCluster = mc.getClustersFromDocuments(documents);

		for (Category category : categoryCluster) {
			if (category.toString().equals("Daycare")) {
				assertThat(category.getDocumentsOfThisCategory(), containsInAnyOrder(
						hasProperty("title", is("Parrot daycare and training in west Auckland"))));

			}
		}
	}

	@SuppressWarnings({ "deprecation" })
	@Test
	public void searchTrainingWithBaristaCategoryShouldReturnOneDocument() {
		MarketComprehension mc = new MarketComprehension();
		List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Training")));
		List<Document> documents = getDocumentsOnKeywords(keywords);
		List<Category> categoryCluster = mc.getClustersFromDocuments(documents);

		for (Category category : categoryCluster) {
			if (category.toString().equals("Barista")) {
				assertThat(category.getDocumentsOfThisCategory(), containsInAnyOrder(
						hasProperty("title", is("Barista training in west Auckland"))));

			}
		}		
	}

		@Test
		public void generateBaristaLabelShouldReturnTwoCategories() {
			MarketComprehension mc = new MarketComprehension();
			List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Training")));
			List<Document> documents = getDocumentsOnKeywords(keywords);
			List<Category> categoryCluster = mc.getClustersFromDocuments(documents);
			String generateLabelForCategory = "Barista";
			
			String expectedLabel = "Searching for keyword: Training contains"
					+ " 2 categories. The Barista category contains Barista documents for Barista, Training.";
			for (Category category : categoryCluster) {
				if (category.toString().equals(generateLabelForCategory)) {
					assertEquals(category.generateLabel(generateLabelForCategory, keywords, documents), expectedLabel);
				}
			}
		}
		
		@Test
		public void generateTrainingLabelShouldReturnSixCategories() {
			MarketComprehension mc = new MarketComprehension();
			List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Training")));
			List<Document> documents = getDocumentsOnKeywords(keywords);
			List<Category> categoryCluster = mc.getClustersFromDocuments(documents);
			String generateLabelForCategory = "Training";
			
			String expectedLabel = "Searching for keyword: Training contains"
					+ " 6 categories. The Training category contains Training documents for Animals, Barista, Daycare, Dogs, Parrots, Training.";
			for (Category category : categoryCluster) {
				if (category.toString().equals(generateLabelForCategory)) {
					assertEquals(category.generateLabel(generateLabelForCategory, keywords, documents), expectedLabel);
				}
			}
		}
		
		@Test
		public void generateWalkingLabelShouldReturnTwoCategories() {
			MarketComprehension mc = new MarketComprehension();
			List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Walking")));
			List<Document> documents = getDocumentsOnKeywords(keywords);
			List<Category> categoryCluster = mc.getClustersFromDocuments(documents);
			String generateLabelForCategory = "Walking";
			
			String expectedLabel = "Searching for keyword: Walking contains"
					+ " 2 categories. The Walking category contains Walking documents for Dogs, Walking.";
			for (Category category : categoryCluster) {
				if (category.toString().equals(generateLabelForCategory)) {
					assertEquals(category.generateLabel(generateLabelForCategory, keywords, documents), expectedLabel);
				}
			}
		}
		
		@Test
		public void trainingCategoryGeneratesASummary() {
			MarketComprehension mc = new MarketComprehension();
			List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Training")));
			List<Document> documents = getDocumentsOnKeywords(keywords);
			List<Category> categoryCluster = mc.getClustersFromDocuments(documents);
			String categoryToSummarize = "Training";
			
			Mockito.when(s.getSummary(categoryToSummarize, documents)).thenReturn("This category contains 3 documents about training. These documents, "
					+ "are about teaching a person or animals a particular skill or type of behaviour. "
					+ "This category has training documents containing the keywords Animals, Barista, Daycare, Dogs, Parrots, Training.");
					
					
			for (Category c : categoryCluster) {
				if (c.toString().equals(categoryToSummarize)) {
					assertEquals(s.getSummary(categoryToSummarize, documents), "This category contains 3 documents about training. These documents, "
							+ "are about teaching a person or animals a particular skill or type of behaviour. "
							+ "This category has training documents containing the keywords Animals, Barista, Daycare, Dogs, Parrots, Training.");
				}
			}
		}
}

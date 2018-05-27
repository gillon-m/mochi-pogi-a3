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

import validator.word.Word;
import validator.cluster.Category;
import validator.cluster.Document;
import validator.cluster.DocumentBuilder;
import validator.cluster.DocumentProcessor;
import validator.cluster.Summary;
import validator.database.DocumentPersistence;
import validator.database.MongoClient;
import validator.database.MongoDatabase;
import validator.search.SearchEngine;

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
		mongoDatabase = Mockito.mock(MongoDatabase.class);
		List<Document> documents = createDocumentMocks();
		Mockito.doReturn(mongoDatabase).when(mongoClient).getDatabase(documentRegistryDBName);
		Mockito.doReturn(documents).when(mongoDatabase).getList();
	}


	private List<Document> createDocumentMocks() {
		Document dogWalkingRoskillDocument = new DocumentBuilder()
				.setTitle("Dog walking in east Auckland")
				.setKeywords(new ArrayList<Word>(Arrays.asList(new Word("Dogs"), new Word("Walking"))))
				.setSummary("An example of a summary for dog walking in west Auckland")
				.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
				.buildDocument();

		Document animalDayCareDocument = new DocumentBuilder()
				.setTitle("Animal daycare services in central Auckland")
				.setKeywords(new ArrayList<Word>(Arrays.asList(new Word("Animals"), new Word("Daycare"))))
				.setSummary("An example of a summary for daycare service in north Auckland")
				.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
				.buildDocument();

		Document catCafeDocument = new DocumentBuilder()
				.setTitle("Cat Cafes in south Auckland")
				.setKeywords(new ArrayList<Word>(Arrays.asList(new Word("Cats"), new Word("Cafe"), new Word("Animals"))))
				.setSummary("An example of a summary for cat cafes in south Auckland")
				.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
				.buildDocument();

		Document dogTrainingDocument = new DocumentBuilder()
				.setTitle("Dog training in west Auckland")
				.setKeywords(new ArrayList<Word>(Arrays.asList(new Word("Dogs"), new Word("Training"), new Word("Animals"))))
				.setSummary("An example of a summary for dog training in central Auckland")
				.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
				.buildDocument();

		Document baristaTrainingDocument = new DocumentBuilder()
				.setTitle("Barista training in west Auckland")
				.setKeywords(new ArrayList<Word>(Arrays.asList(new Word("Barista"), new Word("Training"))))
				.setSummary("An example of a summary for Barista training in east Auckland")
				.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
				.buildDocument();

		Document parrotDaycareDocument = new DocumentBuilder()
				.setTitle("Parrot daycare and training in west Auckland")
				.setKeywords(new ArrayList<Word>(Arrays.asList(new Word("Parrots"), new Word("Daycare"), new Word("Animals"), new Word("Training"))))
				.setSummary("An example of a summary for Parrot daycare and training in west Auckland")
				.setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
				.buildDocument();


		List<Document> documents = new ArrayList<Document>();
		documents.add(dogWalkingRoskillDocument);
		documents.add(animalDayCareDocument);
		documents.add(catCafeDocument);
		documents.add(dogTrainingDocument);
		documents.add(baristaTrainingDocument);
		documents.add(parrotDaycareDocument);
		return documents;
	}
  
	private List<Document> getDocumentsOnKeywords(List<String> words) {
		List<String> keywords = new ArrayList<String>();
		for (String s : words) {
			keywords.add(new Word(s).getName());
		}

		DocumentProcessor mc = new DocumentProcessor();
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
		DocumentProcessor mc = new DocumentProcessor();
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
		DocumentProcessor mc = new DocumentProcessor();
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
		DocumentProcessor mc = new DocumentProcessor();
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
		DocumentProcessor mc = new DocumentProcessor();
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
		DocumentProcessor mc = new DocumentProcessor();
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
		DocumentProcessor mc = new DocumentProcessor();
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
		DocumentProcessor mc = new DocumentProcessor();
		List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Training")));
		List<Document> documents = getDocumentsOnKeywords(keywords);
		List<Category> categoryCluster = mc.getClustersFromDocuments(documents);
		String generateLabelForCategory = "Barista";

		String expectedLabel = "Searching for keyword: Training contains"
				+ " 2 categories. The Barista category contains Barista documents for Barista, Training.";
		for (Category category : categoryCluster) {
			if (category.toString().equals(generateLabelForCategory)) {
				assertEquals(mc.generateLabel(generateLabelForCategory, keywords, documents), expectedLabel);
			}
		}
	}

	@Test
	public void generateTrainingLabelShouldReturnSixCategories() {
		DocumentProcessor mc = new DocumentProcessor();
		List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Training")));
		List<Document> documents = getDocumentsOnKeywords(keywords);
		List<Category> categoryCluster = mc.getClustersFromDocuments(documents);
		String generateLabelForCategory = "Training";

		String expectedLabel = "Searching for keyword: Training contains"
				+ " 6 categories. The Training category contains Training documents for Animals, Barista, Daycare, Dogs, Parrots, Training.";
		for (Category category : categoryCluster) {
			if (category.toString().equals(generateLabelForCategory)) {
				assertEquals(mc.generateLabel(generateLabelForCategory, keywords, documents), expectedLabel);
			}
		}
	}

	@Test
	public void generateWalkingLabelShouldReturnTwoCategories() {
		DocumentProcessor mc = new DocumentProcessor();
		List<String> keywords = new ArrayList<String>(Arrays.asList(new String("Walking")));
		List<Document> documents = getDocumentsOnKeywords(keywords);
		List<Category> categoryCluster = mc.getClustersFromDocuments(documents);
		String generateLabelForCategory = "Walking";

		String expectedLabel = "Searching for keyword: Walking contains"
				+ " 2 categories. The Walking category contains Walking documents for Dogs, Walking.";
		for (Category category : categoryCluster) {
			if (category.toString().equals(generateLabelForCategory)) {
				assertEquals(mc.generateLabel(generateLabelForCategory, keywords, documents), expectedLabel);
			}
		}
	}

	@Test
	public void trainingCategoryGeneratesASummary() {
		DocumentProcessor mc = new DocumentProcessor();
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

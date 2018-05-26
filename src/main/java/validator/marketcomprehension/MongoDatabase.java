package validator.marketcomprehension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import authentication.Registry;
import validator.Word;
import validator.marketcomprehension.Document;
import validator.marketcomprehension.DocumentBuilder;

public class MongoDatabase { 

	private List<Document> documents;
	
	public MongoDatabase() {
		initializeDocumentRegistry();
	}

	public List<Document> getList() {
		initializeDocumentRegistry();
		return documents;
		
	}

	public void addDocument(Document document) {
		documents.add(document);
	}
	
	public void addAllDocuments(List<Document> documentz) {
		documents.addAll(documentz);
	}
	
	public void initializeDocumentRegistry() {
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
		
		documents = new ArrayList<Document>();
		documents.add(dogWalkingRoskillDocument);
		documents.add(animalDayCareDocument);
		documents.add(catCafeDocument);
		documents.add(dogTrainingDocument);
		documents.add(baristaTrainingDocument);
		documents.add(parrotDaycareDocument);
		
	}
}

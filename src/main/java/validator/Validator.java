package validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.mockito.Mockito;

import validator.authentication.Administrator;
import validator.authentication.Registry;
import validator.authentication.Role;
import validator.authentication.User;
import validator.cluster.Category;
import validator.cluster.Cluster;
import validator.cluster.Document;
import validator.cluster.DocumentBuilder;
import validator.cluster.DocumentProcessor;
import validator.database.MongoClient;
import validator.database.MongoDatabase;
import validator.word.InputProcessor;
import validator.word.KeywordsEditor;
import validator.word.SearchHandler;
import validator.word.Word;

public class Validator {
	public static void main(String[] args){
		new Validator();
	}
	
	public Validator(){
		MongoDatabase db = Mockito.mock(MongoDatabase.class);
		MongoClient client = Mockito.mock(MongoClient.class);
		InputProcessor ip = Mockito.mock(InputProcessor.class);
		initialiseMockMethods(db,client,ip);
		Registry registry = Registry.getInstance();
		registry.setDatabase(db);
		registry.setRoles();
		BufferedReader br = null;
		try{
			System.out.println("This is a demo shows how the system can be made runnable. Not all functionalities are used in this demo");
			System.out.println();
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("List of registered users you can use to sign in");
			for(Role r: db.getRoles()){
				System.out.println("USERNAME: "+ r.getUsername()+" | PASSWORD: "+r.getPassword());
			}
			System.out.println();
			System.out.println("LOGIN ");
			System.out.print("Username: ");
			String username = br.readLine();
			System.out.print("Password: ");
			String password = br.readLine();
			User user = (User) registry.signIn(username, password);
			System.out.println();
			System.out.println("Enter a search query: I want to start a business with cats and dogs");
			SearchHandler sh = new SearchHandler(ip);
			Set<Word> keyWords = sh.doSearch("k", user);
			System.out.println("Keywords from search query: ");
			for(Word word: keyWords){
				System.out.println("- "+word.getName());
			}
			KeywordsEditor keywordEditor = new KeywordsEditor(keyWords);
			System.out.println();
			System.out.print("Choose word: ");
			Word word = null;
			for(Word w: keyWords){
				if(w.getName().equals(br.readLine())){
					word = w;
					break;
				}
			}
			if(word ==null){
				throw new Exception("Word doesn't exist");
			}
			System.out.print("Set the weight of the keyword: ");
			keywordEditor.changeKeywordPriority(word, Integer.parseInt(br.readLine()));
			DocumentProcessor dp = new DocumentProcessor();
			List<String> stringWords = new LinkedList<String>();
			System.out.println();
			System.out.println("Documents returned: ");
			for(Word w: keyWords){
				stringWords.add(w.getName());
			}
			List<Document> documents = dp.getDocumentsFromKeywords(stringWords, client, "DocumentRegistry");
			for(Document d: documents){
				System.out.println("-------------------------------------------------");
				System.out.println("Title: "+ d.getTitle());
				System.out.print("Keywords: ");
				for(Word w: d.getKeywords()){
					System.out.print(w.getName()+" ");
				}
				System.out.println();
				System.out.println("Summary: ");
				System.out.println(d.getSummary());
				System.out.println("Content: ");
				System.out.println(d.getContent());
				System.out.println("-------------------------------------------------");
			}
			System.out.println();
			List<Category> categories = dp.getClustersFromDocuments(documents);
			System.out.println("Categories: ");
			for(Category c : categories){
				System.out.print("- "+c+": ");
				for(Document d: c.getDocumentsOfThisCategory()){
					System.out.println("\""+d.getTitle()+"\"");
				}
				System.out.println();
			}
			Set<Category> categorySet = new HashSet<Category>(categories);
			Cluster cluster = new Cluster(categorySet);
			System.out.println("Get maturity of business idea: ");
			System.out.println(cluster.maturity());
			System.out.println("==END OF DEMO==");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Initialize the mock methods for the mock objects used
	 * @param db
	 * @param client
	 * @param ip
	 */
	private void initialiseMockMethods(MongoDatabase db, MongoClient client, InputProcessor ip){
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
		Mockito.when(db.getList()).thenReturn(documents);
		Mockito.doReturn(db).when(client).getDatabase("DocumentRegistry");
		Mockito.doReturn(documents).when(db).getList();
		
		Registry registry = Registry.getInstance();
		registry.setDatabase(db);
		registry.setRoles();
		Mockito.when(db.getTotalSearchCount("u1")).thenReturn(1);
		Mockito.when(db.getTotalSearchCount("u2")).thenReturn(2);
		Mockito.when(db.getTotalSearchCount("u3")).thenReturn(3);
		Mockito.when(db.getTotalSearchCount("u4")).thenReturn(4);

		List<Role> roles = new ArrayList<Role>();
		roles.add(Mockito.spy(new User("u1", "p1")));
		roles.add(Mockito.spy(new User("u2", "p2")));
		roles.add(Mockito.spy(new User("u3", "p3")));
		roles.add(Mockito.spy(new User("u4", "p4")));
		roles.add(Mockito.spy(new Administrator("a1", "p1")));
		Mockito.when(db.getRoles()).thenReturn(roles);

		Word word1 = Mockito.spy(new Word("Cats", 10));
		Word word2 = Mockito.spy(new Word("Dogs", 9));
		Set<Word> words = new HashSet<Word>();
		words.add(word1);
		words.add(word2);
		
		Mockito.when(ip.extractKeywords("k")).thenReturn(words);	
	}
	
}

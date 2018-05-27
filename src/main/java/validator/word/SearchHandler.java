package validator.word;

import java.util.Set;

import validator.authentication.Registry;
import validator.authentication.User;

public class SearchHandler {
	private InputProcessor _ip;
	public SearchHandler(InputProcessor ip) {
		_ip = ip;
	}
	public Set<Word> doSearch(String input, User user) {
		if (user.isSignedIn()) {
			user.addSearchCount();
			return _ip.extractKeywords(input);
		}
		return null;
	}
	
	public int getTotalSearchCount(String username) {
		return Registry.getInstance().getTotalSearchCount(username);
	}

	public void setTotalSearchCount(String username, int updatedCount) {
		Registry.getInstance().setTotalSearchCount(username, updatedCount);
	}
}

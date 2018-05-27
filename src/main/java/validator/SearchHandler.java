package validator;

import java.util.Set;

import authentication.User;

public class SearchHandler {
	private InputProcessor _ip;
	
	public SearchHandler(InputProcessor ip) {
		_ip = ip;
	}
	public Set<Word> doSearch(String input, User user) {
		if (user._signStatus) {
			return _ip.extractKeywords(input);
		}
		return null;
	}
}

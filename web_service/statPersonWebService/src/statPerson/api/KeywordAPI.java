package statPerson.api;

import java.util.List;

import statPerson.element.account.Account;
import statPerson.element.keyword.Keyword;

public interface KeywordAPI {
	List<Keyword> getKeywords(Account administrator, Integer IDperson);
	void addKeyword(Account administrator, String keywordName);
	void removeKeyword(Account administrator, Integer IDkeyword);
}

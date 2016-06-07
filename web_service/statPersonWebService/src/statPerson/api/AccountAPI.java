package statPerson.api;

import statPerson.element.account.Account;

public interface AccountAPI {
	Integer addPrimaryAdministrator(String email, String password);
	Integer addSecondaryAdministrator(String email, String password, int idLinkedAdministrator);
	Integer addUser(String email, String password, int idLinkedAdministrator);
	void changePassword(Account account, String newPassword);
	Integer getAccount(String email, String password);
	void removeAccount(Integer idAccount);
}

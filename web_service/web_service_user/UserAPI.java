interface IRepository{

	Person[] getPersons(User user);
	Map<Site, int> getPersonRanks(Person person);
	
}

class User {
	int id;
}

class Person {
	int id;
	String name;
	
}

class Site {
	int id;
	String name;
}

class DBConnector {
	//синглтон класс для соединения с базой данных
  private static DBConnector instance;
  
  private DBConnector() {}

  public static DBConnector getInstance() {
    if (instance == null) {
      instance = new DBConnector();
    }
    return instance;
  }
}


class UserAPI implements IRepository{
	DBConnector = dbConnector;

	Person[] getPersons(User user) {
		DBConnector dbConnector = DBConnector.getInstance();
	}
}
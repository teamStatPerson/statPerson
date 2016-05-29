public class DBConnector {
    //синглтон класс для соединения с базой данных
    private static DBConnector instance;
    private static final String url = "";
    private static final String user = "";
    private static final String password = "";

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }
}



import java.sql.*;

public class DBConnector {
    //синглтон класс для соединения с базой данных
    private static DBConnector instance;

    private static final String url = "jdbc:mysql://localhost/statPerson";
    private static final String user = "root";
    private static final String password = "";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    public boolean MakeQuery(String query) throws SQLException {
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
            statement.close();
            resultSet.close();
        }

        return true;
    }
}



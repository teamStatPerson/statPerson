import java.sql.*;
import java.util.ArrayList;

/**
 * Created by alexey_n on 24.05.2016.
 */
public class DataSource {
    private final static String URL = "jdbc:mysql://localhost:3306/statperson?useSSL=false";
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";

    DataSource() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void executeUpdate(String insert) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement ps = con.createStatement()) {
            ps.executeUpdate(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getPersonNameById(int personId) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery("SELECT Name FROM persons WHERE id = " + personId)) {
            rs.next();
            return rs.getString("Name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getListKeywordsByPersonId(int personId) {
        ArrayList<String> keywords = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery("SELECT Name FROM keywords WHERE PersonID = " + personId)) {
            while (rs.next()) {
                String keyword = rs.getString("Name");
                keywords.add(keyword);
            }
            return keywords;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPageUrlById(int pageID) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement ps = con.createStatement();
             ResultSet rs = ps.executeQuery("SELECT Url FROM pages WHERE id = " + pageID)) {
            rs.next();
            return rs.getString("Url");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateLastScanDate(int pageID) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement ps = con.createStatement()) {
            ps.executeUpdate("UPDATE pages SET LastScanDate = now() WHERE ID =" +pageID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

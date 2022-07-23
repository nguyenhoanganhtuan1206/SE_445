import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    private static Connection connection;
    private static String jdbcURL = "jdbc:mariadb://localhost:3308/test_demo";
    private static String user = "root";
    private static String password = "anhtuan1206";

    static ReadFile readFile = new ReadFile();

    public static void main(String[] args) {
        try {
            openDatabaseConnection();

//            createData("Anh Tuan");

            /* Write from database to csv */
            readFile.writeFile(readData());
        } finally {
            closeDatabaseConnection();
        }
    }

    private static List<User> readData() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from user");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                users.add(new User(id,name));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    private static void createData(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into user(name) values (?)");
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void updateData(String name, int rating) {
    }

    private static void deleteData(String name) {
    }

    private static void openDatabaseConnection() {
        System.out.println("Connecting to the database");
        try {
            connection = DriverManager.
                    getConnection(jdbcURL,user,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeDatabaseConnection() {
        System.out.println("Closing database connection");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

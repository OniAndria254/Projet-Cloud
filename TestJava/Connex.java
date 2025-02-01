package TestJava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connex {
    private static final String URL = "jdbc:postgresql://localhost:5432/crypto";
    private static final String USER = "postgres";
    private static final String PASSWORD = "itu16";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

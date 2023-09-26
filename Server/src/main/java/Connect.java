import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static String DB_URL = "jdbc:mysql://localhost:3306/library";
    private static String USER_NAME = "root";
    private static String PASSWORD = "9003";

    public Connection getConnect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
            System.out.println(">> Connect successfully!");
        } catch (SQLException e) {
            System.out.println(">> Connect failure!");
            e.printStackTrace();
        }
        return connection;
    }
}

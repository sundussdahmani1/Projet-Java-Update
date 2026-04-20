import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String URL      = "jdbc:mysql://localhost:3306/paie_db";
    private static final String USER     = "root";
    private static final String PASSWORD = "";
    private static Connection instance = null;
    private DatabaseConnection() {}
    public static Connection getInstance() throws SQLException {
        if (instance == null || instance.isClosed())
            instance = DriverManager.getConnection(URL, USER, PASSWORD);
        return instance;
    }
}

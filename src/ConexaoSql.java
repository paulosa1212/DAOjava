import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSql {
    private static final String URL = "jdbc:postgrsql://localhost:5432/java";
    private static final String User = "postgres";
    private static final String PASSWORD = "1212";

    public static Connection getconnection() throws SQLException {
        return DriverManager.getConnection(URL, User, PASSWORD);
    }

}

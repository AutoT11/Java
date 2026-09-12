package DataBase;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseConnectionTest {

    @Test
    void DatabaseConnection() throws SQLException {

        Connection connection = DatabaseConnection.getConnection();

        assertTrue(connection.isValid(2));

        connection.close();

    }

}

package DataBase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDatabaseTest {

    @Test
    @DisplayName("Проверка создания записи в user")
    void createUser() throws Exception {

        int userId = 0;

        String deleteSql =
                """
                DELETE FROM users
                WHERE id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection()) {

           String insertSql =
                   """
                   INSERT INTO users (name, email)
                   VALUES (?, ?)
                   """;

           String selectSql =
                    """
                    SELECT id, name, email
                    FROM users
                    WHERE id = ?
                    """;

           try (PreparedStatement insert = connection.prepareStatement(
                   insertSql,
                   Statement.RETURN_GENERATED_KEYS
           )) {

               insert.setString(1, "Test User_777");
               insert.setString(2, "Test456@gmail.com");

               int rowsInserted = insert.executeUpdate();

               assertEquals(1, rowsInserted);

               try (ResultSet generatedKeys = insert.getGeneratedKeys()) {

                   assertTrue(generatedKeys.next());

                   userId = generatedKeys.getInt(1);

               }

               try (PreparedStatement select = connection.prepareStatement(selectSql)) {

                   select.setInt(1, userId);

                        try (ResultSet result = select.executeQuery()) {

                            assertTrue(result.next());

                            assertEquals(userId, result.getInt("id"));
                            assertEquals("Test User_777", result.getString("name"));
                            assertEquals("Test456@gmail.com", result.getString("email"));
                        }
                    }
                }
            } finally {

            if (userId > 0) {

                try (Connection connection = DatabaseConnection.getConnection()) {

                    try (PreparedStatement delete = connection.prepareStatement(deleteSql)) {

                        delete.setInt(1, userId);

                        delete.executeUpdate();

                    }
                }
            }
        }
    }


    @Test
    @DisplayName("Проверка обновления записи в user")
    void updateUser() throws SQLException {

        int userId = 0;
        String originalName = "Test user";
        String updatedName = "Updated Test User";
        String testEmail = "Test666@gmail.com";

        try (Connection connection = DatabaseConnection.getConnection()) {

            String insertSql =
                    """
                    INSERT INTO users (name, email)
                    VALUES (?, ?)
                    """;

            try (PreparedStatement insert = connection.prepareStatement(
                    insertSql,
                    Statement.RETURN_GENERATED_KEYS
            )) {

                insert.setString (1, originalName);
                insert.setString (2, testEmail);

                int rowsInserted = insert.executeUpdate();
                assertEquals(1, rowsInserted);

                try (ResultSet generatedKeys = insert.getGeneratedKeys()) {

                    assertTrue(generatedKeys.next());
                    userId = generatedKeys.getInt(1);

                }

            }

            String updateSql =
                    """
                    UPDATE users
                    SET name = ?
                    WHERE id = ?
                    """;

            try (PreparedStatement update = connection.prepareStatement(updateSql)) {

                update.setString (1, updatedName);
                update.setInt (2, userId);

                int rowsUpdated = update.executeUpdate();
                assertEquals(1, rowsUpdated);

            }

            String selectSql =
                    """
                    SELECT id, name, email
                    FROM users
                    WHERE id = ?
                    """;

            try (PreparedStatement select = connection.prepareStatement(selectSql)) {

                select.setInt(1, userId);

                try (ResultSet result = select.executeQuery()) {

                    assertTrue(result.next());

                    assertEquals(userId, result.getInt("id"));
                    assertEquals(updatedName, result.getString("name"));
                    assertEquals(testEmail, result.getString("email"));
                }
            }
        }

        finally {
            if (userId > 0) {

                String deleteSql =
                        """
                        DELETE FROM users
                        WHERE id = ?
                        """;

                try (
                        Connection cleanupConnection = DatabaseConnection.getConnection();
                        PreparedStatement delete = cleanupConnection.prepareStatement(deleteSql)
                ) {

                    delete.setInt(1, userId);
                    delete.executeUpdate();

                }

            }
        }
    }





} //Закрытие всего
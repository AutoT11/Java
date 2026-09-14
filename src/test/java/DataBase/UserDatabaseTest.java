package DataBase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDatabaseTest {

    @Test
    @DisplayName("Проверка создания записи в user")
    void createUser() throws Exception {

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

               insert.setString(1, "Test User_2");
               insert.setString(2, "Test456@gmail.com");

               int rowsInserted = insert.executeUpdate();

               assertEquals(1, rowsInserted);

               int userId;

               try (ResultSet generatedKeys = insert.getGeneratedKeys()) {

                   assertTrue(generatedKeys.next());

                   userId = generatedKeys.getInt(1);

               }

               try (PreparedStatement select = connection.prepareStatement(selectSql)) {

                   select.setInt(1, userId);

                        try (ResultSet result = select.executeQuery()) {

                            assertTrue(result.next());
                        }

               }

        }




    }
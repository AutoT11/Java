package DataBase.Pozitive;

import API.untils.TestNumberGenerator;
import DataBase.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse; // Нужно для проверки удаления!
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDatabaseTestNEW {

    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {

        connection = DatabaseConnection.getConnection();
        connection.setAutoCommit(false);

    }

    @AfterEach
    void tearDown() throws SQLException {

        if (connection != null && !connection.isClosed()) {
            connection.rollback();
            connection.close();
        }
    }

    @Test
    @DisplayName("Проверка удаления записи в user-NEW")
    void deleteUserNew () throws SQLException {

        //Переменные для тестового юзера.
        int userId = 0;
        String testName = TestNumberGenerator.uniqueUserName();
        String testEmail = TestNumberGenerator.uniqueUserEmail();

        String insertSql =
                """
                INSERT INTO users (name, email)
                VALUES  (?, ?)
                """;

        try (PreparedStatement insert = connection.prepareStatement(
                insertSql,
                Statement.RETURN_GENERATED_KEYS
        )) {
            insert.setString (1, testName);
            insert.setString (2, testEmail);

            // Выполняем вставку и проверяем, что добавилась 1 строка.
            int rowsInserted = insert.executeUpdate();
            assertEquals(1, rowsInserted);

            // Вытаскиваем сгенерированный базой данных ID.
            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {

                assertTrue(generatedKeys.next());
                userId = generatedKeys.getInt(1);
            }

        }

        String deleteSql =
                """
                DELETE FROM users
                WHERE id = ?
                """;

        //Выполнение удаления.
        try (PreparedStatement delete = connection.prepareStatement(deleteSql)) {

            delete.setInt(1, userId);
            int rowsDeleted = delete.executeUpdate();
            assertEquals(1, rowsDeleted);
        }

        String selectSql =
                """
                SELECT id 
                FROM users
                WHERE id = ?
                """;

        //Запрос в базу данных и получение результата.
        try (PreparedStatement select = connection.prepareStatement(selectSql)) {

            select.setInt(1, userId);
            try (ResultSet result = select.executeQuery()) {

                assertFalse(result.next());

            }

        }




    }







}//ВСЕ

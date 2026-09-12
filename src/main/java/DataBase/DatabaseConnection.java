package DataBase;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {

        Dotenv dotenv = Dotenv.load();

        String jdbcUrl = "jdbc:postgresql://"
                + dotenv.get("DB_HOST")
                + ":"
                + dotenv.get("DB_PORT")
                + "/"
                + dotenv.get("DB_NAME")
                + "?sslmode=require&channel_binding=require";

        return DriverManager.getConnection(
                jdbcUrl,
                dotenv.get("DB_USER"),
                dotenv.get("DB_PASSWORD")
        );

    }

}

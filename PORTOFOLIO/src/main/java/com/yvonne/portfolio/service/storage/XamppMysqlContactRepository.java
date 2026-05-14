package com.yvonne.portfolio.service.storage;

import com.yvonne.portfolio.model.ContactMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;

@Repository
@Profile("xampp")
public class XamppMysqlContactRepository implements ContactRepository {

    private final String serverUrl;
    private final String databaseName;
    private final String username;
    private final String password;

    public XamppMysqlContactRepository(
            @Value("${portfolio.mysql.server-url}") String serverUrl,
            @Value("${portfolio.mysql.database}") String databaseName,
            @Value("${portfolio.mysql.username}") String username,
            @Value("${portfolio.mysql.password}") String password
    ) {
        this.serverUrl = serverUrl;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        initializeDatabase();
    }

    @Override
    public ContactMessage save(ContactMessage message) {
        Instant submittedAt = Instant.now();
        message.setSubmittedAt(submittedAt);

        String sql = """
                INSERT INTO contact_messages (first_name, last_name, email, message, submitted_at)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = connectToDatabase();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, message.getFirstName());
            statement.setString(2, message.getLastName());
            statement.setString(3, message.getEmail());
            statement.setString(4, message.getMessage());
            statement.setTimestamp(5, Timestamp.from(submittedAt));
            statement.executeUpdate();
            return message;
        } catch (SQLException exception) {
            throw new IllegalStateException("Could not save the contact message to XAMPP MySQL.", exception);
        }
    }

    @Override
    public int count() {
        try (Connection connection = connectToDatabase();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM contact_messages")) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException exception) {
            throw new IllegalStateException("Could not count contact messages in XAMPP MySQL.", exception);
        }
    }

    private void initializeDatabase() {
        try (Connection serverConnection = DriverManager.getConnection(serverUrl, username, password);
             Statement serverStatement = serverConnection.createStatement()) {
            serverStatement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
        } catch (SQLException exception) {
            throw new IllegalStateException("Could not create or connect to the XAMPP MySQL database.", exception);
        }

        try (Connection databaseConnection = connectToDatabase();
             Statement databaseStatement = databaseConnection.createStatement()) {
            databaseStatement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS contact_messages (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        first_name VARCHAR(60) NOT NULL,
                        last_name VARCHAR(60) NOT NULL,
                        email VARCHAR(120) NOT NULL,
                        message TEXT NOT NULL,
                        submitted_at TIMESTAMP NOT NULL
                    )
                    """);
        } catch (SQLException exception) {
            throw new IllegalStateException("Could not create the contact_messages table.", exception);
        }
    }

    private Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection(serverUrl + databaseName, username, password);
    }
}
